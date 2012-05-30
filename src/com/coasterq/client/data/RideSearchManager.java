package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.List;

import com.finfrock.client.DataChangeListener;

public class RideSearchManager implements RideManagerable
{
   // --------------------------------------------------------------------------
   // Public static Data
   // --------------------------------------------------------------------------

   public static final String ALL_SELECTION = "All";
   public static final String YES_SELECTION = "Yes";
   public static final String NO_SELECTION = "No";
   
   public static final String MAX_WAIT_TIME_30 = "< 30 minutes";
   public static final String MAX_WAIT_TIME_60 = "< 1 hour";
   public static final String MAX_WAIT_TIME_90 = "< 1 1/2 hours";
   public static final String MAX_WAIT_TIME_120 = "< 2 hours";

   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
  
   private RideManagerable rideManager;
   private String locationSelection = ALL_SELECTION;
   private String fastpassSelection = ALL_SELECTION;
   private String currentWaitTimeSelection = ALL_SELECTION;
   private String heightRequirementSelection = ALL_SELECTION;
   
   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
  
   public RideSearchManager(RideManagerable rideManager)
   {
      this.rideManager = rideManager;
      
      rideManager.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            if(currentWaitTimeSelection != ALL_SELECTION)
            {
               dataChanged();
            }
         }
      });
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
  
   @Override
   public List<Ride> getRides()
   {
      List<Ride> foundRides = new ArrayList<Ride>();
      
      for(Ride ride : rideManager.getRides())
      {
         if (!locationSelection.equalsIgnoreCase(ALL_SELECTION)
               && !locationSelection.equalsIgnoreCase(ride.getLocation()))
         {
            continue;
         }
         if (!fastpassSelection.equalsIgnoreCase(ALL_SELECTION)
               && (fastpassSelection.equalsIgnoreCase(NO_SELECTION) && ride
                  .getHasFastPass())
               || (fastpassSelection.equalsIgnoreCase(YES_SELECTION) && !ride
                  .getHasFastPass()))
         {
            continue;
         }
         if(!currentWaitTimeSelection.equalsIgnoreCase(ALL_SELECTION) &&
               (currentWaitTimeSelection.equalsIgnoreCase(MAX_WAIT_TIME_30) && 
               ride.getCurrentWaitTime() > 30) ||
               (currentWaitTimeSelection.equalsIgnoreCase(MAX_WAIT_TIME_60) && 
               ride.getCurrentWaitTime() > 60) ||
               (currentWaitTimeSelection.equalsIgnoreCase(MAX_WAIT_TIME_90) && 
               ride.getCurrentWaitTime() > 90) ||
               (currentWaitTimeSelection.equalsIgnoreCase(MAX_WAIT_TIME_120) && 
               ride.getCurrentWaitTime() > 120))
         {
            continue;
         }
         if (!heightRequirementSelection.equalsIgnoreCase(ALL_SELECTION) &&
               (heightRequirementSelection.equalsIgnoreCase(NO_SELECTION) && 
                     ride.getHeightRequirement() > 0) ||
                     (heightRequirementSelection.equals(YES_SELECTION) && 
                     ride.getHeightRequirement() == 0))
         {
            continue;
         }
         foundRides.add(ride);
      }
      
      return foundRides;
   }

   @Override
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }

   @Override
   public Ride getRide(int rideId)
   {
      return rideManager.getRide(rideId);
   }

   @Override
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      rideManager.removeDataChangeListener(dataChangeListener);
      
      dataChangeListeners.remove(dataChangeListener);
   }

   public void setLocationSelection(String locationSelection)
   {
      this.locationSelection = locationSelection;
      
      dataChanged();
   }
   
   public void setFastpassSelection(String fastpassSelection)
   {
      this.fastpassSelection = fastpassSelection;
      
      dataChanged();
   }
   
   public void setHeightRequirementSelection(String heightRequirementSelection)
   {
      this.heightRequirementSelection = heightRequirementSelection;
      
      dataChanged();
   }
   
   public void setCurrentWaitTimeSelection(String currentWaitTimeSelection)
   {
      this.currentWaitTimeSelection = currentWaitTimeSelection;
      
      dataChanged();
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
