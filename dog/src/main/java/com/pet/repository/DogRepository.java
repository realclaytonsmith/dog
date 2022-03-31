package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.model.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long>{

}
