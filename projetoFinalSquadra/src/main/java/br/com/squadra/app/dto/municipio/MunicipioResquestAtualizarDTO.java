package br.com.squadra.app.dto.municipio;

import javax.validation.constraints.NotNull;

public class MunicipioResquestAtualizarDTO extends MunicipioResquestDTO {

    @NotNull(message = "O codigo do municipio não pode ser nulo ou vazio!")
    private Long codigoMunicipio;

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

}
