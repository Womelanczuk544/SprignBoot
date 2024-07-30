package org.womelanczuk.springproject.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.womelanczuk.springproject.model.Person;

@Configuration
public class ConfigRepo {
    @Bean
    CommandLineRunner commandLineRunner(Contactsrepo contactsRepository){
        return args -> {
            Person first = new Person("Jan", "Kowalski", "123456789");
            contactsRepository.save(first);
        };
    }
}
