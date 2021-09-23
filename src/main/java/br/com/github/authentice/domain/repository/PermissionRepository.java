package br.com.github.authentice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.github.authentice.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
}
