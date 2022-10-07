package ru.dob.library.WebLibrary.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dob.library.WebLibrary.models.Visitor;
import ru.dob.library.WebLibrary.services.VisitorsService;


@Component
public class VisitorValidator implements Validator {


    private final VisitorsService visitorsService;
    @Autowired
    public VisitorValidator(VisitorsService visitorsService) {
        this.visitorsService = visitorsService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Visitor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Visitor visitor = (Visitor) o;

        if(visitorsService.getPersonByFullName(visitor.getFullName()).isPresent())

        errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
    }
}



