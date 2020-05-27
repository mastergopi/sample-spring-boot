package com.poc.person.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.person.dao.model.Person;
import com.poc.person.exception.PersonExceptionHandler;
import com.poc.person.exception.PersonNotFoundException;
import com.poc.person.mapper.PersonMapper;
import com.poc.person.model.PersonVO;
import com.poc.person.repository.PersonRepository;

@Component
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonMapper personMapper;
		
	
	/**
	 * To get list of all persons 
	 * @return
	 * @throws PersonNotFoundException
	 */
	public List<PersonVO> getAllPersonDetails(){
		List<Person> persons = personRepository.findAll();
		if(null != persons && !persons.isEmpty()) {
			return personMapper.mapPersonDOtoList(persons);
		}
		throw new PersonNotFoundException("No Persons found");
	}

	/**
	 * To update person details with given ID
	 */
	@Override
	public PersonVO updatePersonDetails(final long id, PersonVO personVO) {
		Optional<Person> personDO = personRepository.findById(id);
		Person person = null;
		if(personDO.isPresent()) {
			person = personMapper.mapPersonVOtoDO(personVO);
			person.setId(id);
			Person updatedPerson = personRepository.save(person);
			return personMapper.mapPersonDOtoVO(updatedPerson);
		}else {
			throw new PersonNotFoundException("Person details not found to update");
		}				
	}

	/**
	 * To delete the Person with given Id
	 */
	@Override
	public void deletePerson(long id){
		Optional<Person> personDO = personRepository.findById(id);
		if(personDO.isPresent()) {
			personRepository.deleteById(id);
		}else {
			throw new PersonNotFoundException("Person details not found to delete");
		}		
	}

	/**
	 * To get the List of Persons whose age greater than given value
	 */
	@Override
	public List<PersonVO> getPersonsByAge(int age){
		List<Person> persons = personRepository.findPersonByAge(age);
		if(null != persons && !persons.isEmpty()) {
			return personMapper.mapPersonDOtoList(persons);
		}
		throw new PersonNotFoundException("No Persons found");
	}

	/**
	 * To create a person with given Person details
	 */
	@Override
	public PersonVO createPerson(PersonVO personVO) {
		
		Person person = personMapper.mapPersonVOtoDO(personVO);
		Person updatedPerson = personRepository.save(person);
		
		return personMapper.mapPersonDOtoVO(updatedPerson);
	}

}
