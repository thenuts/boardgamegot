package nutis.client.game;

import nutis.client.KeyDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Main extends Composite {

  private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

  interface MainUiBinder extends UiBinder<Widget, Main> {
  }

  public Main(KeyDTO keyDTO) {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
