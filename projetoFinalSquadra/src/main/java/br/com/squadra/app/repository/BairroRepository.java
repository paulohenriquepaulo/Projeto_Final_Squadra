package br.com.squadra.app.repository;

import br.com.squadra.app.model.Bairro;
import br.com.squadra.app.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long>, JpaSpecificationExecutor<Bairro> {
    List<Bairro> findByMunicipio(Municipio municipio);

}
