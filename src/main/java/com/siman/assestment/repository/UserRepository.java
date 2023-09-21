package com.siman.assestment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siman.assestment.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(final String username);
	Optional<User> findByPhoneNumber(final String phoneNumber);
    Optional<User> findByEmail(final String email);

}
