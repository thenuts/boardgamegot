package nutis.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("common")
public interface CommonService extends RemoteService {

  InitializeResultDTO initialize();

  void createGame();
}
