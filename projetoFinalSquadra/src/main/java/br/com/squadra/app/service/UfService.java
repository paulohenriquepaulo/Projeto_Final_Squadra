package br.com.squadra.app.service;

import br.com.squadra.app.exception.SquadraException;
import br.com.squadra.app.mapper.UfMapper;
import br.com.squadra.app.model.Uf;
import br.com.squadra.app.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UfService {

    @Autowired
    private UfRepository ufRepository;

    @Autowired
    private UfMapper ufMapper;

    public List<Uf> cadastrarUf(Uf uf) {
        uf.setSigla(uf.getSigla().toUpperCase());
        uf.setNome(uf.getNome().toUpperCase());
        validarUf(uf);
        ufRepository.save(uf);
        return listarTodos();
    }

    public Uf bucarUf(Integer codigoUf) {
        return ufRepository.findById(codigoUf).
                orElseThrow(() -> new SquadraException("UF não encotrada!", HttpStatus.NOT_FOUND));
    }

    public Object buscarUf(Integer codigoUF, String sigla, String nome, Integer status) {
        if (status != null && status != 1 && status != 2) {
            throw new SquadraException("Status invalido: 1 - ATIVADO, 2 - DESATIVADO", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<Uf> ufs = buscar(codigoUF, sigla, nome, status);
        if (status != null && sigla == null && nome == null && codigoUF == null ||
                status == null && sigla == null && nome == null && codigoUF == null) {
            return ufs;
        } else {
            return !ufs.isEmpty() ? ufs.get(0) : ufs;
        }
    }

    private List<Uf> buscar(Integer codigoUF, String sigla, String nome, Integer status) {
        return ufRepository.findAll(new Specification<Uf>() {
            @Override
            public Predicate toPredicate(Root<Uf> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (codigoUF != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("codigoUF"), codigoUF)));
                }
                if (sigla != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("sigla"), sigla.toUpperCase())));
                }
                if (nome != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("nome"), nome.toUpperCase())));
                }
                if (status != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), status)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public Uf buscarPorSigla(String sigla) {
        return ufRepository.findBySigla(sigla.toUpperCase()).
                orElseThrow(() -> new SquadraException("UF não encotrada!", HttpStatus.NOT_FOUND));
    }

    public List<Uf> listarTodos() {
        return ufRepository.findAll();
    }

    public List<Uf> deletarPorSigla(String sigla) {
        Uf uf = buscarPorSigla(sigla);
        try {
            ufRepository.delete(uf);
            return listarTodos();
        } catch (DataIntegrityViolationException exception) {
            throw new SquadraException("Existem municipios associados a esta UF!",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void validarUf(Uf uf) {
        Boolean valido = Boolean.TRUE;
        SquadraException squadraException = new SquadraException();
        Long quantidade = ufRepository.countByNome(uf.getNome());
        StringBuilder mensagem = new StringBuilder();
        if (quantidade >= 1) {
            valido = Boolean.FALSE;
            mensagem.append("Já existe um estado com o nome " + uf.getNome().toUpperCase()
                    + " Você não pode cadastrar dois estados com o mesmo nome.");
        }
        quantidade = ufRepository.countBySigla(uf.getSigla());
        if (quantidade >= 1) {
            mensagem.append(!valido ? " " : "");
            mensagem.append("Já existe um estado com a sigla " + uf.getSigla().toUpperCase() +
                    ". Você não pode cadastrar dois estados com o mesma sigla.");
            valido = Boolean.FALSE;
        }

        if (!valido) {
            squadraException.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            squadraException.add("Status", "422");
            squadraException.add("mesagem", mensagem.toString());
            throw squadraException;
        }
    }

    public List<Uf> atulaziarUf(Uf uf) {
        Uf ufAtualizada = bucarUf(uf.getCodigoUF());
        ufAtualizada.setNome(uf.getNome().toUpperCase());
        ufAtualizada.setSigla(uf.getSigla());
        uf.setStatus(uf.getStatus());
        ufRepository.save(uf);
        return listarTodos();
    }

}
