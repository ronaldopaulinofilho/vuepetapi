package com.vuepetapi.vuepetapi.repositories;

import com.vuepetapi.vuepetapi.domain.Vet;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VetRepository extends JpaRepository <Vet, Integer> {

}
