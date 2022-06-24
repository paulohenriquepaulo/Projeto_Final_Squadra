package br.com.squadra.app.dto.municipio;

import br.com.squadra.app.dto.BaseDTO;

public class MunicipioResponseDTO extends BaseDTO {

    private String nome;

    private Long codigoMunicipio;

    private Integer codigoUF;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Integer getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Integer codigoUF) {
        this.codigoUF = codigoUF;
    }

}
