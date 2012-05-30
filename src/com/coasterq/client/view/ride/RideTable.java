package com.coasterq.client.view.ride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideManagerable;
import com.coasterq.client.data.RideTableSortManager;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.Row;
import com.finfrock.client.Table;

public class RideTable extends Table
{
   // --------------------------------------------------------------------------
   // Private Instance Data
   // --------------------------------------------------------------------------

   private ItineraryRideManagerable itineraryRideManager;

   private RideTableSortManager rideSorterManager;
   
   private CQImageBundle cqImageBundle;
   
   private HashMap<Ride, RideRow> rideRowTable = new HashMap<Ride, RideRow>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RideTable(ItineraryRideManagerable itineraryRideManager, 
                    CQImageBundle cqImageBundle, RideManagerable rideManagerable)
   {
      this.itineraryRideManager = itineraryRideManager;
      this.cqImageBundle = cqImageBundle;
      
      initialize(rideManagerable);
      
      updateTable();
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void addRides(List<Ride> rides)
   {
      rideSorterManager.setRides(rides);
   }
   
   // --------------------------------------------------------------------------
   // Table Members
   // --------------------------------------------------------------------------
   
   @Override
   protected void columnHeaderClicked(int column)
   {
      if (column == RideRow.RIDE_NAME)
      {
         rideSorterManager.setSortBy(RideTableSortManager.RIDE_NAME);
      }
      else if (column == RideRow.LOCATION_COLUMN)
      {
         rideSorterManager.setSortBy(RideTableSortManager.LAND_NAME);
      }
      else if (column == RideRow.CURRNET_RIDE_WAIT)
      {
         rideSorterManager.setSortBy(RideTableSortManager.CURRENT_TIME);
      }
      else if (column == RideRow.ADD_TO_MY_Q)
      {
         rideSorterManager.setSortBy(RideTableSortManager.ADD_TO_MY_Q);
      }
   }
   
   @Override
   protected List<Row> getRows()
   {
      List<Row> rideRows = new ArrayList<Row>();
      
      for(Ride ride : rideSorterManager.getRides())
      {
         RideRow rideRow = getRow(ride);
         
         rideRows.add(rideRow);
      }
      
      return rideRows;
   }

   @Override
   protected Row getHeaderRow()
   {
      return new RideHeaderRow();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void initialize(RideManagerable rideManagerable)
   {
      rideSorterManager = new RideTableSortManager(
         itineraryRideManager, rideManagerable);
      
      rideSorterManager.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            updateTable();
         }
      });
   }
   
   private RideRow getRow(Ride ride)
   {
      RideRow rideRow = rideRowTable.get(ride);
      
      if(rideRow == null)
      {
         rideRow = new RideRow(ride, itineraryRideManager, cqImageBundle);
         
         rideRowTable.put(ride, rideRow);
      }
      return rideRow;
   }
}
