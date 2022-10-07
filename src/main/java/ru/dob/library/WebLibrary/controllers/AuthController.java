package ru.dob.library.WebLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.services.RegistrationService;
import ru.dob.library.WebLibrary.util.StaffValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}
