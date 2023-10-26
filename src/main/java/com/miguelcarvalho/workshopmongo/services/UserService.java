package com.miguelcarvalho.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguelcarvalho.workshopmongo.domain.User;
import com.miguelcarvalho.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository; // mecanismo de injecao de dependencia automatica
	
	public List<User> findAll() {
		return repository.findAll();
	}

}
