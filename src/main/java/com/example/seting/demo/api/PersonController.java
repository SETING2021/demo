package com.example.seting.demo.api;

import com.example.seting.demo.model.Person;
import com.example.seting.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import java.util.List;
import java.util.UUID;

// el nombre del servicio que se va a llamar con JSON
@RequestMapping ("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){

        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllpeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person selectPersonById(@PathVariable("id") UUID id){
        return personService.selectPersonById(id).orElse(null);

    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
