package com.poc.person.repository;

import java.util.List;

import com.poc.person.dao.model.Person;

public interface PersonRepository extends BaseRepository<Person>{
	
	public List<Person> findPersonByAge(int age);	
	
}
