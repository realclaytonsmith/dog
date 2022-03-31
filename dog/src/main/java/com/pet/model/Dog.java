package com.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dogs")
public class Dog {

	 private long id;
	    private String name;
	    private String age;
	    private String breed;

	    public Dog() {

	    }

	    public Dog(String name, String age, String breed) {
	        this.name = name;
	        this.age = age;
	        this.breed = breed;
	    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    public long getId() {
	        return id;
	    }
	    public void setId(long id) {
	        this.id = id;
	    }

	    @Column(name = "name", nullable = false)
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }

	    @Column(name = "age", nullable = false)
	    public String getAge() {
	        return age;
	    }
	    public void setAge(String age) {
	        this.age = age;
	    }

	    @Column(name = "breed", nullable = false)
	    public String getBreed() {
	        return breed;
	    }
	    public void setBreed(String breed) {
	        this.breed = breed;
	    }
}
