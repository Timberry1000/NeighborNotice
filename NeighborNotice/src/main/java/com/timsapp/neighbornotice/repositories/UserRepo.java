package com.timsapp.neighbornotice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timsapp.neighbornotice.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	User findByEmail(String email);

}
