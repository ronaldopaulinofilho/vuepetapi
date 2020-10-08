package com.vuepetapi.vuepetapi.repositories;
import com.vuepetapi.vuepetapi.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin
@Repository
public interface DogRepository extends JpaRepository <Dog, Integer> {

 Optional<Dog> findByNome(String nome);


}



