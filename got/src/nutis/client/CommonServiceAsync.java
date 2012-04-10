package nutis.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CommonServiceAsync {

  void initialize(AsyncCallback<InitializeResultDTO> callback);

  void createGame(AsyncCallback<Void> callback);
}
