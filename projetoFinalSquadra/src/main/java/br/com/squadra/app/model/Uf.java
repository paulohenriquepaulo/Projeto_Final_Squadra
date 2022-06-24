package br.com.squadra.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_UF", uniqueConstraints = {
        @UniqueConstraint(name = "UF_UNIQUE", columnNames = {
                "NOME", "SIGLA"
        })
})
public class Uf extends BaseEntity {
    @Id
    @Column(name = "CODIGO_UF")
    @SequenceGenerator(name = "sequenceUF", sequenceName = "SEQUENCE_UF", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceUF")
    private Integer codigoUF;

    @Size(min = 3, max = 60, message = "O nome deve ter no mínimo 3 e no máximo 60 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    @Column(name = "NOME", unique = true)
    private String nome;

    @Size(min = 2, max = 3, message = "A sigla deve ter no mínimo 2 e no máximo 3 caracteres!")
    @NotBlank(message = "A sigla não pode ser nulo ou vazio!")
    @Column(name = "SIGLA", unique = true)
    private String sigla;

    public Integer getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Integer codigoUF) {
        this.codigoUF = codigoUF;
    }

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
