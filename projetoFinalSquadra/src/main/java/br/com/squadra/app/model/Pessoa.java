package br.com.squadra.app.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa extends BaseEntity {

    @Id
    @Column(name = "CODIGO_PESSOA")
    @SequenceGenerator(name = "sequencePessoa", sequenceName = "SEQUENCE_PESSOA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencePessoa")
    private Long codigoPessoa;

    @Size(min = 3, max = 256, message = "O nome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    @Column(name = "NOME")
    private String nome;

    @Size(min = 3, max = 256, message = "O sobrenome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O sobreno não pode ser nulo ou vazio!")
    private String sobrenome;

    @Size(min = 6, max = 50, message = "O login deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "O login não pode ser nulo ou vazio!")
    @Column(name = "LOGIN")
    private String login;

    @Size(min = 6, max = 50, message = "A senha deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "A senha não pode ser nulo ou vazio!")
    @Column(name = "SENHA")
    private String senha;

    @Column(name = "IDADE")
    @NotNull(message = "A idade não pode ser nulo ou vazio!")
    private Integer idade;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
