package com.poc.person.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.poc.person.dao.model.Person;
import com.poc.person.model.PersonVO;

@Component
public class PersonMapper {
	
		/**
		 * 
		 * @param persons
		 * @return
		 */
		public List<PersonVO> mapPersonDOtoList(List<Person> persons){
			List<PersonVO> personList = new ArrayList<PersonVO>(persons.size());
			persons.forEach(person -> {			
				PersonVO personVO = mapPersonDOtoVO(person);
				personList.add(personVO);
			});
			return personList;
		}
		
		/**
		 * 
		 * @param person
		 * @return
		 */
		public PersonVO mapPersonDOtoVO(Person person) {
			
			PersonVO personVO = new PersonVO();
			BeanUtils.copyProperties(person, personVO);
			
			return personVO;			
		}
		
		/**
		 * 
		 * @param personVO
		 * @return
		 */
		public Person mapPersonVOtoDO(PersonVO personVO) {
			
			Person person = new Person();
			BeanUtils.copyProperties(personVO, person);
			
			return person;			
		}
}
