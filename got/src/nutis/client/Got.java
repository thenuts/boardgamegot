package nutis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Got implements EntryPoint {

//  /**
//   * Create a remote service proxy to talk to the server-side Greeting service.
//   */
//  private final CommonServiceAsync greetingService = GWT.create(CommonService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    RootLayoutPanel.get().add(new Games());   
  }
}
