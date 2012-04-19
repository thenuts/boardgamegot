package nutis.client.game;

import nutis.client.dto.HouseTrackDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class SupplyTrack extends Composite {
  @UiField
  FlexTable track;

  private static SupplyTrackUiBinder uiBinder = GWT.create(SupplyTrackUiBinder.class);

  interface SupplyTrackUiBinder extends UiBinder<Widget, SupplyTrack> {
  }

  public SupplyTrack(HouseTrackDto[] houses, int[][] supplyTrack) {
    initWidget(uiBinder.createAndBindUi(this));

    int[] stackSize = new int[supplyTrack.length];
    int maxSize = 0;
    for (HouseTrackDto house : houses) {
      stackSize[house.getSupply()]++;
      if (stackSize[house.getSupply()] > maxSize) {
        maxSize = stackSize[house.getSupply()];
      }
    }
    for (HouseTrackDto house : houses) {
      int i = maxSize;
      while (track.getText(i, house.getSupply()).equals(" ")) {
        i--;
      }
      track.setText(i, house.getSupply(), " ");
      track.getCellFormatter().getElement(i, house.getSupply()).getStyle()
          .setProperty("backgroundColor", house.getHouseColor());
    }
    for (int r = 0; r < supplyTrack.length; r++) {
      for (int c = 0; c < supplyTrack[r].length; c++) {
        track.setText(c+maxSize+1, r, Integer.toString(supplyTrack[r][c]));
      }
    }
  }
}
