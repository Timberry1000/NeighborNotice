package com.timsapp.neighbornotice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timsapp.neighbornotice.models.Friendships;

@Repository
public interface FriendshipRepo extends CrudRepository<Friendships, Long> {

}
