package net.javaguides.springboot.controller;

import net.javaguides.springboot.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import net.javaguides.springboot.models.User;

@Controller
public class AdminController {

	private  UserService userServiceEntityImpl;

	User user;

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

	@GetMapping("admin/{email}")
	public String sessionAdminInitializr(@PathVariable(value = "email") String email) {
		user = userServiceEntityImpl.getByEmail(email);
		return "redirect:/admin";
	}

	@GetMapping("admin")
	public String AdminPage(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user2 = (User) userDetails;
		StringBuilder sb = new StringBuilder();
		String firstRole = user2.getRoles().get(0).toString().substring(5);
		if(user2.getRoles().size() > 0){
			String secondRole = user2.getRoles().get(1).toString().substring(5);
			sb.append(firstRole + " " + secondRole);
			model.addAttribute("role", sb);
		}
		model.addAttribute("currentUser", userDetails);
		model.addAttribute("newUser", new User());
		model.addAttribute("users", userServiceEntityImpl.listUsers());
		return "admin";
	}


	@GetMapping("admin/users/remove/{id}")
	public String removeUser(@PathVariable Long id) {
		userServiceEntityImpl.removeUserById(id);
		return "redirect:/admin/users";
	}

	@PostMapping("admin/edit")
	public String editUser(@ModelAttribute("user") User user) {
		userServiceEntityImpl.updateUser(user);
		return "redirect:/admin";
	}

	@GetMapping("admin/users/edituser/{id}")
	public String updateUser(@PathVariable Long id, Model model){
		model.addAttribute("edituser", userServiceEntityImpl.getUserById(id));
		return "edit";
	}

	@GetMapping("/admin/users/new")
	public String create(Model model){
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping("admin/users/new")
	public String create(@ModelAttribute("user") User user){
		userServiceEntityImpl.addUser(user);
		return "redirect:/admin/";
	}

	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("toDelete", userServiceEntityImpl.getUserById(id));
		return "delete";
	}
	@GetMapping("delete")
	public String deleteUser(@ModelAttribute("user") User user) {
		userServiceEntityImpl.removeUserById(user.getId());
		return "redirect:/admin";
	}

	@GetMapping("user/{email}")
	public String sessionUserInitializr(@PathVariable(value = "email") String email) {
		user = userServiceEntityImpl.getByEmail(email);
		return "redirect:/user";
	}

	@GetMapping("/user")
	public String homePage(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user2 = (User) userDetails;
		StringBuilder sb = new StringBuilder();
		String firstRole = user2.getRoles().get(0).toString().substring(5);
		if(user2.getRoles().size() > 0){
			String secondRole = user2.getRoles().get(1).toString().substring(5);
			sb.append(firstRole + " " + secondRole);
			model.addAttribute("role", sb);
		}
		model.addAttribute("currentUser", userDetails);
		return "user";
	}
	@GetMapping("/userprofile")
	public String userProfile(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("currentUser", userDetails);
		return "userprofile";
	}
}