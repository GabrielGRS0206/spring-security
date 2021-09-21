package br.com.github.authentice.domain.exception.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.github.authentice.domain.exception.negocio.NegocioException;
import br.com.github.authentice.domain.exception.userException.DtoInvalidoException;
import br.com.github.authentice.domain.exception.userException.TokenInvalidoException;

@ControllerAdvice
public class JwtExceptionHandler extends ResponseEntityExceptionHandler {

	public static String camposInvalidos = "Campos inválidos";
	public static String recursoNaoEncontrado = "Recurso não encontrado";

	@Autowired
	private MessageSource messageSource;

	public static final String MSG_ERRO_PADRAO =
			"Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
					+ "o problema persistir, entre em contato com o administrador do sistema.";

	public static final String MSG_ERRO_ENTIDADE_EM_USO = "Não foi possível processar "
			+ "essa solicitação.";

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
		TipoErro problemType = TipoErro.ERRO_DE_SISTEMA;
		String detail = ExceptionUtils.getRootCauseMessage(ex);

		Erro problem = problema(status, problemType, detail);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ArrayList<Field> listaAtributo = new ArrayList<Field>();
		Field atributo = null;

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			atributo = new Field(nome, mensagem);
			listaAtributo.add(atributo);
		}

		Erro problema = new Erro();
		problema.setStatus(status.value());
		problema.setMensagem(camposInvalidos);
		problema.setDataHora(LocalDateTime.now());
		problema.setListaFields(listaAtributo);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler({ DtoInvalidoException.class })
	public ResponseEntity<Object> handleEntidadeValidaException(DtoInvalidoException ex, WebRequest request) {
		String mensagemUsuario = ex.getMessage(); // Exception usada quando utiliza dto
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

		Erro problema = new Erro();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setMensagem(camposInvalidos);
		problema.setDataHora(LocalDateTime.now());

		List<Field> listaFields = Arrays.asList(new Field(mensagemUsuario,mensagemDesenvolvedor));
		problema.setListaFields(listaFields);

		return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private Erro problema(HttpStatus status,TipoErro problemType, String detail) {
		return problema(status, problemType, detail,null);
	}

	private Erro problema(HttpStatus status,
			TipoErro problemType, String detail,String mensagemErro) {

		Erro erro = null;

		if(mensagemErro != null && !mensagemErro.isEmpty()) {
			erro = new Erro(status.value(), LocalDateTime.now(), detail, null);
		} else {
			erro = new Erro(status.value(), LocalDateTime.now(), detail,mensagemErro, null);
		}

		return erro;
	}


}
