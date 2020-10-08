package com.vuepetapi.vuepetapi.repositories;

import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.domain.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository <Vet, Integer> {

    Optional<Vet> findByNome(String nome);
   @Query( value ="SELECT v.dogs from Vet v where v.id = :id")
    List<Dog>  findDogsById(@Param("id")Integer id);

}
