package br.com.squadra.app.repository;

import br.com.squadra.app.model.Municipio;
import br.com.squadra.app.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface MunicipioRepository extends JpaRepository<Municipio, Long>, JpaSpecificationExecutor<Municipio> {

    List<Municipio> findByUf(Uf uf);

    @Query(value = "SELECT m FROM Municipio m WHERE m.nome = :nome AND m.uf.sigla = :siglaUf")
    Optional<Municipio> findByNomeAndUf(String nome, String siglaUf);

}
