package org.womelanczuk.springproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.womelanczuk.springproject.api.Contactsrepo;
import org.womelanczuk.springproject.model.*;
import org.womelanczuk.springproject.api.ApiApi;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
//@EnableSwagger2
@RequestMapping("/api/contacts")
public class SpringProjectApplication implements ApiApi{

	private final Contactsrepo dataBase;

	@Autowired
	public SpringProjectApplication(Contactsrepo dataBase){
		this.dataBase = dataBase;
	}

	@PostMapping
	@Override
	public ResponseEntity<?> apiContactsPost(@RequestBody Person person){
		dataBase.save(person);
		return new ResponseEntity<String>("Person added", HttpStatus.CREATED);
	}

	@GetMapping
	@Override
	public  ResponseEntity<List<Person>> apiContactsGet(){
		return new ResponseEntity<List<Person>>(dataBase.findAll(),HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable int id){
		return dataBase.findById(id).get();
	}

	@GetMapping("/{sortingtype}")
	public ResponseEntity<List<Person>> apiContactsSortingtypeGet(@PathVariable String sortingtype){
			return new ResponseEntity<>(dataBase.findAll(Sort.by(sortingtype)),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apiContactsDelete(@PathVariable int id){
		boolean exist = dataBase.existsById(id);
		if(!exist) {
			throw new IllegalStateException("PhoneBook with id " + id + " does not exist");
		}
		dataBase.deleteById(id);
		return new ResponseEntity<String>("contact delete",HttpStatus.OK);
	}
}
