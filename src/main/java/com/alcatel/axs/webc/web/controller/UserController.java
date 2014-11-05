package com.alcatel.axs.webc.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alcatel.axs.webc.persistence.entities.User;
import com.alcatel.axs.webc.persistence.repository.UserRepository;
import com.alcatel.axs.webc.web.cmd.NewUserCmd;



@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(method = GET)
	public List<User> listUsers() {

		List<User> rv = new ArrayList<User>();

//		User user = new User();
//		user.setId(1);
//		user.setName("User 1");
//		user.setDescription("User Description 1");
//
//		rv.add(user);
//
//		user = new User();
//		user.setId(2);
//		user.setName("User 2");
//		user.setDescription("User Description 2");
//
//		rv.add(user);

		rv = userRepository.findAll();

		return rv;
	}

	@RequestMapping(value = "{id:\\d+}", method = GET)
	public User getUser(int id) {
		User user = new User();
		user.setId(1);
		user.setName("User 1");
		user.setDescription("User Description 1");

		return user;
	}
	
	public User findByName(final String name)
	{
		return null;
	}

	@RequestMapping(method = POST)
	public ResponseEntity<User> createUser(
			final @RequestBody @Valid NewUserCmd newUserCmd,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new IllegalArgumentException("Invalid arguments.");

		ResponseEntity<User> rv;

		final User user = new User();
		user.setName(newUserCmd.getName());
		user.setDescription(newUserCmd.getDescription());


		try {
			this.userRepository.save(user);
			rv = new ResponseEntity<>(user, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			rv = new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return rv;
	}
}
