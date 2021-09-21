package br.com.github.authentice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.authentice.api.mapper.dto.ClientAuthenticeResponseDTO;
import br.com.github.authentice.api.mapper.dto.UserRequestDTO;
import br.com.github.authentice.domain.model.TokenJwt;
import br.com.github.authentice.domain.service.AuthenticeService;

@RestController
@RequestMapping("/api/v1/authentice")
public class AuthenticeController {

	@Autowired
    private AuthenticeService authentice;

    @PostMapping("/login")
    public ResponseEntity<Object> token(@RequestBody UserRequestDTO reqeuest){
        TokenJwt user = authentice.authentice(reqeuest);
        return ResponseEntity.ok(new ClientAuthenticeResponseDTO(user));
    }
}
