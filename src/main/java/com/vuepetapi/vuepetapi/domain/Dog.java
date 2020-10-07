package com.vuepetapi.vuepetapi.domain;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    public class Dog  {

        @Id
        private Integer id;

        @Column
        private String nome;

        @Column
        private String raca;
        private Number peso;
        private Number idade;

        public Dog(Integer id, String nome, String raca, Number peso, Number idade) {
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
    }
