package ru.dob.library.WebLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dob.library.WebLibrary.models.Person;

import ru.dob.library.WebLibrary.repositories.VisitorsRepository;
import ru.dob.library.WebLibrary.security.PersonDetails;

import java.util.Optional;
@Service
public class VisitorsDetailsService implements UserDetailsService {
    private final VisitorsRepository visitorsRepository;

    @Autowired
    public VisitorsDetailsService(VisitorsRepository visitorsRepository) {
        this.visitorsRepository = visitorsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = visitorsRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
