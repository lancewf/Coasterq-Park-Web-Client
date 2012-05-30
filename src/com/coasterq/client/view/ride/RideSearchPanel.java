package com.coasterq.client.view.ride;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.data.RideManagerable;
import com.coasterq.client.data.RideSearchManager;
import com.coasterq.client.view.Legend;
import com.finfrock.client.DataChangeListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This panel Display the rides and allows the user to search for a ride. 
 */
public class RideSearchPanel extends VerticalPanel implements DataChangeListener
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
	private RideTable rideTable;
	private RideManagerable rideManager;
	private SearchControl searchControl;
	
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
	public RideSearchPanel(RideManagerable rideManager, 
	                       ItineraryRideManagerable itineraryRideManager, 
	                       ParkInformation parkInformation, 
	                       CQImageBundle cqImageBundle)
	{
		initialize(itineraryRideManager, rideManager, 
		   parkInformation, cqImageBundle);
	}
	
   // --------------------------------------------------------------------------
   // DataChangeListener Members
   // --------------------------------------------------------------------------
   
   @Override
   public void onDataChange()
   {
      rideTable.addRides(rideManager.getRides());
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
  
   public void refreshView()
   {
      // TODO Auto-generated method stub
      
   }
   
   public String getName()
   {
      return "Ride Search";
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void initialize(ItineraryRideManagerable itineraryRideManager, 
                           RideManagerable rideManager, 
                           ParkInformation parkInformation, 
                           CQImageBundle cqImageBundle) 
   {
      RideSearchManager rideSearchManager = new RideSearchManager(rideManager);
         
      this.rideManager = rideSearchManager;
      
      searchControl = new SearchControl(rideSearchManager, parkInformation);
      
      rideTable = new RideTable(itineraryRideManager, 
         cqImageBundle, rideManager);
       
      rideTable.addRides(rideManager.getRides());
      
      rideSearchManager.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            rideTable.addRides(RideSearchPanel.this.rideManager.getRides());
         }
      });
      
      Legend legend = new Legend(parkInformation, cqImageBundle);

      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      add(searchControl);
      add(rideTable);
      
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
      add(legend);
   }
}
