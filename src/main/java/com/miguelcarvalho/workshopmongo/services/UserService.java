package com.miguelcarvalho.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.dto.UserDTO;
import com.miguelcarvalho.workshopmongo.repository.UserRepository;
import com.miguelcarvalho.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository; // mecanismo de injecao de dependencia automatica

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {

		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public User insert(User user) {
		return repository.insert(user);
		
	}
	
	public void delete(String id) {
		findById(id); // para tratar excecao, faz a busca priemiro
		repository.deleteById(id);
		
	}
	
	public User fromDTO(UserDTO userDto) {  // instanicar usuario a partir do dto, fizemos aqui em vez de no userDto, para instanciar o user podemos querer instanciar o banco de dados, e quem ja tem a dependcia para o banco de dados é o user service
		User user = new User(userDto.getId(),userDto.getName(),userDto.getEmail());
		return user;
	}
	

	

}
