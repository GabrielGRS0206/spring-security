package br.com.github.authentice.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.authentice.domain.model.UserSystem;

@RestController
@RequestMapping("/home_com_token")
public class TesteRotaComJWTController {

	@GetMapping 
	public String getUsers(@AuthenticationPrincipal UserSystem cliente) {
		System.out.println("CHAMOU ROTA COM TOKEN..");
		
		System.out.println("USUARIO : "+cliente.getUsername());
		return "Rota com token...";
	}
	
	@GetMapping("/consultar")
	public String testeRota(@AuthenticationPrincipal UserSystem cliente) {
		System.out.println("CHAMOU ROTA COM TOKEN..");
		
		System.out.println("USUARIO : "+cliente.getUsername());
		return "Usuário foi passado no token e está tudo certo...";
	}

}
