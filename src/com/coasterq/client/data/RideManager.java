package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.List;

import com.finfrock.client.DataChangeListener;

public class RideManager implements RideManagerable 
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
  
	private List<Ride> rides = new ArrayList<Ride>();
   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
	public RideManager(List<Ride> rides)
   {
      this.rides = rides;
	}
	
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public void setRides(List<Ride> updatedRides) 
   {
      for(Ride updatedRide : updatedRides)
      {
         Ride ride = getRide(updatedRide.getId());
         
         ride.setCurrentWaitTime(updatedRide.getCurrentWaitTime());
      }
      
      dataChanged();
   }
	
   // --------------------------------------------------------------------------
   // RideManagerable Members
   // --------------------------------------------------------------------------
	
	/* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#getRides()
    */
	public List<Ride> getRides() 
	{
		return new ArrayList<Ride>(rides);
	}
	
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#getRide(com.coasterq.client.data.RideId)
    */
   public Ride getRide(int rideId)
   {
      for(Ride ride : rides)
      {
         if(ride.getId() == rideId)
         {
            return ride;
         }
      }
      return null;
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#addDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#removeDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.remove(dataChangeListener);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void dataChanged()
   {
      for(DataChangeListener dataChangeListener : dataChangeListeners)
      {
         dataChangeListener.onDataChange();
      }
   }

}
