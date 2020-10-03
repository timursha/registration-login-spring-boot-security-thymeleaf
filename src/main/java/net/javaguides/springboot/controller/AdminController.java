package net.javaguides.springboot.controller;

import net.javaguides.springboot.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import net.javaguides.springboot.models.User;

@Controller
public class AdminController {

	private  UserService userServiceEntityImpl;

	public AdminController(UserService userServiceEntityImpl) {
		this.userServiceEntityImpl = userServiceEntityImpl;
	}

	@GetMapping("/")
	public String redirect(){
		return "redirect:/admin/users/";
	}

	@GetMapping(value = {"admin/users"})
	public String indexUsers(Model model){
		model.addAttribute("users", userServiceEntityImpl.listUsers());
		return "index_users";
	}

	@GetMapping("/admin")
	public String adminPage(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", userDetails);
		return "admin";
	}

	@GetMapping("admin/users/remove/{id}")
	public String removeUser(@PathVariable Long id) {
		userServiceEntityImpl.removeUserById(id);
		return "redirect:/admin/users";
	}

	@PostMapping("admin/users/edituser")
	public String updateUser(@ModelAttribute User user) {
		userServiceEntityImpl.updateUser(user);
		return "redirect:/admin/users";
	}

	@GetMapping("admin/users/edituser/{id}")
	public String updateUser(@PathVariable Long id, Model model){
		model.addAttribute("edituser", userServiceEntityImpl.getUserById(id));
		return "edituser";
	}

	@GetMapping("/admin/users/new")
	public String create(Model model){
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping("admin/users/new")
	public String create(@ModelAttribute("user") User user){
		userServiceEntityImpl.addUser(user);
		return "redirect:/admin/users";
	}
}
