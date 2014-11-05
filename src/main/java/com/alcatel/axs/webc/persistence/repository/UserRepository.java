package com.alcatel.axs.webc.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alcatel.axs.webc.persistence.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	public User findByName(final String name);
}
