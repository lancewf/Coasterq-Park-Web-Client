package com.coasterq.client.view.myqueue;

import com.coasterq.client.data.ItineraryRide;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.ItineraryRideSorterManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ItineraryRideDeleteButton extends Button implements ClickHandler
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private ItineraryRideManagerable itineraryRideSorterManager;
   private ItineraryRide itineraryRide;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ItineraryRideDeleteButton(
                          ItineraryRideManagerable itineraryRideManagerable, 
                          ItineraryRide itineraryRide)
   {
      super("X");

      this.itineraryRideSorterManager = itineraryRideManagerable;
      this.itineraryRide = itineraryRide;
      
      initalize();
   }

   // --------------------------------------------------------------------------
   // ClickHandler Members
   // --------------------------------------------------------------------------
   
   @Override
   public void onClick(ClickEvent event)
   {
      itineraryRideSorterManager.removeItineraryRide(itineraryRide);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void initalize()
   {
      setTitle("Remove the '" + 
         itineraryRide.getRide().getName() + "' ride from the Q");
      
      setStyleName("cq-Q-Button");

      addClickHandler(this);
   }
}
