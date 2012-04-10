package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class ClashOfKings extends Composite {

  private static ClashOfKingsUiBinder uiBinder = GWT.create(ClashOfKingsUiBinder.class);

  interface ClashOfKingsUiBinder extends UiBinder<Widget, ClashOfKings> {
  }

  public ClashOfKings() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
