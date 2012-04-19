package nutis.server;

import javax.persistence.EntityManager;

import nutis.client.dto.RetornoPadraoDTO;


public abstract class ExecutavelComRetorno {

  protected RetornoPadraoDTO retorno;

  public abstract RetornoPadraoDTO execute();

  public RetornoPadraoDTO getRetorno() {
    return retorno;
  }

}