package com.timsapp.neighbornotice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Friendships {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	

}
