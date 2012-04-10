package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class WildlingAttack extends Composite {

  private static WildlingAttackUiBinder uiBinder = GWT.create(WildlingAttackUiBinder.class);

  interface WildlingAttackUiBinder extends UiBinder<Widget, WildlingAttack> {
  }

  public WildlingAttack() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
