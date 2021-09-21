package br.com.github.authentice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.authentice.domain.model.Permissao;

public interface Permissoes extends JpaRepository<Permissao, Long> {
	
}
