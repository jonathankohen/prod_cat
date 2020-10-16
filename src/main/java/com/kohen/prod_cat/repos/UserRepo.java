package com.kohen.prod_cat.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kohen.prod_cat.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
