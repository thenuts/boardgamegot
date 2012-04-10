package nutis.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;


public abstract class DefaultAsyncCallback<T> implements AsyncCallback<T> {

  @Override
  public void onFailure(Throwable caught) {
    Window.alert(caught.getMessage());
  }

}
