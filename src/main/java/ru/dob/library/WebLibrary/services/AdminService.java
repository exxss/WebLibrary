package ru.dob.library.WebLibrary.services;

import org.hibernate.Hibernate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dob.library.WebLibrary.models.Book;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.models.Visitor;
import ru.dob.library.WebLibrary.repositories.StaffRepository;
import ru.dob.library.WebLibrary.repositories.VisitorsRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final StaffRepository staffRepository;

    public AdminService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_SOME_OTHER')")
    public void doAdminStuff() {
        System.out.println("Only admin here");
    }


    public List<Staff> findAll(){
        return staffRepository.findAll();
    }

    public Staff findOne(int id){
        Optional<Staff> foundPerson = staffRepository.findById(id);
        return foundPerson.orElse(null);

    }

    @Transactional
    public void update(int id, Staff updatePerson){
        updatePerson.setId(id);
        staffRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        staffRepository.deleteById(id);
    }
}



