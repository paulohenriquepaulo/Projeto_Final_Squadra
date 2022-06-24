package br.com.squadra.app.dto.uf;

import javax.validation.constraints.NotNull;

public class UfRequestAtualizarDTO extends UfRequestDTO {

    @NotNull(message = "O codigo da Uf não pode ser nulo ou vazio!")
    private Integer codigoUF;

    public Integer getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Integer codigoUF) {
        this.codigoUF = codigoUF;
    }

}
