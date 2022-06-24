package br.com.squadra.app.dto.bairro;

import br.com.squadra.app.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class BairroResquestDTO extends BaseDTO {

    @Size(min = 3, max = 256, message = "O nome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    private String nome;

    @NotNull(message = "O codigo não pode ser nulo ou vazio")
    private Integer codigoMunicipio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

}
