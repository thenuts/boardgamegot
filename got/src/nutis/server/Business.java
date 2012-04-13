package nutis.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Business {
  final static Logger logger = LoggerFactory.getLogger(Business.class);
  private Business(){
  }
  
//  /**
//   * Método requisitados para operações com mensagem de erro capturada na excessão
//   * @param executavel - Classe anômica com o método que irá executar
//   * @return {@link RetornoPadraoDTO}
//   */
//  public static RetornoPadraoDTO execute(Executavel executavel) {
//    RetornoPadraoDTO retorno = new RetornoPadraoDTO();
//    try {
//      executavel.execute();
//    } catch (RuntimeException e) {
//      retorno.setMensagemDeErro(e.getMessage().split("\n"));
//      logger.error(e.getMessage(), e);
//    }
//    return retorno;
//  }

  /**
   * <p>Método requisitados para operações com mensagem e outros dados que outros dados que forem necessários.</p>
   * <p>Para isso, uma nova classe deverá ser criado com essas propriedades, herdando de {@link RetornoPadraoDTO}</p>
   * @param executavel - Classe anômica com o método que irá executar
   * @return {@link RetornoPadraoDTO}
   */
  public static RetornoPadraoDTO execute(ExecutavelComRetorno run) {
    try {
      run.execute();
    } catch (RuntimeException e) {
      run.getRetorno().setMensagemDeErro(e.getMessage().split("\n"));
      logger.error(e.getMessage(), e);
    }
    return run.getRetorno();
  }
}