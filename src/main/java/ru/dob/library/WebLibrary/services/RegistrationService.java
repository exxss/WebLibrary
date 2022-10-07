package ru.dob.library.WebLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.repositories.StaffRepository;

@Service
public class RegistrationService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setRole("ROLE_STAFF");
        staffRepository.save(staff);
    }
}
