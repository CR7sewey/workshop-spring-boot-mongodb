package com.miguelcarvalho.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.dto.UserDTO;
import com.miguelcarvalho.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping // equivalent to @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		//User maria = new User("1","Maria Brown","maria@gmail.com");
		//User alex = new User("2","Alex Green","alex@gmail.com");
		List<User> list = service.findAll(); // busca rno banco de dados a lista
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); // --> 200 no postman
	}

}
