package br.com.github.authentice.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.authentice.domain.model.User;
import br.com.github.authentice.domain.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User findByUserEmail(String email) {
		return repository.findByEmail(email);
	}

	public void update(User user) {
		repository.save(user);
	}

}
