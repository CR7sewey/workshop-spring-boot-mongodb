package com.miguelcarvalho.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelcarvalho.workshopmongo.domain.Post;
import com.miguelcarvalho.workshopmongo.repository.PostRepository;
import com.miguelcarvalho.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository; // mecanismo de injecao de dependencia automatica
/*
	public List<Post> findAll() {
		return repository.findAll();
	}*/

	public Post findById(String id) {

		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	
	

}
