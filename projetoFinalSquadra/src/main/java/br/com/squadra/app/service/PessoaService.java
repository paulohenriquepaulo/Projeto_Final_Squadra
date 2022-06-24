package br.com.squadra.app.service;

import br.com.squadra.app.exception.SquadraException;
import br.com.squadra.app.model.Endereco;
import br.com.squadra.app.model.Pessoa;
import br.com.squadra.app.repository.EnderecoRepository;
import br.com.squadra.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BairroService bairroService;

    public List<Pessoa> cadastrarPessoa(Pessoa pessoa) {
        pessoa.getEnderecos().stream().forEach(endereco -> {
            endereco.setBairro(bairroService.bucarPorCodigo(endereco.getBairro().getCodigoBairro()));
            endereco.setPessoa(pessoa);
        });
        pessoaRepository.save(pessoa);
        List<Pessoa> pessoas = listarPessoas();
        pessoas.forEach(p -> p.setEnderecos(new ArrayList<>()));
        return pessoas;
    }

    public List<Pessoa> atulaziarPessoa(Pessoa pessoa) {
        validarPessoa(pessoa.getCodigoPessoa());
        List<Endereco> enderecosAtuais = enderecoRepository.getByPessoa(pessoa);
        List<Endereco> enderecosEditados = new ArrayList<>();
        List<Endereco> enderecosNovos = new ArrayList<>();
        for (Endereco endereco : pessoa.getEnderecos()) {
            validarPessoaEndereco(pessoa, endereco);
            if (endereco.getCodigoEndereco() == null) {
                enderecosNovos.add(endereco);
            } else {
                if (enderecosAtuais.contains(endereco)) {
                    enderecosEditados.add(endereco);
                } else {
                    throw new SquadraException("O endereço de codigo " + endereco.getCodigoEndereco() + " não existe!", HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
        }
        List<Endereco> enderecosRemovidos = enderecosAtuais.stream().filter(e -> !pessoa.getEnderecos().contains(e)).collect(Collectors.toList());
        enderecoRepository.deleteAll(enderecosRemovidos);
        Pessoa pessoaAtualizada = bucarPorCodigo(pessoa.getCodigoPessoa());
        atulaizarPessoaNoEndereco(enderecosNovos, pessoaAtualizada);
        atualizarEnderecos(enderecosAtuais, enderecosEditados);
        atulaziarDadosPessoa(pessoa, pessoaAtualizada);
        pessoaRepository.save(pessoaAtualizada);
        List<Pessoa> pessoas = listarPessoas();
        pessoas.forEach(p -> p.setEnderecos(new ArrayList<>()));
        return pessoas;

    }

    private void atualizarEnderecos(List<Endereco> enderecosAtuais, List<Endereco> enderecosEditados) {
        enderecosEditados.forEach(enderecoEditado -> {
            int index = enderecosAtuais.indexOf(enderecoEditado);
            atualizarDadosEndereco(enderecoEditado, enderecosAtuais.get(index));
        });
    }

    private void atulaizarPessoaNoEndereco(List<Endereco> enderecosNovos, Pessoa pessoaAtualizada) {
        enderecosNovos.forEach(e -> {
            e.setPessoa(pessoaAtualizada);
            pessoaAtualizada.getEnderecos().add(e);
        });
    }

    private void validarPessoaEndereco(Pessoa pessoa, Endereco endereco) {
        if (!endereco.getPessoa().getCodigoPessoa().equals(pessoa.getCodigoPessoa())) {
            throw new SquadraException("A pessoa no endereço " + endereco.getCodigoEndereco()
                    + " é diferente da pesssoa" + pessoa.getCodigoPessoa(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    private void validarPessoa(Long codigPessoa) {
        if (pessoaRepository.countByCodigoPessoa(codigPessoa) == 0) {
            throw new SquadraException("Pessoa não encotrada!", HttpStatus.NOT_FOUND);
        }
    }

    private void atulaziarDadosPessoa(Pessoa pessoaEditada, Pessoa pessoaAtual) {
        pessoaAtual.setNome(pessoaEditada.getNome());
        pessoaAtual.setSobrenome(pessoaEditada.getSobrenome());
        pessoaAtual.setIdade(pessoaEditada.getIdade());
        pessoaAtual.setSenha(pessoaEditada.getSenha());
        pessoaAtual.setLogin(pessoaEditada.getLogin());
    }

    private void atualizarDadosEndereco(Endereco enderecoEditado, Endereco enderecoAtual) {
        enderecoAtual.setBairro(bairroService.bucarPorCodigo(enderecoEditado.getBairro().getCodigoBairro()));
        enderecoAtual.setComplemento(enderecoEditado.getComplemento());
        enderecoAtual.setNumero(enderecoEditado.getNumero());
        enderecoAtual.setCep(enderecoEditado.getCep());
        enderecoAtual.setNomeRua(enderecoEditado.getNomeRua());
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa bucarPorCodigo(Long codigoPessoa) {
        return pessoaRepository.findById(codigoPessoa).
                orElseThrow(() -> new SquadraException("Pessoa não encotrada!", HttpStatus.NOT_FOUND));
    }

    public Object buscarPessoa(Long codigoPessoa, String login, Integer status) {

        if (status != null && status != 1 && status != 2) {
            throw new SquadraException("Status invalido: 1 - ATIVADO, 2 - DESATIVADO", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<Pessoa> pessoas = buscar(codigoPessoa, login, status);
        if (codigoPessoa != null) {
            return !pessoas.isEmpty() ? pessoas.get(0) : pessoas;
        } else {
            pessoas.forEach(p -> p.setEnderecos(new ArrayList<>()));
            return pessoas;
        }

    }

    private List<Pessoa> buscar(Long codigoPessoa, String login, Integer status) {
        return pessoaRepository.findAll(new Specification<Pessoa>() {
            @Override
            public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (codigoPessoa != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("codigoPessoa"), codigoPessoa)));
                }
                if (login != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("login"), login)));
                }
                if (status != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), status)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

}
