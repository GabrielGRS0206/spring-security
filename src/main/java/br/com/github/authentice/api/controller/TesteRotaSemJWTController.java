package br.com.github.authentice.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home_sem_token")
public class TesteRotaSemJWTController {

	@GetMapping 
	public String getUsers() {
		return "Gabriel,Bruna,Davi";
	}
}
