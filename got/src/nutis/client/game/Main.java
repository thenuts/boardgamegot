package nutis.client.game;

import java.util.Map;

import nutis.client.CommonService;
import nutis.client.CommonServiceAsync;
import nutis.client.DefaultAsyncCallback;
import nutis.client.dto.KeyDto;
import nutis.client.dto.LoadGameResultDto;
import nutis.client.dto.PieceDto;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Main extends Composite {

  @UiField
  Button planning;
  @UiField
  VerticalPanel units;
  @UiField
  HTMLPanel canvasPlace;
  @UiField
  Image map;
  @UiField
  Image footman;
  @UiField
  Image knigth;
  @UiField
  Image ship;
  int imageLoadCount;
  Canvas canvas;
  ImageElement imageMap;
  ImageElement imageFootman;
  ImageElement imageKnigth;
  ImageElement imageShip;
  private final CommonServiceAsync service = GWT.create(CommonService.class);
  private KeyDto gameKey;
  private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

  interface MainUiBinder extends UiBinder<Widget, Main> {
  }

  public Main(KeyDto gameKey) {
    initWidget(uiBinder.createAndBindUi(this));
    this.gameKey = gameKey;
    canvas = Canvas.createIfSupported();
    int width = 980;
    int height = 2000;
    canvas.setWidth(width + "px");
    canvas.setHeight(height + "px");
    canvas.setCoordinateSpaceWidth(width);
    canvas.setCoordinateSpaceHeight(height);
    canvasPlace.add(canvas);
  }

  @UiHandler("planning")
  void planningClick(ClickEvent event) {
    PopupPanel planningPopup = new PopupPanel(true);
    planningPopup.setWidth("400px");
    planningPopup.setHeight("200px");
    planningPopup.add(new Planning(gameKey));
    planningPopup.center();
  }

  @UiHandler(value = { "map", "footman", "knigth", "ship" })
  void imageLoad(LoadEvent event) {
    imageLoadCount++;
    if (imageLoadCount == 4) {
      final Context2d context = canvas.getContext2d();
      imageMap = (ImageElement) map.getElement().cast();
      imageFootman = (ImageElement) footman.getElement().cast();
      imageKnigth = (ImageElement) knigth.getElement().cast();
      imageShip = (ImageElement) ship.getElement().cast();
      context.drawImage(imageMap, 0, 0);
      service.loadGame(gameKey, new DefaultAsyncCallback<LoadGameResultDto>() {

        @Override
        public void onSuccess(LoadGameResultDto result) {
          for (PieceDto piece : result.getPieces()) {
            units.add(new Label(piece.getTerrain() + "-" + piece.getHouse() + "-" + piece.getPiecesText()));
            int i=0;
            //TODO migrar imagens estaticas para dinamicas
            //TODO colorir as peças
            for (Map.Entry<Integer, Integer> entry : piece.getPieces().entrySet()) {
              if (entry.getKey() == 1) {
                context.drawImage(imageFootman, piece.getX()+i, piece.getY());
              } else if (entry.getKey() == 2) {
                context.drawImage(imageKnigth, piece.getX()+i, piece.getY());
              } else {
                context.drawImage(imageShip, piece.getX()+i, piece.getY());
              }
              i+=10;
            }
          }
          // piece3.png
        }
      });
      
      
      
    }
  }
}
