package com.coasterq.client.view;

import com.coasterq.client.data.ParkInformation;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ParkTitlePanel extends VerticalPanel
{
   public ParkTitlePanel(ParkInformation parkInformation)
   {
      addStyleName("parkTitlePanel");
      
      Label parkTitle = new Label(parkInformation.getParkName());
      
      parkTitle.addStyleName("parkTitle");

      add(parkTitle);      
   }
}
