package ru.dob.library.WebLibrary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.services.StaffDetailsService;

@Controller
public class StaffValidator implements Validator {
    private final StaffDetailsService staffDetailsService;

    @Autowired
    public StaffValidator(StaffDetailsService staffDetailsService) {
        this.staffDetailsService = staffDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Staff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Staff staff = (Staff) o;

        try {
            staffDetailsService.loadUserByUsername(staff.getUsername());
            staffDetailsService.loadUserByFullName(staff.getFullName());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
    }
}
