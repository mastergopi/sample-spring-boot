package com.poc.person.service;

import java.util.List;

import com.poc.person.model.PersonVO;

public interface PersonService {
	
	public PersonVO createPerson(PersonVO personVO);
	
	public List<PersonVO> getAllPersonDetails();
	
	public PersonVO updatePersonDetails(long id,PersonVO personVO);
	
	public void deletePerson(long id);
	
	public List<PersonVO> getPersonsByAge(int age);
	
}
