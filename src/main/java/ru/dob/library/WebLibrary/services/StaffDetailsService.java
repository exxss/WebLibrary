package ru.dob.library.WebLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.repositories.PeopleRepository;
import ru.dob.library.WebLibrary.security.StaffDetails;

import java.util.Optional;

@Service
public class StaffDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public StaffDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Staff> staff = peopleRepository.findByUsername(s);

        if (staff.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new StaffDetails(staff.get());
    }
}
