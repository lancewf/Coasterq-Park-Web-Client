package com.coasterq.client.view.enterwaittime;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubmitPagePanel extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private Label emptylabel1;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public SubmitPagePanel()
   {
      addStyleName("enterWaitTimePanel");
      
      add(createClosedLabel());
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
  
   public void setRideName(String rideName)
   {
      emptylabel1.setText(
         "Thank you for submitting a ride time for " + rideName + ".");
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private Widget createClosedLabel()
   {
      HorizontalPanel labelPanel = new HorizontalPanel();
      
      emptylabel1 = new Label("");
      
      labelPanel.add(emptylabel1);
      
      labelPanel.addStyleName("enterWaitTimeSubPanel");
      
      return labelPanel;
   }
}
