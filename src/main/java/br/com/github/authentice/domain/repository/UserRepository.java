package br.com.github.authentice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.github.authentice.domain.model.UserSystem;

@Repository
public interface UserRepository extends JpaRepository<UserSystem, Long>{

	UserSystem findByEmail(String email);

}
