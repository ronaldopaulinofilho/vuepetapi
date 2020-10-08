package com.vuepetapi.vuepetapi.controller;

import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/dogs")
public class DogController  {

    @Autowired
    private DogService service;

@GetMapping(value = "/{id}")
public ResponseEntity <Dog> find (@PathVariable Integer id){
   Dog obj = service.find(id);
   return ResponseEntity.ok().body(obj);
}
@GetMapping("/{nome}")
public ResponseEntity<Dog> findByName (@PathVariable("nome") String nome){
Dog dogName = null;
    Dog[] dogs = new Dog[0];
    for (Dog dog: dogs){
        if (dog.getNome().equalsIgnoreCase(nome)) {
            dogName = dog;
        }
    }
   if(dogName != null){
       return new ResponseEntity<Dog>(dogName, HttpStatus.OK);
   } else{
       return new ResponseEntity<Dog>(HttpStatus.NO_CONTENT);
   }
   }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert (@RequestBody Dog obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void>update( @RequestBody Dog obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    public  class  DogPeso{
        private Integer id;
        private Number peso;
    }
    @PatchMapping("/dogs/{id}")
    public ResponseEntity<?> partialUpdateName(
            @RequestBody DogPeso partialUpdate, @PathVariable("id") String id) {

        Object peso = null;
        service.update(partialUpdate, peso);
        return ResponseEntity.ok("resource address updated");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<Dog>> findAll(){
        List <Dog> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }


}
