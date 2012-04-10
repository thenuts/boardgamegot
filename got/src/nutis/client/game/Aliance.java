package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Aliance extends Composite {

  private static AlianceUiBinder uiBinder = GWT.create(AlianceUiBinder.class);

  interface AlianceUiBinder extends UiBinder<Widget, Aliance> {
  }

  public Aliance() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
