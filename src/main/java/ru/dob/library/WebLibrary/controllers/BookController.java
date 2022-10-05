package ru.dob.library.WebLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookController {
//    private final BookDAO bookDAO;
//    private final PersonDAO personDAO;
//
//
//    @Autowired
//    public BookController(BookDAO bookDAO,PersonDAO personDAO) {
//        this.bookDAO = bookDAO;
//        this.personDAO = personDAO;
//    }
//
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("books", bookDAO.index());
//        return "books/index";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person) {
//        model.addAttribute("books",bookDAO.show(id));
//
//        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
//
//        if(bookOwner.isPresent())
//            model.addAttribute("owner",bookOwner.get());
//        else
//            model.addAttribute("people",personDAO.index());
//
//        return "books/show";
//    }
//
//    @GetMapping("/new")
//    public String newBook(@ModelAttribute("books") Book book) {
//        return "books/new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("books") @Valid Book book,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "books/new";
//
//        bookDAO.save(book);
//        return "redirect:/books";
//    }
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("books", bookDAO.show(id));
//        return "books/edit";
//    }
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("books") @Valid Book book, BindingResult bindingResult,
//                         @PathVariable("id") int id) {
//        if (bindingResult.hasErrors())
//            return "books/edit";
//
//        bookDAO.update(id, book);
//        return "redirect:/books";
//    }
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        bookDAO.delete(id);
//        return "redirect:/books";
//    }
//
//    @PatchMapping("/{id}/release")
//    public String release(@PathVariable("id") int id){
//        bookDAO.release(id);
//        return "redirect:/books/" + id;
//    }
//    @PatchMapping("/{id}/assign")
//    public String assign(@PathVariable("id") int id,@ModelAttribute("person") Person selectedPerson){
//        bookDAO.assign(id,selectedPerson);
//        return "redirect:/books/" + id;
//    }

}
