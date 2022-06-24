package br.com.squadra.app.dto.pessoa;

import br.com.squadra.app.dto.endereco.EnderecoResponseDTO;

import java.util.List;

public class PessoaResponseCadastroDTO extends PessoaResponseBaseDTO {

    private List<EnderecoResponseDTO> enderecos;

    public List<EnderecoResponseDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoResponseDTO> enderecos) {
        this.enderecos = enderecos;
    }

}
