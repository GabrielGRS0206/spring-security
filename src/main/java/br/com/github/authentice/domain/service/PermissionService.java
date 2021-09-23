package br.com.github.authentice.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.authentice.domain.model.Permission;
import br.com.github.authentice.domain.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository repository;

	public List<Permission> findPermissionUserId(Long id) {
		List<Permission> list = new ArrayList<Permission>();

		return list;
	}
}
