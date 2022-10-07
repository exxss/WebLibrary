package ru.dob.library.WebLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dob.library.WebLibrary.models.Visitor;
import ru.dob.library.WebLibrary.services.VisitorsService;
import ru.dob.library.WebLibrary.util.VisitorValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class VisitorController {

    private final VisitorsService visitorsService;
    private final VisitorValidator visitorValidator;

    @Autowired
    public VisitorController(VisitorsService visitorsService, VisitorValidator visitorValidator) {
        this.visitorsService = visitorsService;
        this.visitorValidator = visitorValidator;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("visitors", visitorsService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("visitor", visitorsService.findOne(id));
        model.addAttribute("books", visitorsService.getBookByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("visitor") Visitor visitor) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("visitor") @Valid Visitor visitor,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        visitorsService.save(visitor);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("visitor", visitorsService.findOne(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("visitor") @Valid Visitor visitor, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        visitorsService.update(id, visitor);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        visitorsService.delete(id);
        return "redirect:/people";
    }
}
