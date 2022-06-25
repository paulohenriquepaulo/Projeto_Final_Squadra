package br.com.squadra.app.dto.pessoa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PessoaResquestAtualizarSenhaDTO {

    @NotBlank(message = "O campo login não pode ser nulo ou vazio!")
    private String login;

    @NotBlank(message = "O campo senha não pode ser nulo ou vazio!")
    private String senhaAtual;

    @Size(min = 6, max = 50, message = "A senha nova deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "A senha não pode ser nulo ou vazio!")
    private String senhaNova;

    @Size(min = 6, max = 50, message = "A senha nova deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "A senha não pode ser nulo ou vazio!")
    private String repetirSenha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }
}
