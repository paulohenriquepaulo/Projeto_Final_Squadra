package br.com.squadra.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB_BAIRRO")
public class Bairro extends BaseEntity {

    @Id
    @Column(name = "CODIGO_BAIRRO")
    @SequenceGenerator(name = "sequenceBairro", sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceBairro")
    private Long codigoBairro;

    @Size(min = 3, max = 256, message = "O nome deve ter no mínimo 3 e no máximo 256 caracteres!")
    @NotBlank(message = "O nome não pode ser nulo ou vazio!")
    @Column(name = "NOME", unique = true)
    private String nome;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private Municipio municipio;

    @OneToMany(mappedBy = "bairro", cascade = CascadeType.PERSIST)
    private List<Endereco> enderecos;

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
