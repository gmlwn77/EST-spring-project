package com.estsoft.springproject.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.springproject.user.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	// select * from users where email = ${email};
	Optional<Users> findByEmail(String email);
}
