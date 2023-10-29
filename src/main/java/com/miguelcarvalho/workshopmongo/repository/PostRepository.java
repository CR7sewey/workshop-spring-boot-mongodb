package com.miguelcarvalho.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.miguelcarvalho.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	// versao altrnativa a procurar logo no mongoDB, personalixado
	@Query("{ 'title': {$regex: ?0, $options: 'i'}}")  // consulata, json, ver link no pdf // title pq é o que eu quero ir ver, regex é o text o parametro (0) pq é unico, options i para ser case insensitive
	List<Post> searchTitle(String text);
}

