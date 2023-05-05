package ru.dob.library.WebLibrary.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dob.library.WebLibrary.security.StaffDetails;
import ru.dob.library.WebLibrary.services.AdminService;


@Controller
public class HelloController {
    private final AdminService adminService;

    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String hello(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       StaffDetails staffDetails = (StaffDetails) authentication.getPrincipal();
       model.addAttribute("fullNam4e",staffDetails.getStaff().getFullName());
       return "hello";
    }
}
