package com.vuepetapi.vuepetapi.repositories;


import com.vuepetapi.vuepetapi.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository <Dog, Integer> {



}

