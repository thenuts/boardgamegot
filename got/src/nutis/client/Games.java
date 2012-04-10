package nutis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Games extends Composite {

  private static GamesUiBinder uiBinder = GWT.create(GamesUiBinder.class);

  interface GamesUiBinder extends UiBinder<Widget, Games> {
  }

  public Games() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
