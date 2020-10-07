package com.vuepetapi.vuepetapi.controller;

import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.dto.DogDTO;
import com.vuepetapi.vuepetapi.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/dogs")
public class DogController  {

        @Autowired
        private DogService service;

    private static List<Dog> dogsDoBanco = List.of(new Dog(1, "Doguinho", "Beagle",2,2),
            new Dog(2, "Dog","Poodle", 2, 2),
            new Dog(3,"Dogz","Husk", 4, 4),
            new Dog(4,"Qualquer", "Vira Lata",5,5));


    @RequestMapping(value = "" , method = RequestMethod.POST)
    public ResponseEntity<Void>insert ( @RequestBody DogDTO objDto){
        Dog obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



    @GetMapping("")
    public List<Dog> dogs() {
        return dogsDoBanco;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
