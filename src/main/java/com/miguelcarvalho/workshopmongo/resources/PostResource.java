package com.miguelcarvalho.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcarvalho.workshopmongo.domain.Post;
import com.miguelcarvalho.workshopmongo.resources.util.URL;
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
	
	// ver pdf com links para ver as hiposteses de keywords
	
	// buscar titulos com o texto la!
	@GetMapping(value="/titlesearch")  // busca no postman
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text",defaultValue="") String text) {  // value text para ser o que é inserido
		text = URL.decodeParam(text); //decodifica o text!
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value="/fullsearch")  // busca no postman
	public ResponseEntity<List<Post>> findSearch(
			@RequestParam(value="text",defaultValue="") String text, 
			@RequestParam(value="minDate",defaultValue="") String mindate,
			@RequestParam(value="maxDate",defaultValue="") String maxdate) {  // value text para ser o que é inserido
		text = URL.decodeParam(text); //decodifica o text!
		Date minDate = URL.convertDate(mindate, new Date(0L));
		Date maxDate = URL.convertDate(maxdate, new Date());
		
		List<Post> posts = service.fullSearch(text,minDate,maxDate);
		return ResponseEntity.ok().body(posts);
	}
	

}
