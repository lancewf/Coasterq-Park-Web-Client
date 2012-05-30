package com.coasterq.client.view.ride;

import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.Ride;
import com.finfrock.client.DataChangeListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AddToItineraryControl extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private Label inQText = new Label("in Q");
   private Button addToQueButton = new Button("Q");
   private ItineraryRideManagerable itineraryRideManager;
   private Ride ride;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public AddToItineraryControl(ItineraryRideManagerable itineraryRideManager, 
                                Ride ride)
   {
      this.ride = ride;
      this.itineraryRideManager = itineraryRideManager;
      
      initialize();
      
      itineraryRideManager.addDataChangeListener(
         new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            updateButton();
         }
      });
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void initialize()
   {
      inQText.addStyleName("cq-Label");
      addToQueButton.setTitle("Add the '" + ride.getName() + 
      "' ride to the Q");
      
      addToQueButton.setStyleName("cq-Q-Button");
      
      addToQueButton.addClickHandler(new ClickHandler()
      {
         @Override
         public void onClick(ClickEvent event)
         {
            itineraryRideManager.addNewItineraryRide(ride);
            
            remove(addToQueButton);
            add(inQText);
         }
      });
      
      updateButton();
   }
   
   private void updateButton()
   {
      if (itineraryRideManager.doesContainRide(ride))
      {
         remove(addToQueButton);
         add(inQText);
      }
      else
      {
         remove(inQText);
         add(addToQueButton);
      }
   }
}
