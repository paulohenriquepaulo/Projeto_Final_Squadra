package br.com.squadra.app.vo;

public class PessoaVO {

    private String login;

    private String senha;

    public PessoaVO() {
        super();
    }

    public PessoaVO(String login, String senha) {
        this();
        this.login = login;
        this.senha = senha;
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


}
