package br.com.squadra.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @Column(name = "CODIGO_ENDERECO")
    @SequenceGenerator(name = "sequenceEndereco", sequenceName = "SEQUENCE_ENDERECO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceEndereco")
    private Long codigoEndereco;

    @Size(min = 3, max = 256, message = "O nome da rua deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome da rua não pode ser nulo ou vazio!")
    @Column(name = "NOME_RUA")
    private String nomeRua;

    @Size(min = 1, max = 10, message = "O numero deve ter no mínimo 1 e no máximo 10 caracteres!")
    @NotBlank(message = "O número não pode ser nulo ou vazio!")
    @Column(name = "NUMERO")
    private String numero;

    @Size(max = 20, message = "O complemento deve ter no máximo 20 caracteres!")
    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Size(min = 9, max = 9, message = "O CEP deve estar no formato 99999-999")
    @Column(name = "CEP")
    private String cep;

    @NotNull(message = "O Endereço precisa estar vinculado a uma pessoa")
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CODIGO_PESSOA")
    private Pessoa pessoa;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CODIGO_BAIRRO")
    private Bairro bairro;

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(codigoEndereco, endereco.codigoEndereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEndereco);
    }

}
