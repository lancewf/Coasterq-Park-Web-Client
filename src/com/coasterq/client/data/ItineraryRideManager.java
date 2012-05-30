package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.coasterq.client.connection.ItineraryRideUpdater;
import com.coasterq.client.connection.ServiceLocations;
import com.finfrock.client.DataChangeListener;
import com.google.gwt.user.client.Window;

/**
 * This class manages itinerary rides
 *  
 * @author lancewf
 *
 */
public class ItineraryRideManager implements ItineraryRideManagerable
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private List<ItineraryRide> itineraryRides = new ArrayList<ItineraryRide>();
   
   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();
   
   private ItineraryRideUpdater itineraryRideUpdater;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ItineraryRideManager(RideManagerable rideManager, 
                               List<ItineraryRide> itineraryRides,
                               ServiceLocations serviceLocations)
   {
      this.itineraryRides = itineraryRides;
      
      itineraryRideUpdater = new ItineraryRideUpdater(serviceLocations);
      
      rideManager.addDataChangeListener(new DataChangeListener(){

         @Override
         public void onDataChange()
         {
            dataChanged();            
         }
         
      });
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#addNewItineraryRide(com.coasterq.client.data.Ride)
    */
   public void addNewItineraryRide(Ride ride)
   {
      if(!doesContainRide(ride))
      {
         ItineraryRide newItineraryRide = new ItineraryRide();

         int nextPriority = getNextPriority();

         newItineraryRide.setPriority(nextPriority);

         newItineraryRide.setRide(ride);

         itineraryRides.add(newItineraryRide);
         
         itineraryRideUpdater.update(itineraryRides);

         dataChanged();
      }
   }

   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#getItineraryRides()
    */
   public List<ItineraryRide> getItineraryRides()
   {
      return new ArrayList<ItineraryRide>(itineraryRides);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#removeItineraryRide(com.coasterq.client.data.ItineraryRide)
    */
   public void removeItineraryRide(ItineraryRide itineraryRide)
   {
      itineraryRides.remove(itineraryRide);
      
      renumberPriority();
      
      itineraryRideUpdater.update(itineraryRides);
      
      dataChanged();
   }

   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#addDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#removeDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.remove(dataChangeListener);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#doesContainRide(com.coasterq.client.data.Ride)
    */
   public boolean doesContainRide(Ride ride)
   {
      for(ItineraryRide itineraryRide : itineraryRides)
      {
         if(itineraryRide.getRide().equals(ride))
         {
            return true;
         }
      }
      return false;
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#increaseItineraryRidePriority(com.coasterq.client.data.ItineraryRide)
    */
   public void increaseItineraryRidePriority(ItineraryRide itineraryRide)
   {
      ItineraryRide nextHighestItineraryRide = 
         getItineraryRideFromPriority(itineraryRide.getPriority() - 1);
      
      if(nextHighestItineraryRide != null)
      {
         swapPriority(itineraryRide, nextHighestItineraryRide);
         
         renumberPriority();
         
         itineraryRideUpdater.update(itineraryRides);
         
         dataChanged();
      }
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.ItineraryRideManagerable#decreaseItineraryRidePriority(com.coasterq.client.data.ItineraryRide)
    */
   public void decreaseItineraryRidePriority(ItineraryRide itineraryRide)
   {
      ItineraryRide nextHighestItineraryRide = 
         getItineraryRideFromPriority(itineraryRide.getPriority() + 1);
      
      if(nextHighestItineraryRide != null)
      {
         swapPriority(itineraryRide, nextHighestItineraryRide);
         
         renumberPriority();
   
         itineraryRideUpdater.update(itineraryRides);
         
         dataChanged();
      }
   }
   
   @Override
   public void setItineraryRides(List<ItineraryRide> itineraryRides)
   {      
      this.itineraryRides.clear();
      
      this.itineraryRides.addAll(itineraryRides);
      
      dataChanged();
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void swapPriority(ItineraryRide itineraryRide1,
                             ItineraryRide itineraryRide2)
   {
      int tempPriority = itineraryRide1.getPriority();
      
      itineraryRide1.setPriority(itineraryRide2.getPriority());
      
      itineraryRide2.setPriority(tempPriority);
   }
   
   private ItineraryRide getItineraryRideFromPriority(int priority)
   {
      for(ItineraryRide itineraryRide : itineraryRides)
      {
         if(itineraryRide.getPriority() == priority)
         {
            return itineraryRide;
         }
      }
      
      return null;
   }
   
   private void renumberPriority()
   {
      Collections.sort(itineraryRides);
      
      int priority = 1;
      
      for(ItineraryRide itineraryRide : itineraryRides)
      {
         itineraryRide.setPriority(priority);
         
         priority++;
      }
   }
   
   private int getNextPriority()
   {
      return itineraryRides.size() + 1;
   }
   
   private void dataChanged()
   {
      for(DataChangeListener dataChangeListener : dataChangeListeners)
      {
         dataChangeListener.onDataChange();
      }
   }
}
