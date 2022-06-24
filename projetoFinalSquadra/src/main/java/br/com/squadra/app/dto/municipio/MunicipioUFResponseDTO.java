package br.com.squadra.app.dto.municipio;

import br.com.squadra.app.dto.BaseDTO;
import br.com.squadra.app.dto.uf.UfResponseDTO;

public class MunicipioUFResponseDTO extends BaseDTO {

    private String nome;
    private UfResponseDTO uf;
    private Long codigoMunicipio;

    private Integer codigoUF;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UfResponseDTO getUf() {
        return uf;
    }

    public void setUf(UfResponseDTO uf) {
        this.uf = uf;
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
