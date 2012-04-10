package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Mustering extends Composite {

  private static MusteringUiBinder uiBinder = GWT.create(MusteringUiBinder.class);

  interface MusteringUiBinder extends UiBinder<Widget, Mustering> {
  }

  public Mustering() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
