package com.miguelcarvalho.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcarvalho.workshopmongo.domain.Post;
import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.dto.UserDTO;
import com.miguelcarvalho.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
/*
	@GetMapping 
	public ResponseEntity<List<Post>> findAll() {
		List<Post> list = service.findAll(); // busca rno banco de dados a lista
		//List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list); // --> 200 no postman
	}*/

	@GetMapping(value="/{id}") // este caminho equivale ao que ele faz!! no postman
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post post = service.findById(id);
		return ResponseEntity.ok().body(post); // --> 404 no postman, nao pode dar o interno 500

	}
	
	
	

}
