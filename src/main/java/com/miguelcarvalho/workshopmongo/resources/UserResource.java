package com.miguelcarvalho.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping // equivalent to @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		//User maria = new User("1","Maria Brown","maria@gmail.com");
		//User alex = new User("2","Alex Green","alex@gmail.com");
		List<User> list = service.findAll(); // busca rno banco de dados a lista
		return ResponseEntity.ok().body(list); // --> 200 no postman
	}

}
