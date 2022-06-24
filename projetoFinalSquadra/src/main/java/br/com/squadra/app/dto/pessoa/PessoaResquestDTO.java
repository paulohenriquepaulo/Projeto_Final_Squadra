package br.com.squadra.app.dto.pessoa;

import br.com.squadra.app.dto.BaseDTO;
import br.com.squadra.app.dto.endereco.EnderecoResquestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PessoaResquestDTO extends BaseDTO {
    @Size(min = 3, max = 256, message = "O nome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    private String nome;

    @Size(min = 3, max = 256, message = "O sobrenome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O sobreno não pode ser nulo ou vazio!")
    private String sobrenome;

    @Size(min = 6, max = 50, message = "O login deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "O login não pode ser nulo ou vazio!")
    private String login;

    @Size(min = 6, max = 50, message = "A senha deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "A senha não pode ser nulo ou vazio!")
    private String senha;


    @NotNull(message = "A idade não pode ser nulo ou vazio!")
    private Integer idade;

    @Valid
    private List<EnderecoResquestDTO> enderecos;

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

    public List<EnderecoResquestDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoResquestDTO> enderecos) {
        this.enderecos = enderecos;
    }

}
