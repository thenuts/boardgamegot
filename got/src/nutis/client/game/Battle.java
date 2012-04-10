package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Battle extends Composite {

  private static BattleUiBinder uiBinder = GWT.create(BattleUiBinder.class);

  interface BattleUiBinder extends UiBinder<Widget, Battle> {
  }

  public Battle() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
