package br.com.squadra.app.dto.bairro;

import javax.validation.constraints.NotNull;

public class BairroResquestAtualizarDTO extends BairroResquestDTO {

    @NotNull(message = "O codigo não pode ser nulo ou vazio")
    private Long codigoBairro;

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

}
