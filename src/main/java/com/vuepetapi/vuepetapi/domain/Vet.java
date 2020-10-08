package com.vuepetapi.vuepetapi.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private String data;

    @Column(unique=true)
    private String cpf;

    @OneToMany(mappedBy = "vetResponsavel", targetEntity = Dog.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Dog> dogs;


    public Vet(){

    }

    public Vet(Integer id, String nome, String data, String cpf, List<Dog> dogs) {
        super();
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.cpf = cpf;
        this.dogs = dogs;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }


}
