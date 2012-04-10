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
//  Canvas canvas = Canvas.createIfSupported();
//  Context2d context = canvas.getContext2d();
//  context.setStrokeStyle(CssColor.make(0, 255, 0));
//  context.strokeRect(10, 10, 50, 50);
//  context.setFillStyle(CssColor.make(255, 0, 0));
//  context.fillRect(60, 10, 50, 50);
//  context.setGlobalAlpha(0.5);
//  context.setFillStyle(CssColor.make(0, 0, 255));
//  context.fillRect(25, 10, 50, 100);
//  canvas.addClickHandler(new ClickHandler() {
//    
//    @Override
//    public void onClick(ClickEvent event) {
//      Window.alert(event.getClientX()+","+event.getClientY());
//    }
//  });
//  RootLayoutPanel.get().add(canvas);    
    
    // Use RootPanel.get() to get the entire body element
//    greetingService.greetServer(null, new AsyncCallback<String>() {
//      
//      @Override
//      public void onSuccess(String result) {
//        RootLayoutPanel.get().add(new Label(result));
//      }
//      
//      @Override
//      public void onFailure(Throwable caught) {
//        Window.alert(caught.getMessage());
//      }
//    });
  }
}
