package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Defense extends Composite {

  private static DefenseUiBinder uiBinder = GWT.create(DefenseUiBinder.class);

  interface DefenseUiBinder extends UiBinder<Widget, Defense> {
  }

  public Defense() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
