package br.com.squadra.app.model;

public enum Status {

    ATIVADO(1), DESATIVADO(2);

    private Integer codigo;

    private Status(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

}
