package com.omt.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.Person;
import com.omt.repository.PersonRepository;

@Service
public class PersonService{
	
	PersonRepository personRepository;
	
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	public Person save(Person person) {
		// TODO Auto-generated method stub
		return personRepository.save(person);
	}

	public Person findOne(Long id) {
		// TODO Auto-generated method stub
		return personRepository.findOne(id);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		personRepository.delete(id);
	}
	
}