package ru.dob.library.WebLibrary.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.repositories.StaffRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff findOne(int id) {
        Optional<Staff> foundPerson = staffRepository.findById(id);
        return foundPerson.orElse(null);

    }
    @Transactional
    public void update(int id, Staff updateStaff) {
        updateStaff.setPassword(passwordEncoder.encode(updateStaff.getPassword()));
        updateStaff.setId(id);
        staffRepository.save(updateStaff);
    }

    @Transactional
    public void delete(int id) {
        staffRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, String selectedRole) {
        staffRepository.findById(id).ifPresent(
                staff -> {
                    staff.setRole(selectedRole);
                }
        );
    }

}



