package com.vuepetapi.vuepetapi.services;


import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.dto.DogDTO;
import com.vuepetapi.vuepetapi.exceptions.ObjectNotFoundException;
import com.vuepetapi.vuepetapi.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository repo;

    public List<Dog> findByNome(String nome) {
        List<Dog> obj = repo.findAll();
        return  repo.findAll();
    }

    public Dog find (Integer id){
        Optional<Dog> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Dog.class.getName()));
    }
    public Dog insert(Dog obj){
        obj.setId(null);
        return repo.save(obj);
    }
    public Dog update(Dog obj){
        Dog newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    public void delete (Integer id){
        find(id);
            repo.deleteById(id);

    }

    private void updateData (Dog newObj, Dog obj){
        newObj.setNome(obj.getNome());
    }
    public List<Dog>  findAll (){
        return repo.findAll();
    }


    public Dog fromDTO(DogDTO objDto) {
        return new Dog(objDto.getId(), objDto.getNome(), objDto.getRaca(), objDto.getPeso(), objDto.getIdade());
    }
}

