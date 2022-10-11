package ru.dob.library.WebLibrary.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dob.library.WebLibrary.models.Book;
import ru.dob.library.WebLibrary.models.Visitor;
import ru.dob.library.WebLibrary.repositories.VisitorsRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VisitorsService {
    @Autowired
    public VisitorsService(VisitorsRepository visitorsRepository) {
        this.visitorsRepository = visitorsRepository;
    }

    private final VisitorsRepository visitorsRepository;


    public List<Visitor> findAll(){
        return visitorsRepository.findAll();
    }

    public Visitor findOne(int id){
        Optional<Visitor> foundPerson = visitorsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Visitor visitor){
        visitorsRepository.save(visitor);
    }

    @Transactional
    public void update(int id, Visitor updateVisitor){
        updateVisitor.setId(id);
        visitorsRepository.save(updateVisitor);
    }
    @Transactional
    public void delete(int id){
        visitorsRepository.deleteById(id);
    }

    public Optional<Visitor> getPersonByFullName(String fullName){
        return visitorsRepository.findByFullName(fullName);
    }

    public List<Book> getBookByPersonId(int id){
        Optional<Visitor> visitor = visitorsRepository.findById(id);
        if(visitor.isPresent()) {
            Hibernate.initialize(visitor.get().getBooks());
            visitor.get().getBooks().forEach(book -> {
                long diffInMillis = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                if (diffInMillis > 864000000)
                    book.setExpired(true);
            });
            return visitor.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}
