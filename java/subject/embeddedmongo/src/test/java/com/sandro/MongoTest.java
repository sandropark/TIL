package com.sandro;

import com.sandro.data.Person;
import com.sandro.data.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class MongoTest {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    PersonRepository personRepository;

    @Test
    void test() throws Exception {
        String name = "산드로";
        Person person = Person.builder().name(name).build();
        personRepository.save(person);

        Person foundPerson = personRepository.findByName(name)
                .orElseThrow();

        assertThat(foundPerson.getName()).isEqualTo(name);
    }

}
