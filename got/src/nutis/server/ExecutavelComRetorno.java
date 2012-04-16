package nutis.server;

import javax.persistence.EntityManager;

import nutis.client.dto.RetornoPadraoDTO;


public abstract class ExecutavelComRetorno {

  protected RetornoPadraoDTO retorno;
  protected EntityManager em;

  public abstract RetornoPadraoDTO execute();

  public RetornoPadraoDTO getRetorno() {
    return retorno;
  }

  public void setEntityManager(EntityManager em) {
    this.em=em;
  }
}