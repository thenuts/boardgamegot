package nutis.client.game;

import nutis.client.KeyDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;


public class Main extends Composite {
  @UiField
  Button planning;
  private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

  interface MainUiBinder extends UiBinder<Widget, Main> {
  }

  public Main(KeyDTO keyDTO) {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  @UiHandler("planning")
  void planningClick(ClickEvent event){
    PopupPanel planningPopup = new PopupPanel(true);
    planningPopup.add(new Planning());
    planningPopup.center();
  }
}
