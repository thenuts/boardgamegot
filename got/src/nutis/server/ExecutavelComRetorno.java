package nutis.server;


public abstract class ExecutavelComRetorno {

  protected RetornoPadraoDTO retorno;

  // TODO Documentacao obrigatorio intanciar o retorno
  // TODO: Documetação, esta estrutura foi copiada pra a classe MenuServiceImpl, centralizar posteriormente
  public abstract RetornoPadraoDTO execute();

  public RetornoPadraoDTO getRetorno() {
    return retorno;
  }
}