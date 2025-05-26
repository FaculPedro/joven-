package br.com.unicuritiba.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unicuritiba.models.User;
import br.com.unicuritiba.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> getAllUser() {
		return repository.findAll();
	}

	public User getUserById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public User saveUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return repository.save(user);
	}

	public void removeUser(Long id) {
		repository.deleteById(id);
	}

	public User updateUser(Long id, User user) {
		user.setId(id);
		return saveUser(user);
	}
	
	public User login(String cpf, String senha) {
	    return repository.findByCpf(cpf)
	        .filter(user -> passwordEncoder.matches(senha, user.getPassword()))
	        .orElse(null);
	}

}
