package br.com.squadra.app.repository;

import br.com.squadra.app.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer>, JpaSpecificationExecutor<Uf> {

    Long countByNome(String nome);

    Long countBySigla(String sigla);

    Optional<Uf> findBySigla(String sigla);

}
