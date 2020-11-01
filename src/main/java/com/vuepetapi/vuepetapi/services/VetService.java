package com.vuepetapi.vuepetapi.services;


import com.vuepetapi.vuepetapi.domain.Dog;
import com.vuepetapi.vuepetapi.domain.Vet;
import com.vuepetapi.vuepetapi.exceptions.DataIntegrityException;
import com.vuepetapi.vuepetapi.exceptions.ObjectNotFoundException;
import com.vuepetapi.vuepetapi.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService {
    @Autowired
    private VetRepository repo;

    public Vet find(Integer id) {
        Optional<Vet> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Vet.class.getName()));
    }

    public Vet findByNome(String nome) {
        Optional<Vet> obj = repo.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Nome:" + nome + ", Tipo:" + Dog.class.getName()));
    }

    public Vet insert(Vet obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Vet update(Vet obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse Veterinário ");

        }
    }

    public List<Vet> findAll() {
        return repo.findAll();
    }

}
