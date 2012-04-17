package nutis.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nutis.client.dto.RetornoPadraoDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Business {

  final static Logger logger = LoggerFactory.getLogger(Business.class);
  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");

  private Business() {
  }
  
  /**
   * <p>
   * Método requisitados para operações com mensagem e outros dados que outros dados que forem necessários.
   * </p>
   * <p>
   * Para isso, uma nova classe deverá ser criado com essas propriedades, herdando de {@link RetornoPadraoDTO}
   * </p>
   * 
   * @param executavel
   *          - Classe anômica com o método que irá executar
   * @return {@link RetornoPadraoDTO}
   */
  //TODO estranho esse uncecked pois o RetornoPadraoDTO esta no extends do T 
  @SuppressWarnings("unchecked")
  public static <T extends RetornoPadraoDTO> T execute(ExecutavelComRetorno run) {
    EntityManager em = null;
    try {
      em = emfInstance.createEntityManager();
      run.setEntityManager(em);
      run.execute();
    } catch (Throwable e) {
      if(e.getMessage()!=null){
        run.getRetorno().setMensagemDeErro(e.getMessage().split("\n"));
      }
      //TODO logger não esta funcionando no ambiente de desenvolvimento, analisar 
      System.out.println(e.getMessage());
      logger.error(e.getMessage(), e);
    } finally {
      if (em != null) {
        if (em.getTransaction().isActive()) {
          em.getTransaction().rollback();
        }
        em.close();
      }
    }
    return (T) run.getRetorno();
  }
}