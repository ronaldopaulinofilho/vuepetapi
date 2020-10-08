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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/vets")
public class VetController {

    @Autowired
    private VetService service;

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
    @GetMapping("/{nome}")
    public ResponseEntity<Vet> findByName (@PathVariable("nome") String nome){
        Vet vetName = null;
        Vet [] vets = new Vet[0];
        for (Vet vet: vets){
            if (vet.getNome().equalsIgnoreCase(nome)) {
                vetName = vet;
            }
        }
        if(vetName != null){
            return new ResponseEntity<Vet>(vetName, HttpStatus.OK);
        } else{
            return new ResponseEntity<Vet>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void>update( @RequestBody Vet obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<Vet>> findAll(){
        List <Vet> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }





}
