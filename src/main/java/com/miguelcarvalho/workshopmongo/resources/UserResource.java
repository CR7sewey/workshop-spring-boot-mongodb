package com.miguelcarvalho.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.dto.UserDTO;
import com.miguelcarvalho.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping // equivalent to @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		// User maria = new User("1","Maria Brown","maria@gmail.com");
		// User alex = new User("2","Alex Green","alex@gmail.com");
		List<User> list = service.findAll(); // busca rno banco de dados a lista
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); // --> 200 no postman
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		User user = service.findById(id);
		UserDTO userDto = new UserDTO(user);
		return ResponseEntity.ok().body(userDto); // --> 404 no postman, nao pode dar o interno 500

	}
	
	@PostMapping(value="/{id}")
	public ResponseEntity<Void> insert(@RequestBody UserDTO obj) {

		User user = service.fromDTO(obj);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build(); // --> 201 e cabeçaljo com o uri!!, retorna vazio mas no header esta la o caminho
		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); // --> 204 --> no contant
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO obj,@PathVariable String id) {
		User user = service.fromDTO(obj);
		user.setId(id); // garantir que o obj tem o id da requesicao
		user = service.update(user);
		return ResponseEntity.noContent().build(); // --> 204 --> no contant
		
	}

}
