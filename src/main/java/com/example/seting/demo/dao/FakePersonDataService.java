package com.example.seting.demo.dao;

import com.example.seting.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



//esto es de SpringData ó tambien podrias usar @Component
@Repository ("fakeDao")
public class FakePersonDataService implements PersonDao{

    // Esta sera nuestra base de datos falsa que sera una lista por el momento
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));

        // devolveremos uno por que es una lista y nunca habra errores de insercion
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (!personMaybe.isPresent()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;

    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        System.out.println("FakePersonDataService -> updatePersonById");
        System.out.println("id = "+id.toString());
        System.out.println("name = "+update.getName());

         return selectPersonById(id)
                 .map(person ->{
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    // si indexOf() = -1 es por que no existe el objeto y solo return 0;
                    if(indexOfPersonToUpdate>=0){
                        // set() remplaza el Objeto por el nuevo del indice
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                 })
                 .orElse(0);



    }
}
