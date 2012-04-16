package nutis.client.component;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListBox2 extends Composite {

  @UiField
  ScrollPanel scrollPanel;
  @UiField
  VerticalPanel verticalPanel;
  Label selectedLabel;
  String selectedValue;
  String selectedText;
  HashMap<String, Label> labels = new HashMap<String, Label>();
  private static ListBox2UiBinder uiBinder = GWT.create(ListBox2UiBinder.class);

  interface ListBox2UiBinder extends UiBinder<Widget, ListBox2> {
  }

  public ListBox2() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setHeight(String height) {
    super.setHeight(height);
    scrollPanel.setHeight(height);
  }

  public void setWidth(String width) {
    super.setWidth(width);
    scrollPanel.setWidth(width);
  }

  public void addItem(final String item, final String value) {
    final Label label = new Label(item);
    labels.put(value, label);
    label.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        // //WRONG!
        // myWidget.getElement().getStyle().setProperty("background-color", "red");
        // // RIGHT
        // myWidget.getElement().getStyle().setProperty("backgroundColor", "red");
        // TODO resgatar background e cor original
        if (selectedLabel != null) {
          selectedLabel.getElement().getStyle().setProperty("backgroundColor", "white");
          selectedLabel.getElement().getStyle().setProperty("color", "black");
        }
        selectedLabel = label;
        selectedText = item;
        label.getElement().getStyle().setProperty("backgroundColor", "#3399FF");
        label.getElement().getStyle().setProperty("color", "white");
        selectedValue = value;
      }
    });
    verticalPanel.add(label);
  }

  public String getSelectedValue() {
    return selectedValue;
  }

  public String getSelectedText() {
    return selectedText;
  }

  public void removeSelected() {
    verticalPanel.remove(selectedLabel);
    labels.remove(selectedValue);
    selectedValue = null;
    selectedText = null;
  }

  public void removeByValue(String string) {
    Label value = labels.get(string);
    if (value != null) {
      verticalPanel.remove(value);
      labels.remove(string);
    }
  }
}
