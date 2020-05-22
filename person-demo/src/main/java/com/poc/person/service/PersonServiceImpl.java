package com.poc.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.person.dao.model.Person;
import com.poc.person.exception.PersonException;
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
	 * 
	 * @return
	 * @throws PersonException
	 */
	public List<PersonVO> getAllPersonDetails() throws PersonException{
		List<Person> persons = personRepository.findAll();
		if(null != persons && !persons.isEmpty()) {
			return personMapper.mapPersonDOtoList(persons);
		}
		throw new PersonException("No Persons found");
	}

	@Override
	public PersonVO updatePersonDetails(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonVO deletePerson(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonVO> getPersonsByAge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonVO createPerson(PersonVO personVO) {
		
		Person person = personMapper.mapPersonVOtoDO(personVO);
		Person updatedPerson = personRepository.save(person);
		
		return personMapper.mapPersonDOtoVO(updatedPerson);
	}

}
