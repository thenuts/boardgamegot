package nutis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Got implements EntryPoint {

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    // Use RootPanel.get() to get the entire body element
    greetingService.greetServer(null, new AsyncCallback<String>() {
      
      @Override
      public void onSuccess(String result) {
        RootLayoutPanel.get().add(new Label(result));
      }
      
      @Override
      public void onFailure(Throwable caught) {
        Window.alert(caught.getMessage());
      }
    });
  }
}
