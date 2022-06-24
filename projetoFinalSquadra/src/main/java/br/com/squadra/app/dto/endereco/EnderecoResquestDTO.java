package br.com.squadra.app.dto.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EnderecoResquestDTO {

    @NotNull(message = "O codigo do bairro não poder ser null ou vazio")
    private Long codigoBairro;

    @Size(min = 3, max = 256, message = "O nome da rua deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome da rua não pode ser nulo ou vazio!")
    private String nomeRua;

    @Size(min = 1, max = 10, message = "O numero deve ter no mínimo 1 e no máximo 10 caracteres!")
    @NotBlank(message = "O múmero da rua não pode ser nulo ou vazio!")
    private String numero;

    @Size(max = 20, message = "O complemento deve ter no máximo 20 caracteres!")
    private String complemento;

    @Pattern(regexp = "\\d{2}\\d{3}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
    private String cep;

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
