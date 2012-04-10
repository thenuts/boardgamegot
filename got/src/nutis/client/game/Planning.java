package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Planning extends Composite {

  private static PlanningUiBinder uiBinder = GWT.create(PlanningUiBinder.class);

  interface PlanningUiBinder extends UiBinder<Widget, Planning> {
  }

  public Planning() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
