package com.vuepetapi.vuepetapi.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "raca")
    private String raca;
    @Column(name = "peso")
    private Number peso;
    @Column(name = "idade")
    private Number idade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vetResponsavel;

    public Dog() {
    }
    public Dog(String nome, String raca, Number peso, Number idade) {
        super();
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.peso = peso;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Number getPeso() {
        return peso;
    }
    public void setPeso(Number peso) {
        this.peso = peso;
    }

    public Number getIdade() {
        return idade;
    }
    public void setIdade(Number idade) {
        this.idade = idade;
    }

    public Vet getVetResponsavel() {
        return vetResponsavel;
    }
    public void setVetResponsavel(Vet vetResponsavel) {
        this.vetResponsavel = vetResponsavel;
    }
}
