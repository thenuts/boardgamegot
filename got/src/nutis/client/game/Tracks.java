package nutis.client.game;

import nutis.client.dto.HouseTrackDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;


public class Tracks extends Composite {
  @UiField
  FlexTable tracks;
  private static TracksUiBinder uiBinder = GWT.create(TracksUiBinder.class);

  interface TracksUiBinder extends UiBinder<Widget, Tracks> {
  }
 
  
  public Tracks(HouseTrackDto[] houses) {
    initWidget(uiBinder.createAndBindUi(this));
    tracks.setText(0, 0, "");
    tracks.setText(1, 0, "Iron Throne");
    tracks.setText(2, 0, "Fiefdoms");
    tracks.setText(3, 0, "King´s Court");
    for(HouseTrackDto house:houses){
      tracks.getCellFormatter().getElement(1, house.getIronThrone()).getStyle().setProperty("backgroundColor", house.getHouseColor());
      tracks.getCellFormatter().getElement(2, house.getFiefdoms()).getStyle().setProperty("backgroundColor", house.getHouseColor());
      tracks.getCellFormatter().getElement(3, house.getCourt()).getStyle().setProperty("backgroundColor", house.getHouseColor());
      tracks.setText(3, house.getCourt(), Integer.toString(house.getStars()));
    }
  }
}
