package ru.dob.library.WebLibrary.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.services.VisitorsDetailsService;


@Component
public class PersonValidator implements Validator {

    private final VisitorsDetailsService visitorsDetailsService;


    @Autowired
    public PersonValidator(VisitorsDetailsService visitorsDetailsService) {
        this.visitorsDetailsService = visitorsDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Staff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Staff staff = (Staff) o;

        try {
            visitorsDetailsService.loadUserByUsername(staff.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
    }
}



