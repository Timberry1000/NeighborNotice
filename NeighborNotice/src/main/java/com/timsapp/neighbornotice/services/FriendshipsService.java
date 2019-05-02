package com.timsapp.neighbornotice.services;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.timsapp.neighbornotice.models.Friendships;
import com.timsapp.neighbornotice.repositories.FriendshipRepo;

@Service
public class FriendshipsService {
	private FriendshipRepo fR;
	
	public FriendshipsService(FriendshipRepo fR) {
		this.fR = fR;
	}
	
	public void create(Friendships friendships) {
		fR.save(friendships);
	}
	
	public ArrayList<Friendships> findAll(){
		return (ArrayList<Friendships>)fR.findAll();
	}
	
	public Friendships findById(Long id) {
		return fR.findById(id).get();
	}
	
//	public  findByEmail(String email) {
//		return uR.findByEmail(email);
//	}
	
	public void logout(HttpSession session, Long id) {
		session.setAttribute("id", null);
	}
	
	public Friendships update(Friendships friendship) {
		return fR.save(friendship);
	}
	
	public void destroy(Long id) {
		fR.deleteById(id);
	}
	
	public void destroy(Friendships friendship) {
		fR.delete(friendship);
	}
}
