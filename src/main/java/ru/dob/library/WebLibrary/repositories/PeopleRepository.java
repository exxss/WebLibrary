package ru.dob.library.WebLibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dob.library.WebLibrary.models.Staff;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Repository
public interface PeopleRepository extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByUsername(String username);
}