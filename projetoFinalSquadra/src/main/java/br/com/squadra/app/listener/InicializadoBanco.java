package br.com.squadra.app.listener;

import br.com.squadra.app.model.*;
import br.com.squadra.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InicializadoBanco {

    @Value(value = "${squadra.database.deveAtualizar:true}")
    private boolean deveAtualizar;

    @Autowired
    private EnderecoRepository repository;

    @EventListener
    @Transactional
    public void criarDadosBanco(ContextRefreshedEvent event) {
        if (this.deveAtualizar) {
            Pessoa pessoa = getPessoa();
            Uf uf = getUf();
            Municipio municipio = getMunicipio(uf);
            Bairro bairro = getBairro(municipio);
            Endereco endereco = getEndereco(pessoa, bairro);
            repository.save(endereco);
            this.deveAtualizar = Boolean.FALSE;
        }
    }

    private Endereco getEndereco(Pessoa pessoa, Bairro bairro) {
        Endereco endereco = new Endereco();
        endereco.setBairro(bairro);
        endereco.setCep("30411-580");
        endereco.setNumero("202");
        endereco.setComplemento("ap 102");
        endereco.setNomeRua("Rua da Paz");
        endereco.setPessoa(pessoa);
        return endereco;
    }

    private Bairro getBairro(Municipio municipio) {
        Bairro bairro = new Bairro();
        bairro.setNome("CALAFATE");
        bairro.setMunicipio(municipio);
        bairro.setStatus(Status.ATIVADO);
        return bairro;
    }

    private Municipio getMunicipio(Uf uf) {
        Municipio municipio = new Municipio();
        municipio.setNome("BELO HORIZONTE");
        municipio.setUf(uf);
        municipio.setStatus(Status.ATIVADO);
        return municipio;
    }

    private Uf getUf() {
        Uf uf = new Uf();
        uf.setNome("MINAS GERAIS");
        uf.setSigla("MG");
        uf.setStatus(Status.ATIVADO);
        return uf;
    }

    private Pessoa getPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo");
        pessoa.setSobrenome("Oliveira");
        pessoa.setLogin("paulo@gmail.com");
        pessoa.setSenha("batata");
        pessoa.setIdade(24);
        pessoa.setStatus(Status.ATIVADO);
        return pessoa;
    }

}
