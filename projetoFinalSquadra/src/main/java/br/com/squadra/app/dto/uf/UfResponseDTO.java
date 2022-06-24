package br.com.squadra.app.dto.uf;

import br.com.squadra.app.dto.BaseDTO;

public class UfResponseDTO extends BaseDTO {
    private String nome;
    private String sigla;
    private Integer codigoUF;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Integer codigoUF) {
        this.codigoUF = codigoUF;
    }

}
