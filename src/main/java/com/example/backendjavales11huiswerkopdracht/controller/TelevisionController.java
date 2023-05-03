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
import java.util.Optional;

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
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
        repository.save(television);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + television.getId()).toUriString());
        return ResponseEntity.created(uri).body(television);
    }


    //een televisie object updaten
    //Een Optional<T> is een container die kan worden gebruikt om te bepalen of een waarde bestaat of niet. Het is ontworpen om null-referenties te voorkomen en om aan te geven dat een waarde mogelijk leeg kan zijn.
    @PutMapping("{id}")
    public ResponseEntity<Television> updateTelevision(@RequestBody Television newTelevision, @PathVariable long id) {
        Optional<Television> existingTelevision = repository.findById(id);
        if (existingTelevision.isEmpty()) {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        } else {
            //nieuw object aanmaken
            Television televisionToUpdate = existingTelevision.get();
            //geef aan welke velden aangepast kunnen worden
            televisionToUpdate.setType(newTelevision.getType());
            televisionToUpdate.setBrand(newTelevision.getBrand());
            televisionToUpdate.setName(newTelevision.getName());
            televisionToUpdate.setPrice(newTelevision.getPrice());
            televisionToUpdate.setAvailableSize(newTelevision.getAvailableSize());
            televisionToUpdate.setRefreshRate(newTelevision.getRefreshRate());
            televisionToUpdate.setScreenType(newTelevision.getScreenType());
            televisionToUpdate.setScreenQuality(televisionToUpdate.getScreenQuality());
            //etc.

            //sla bijgewerkte televisie-object op in de database
            repository.save(televisionToUpdate);

            //retourneer een HTTP respons met status 200, update is succesvol uitgevoerd
            return ResponseEntity.ok(televisionToUpdate);

        }

    }

    //tv verwijderen
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTelevision(@PathVariable long id) {
        Optional<Television> televisionToDelete = repository.findById(id);
        if (televisionToDelete.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
