package com.miguelcarvalho.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.miguelcarvalho.workshopmongo.domain.Post;
import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.dto.AuthorDTO;
import com.miguelcarvalho.workshopmongo.dto.CommentDTO;
import com.miguelcarvalho.workshopmongo.repository.PostRepository;
import com.miguelcarvalho.workshopmongo.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); //london horario
		
		userRepository.deleteAll(); // apaga no mongoDb
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));// Tenho de salvar antes do post por causa da persistencia!! Isto que o authorDTO vai ligar diretamente ao mongo, para associar auhtor com post, usuarios com id proprio criado pelo mongo!! 
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia","Acordei feliz hoje!",new AuthorDTO(maria));
				
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
//		CommentDTO comment1 = new CommentDTO("Boa viagem mano!",post1);
//		CommentDTO comment2 = new CommentDTO("Aproveite!",post1);
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!",sdf.parse("21/03/2018"),new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite!",sdf.parse("22/03/2018"),new AuthorDTO(bob));
		post1.getComment().add(comment1);
		post1.getComment().add(comment2);
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!",sdf.parse("23/03/2018"),new AuthorDTO(alex));
		post2.getComment().add(comment3);
		postRepository.saveAll(Arrays.asList(post1,post2));
		
	}

}
