package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.Person;
import com.omt.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public List<Person> findAll(){
		return personService.findAll();
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public Person findOne(@PathVariable("id") Long id){
		return personService.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Person save(@RequestBody Person person){
		return personService.save(person);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Person update(@RequestBody Person person){
		return personService.save(person);
	}
	
	@RequestMapping(path="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		personService.delete(id);
} 
	
	
}
