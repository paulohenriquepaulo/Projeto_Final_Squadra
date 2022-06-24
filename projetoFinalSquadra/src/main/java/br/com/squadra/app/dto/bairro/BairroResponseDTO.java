package br.com.squadra.app.dto.bairro;

import br.com.squadra.app.dto.BaseDTO;

public class BairroResponseDTO extends BaseDTO {

    private Long codigoMunicipio;
    private String nome;
    private Long codigoBairro;

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }
}
