package com.vuepetapi.vuepetapi.services;


import com.vuepetapi.vuepetapi.domain.Dog;

import com.vuepetapi.vuepetapi.exceptions.DataIntegrityException;
import com.vuepetapi.vuepetapi.exceptions.ObjectNotFoundException;
import com.vuepetapi.vuepetapi.repositories.DogRepository;
import com.vuepetapi.vuepetapi.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository repo;
    @Autowired
    private VetRepository vetRepo;




    public Dog find (Integer id){
        Optional<Dog> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Dog.class.getName()));
    }
    public Dog insert(Dog obj){
        obj.setId(null);
        return repo.save(obj);
    }
    public Dog update (Dog obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete (Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse cachorro");

        }

    }
    public List<Dog> findAll(){
        return repo.findAll();
    }




}







