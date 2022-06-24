package br.com.squadra.app.dto.uf;

import br.com.squadra.app.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UfRequestDTO extends BaseDTO {

    @Size(min = 3, max = 60, message = "O nome deve ter no mínimo 3 e no máximo 60 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    private String nome;

    @Size(min = 2, max = 3, message = "A sigla deve ter no mínimo 2 e no máximo 3 caracteres!")
    @NotBlank(message = "A sigla não pode ser nulo ou vazio!")
    private String sigla;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
