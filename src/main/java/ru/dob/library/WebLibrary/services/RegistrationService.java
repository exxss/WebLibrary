package ru.dob.library.WebLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.repositories.PeopleRepository;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setRole("ROLE_USER");
        peopleRepository.save(staff);
    }
}
