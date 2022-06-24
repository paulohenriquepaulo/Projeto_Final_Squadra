package br.com.squadra.app.dto.pessoa;

import br.com.squadra.app.dto.endereco.EnderecoBairroResponseDTO;

import java.util.List;

public class PessoaResponseDTO extends PessoaResponseBaseDTO {

    private List<EnderecoBairroResponseDTO> enderecos;

    public List<EnderecoBairroResponseDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoBairroResponseDTO> enderecos) {
        this.enderecos = enderecos;
    }

}
