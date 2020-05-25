package com.poc.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	 * To Create person
	 * @param personVO
	 * @return
	 */
	@PostMapping(value = "/createPerson")
	public ResponseEntity<Object> createPerson(@RequestBody PersonVO personVO){
		personService.createPerson(personVO);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>("Person details are saved successfully", HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * To get List of all persons
	 * @return
	 */
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
	
	/**
	 * To update a person detail with Id
	 * @param id
	 * @param personVO
	 * @return
	 */
	@PostMapping(value = "/updatePerson/{id}")
	public ResponseEntity<Object> updatePerson(@PathVariable long id, @RequestBody PersonVO personVO){
		
		ResponseEntity<Object> responseEntity = null;
		try {
			personService.updatePersonDetails(id, personVO);
			responseEntity = new ResponseEntity<Object>("Person details are updated successfully", HttpStatus.OK);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return responseEntity;
	}
	
	/**
	 * To get the List of Persons greater than the given age parameter
	 * @param age
	 * @return
	 */
	@GetMapping(value = "/getPersonsByAge/{age}")	
	public ResponseEntity<Object> getPersonsByAge(@PathVariable int age){
		ResponseEntity<Object> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<Object>(personService.getPersonsByAge(age), HttpStatus.OK);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@GetMapping(value = "/deletePerson/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable long id){
		
		ResponseEntity<Object> responseEntity = null;
		try {
			personService.deletePerson(id);
			responseEntity = new ResponseEntity<Object>("Person details are deleted successfully", HttpStatus.OK);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return responseEntity;
	}
}
