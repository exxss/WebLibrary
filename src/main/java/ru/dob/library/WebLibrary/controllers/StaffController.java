package ru.dob.library.WebLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dob.library.WebLibrary.models.Staff;
import ru.dob.library.WebLibrary.models.Visitor;
import ru.dob.library.WebLibrary.services.AdminService;
import ru.dob.library.WebLibrary.services.RegistrationService;
import ru.dob.library.WebLibrary.util.StaffValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffValidator staffValidator;
    private final RegistrationService registrationService;
    private final AdminService adminService;

    @Autowired
    public StaffController(StaffValidator staffValidator, RegistrationService registrationService, AdminService adminService) {
        this.staffValidator = staffValidator;
        this.registrationService = registrationService;
        this.adminService = adminService;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("staff") Staff staff) {
        return "staff/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("staff") @Valid Staff staff,
                                      BindingResult bindingResult) {
        staffValidator.validate(staff, bindingResult);

        if (bindingResult.hasErrors())
            return "/staff/registration";

        registrationService.register(staff);

        return "redirect:/staff";
    }
    /////
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("staff", adminService.findOne(id));
        return "staff/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("staff") @Valid Staff staff, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "staff/edit";

        adminService.update(id, staff);
        return "redirect:/staff";
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("staffs", adminService.findAll());
        return "staff/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("staff",adminService.findOne(id));
        return "staff/show";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        adminService.delete(id);
        return "redirect:/staff";
    }
    @PatchMapping("/{id}/assign")
    public @ResponseBody String assign(@PathVariable("id") int id,@ModelAttribute("staff") String selectedRole){
        adminService.assign(id, selectedRole);
        return "redirect:/staff/" + id;
    }
}
