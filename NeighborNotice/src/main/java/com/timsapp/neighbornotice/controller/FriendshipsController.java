package com.timsapp.neighbornotice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.timsapp.neighbornotice.models.Friendships;
import com.timsapp.neighbornotice.models.User;
import com.timsapp.neighbornotice.services.FriendshipsService;
import com.timsapp.neighbornotice.services.UserService;

@Controller
public class FriendshipsController {
	UserService uS;
	FriendshipsService fS;
	
	public FriendshipsController(UserService uS, FriendshipsService fS) {
		this.uS = uS;
		this.fS = fS;
	}
	
	
	@GetMapping("/friends")
	public String friendsPage(HttpSession session, Model model, Friendships friends) {
		Long id = (Long)session.getAttribute("user");
		if(id == null) {
			return "/dash";
		}
		return "friendship.jsp";
	}
	@GetMapping("/user/{firstName}")
	public String userPage(@PathVariable("firstName") String firstName, @ModelAttribute("user") User user, HttpSession session, Model model, Friendships friends) {
		Long id = (Long)session.getAttribute("user");
		if(id == null) {
			return "/dash";
		}
		model.addAttribute("user", user);
		return "userPage.jsp";
	}
	
//	@PostMapping("/addFriend")
//	public String addFriend(@ModelAttribute("friendships") Friendships friendships,HttpSession session, Model model) {
//		Long id = (Long)session.getAttribute("user");
//		if(id == null) {
//			return "/dash";
//		}
//		fS.create(friendships);
//		User u = uS.findById(id);
//		model.addAttribute("user", u);
//		return "redirect: /home";
//	}
	
	
	
}
