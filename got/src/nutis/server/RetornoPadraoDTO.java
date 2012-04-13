package nutis.server;

import java.io.Serializable;

public class RetornoPadraoDTO implements Serializable {

	private static final long serialVersionUID = -5491232383893723618L;
	
	private String[] mensagensDeErro;

	public String[] getMensagemDeErro() {
		return mensagensDeErro;
	}

	public void setMensagemDeErro(String[] mensagensDeErro) {
		this.mensagensDeErro = mensagensDeErro;
	}

}
