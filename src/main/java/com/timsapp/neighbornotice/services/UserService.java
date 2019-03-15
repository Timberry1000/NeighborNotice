package com.timsapp.neighbornotice.services;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.timsapp.neighbornotice.models.User;
import com.timsapp.neighbornotice.repositories.UserRepo;


@Service
public class UserService {
	private UserRepo uR;
	
	public UserService(UserRepo uR){
		this.uR = uR;
	}
	
	public void create(User	user) {
		uR.save(user);
	}
	
	public ArrayList<User> findAll(){
		return (ArrayList<User>)uR.findAll();
	}
	
	public User findById(Long id) {
		return uR.findById(id).get();
	}
	
	public User findByEmail(String email) {
		return uR.findByEmail(email);
	}
	
	public void logout(HttpSession session, Long id) {
		session.setAttribute("id", null);
	}
	
	public User update(User user) {
		return uR.save(user);
	}
	
	public void destroy(Long id) {
		uR.deleteById(id);
	}
	
	public void destroy(User user) {
		uR.delete(user);
	}

}
