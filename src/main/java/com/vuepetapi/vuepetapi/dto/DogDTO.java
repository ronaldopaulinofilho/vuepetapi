package com.vuepetapi.vuepetapi.dto;

import com.vuepetapi.vuepetapi.domain.Dog;


public class DogDTO  {


    private Integer id;
    private String nome;
    private String raca;
    private Number peso;
    private Number idade;
    public DogDTO(){

    }
    public DogDTO(Dog obj) {
        id = obj.getId();
        nome = obj.getNome();
        raca = obj.getRaca();
        peso = obj.getPeso();
        idade = obj.getIdade();
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
}

