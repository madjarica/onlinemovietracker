package com.omt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public List<Person> findAll();

	public Person save(Person person);

	public Person findOne(Long id);

	public void delete(Long id);

	public Person findByTmdbPersonId(Long id);

}
