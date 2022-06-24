package br.com.squadra.app.dto.endereco;

import javax.validation.constraints.NotNull;

public class EnderecoResquestAtualizarDTO extends EnderecoResquestDTO {


    private Long codigoEndereco;
    @NotNull(message = "O codigo da pessoa não poder ser nulo ou vazio!")
    private Long codigoPessoa;

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

}
