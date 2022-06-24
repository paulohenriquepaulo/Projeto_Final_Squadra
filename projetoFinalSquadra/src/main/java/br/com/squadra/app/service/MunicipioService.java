package br.com.squadra.app.service;

import br.com.squadra.app.exception.SquadraException;
import br.com.squadra.app.mapper.MunicipioMapper;
import br.com.squadra.app.model.Municipio;
import br.com.squadra.app.model.Uf;
import br.com.squadra.app.repository.MunicipioRepository;
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
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioMapper municipioMapper;

    @Autowired
    private UfService ufService;

    public List<Municipio> cadastrarMunicipio(Municipio municipio) {
        try {
            Uf uf = ufService.bucarUf(municipio.getUf().getCodigoUF());
            municipio.setUf(uf);
            municipio.setNome(municipio.getNome().toUpperCase());
            municipioRepository.save(municipio);
            return listarTodosMunicipios();
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new SquadraException("Um estado não pode ter 2 municipios com o mesmo nome!",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Municipio bucarPorCodigo(Long codigo) {
        return municipioRepository.findById(codigo).
                orElseThrow(() -> new SquadraException("Municipio não encotrado!", HttpStatus.NOT_FOUND));
    }

    public Municipio bucarPorNomeESigla(String nomeMunicipio, String siglaUf) {
        return municipioRepository.findByNomeAndUf(nomeMunicipio.toUpperCase(), siglaUf.toUpperCase()).
                orElseThrow(() -> new SquadraException("Municipio não encotrado!", HttpStatus.NOT_FOUND));
    }

    public List<Municipio> listarTodosMunicipios() {
        return municipioRepository.findAll();
    }

    public List<Municipio> listarTodosMunicipiosPorUf(Integer codigoUf) {
        Uf uf = ufService.bucarUf(codigoUf);
        return municipioRepository.findByUf(uf);
    }

    public List<Municipio> deletarPorCodigo(Long codigo) {
        Municipio municipio = bucarPorCodigo(codigo);
        try {
            municipioRepository.delete(municipio);
            return listarTodosMunicipios();
        } catch (DataIntegrityViolationException exception) {
            throw new SquadraException("Existem Bairros associados a este municipio!",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<Municipio> atualizarMunicipio(Municipio municipio) {
        Municipio municipioAtualizado = bucarPorCodigo(municipio.getCodigoMunicipio());
        municipioAtualizado.setNome(municipio.getNome().toUpperCase());
        municipioAtualizado.setUf(ufService.bucarUf(municipio.getUf().getCodigoUF()));
        municipioAtualizado.setStatus(municipio.getStatus());
        municipioRepository.save(municipioAtualizado);
        return listarTodosMunicipios();
    }

    public Object buscarMunicipio(Long codigoMunicipio, Integer codigoUF, String nome, Integer status) {

        if (status != null && status != 1 && status != 2) {
            throw new SquadraException("Status invalido: 1 - ATIVADO, 2 - DESATIVADO", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<Municipio> municipios = buscar(codigoMunicipio, codigoUF, nome, status);
        if (codigoMunicipio != null) {
            return !municipios.isEmpty() ? municipios.get(0) : municipios;
        } else {
            return municipios;
        }

    }

    private List<Municipio> buscar(Long codigoMunicipio, Integer codigoUF, String nome, Integer status) {
        return municipioRepository.findAll(new Specification<Municipio>() {
            @Override
            public Predicate toPredicate(Root<Municipio> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (codigoMunicipio != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("codigoMunicipio"), codigoMunicipio)));
                }
                if (codigoUF != null) {
                    Uf uf = new Uf();
                    uf.setCodigoUF(codigoUF);
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("uf"), uf)));
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
