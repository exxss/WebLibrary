package ru.dob.library.WebLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dob.library.WebLibrary.models.Book;
import ru.dob.library.WebLibrary.models.Visitor;
import ru.dob.library.WebLibrary.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear){
        if(sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }
    public List<Book> findWithPagination(Integer page,Integer booksPerPage,boolean sortByYear){
        if(sortByYear)
            return booksRepository.findAll(PageRequest.of(page,booksPerPage,Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
    }
    public Book findOne(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }
    public List<Book> searchByName(String query){
        return booksRepository.findByNameStartingWith(query);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id,Book updateBook){
        Book bookToBeUpdated = booksRepository.findById(id).get();
        updateBook.setId(id);
        updateBook.setOwner(bookToBeUpdated.getOwner());
        booksRepository.save(updateBook);
    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Visitor getBookOwner(int id){
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }
    @Transactional
    public void release(int id){
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
                });
    }

    @Transactional
    public void assign(int id, Visitor selectedVisitor){
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedVisitor);
                    book.setTakenAt(new Date());
                }
        );
    }
}

