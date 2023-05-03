package com.example.backendjavales11huiswerkopdracht.controller;

import com.example.backendjavales11huiswerkopdracht.exception.RecordNotFoundException;
import com.example.backendjavales11huiswerkopdracht.model.Television;
import com.example.backendjavales11huiswerkopdracht.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController //deze class is een rest controller
@RequestMapping("/televisions") //root path
public class TelevisionController {
    @Autowired//springboot maakt repository automatisch
    //variabel
    private TelevisionRepository repository;

    //lijst met alle tv's, inclusief if-statement met record not found exception melding voor als er geen televisies worden gevonden.
    @GetMapping
    public ResponseEntity<List<Television>> getTelevisions() {
        List<Television> televisions = repository.findAll();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("No televisions found.");
        } else {
            return ResponseEntity.ok(televisions);
        }
    }

    //lijst met een enkele tv, inclusief if-statement met record not found exception melding voor als de tv niet wordt gevonden
    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable int id) {
        List<Television> televisions = repository.findAll();
        if (id >= 0 && id < televisions.size()) {
            Television television = televisions.get(id);
            return ResponseEntity.ok(television);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //tv object maken en toevoegen aan de repository
    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television){
        repository.save(television);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + television.getId()).toUriString());
        return ResponseEntity.created(uri).body(television);
    }




/*



    //tv aanpassen
    @PutMapping("/{id}")

    //tv verwijderen
    @DeleteMapping("/{id}")*/


}
