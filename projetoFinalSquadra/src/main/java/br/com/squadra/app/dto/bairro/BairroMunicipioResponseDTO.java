package br.com.squadra.app.dto.bairro;

import br.com.squadra.app.dto.municipio.MunicipioUFResponseDTO;

public class BairroMunicipioResponseDTO extends BairroResponseDTO {

    private MunicipioUFResponseDTO municipio;

    public MunicipioUFResponseDTO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioUFResponseDTO municipio) {
        this.municipio = municipio;
    }
}
