package br.com.squadra.app.dto.municipio;

import br.com.squadra.app.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MunicipioResquestDTO extends BaseDTO {

    @Size(min = 3, max = 256, message = "O nome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    private String nome;

    @NotNull(message = "O codigo da UF não pode ser nulo ou vazio!")
    private Integer codigoUF;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Integer codigoUF) {
        this.codigoUF = codigoUF;
    }

}
