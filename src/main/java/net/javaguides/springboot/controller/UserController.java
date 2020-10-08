//package net.javaguides.springboot.controller;
//
//import net.javaguides.springboot.models.User;
//import net.javaguides.springboot.service.UserService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//
//@Controller
//public class UserController {
//    private UserService userServiceEntityImpl;
//    private User user;
//    public UserController(UserService userServiceEntityImpl) {
//        this.userServiceEntityImpl = userServiceEntityImpl;
//    }
//
//    @GetMapping("user/{email}")
//    public String sessionUserInitializr(@PathVariable(value = "email") String email) {
//        user = userServiceEntityImpl.getByEmail(email);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/user")
//    public String homePage(Model model) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("currentUser", user);
//        return "user";
//    }
//
//    @GetMapping("/index")
//    public String indexhtml(){
//        return "index";
//    }
//}
