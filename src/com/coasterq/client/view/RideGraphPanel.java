package com.coasterq.client.view;

import java.util.List;

import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideManagerable;
import com.coasterq.client.data.RideSorter;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RideGraphPanel extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
  
   private RideManagerable rideManager;
   private RideGraph rideGraph;
   private ListBox rideCombobox;
  
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
  
   public RideGraphPanel(RideManagerable rideManager)
   {
      this.rideManager = rideManager;
      
      initialize();
   }

   private void initialize()
   {
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      rideGraph = new RideGraph();
      rideCombobox = createRideSelection();
      
      add(rideCombobox);
      add(rideGraph);
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
  
   public String getName()
   {
      return "Graph";
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
  
   private ListBox createRideSelection()
   {
      ListBox rideCombobox = new ListBox();
      
      for(Ride ride : getSortedRides())
      {
         rideCombobox.addItem(ride.getName(), ride.getId() + "");
      }
      
      String text = 
         rideCombobox.getValue(rideCombobox.getSelectedIndex());
      
      int rideId = Integer.parseInt(text);
      
      Ride ride = rideManager.getRide(rideId);
      
      rideGraph.setRide(ride);
      
      rideCombobox.addChangeHandler(new ChangeHandler(){
         @Override
         public void onChange(ChangeEvent event)
         {
            ListBox listBox = (ListBox)event.getSource();
            
            String text = 
               listBox.getValue(listBox.getSelectedIndex());
            
            int rideId = Integer.parseInt(text);
            
            Ride ride = rideManager.getRide(rideId);
            
            rideGraph.setRide(ride);
         }
      });
      
      return rideCombobox;
   }

   private List<Ride> getSortedRides()
   {
      RideSorter rideSorter = new RideSorter();
      List<Ride> rides = rideManager.getRides();
      
      rideSorter.setRides(rides);
      
      return rideSorter.sort(RideSorter.RIDE_NAME);
   }
}
