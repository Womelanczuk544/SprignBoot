package org.womelanczuk.springproject.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.womelanczuk.springproject.model.Person;

public interface Contactsrepo extends JpaRepository<Person, Integer> {
}
