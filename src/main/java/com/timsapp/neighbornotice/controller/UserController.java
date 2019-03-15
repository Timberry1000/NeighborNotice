package com.timsapp.neighbornotice.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.timsapp.neighbornotice.models.User;
import com.timsapp.neighbornotice.services.UserService;

@Controller
//@RequestMapping("/home")
public class UserController {
	UserService uS;
	
	@RequestMapping("/dash")
	public String showDashboard() {
		return "Dashboard.jsp";
	}
	
	@GetMapping("/login")
	public String Login(@ModelAttribute("user") User user, Model model, HttpSession session) {
		session.invalidate();
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String Loginform( @RequestParam("email") String email,@RequestParam("password") String password, Model model,HttpSession session) {
		if(email.length()<1) {
			model.addAttribute("loginError", "email too short! ");
			model.addAttribute("user", new User());
			return "login.jsp";
		}
		if(password.length()<8) {
			model.addAttribute("loginError", "password too short!");
			model.addAttribute("user", new User());
			return "login.jsp";
		}
		User u = uS.findByEmail(email);
		if(u == null) {
			model.addAttribute("loginError", "Email does not exist in our database ");
			model.addAttribute("user", new User());
			return "login.jsp";
		}else {
			boolean matches = BCrypt.checkpw(password, u.getPassword());
			
			if(matches) {
				session.setAttribute("user", u.getId());
				if(u.isHost()) {
					return "redirect:/home";
				}else {
					return "redirect:/home";
				}
			}else {
				model.addAttribute("loginError", "Invalid Credentials!");
				model.addAttribute("user", new User());
				return "login.jsp";
			}
		}
	}
	
	@GetMapping("/register")
	public String Register(@ModelAttribute("user") User user, Model model, HttpSession session) {
		session.invalidate();
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, Model model, BindingResult res) {
		if(res.hasErrors()) {
			return "register.jsp";
		}else {
			if(!user.getPassword().equals(user.getConfirmPass())) {
				model.addAttribute("userError","Password and Password Confirmation must match");
				return "register.jsp";
			}else {
				User exists = uS.findByEmail(user.getEmail());
				if(exists != null) {
					model.addAttribute("userError","A user with this email already exists!");
					return "register.jsp";
				}else {
					String pw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
					user.setPassword(pw);
					uS.create(user);
					return "redirect:/home";
				}
			}
		}
	}
	
	
	@GetMapping("/home")
	public String HomePage(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(user.getId() == null) {
			return "redirect:/dash";
		}
		return "homePage.jsp";
	}

}
