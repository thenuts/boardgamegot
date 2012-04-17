package nutis.client.game;

import java.util.ArrayList;
import java.util.HashMap;

import nutis.client.CommonService;
import nutis.client.CommonServiceAsync;
import nutis.client.DefaultAsyncCallback;
import nutis.client.component.ListBox2;
import nutis.client.dto.KeyDto;
import nutis.client.dto.OrderDto;
import nutis.client.dto.PieceDto;
import nutis.client.dto.PossibleOrdersResultDto;
import nutis.client.dto.RetornoPadraoDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Planning extends DecoratedPopupPanel {

  @UiField
  ListBox2 orders;
  @UiField
  VerticalPanel lands;
  @UiField
  Button save;
  // id do terreno e id da ordem
  private HashMap<Integer, Integer> internalOrders = new HashMap<Integer, Integer>();
  private ArrayList<OrderDto> ordersList;
  private final CommonServiceAsync service = GWT.create(CommonService.class);
  private KeyDto gameKey;
  private static PlanningUiBinder uiBinder = GWT.create(PlanningUiBinder.class);
  private int starUsed;
  @UiField
  HTMLPanel stars;
  @UiField
  InlineLabel startsCount;

  interface PlanningUiBinder extends UiBinder<Widget, Planning> {
  }

  class Terrain {

    PieceDto piece;
    OrderDto order;
  }

  public Planning(KeyDto gameKey) {
    super(true);
    setWidget(uiBinder.createAndBindUi(this));
    this.gameKey = gameKey;
    service.getPossibleOrders(gameKey, new DefaultAsyncCallback<PossibleOrdersResultDto>() {

      @Override
      public void onSuccess(final PossibleOrdersResultDto result) {
        startsCount.setText(Integer.toString(result.getStarOrders()));
        if(result.getStarOrders()==0){
          stars.setVisible(false);
        }
        ordersList = result.getOrders();
        for (OrderDto order : ordersList) {
          orders.addItem(order.getName(), Integer.toString(order.getId()));
        }
        for (final PieceDto piece : result.getPieces()) {
          DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);
          panel.setHeight("30px");
          panel.setWidth("280px");
          final Label label = new Label(piece.getPiecesText());
          panel.addWest(label, 250);
          final Button button = new Button("<<");
          button.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
              if (button.getText().equals("<<")) {
                if (orders.getSelectedValue() != null) {
                  int id = Integer.parseInt(orders.getSelectedValue());
                  internalOrders.put(piece.getTerrainId(), id);
                  button.setText(">>");
                  label.setText(label.getText() + " - " + orders.getSelectedText());
                  orders.removeSelected();
                  if (getOrderById(id).isStar()) {
                    starUsed++;
                    if (starUsed == result.getStarOrders()) {
                      for (OrderDto order : ordersList) {
                        if (order.isStar()) {
                          orders.removeByValue(Integer.toString(order.getId()));
                        }
                      }
                    }
                  }
                }
              } else {
                button.setText("<<");
                int id = internalOrders.get(piece.getTerrainId());
                OrderDto o = getOrderById(id);
                orders.addItem(o.getName(), Integer.toString(o.getId()));
                label.setText(piece.getPiecesText());
                if (getOrderById(id).isStar() && starUsed==result.getStarOrders()) {
                  for (OrderDto order : ordersList) {
                    if (order.isStar() && !isOrderInternal(order.getId())) {
                      orders.addItem(order.getName(), Integer.toString(order.getId()));
                    }
                  }
                }
                internalOrders.remove(piece.getTerrainId());
                starUsed--;
              }
            }

            private OrderDto getOrderById(int id) {
              OrderDto o = null;
              for (OrderDto order : ordersList) {
                if (order.getId() == id) {
                  o = order;
                  break;
                }
              }
              return o;
            }

            private boolean isOrderInternal(int id) {
              boolean result = false;
              for (int internalId : internalOrders.values()) {
                if (internalId == id) {
                  result = true;
                  break;
                }
              }
              return result;
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
    hide();
     service.sendOrders(gameKey,internalOrders, new DefaultAsyncCallback<RetornoPadraoDTO>() {
    
     @Override
     public void onSuccess(RetornoPadraoDTO result) {
     // TODO fechar popup
    
     }
    
     });
  }
}
