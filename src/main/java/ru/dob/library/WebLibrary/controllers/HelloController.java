package ru.dob.library.WebLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dob.library.WebLibrary.security.StaffDetails;
import ru.dob.library.WebLibrary.services.AdminService;
import ru.dob.library.WebLibrary.services.BooksService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class HelloController {
    private final AdminService adminService;

    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

//    @GetMapping("/hello")
//    public String currentUserNameSimple(Model model, @PathVariable int id) {
//        model.addAttribute("staff", adminService.findOne(id));
//        return "hello";
//    }
    @GetMapping("/hello")
    public String hello(String fullName,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       StaffDetails staffDetails = (StaffDetails) authentication.getPrincipal();
       fullName = staffDetails.getStaff().getFullName();
       model.addAttribute("fullName",fullName);
       return "hello";
    }
}
