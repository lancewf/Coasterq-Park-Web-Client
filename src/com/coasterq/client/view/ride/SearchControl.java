package com.coasterq.client.view.ride;

import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.data.RideSearchManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchControl extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private SearchFieldControl searchFieldControl;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public SearchControl(RideSearchManager rideSearchManager, 
                        ParkInformation parkInformation)
   {
      initialize(rideSearchManager, parkInformation);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void initialize(RideSearchManager rideSearchManager, 
                           ParkInformation parkInformation) 
   {
      addStyleName("searchControl");
      
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      searchFieldControl = new SearchFieldControl(
         rideSearchManager, parkInformation);
      
      VerticalPanel searchButtonPanel = new VerticalPanel();
      
      final Button searchButton = new Button("Hide Search");
      
      searchButton.setStyleName("cq-Button");
      
      searchButton.addClickHandler(new ClickHandler()
      {
         @Override
         public void onClick(ClickEvent event)
         {
            if(searchFieldControl.isVisible())
            {
               searchFieldControl.setVisible(false);
               searchButton.setText("Show Search");
            }
            else
            {
               searchFieldControl.setVisible(true);
               searchButton.setText("Hide Search");
            }
         }

      });
      
      searchButtonPanel.add(searchButton);
      
      add(searchButtonPanel);
      add(searchFieldControl);
   }
}
