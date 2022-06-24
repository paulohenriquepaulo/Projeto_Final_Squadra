package br.com.squadra.app.repository;

import br.com.squadra.app.model.Endereco;
import br.com.squadra.app.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> getByPessoa(Pessoa pessoa);

}
