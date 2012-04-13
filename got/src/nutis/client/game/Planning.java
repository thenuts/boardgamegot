package nutis.client.game;

import java.util.HashMap;

import nutis.client.CommonService;
import nutis.client.CommonServiceAsync;
import nutis.client.DefaultAsyncCallback;
import nutis.client.dto.KeyDto;
import nutis.client.dto.OrderDto;
import nutis.client.dto.PieceDto;
import nutis.client.dto.PossibleOrdersResultDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Planning extends Composite {

  @UiField
  ListBox orders;
  @UiField
  VerticalPanel lands;
  @UiField
  Button save;
  private HashMap<Integer, Integer> internalOrders = new HashMap<Integer, Integer>();
  private final CommonServiceAsync service = GWT.create(CommonService.class);
  private Object gameKey;
  private static PlanningUiBinder uiBinder = GWT.create(PlanningUiBinder.class);

  interface PlanningUiBinder extends UiBinder<Widget, Planning> {
  }
  class Terrain{
    PieceDto piece;
    OrderDto order;
  }

  public Planning(KeyDto gameKey) {
    initWidget(uiBinder.createAndBindUi(this));
    this.gameKey=gameKey;
    service.getPossibleOrders(gameKey, new DefaultAsyncCallback<PossibleOrdersResultDto>() {

      @Override
      public void onSuccess(final PossibleOrdersResultDto result) {
        //garate que apenas um item esteja selecionado paesar de multiselect
        orders.addChangeHandler(new ChangeHandler() {
          @Override
          public void onChange(ChangeEvent event) {
            orders.setSelectedIndex(orders.getSelectedIndex());
          }
        });
        for (OrderDto order : result.getOrders()) {
          orders.addItem(order.getName(), Integer.toString(order.getId()));
        }
        for (final PieceDto piece : result.getPieces()) {
          DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);
          panel.setHeight("30px");
          panel.setWidth("300px");
          final Label label = new Label(piece.getTerrain() + " - " + piece.getPiecesText());
          panel.addWest(label, 250);
          final Button button = new Button("<<");
          button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
              if (button.getText().equals("<<")) {
                if (orders.getSelectedIndex() != -1) {
                  int id = Integer.parseInt(orders.getValue(orders.getSelectedIndex()));
                  internalOrders.put(piece.getTerrainId(), id);
                  button.setText(">>");
                  label.setText(label.getText()+" - "+orders.getItemText(orders.getSelectedIndex()));
                  orders.removeItem(orders.getSelectedIndex());
                }
              }
              else {
                button.setText("<<");
                int id = internalOrders.get(piece.getTerrainId());
                internalOrders.remove(piece.getTerrainId());
                for (OrderDto order : result.getOrders()) {
                  if(order.getId()==id){
                    orders.addItem(order.getName(), Integer.toString(order.getId()));
                    label.setText(piece.getTerrain() + " - " + piece.getPiecesText());
                    break;
                  }
                }
              }
            }
          });
          panel.addEast(button, 30);
          lands.add(panel);
        }
      }
    });
  }

  @UiHandler("save")
  void saveClick(ClickEvent e) {
//    service.sendOrders(gameKey, new DefaultAsyncCallback<Void>() {
//
//      @Override
//      public void onSuccess(Void result) {
//        // TODO fechar popup
//        
//      }
//      
//    });
  }
}
