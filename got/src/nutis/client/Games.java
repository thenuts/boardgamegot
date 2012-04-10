package nutis.client;

import nutis.client.game.Main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;


public class Games extends Composite {
  private final CommonServiceAsync service = GWT.create(CommonService.class);
  private static GamesUiBinder uiBinder = GWT.create(GamesUiBinder.class);
  @UiField
  InlineLabel player;
  @UiField
  FlexTable items;
  @UiField
  Label mensage;
  @UiField
  Button createGame;
  
  interface GamesUiBinder extends UiBinder<Widget, Games> {
  }

  public Games() {
    initWidget(uiBinder.createAndBindUi(this));
    initialize();
  }

  private void initialize() {
    service.initialize( new DefaultAsyncCallback<InitializeResultDTO>() {
      
      @Override
      public void onSuccess(InitializeResultDTO result) {
        if(result==null){          
          throw new IllegalStateException("initialize return null");
        }
        
        player.setText(result.getPlayer());
        if("test@example.com".equals(result.getPlayer())){
          createGame.setVisible(true);
        }
        if(result.getGames()==null){
          mensage.setText("no games");
        }
        else{
          int i=0;
          for(final GameDTO game:result.getGames()){
            Anchor gameLink = new Anchor(game.getName());
            gameLink.addClickHandler(new ClickHandler() {              
              @Override
              public void onClick(ClickEvent event) {
                RootLayoutPanel.get().clear();
                RootLayoutPanel.get().add(new Main(game.getId()));
              }
            });
            items.setWidget(i, 0, gameLink);
            i++;
          }
        }
      }
      
    });
  }
  
  @UiHandler("createGame")
  void handleClick(ClickEvent e) {
    service.createGame( new DefaultAsyncCallback<Void>() {
      
      @Override
      public void onSuccess(Void result) {
        initialize();        
      }
    });
  }
}
