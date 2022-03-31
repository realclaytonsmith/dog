package com.pet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.exception.ResourceNotFoundException;
import com.pet.model.Dog;
import com.pet.repository.DogRepository;

@RestController
@RequestMapping("/api/v1")
public class DogController {

	@Autowired
    private DogRepository dogRepository;

    @GetMapping("/dogs")
    public List < Dog > getAllDogs() {
        return dogRepository.findAll();
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity < Dog > getDogById(@PathVariable(value = "id") Long dogId)
    throws ResourceNotFoundException {
        Dog dog = dogRepository.findById(dogId)
            .orElseThrow(() -> new ResourceNotFoundException("dog not found for this id :: " + dogId));
        return ResponseEntity.ok().body(dog);
    }

    @PostMapping("/dogs")
    public Dog createDog(@Valid @RequestBody Dog dog) {
        return dogRepository.save(dog);
    }

    @PutMapping("/dogs/{id}")
    public ResponseEntity < Dog > updateDog(@PathVariable(value = "id") Long dogId,
        @Valid @RequestBody Dog dogDetails) throws ResourceNotFoundException {
        Dog dog = dogRepository.findById(dogId)
            .orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + dogId));

        dog.setBreed(dogDetails.getBreed());
        dog.setAge(dogDetails.getAge());
        dog.setName(dogDetails.getName());
        final Dog updatedDog = dogRepository.save(dog);
        return ResponseEntity.ok(updatedDog);
    }

    @DeleteMapping("/dogs/{id}")
    public Map < String, Boolean > deleteDog(@PathVariable(value = "id") Long dogId)
    throws ResourceNotFoundException {
        Dog dog = dogRepository.findById(dogId)
            .orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + dogId));

        dogRepository.delete(dog);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
