package br.com.squadra.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_MUNICIPIO", uniqueConstraints = {
        @UniqueConstraint(name = "MUNICIPIO_POR_UF_UNIQUE", columnNames = {
                "NOME", "CODIGO_UF"
        })
})
public class Municipio extends BaseEntity {

    @Id
    @Column(name = "CODIGO_MUNICIPIO")
    @SequenceGenerator(name = "sequenceMunicipio", sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceMunicipio")
    private Long codigoMunicipio;

    @Column(name = "NOME")
    @Size(min = 3, max = 256, message = "O nome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    private String nome;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CODIGO_UF")
    private Uf uf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

}
