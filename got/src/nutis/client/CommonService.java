package nutis.client;

import java.util.HashMap;

import nutis.client.dto.InitializeResultDto;
import nutis.client.dto.KeyDto;
import nutis.client.dto.LoadGameResultDto;
import nutis.client.dto.PossibleOrdersResultDto;
import nutis.client.dto.RetornoPadraoDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("common")
public interface CommonService extends RemoteService {

  InitializeResultDto initialize();

  RetornoPadraoDTO createGame();

  LoadGameResultDto loadGame(KeyDto gameKey);

  PossibleOrdersResultDto getPossibleOrders(KeyDto gameKey);

  RetornoPadraoDTO sendOrders(KeyDto gameKey, HashMap<Integer, Integer> internalOrders);
}
