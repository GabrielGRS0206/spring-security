package br.com.github.authentice.domain.exception.handler;

public enum TipoErro {

	ERRO_DE_SISTEMA("/erro-de-sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso"),
	ERRO_NEGOCIO("/erro-negocio");

	private String title;

	TipoErro(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
