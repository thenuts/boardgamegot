package nutis.client.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class Planning extends Composite {

  @UiField
  FlexTable orders;
  @UiField
  Button save;
  
  private static PlanningUiBinder uiBinder = GWT.create(PlanningUiBinder.class);

  interface PlanningUiBinder extends UiBinder<Widget, Planning> {
  }

  public Planning() {
    initWidget(uiBinder.createAndBindUi(this));
    
    orders.setWidget(0, 0, new Label("Winterfell"));
    orders.setWidget(0, 1, new Label("2F 1K"));
    ListBox listBox = new ListBox(false);
    listBox.addItem("None");
    listBox.addItem("March +1 *");
    listBox.addItem("March +0 ");
    listBox.addItem("March -1 ");
    orders.setWidget(0, 2, listBox);
  }
}
