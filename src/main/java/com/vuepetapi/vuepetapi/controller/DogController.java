package com.vuepetapi.vuepetapi.controller;

import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.domain.Vet;
import com.vuepetapi.vuepetapi.repositories.DogRepository;
import com.vuepetapi.vuepetapi.repositories.VetRepository;
import com.vuepetapi.vuepetapi.services.DogService;
import com.vuepetapi.vuepetapi.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value="/dogs")
public class DogController {

    @Autowired
    private DogService service;
    @Autowired
    private VetService vetService;

    @Autowired
    private DogRepository repo;
    @Autowired
    private VetRepository vetRepo;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Dog> find(@PathVariable Integer id) {
        Dog obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@RequestBody Dog obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dog> update(@RequestBody Dog obj, @PathVariable Integer id) {
        return repo.findById(id)
                .map(record -> {
                    record.setNome(obj.getNome());
                    record.setIdade(obj.getIdade());
                    record.setPeso(obj.getPeso());
                    record.setRaca(obj.getNome());
                    Dog updated = repo.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }



    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVet(
            @RequestBody VetRequest vetRequest,
            @PathVariable Integer id) {
        Optional<Dog> dogFromDB = repo.findById(id);

        if (dogFromDB.isPresent()) {
            Dog dog = dogFromDB.get();

            Optional<Vet> vetFromDB = vetRepo.findById(vetRequest.getId());

            if(vetFromDB.isPresent()) {
                Vet vet = vetFromDB.get();

                dog.setVetResponsavel(vet);

                repo.save(dog);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping
    public ResponseEntity<List<Dog>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<Dog> dogName;

        //validar nome
        if (nome != null) {
           Optional<Dog> dog = repo.findByNome(nome);

            if(dog.isPresent()) {
               dogName = Collections.singletonList(dog.get());
            } else {
                dogName = Collections.emptyList();
            }
        } else {
            dogName = repo.findAll();
        }

        return new ResponseEntity(dogName, HttpStatus.OK);
    }





}
