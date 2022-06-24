package br.com.squadra.app.repository;

import br.com.squadra.app.model.Pessoa;
import br.com.squadra.app.vo.PessoaVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

    Boolean existsByLogin(String login);

    @Query(value = "select new br.com.squadra.app.vo.PessoaVO(p.login, p.senha) from Pessoa p where p.codigoPessoa = :codigoPessoa")
    PessoaVO recuperarDadosLogin(Long codigoPessoa);

}

