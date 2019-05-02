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
public class UserController {
	UserService uS;
	
	public 	UserController(UserService uS) {
		this.uS = uS;
	}
	
	@RequestMapping("/dash")
	public String showDashboard(HttpSession session) {
		session.invalidate();
		return "Dashboard.jsp";
	}
	
	@GetMapping("/login")
	public String Login(@ModelAttribute("user") User user, Model model, HttpSession session) {
		session.invalidate();

		return "login.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String Loginform( @RequestParam("email") String email,@RequestParam("password") String password, Model model,HttpSession session) {
		if(email.length()<1) {
			model.addAttribute("loginError", "Please enter an email address ");
			model.addAttribute("user", new User());
			return "login.jsp";
		}
		if(password.length()<8) {
			model.addAttribute("loginError", "Please enter a password");
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
	public String register(@Valid @ModelAttribute("user") User user, BindingResult res, Model model, HttpSession session) {
		if(res.hasErrors()) {
			return "register.jsp";
		}else {
			
			if(!user.getPassword().equals(user.getConfirmPass())) {
				System.out.println("error is password");
				model.addAttribute("userError","Password and Confirmed Password do not match");
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
		Long id = (Long)session.getAttribute("user");
		if(id == null) {
			return "redirect:/dash";
		}else {
			User u = uS.findById(id);
			model.addAttribute("user", u);
			model.addAttribute("allUsers", uS.findAll());
			return "homePage.jsp";
		}
	}
}
