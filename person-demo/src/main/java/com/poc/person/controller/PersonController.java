package com.poc.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.person.exception.PersonException;
import com.poc.person.model.PersonVO;
import com.poc.person.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping(value = "/createPerson")
	public ResponseEntity<Object> createPerson(@RequestBody PersonVO personVO){
		personService.createPerson(personVO);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>("Person details are saved successfully", HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/persons")	
	public ResponseEntity<Object> getAllPersonDetails(){
		ResponseEntity<Object> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<Object>(personService.getAllPersonDetails(), HttpStatus.OK);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseEntity;
	}
	
}
