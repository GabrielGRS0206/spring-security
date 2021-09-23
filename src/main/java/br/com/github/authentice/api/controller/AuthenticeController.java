package br.com.github.authentice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.authentice.api.mapper.dto.ClientAuthenticeResponseDTO;
import br.com.github.authentice.api.mapper.dto.UserRequestDTO;
import br.com.github.authentice.domain.model.TokenJwt;
import br.com.github.authentice.domain.service.AuthenticeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/authentice")
public class AuthenticeController {

	@Autowired
    private AuthenticeService authentice;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
	@ApiOperation("Autenticação")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Autenticado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
    public ResponseEntity<Object> token(@RequestBody UserRequestDTO reqeuest){
        TokenJwt user = authentice.authentice(reqeuest);
        return ResponseEntity.ok(new ClientAuthenticeResponseDTO(user));
    }
}
