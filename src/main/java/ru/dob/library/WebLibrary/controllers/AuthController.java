package ru.dob.library.WebLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.services.RegistrationService;
import ru.dob.library.WebLibrary.util.StaffValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final StaffValidator staffValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, StaffValidator staffValidator) {
        this.registrationService = registrationService;
        this.staffValidator = staffValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("staff") Staff staff) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("staff") @Valid Staff staff,
                                      BindingResult bindingResult) {
        staffValidator.validate(staff, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(staff);

        return "redirect:/auth/login";
    }
}
