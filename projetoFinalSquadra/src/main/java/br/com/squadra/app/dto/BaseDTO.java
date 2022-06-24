package br.com.squadra.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public abstract class BaseDTO {

    @Min(value = 1, message = "Status invalido: 1 - ATIVADO, 2 - DESATIVADO")
    @Max(value = 2, message = "Status invalido: 1 - ATIVADO, 2 - DESATIVADO")
    @NotNull(message = "O status não pode ser nulo ou vazio!")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
