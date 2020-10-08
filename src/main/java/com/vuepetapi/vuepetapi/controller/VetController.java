package com.vuepetapi.vuepetapi.controller;

import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.domain.Vet;
import com.vuepetapi.vuepetapi.repositories.VetRepository;
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

@RestController
@RequestMapping(value = "/vets")
public class VetController {

    @Autowired
    private VetService service;
    @Autowired
   private VetRepository vetRepo;

    @GetMapping(value = "/{id}")
    public ResponseEntity <Vet> find (@PathVariable Integer id){
        Vet obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert (@RequestBody Vet obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Vet> update(@RequestBody Vet obj, @PathVariable Integer id) {
        return vetRepo.findById(id)
                .map(record -> {
                    record.setNome(obj.getNome());
                  record.setCpf(obj.getCpf());
                    Vet updated = vetRepo.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("{id}/dogs")
    public ResponseEntity<List<Dog>> findAllDogs(@PathVariable("id") Integer id) {
        List<Dog> result = vetRepo.findDogsById(id);
        if(!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping
    public ResponseEntity<List<Dog>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<Vet> vetName;

        //validar nome
        if (nome != null) {
            Optional<Vet> vet = vetRepo.findByNome(nome);

            if(vet.isPresent()) {
                vetName = Collections.singletonList(vet.get());
            } else {
                vetName = Collections.emptyList();
            }
        } else {
            vetName = vetRepo.findAll();
        }

        return new ResponseEntity(vetName, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }



}
