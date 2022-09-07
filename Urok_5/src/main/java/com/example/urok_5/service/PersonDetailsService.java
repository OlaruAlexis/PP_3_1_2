package com.example.urok_5.service;

import com.example.urok_5.dao.PersonDAO;
import com.example.urok_5.model.Person;
import com.example.urok_5.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonDetailsService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personDAO.findByUsername(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return  new PersonDetails(person.get());
    }
}
