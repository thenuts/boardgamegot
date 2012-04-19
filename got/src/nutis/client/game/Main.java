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
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
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
  int imageLoadCount;
  Canvas canvas;
  Context2d context;
  Image[] imagePieces;
  ImageElement[] imageElementPieces;
  private final CommonServiceAsync service = GWT.create(CommonService.class);
  private KeyDto gameKey;
  private int pieceKindCount;
  private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
  private ImageElement imageMap;

  interface MainUiBinder extends UiBinder<Widget, Main> {
  }

  public Main(KeyDto gameKey) {
    initWidget(uiBinder.createAndBindUi(this));
    this.gameKey = gameKey;
    canvas = Canvas.createIfSupported();
    context = canvas.getContext2d();
    canvasPlace.add(canvas);
//    service.loadGame(gameKey, new DefaultAsyncCallback<LoadGameResultDto>() {
//
//      @Override
//      public void onSuccess(final LoadGameResultDto result) {
////        for (PieceDto piece : result.getPieces()) {
////          units.add(new Label( piece.getHouse() + "-" + piece.getPiecesText()));
////        }
//        pieceKindCount = result.getPieceKindCount();
//        imagePieces = new Image[pieceKindCount];
//        for (int i = 0; i < imagePieces.length; i++) {
//          imagePieces[i] = new Image("/images/piece" + (i+1)+".png");
//          imagePieces[i].addLoadHandler(new LoadHandler() {
//
//            @Override
//            public void onLoad(LoadEvent event) {
//              imageLoadCount++;
//              if (imageLoadCount == (pieceKindCount+1)) {
//                imageElementPieces = new ImageElement[pieceKindCount];
//                for (int i = 0; i < imagePieces.length; i++) {
//                  imageElementPieces[i] = (ImageElement) imagePieces[i].getElement().cast();
//                }
//                context.drawImage(imageMap, 0, 0);
//                for (PieceDto piece : result.getPieces()) {
//                  int i = 0;
//                  for (Map.Entry<Integer, Integer> entry : piece.getPieces().entrySet()) {
//                    context.drawImage(imageElementPieces[entry.getKey()-1], piece.getX() + i, piece.getY());
//                    i += 20;
//                  }
//                }
//              }
//            }
//          });
//          imagePieces[i].setVisible(false);
//          canvasPlace.add(imagePieces[i]);
//        }
//      }
//    });
  }

  @UiHandler("planning")
  void planningClick(ClickEvent event) {
    Planning planningPopup = new Planning(gameKey);
    planningPopup.center();
  }

  @UiHandler("map")
  void mapLoad(LoadEvent event) {
    imageLoadCount++;
    imageMap = (ImageElement) map.getElement().cast();
    int width = map.getWidth();
    int height = map.getHeight();
    canvas.setWidth(width + "px");
    canvas.setHeight(height + "px");
    canvas.setCoordinateSpaceWidth(width);
    canvas.setCoordinateSpaceHeight(height);
  }
}
