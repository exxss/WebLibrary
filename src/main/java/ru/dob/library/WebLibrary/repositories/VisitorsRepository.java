package ru.dob.library.WebLibrary.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dob.library.WebLibrary.models.Person;


import java.util.Optional;
@Repository
public interface VisitorsRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
 }
