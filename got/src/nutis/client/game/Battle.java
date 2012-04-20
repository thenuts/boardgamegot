package nutis.client.game;

import nutis.client.dto.KeyDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.Widget;


public class Battle  extends DecoratedPopupPanel{

  private static BattleUiBinder uiBinder = GWT.create(BattleUiBinder.class);

  interface BattleUiBinder extends UiBinder<Widget, Battle> {
  }

  public Battle(KeyDto gameKey) {
    super(true);
    setWidget(uiBinder.createAndBindUi(this));
  }
}
