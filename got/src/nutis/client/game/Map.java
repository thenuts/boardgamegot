package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Map extends Composite {

  private static MapUiBinder uiBinder = GWT.create(MapUiBinder.class);

  interface MapUiBinder extends UiBinder<Widget, Map> {
  }

  public Map() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
