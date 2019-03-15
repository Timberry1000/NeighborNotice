package com.timsapp.neighbornotice.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2,max=255,message="First Name must be between 2-255 characters.")
	private String firstName;
	
	@Size(min=2,max=255,message="Last Name must be between 2-255 characters.")
	private String lastName;
	
	@Email
	private String email;
	
	@Size(min=8,max=65,message="Password must be between 8-65 characters.")
	private String password;
	
	@Size(min=8,max=65,message="Confirm Password must be between 8-65 characters.")
	private String confirmPass;
	
	@ManyToMany
//	@JoinTable(name="USER_FRIENDSHIP")
	@JoinColumn(name="USER_ID"))
//	@JoinColumn(name="FRIEND_ID")}
	private Set<User>friends = new HashSet<User>();
	
	private boolean host;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	public boolean isHost() {
		return host;
	}
	public void setHost(boolean host) {
		this.host = host;
	}
}
