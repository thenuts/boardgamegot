package nutis.client;

import java.util.HashMap;

import nutis.client.dto.InitializeResultDto;
import nutis.client.dto.KeyDto;
import nutis.client.dto.LoadGameResultDto;
import nutis.client.dto.PossibleOrdersResultDto;
import nutis.client.dto.RetornoPadraoDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CommonServiceAsync {

  void initialize(AsyncCallback<InitializeResultDto> callback);

  void createGame(AsyncCallback<RetornoPadraoDTO> callback);

  void loadGame(KeyDto keyDTO, AsyncCallback<LoadGameResultDto> callback);

  void getPossibleOrders(KeyDto gameKey, AsyncCallback<PossibleOrdersResultDto> callback);

  void sendOrders(KeyDto gameKey, HashMap<Integer, Integer> internalOrders, AsyncCallback<RetornoPadraoDTO> callback);
}
