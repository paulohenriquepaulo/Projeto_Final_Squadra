package br.com.squadra.app.dto.endereco;

import br.com.squadra.app.dto.bairro.BairroMunicipioResponseDTO;

public class EnderecoBairroResponseDTO extends EnderecoResponseDTO {

    private BairroMunicipioResponseDTO bairro;

    public BairroMunicipioResponseDTO getBairro() {
        return bairro;
    }

    public void setBairro(BairroMunicipioResponseDTO bairro) {
        this.bairro = bairro;
    }

}
