package com.example.seting.demo.dao;

import com.example.seting.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    // JAVA 8 - Interface Default Methods
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    // JAVA 8 introducida
    Optional<Person> selectPersonById(UUID id);

    List<Person> selectAllPeople();

    public int deletePersonById(UUID id);

    public int updatePersonById(UUID id, Person person);



}
