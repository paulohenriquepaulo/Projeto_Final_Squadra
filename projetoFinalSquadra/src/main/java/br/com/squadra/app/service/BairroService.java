package br.com.squadra.app.service;

import br.com.squadra.app.exception.SquadraException;
import br.com.squadra.app.mapper.BairroMapper;
import br.com.squadra.app.model.Bairro;
import br.com.squadra.app.model.Municipio;
import br.com.squadra.app.repository.BairroRepository;
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
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private BairroMapper bairroMapper;

    @Autowired
    private MunicipioService municipioService;


    public List<Bairro> cadastrarBairro(Bairro bairro) {
        try {
            Municipio municipio = municipioService.bucarPorCodigo(bairro.getMunicipio().getCodigoMunicipio());
            bairro.setNome(bairro.getNome().toUpperCase());
            bairro.setMunicipio(municipio);
            bairroRepository.save(bairro);
            return listarTodosBairros();
        } catch (DataIntegrityViolationException exception) {
            throw new SquadraException("Um municipio não pode ter 2 bairro com o mesmo nome!",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Bairro bucarPorCodigo(Long codigo) {
        return bairroRepository.findById(codigo).
                orElseThrow(() -> new SquadraException("Bairro [" + codigo + "] não encotrado!", HttpStatus.NOT_FOUND));
    }

    public List<Bairro> listarTodosBairros() {
        return bairroRepository.findAll();
    }


    public List<Bairro> deletarPorCodigo(Long codigo) {
        Bairro bairro = bucarPorCodigo(codigo);
        try {
            bairroRepository.delete(bairro);
            return listarTodosBairros();
        } catch (DataIntegrityViolationException exception) {
            throw new SquadraException("Existem endereços associados a este bairro!",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<Bairro> atulaziarBairro(Bairro bairro) {
        Bairro bairroAtualizado = bucarPorCodigo(bairro.getCodigoBairro());
        bairroAtualizado.setNome(bairro.getNome().toUpperCase());
        bairroAtualizado.setMunicipio(municipioService.bucarPorCodigo(bairro.getMunicipio().getCodigoMunicipio()));
        bairroAtualizado.setStatus(bairro.getStatus());
        bairroRepository.save(bairroAtualizado);
        return listarTodosBairros();
    }

    public Object buscarBairro(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {

        if (status != null && status != 1 && status != 2) {
            throw new SquadraException("Status invalido: 1 - ATIVADO, 2 - DESATIVADO", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<Bairro> bairros = buscar(codigoBairro, codigoMunicipio, nome, status);
        if (codigoBairro != null) {
            return !bairros.isEmpty() ? bairros.get(0) : bairros;
        } else {
            return bairros;
        }

    }

    private List<Bairro> buscar(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {

        return bairroRepository.findAll(new Specification<Bairro>() {
            @Override
            public Predicate toPredicate(Root<Bairro> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (codigoBairro != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("codigoBairro"), codigoBairro)));
                }
                if (codigoMunicipio != null) {
                    Municipio municipio = new Municipio();
                    municipio.setCodigoMunicipio(codigoMunicipio);
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("municipio"), municipio)));
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

}
