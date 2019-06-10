package com.bil.petclinicgen.controller;

import com.bil.petclinicgen.model.dto.NewPet;
import com.bil.petclinicgen.model.dto.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
public class PetsController implements PetsApi {
    @Override
    public ResponseEntity<Pet> addPet(@Valid NewPet body) {
        try {
            return ResponseEntity.created(new URI("http://localhost:8080/Pet+Store/1.0.0/pets/1")).build();
        } catch (URISyntaxException e) {
            log.error("Wrong URI syntax", e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ResponseEntity<Void> deletePet(Long id) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Pet> findPetById(Long id) {
        Pet pet = new Pet();
        pet.setId(1L);
        return ResponseEntity.ok(pet);
    }

    @Override
    public ResponseEntity<List<Pet>> findPets(@Valid List<String> tags, @Valid Integer limit) {
        Pet pet = new Pet();
        pet.setId(1L);
        return ResponseEntity.ok(Arrays.asList(pet));
    }
}
