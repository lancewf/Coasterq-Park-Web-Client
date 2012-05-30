package com.coasterq.client.view.myqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ItineraryRide;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.ItineraryRideSorterManager;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.Row;
import com.finfrock.client.Table;

/**
 * This table displays the itinerary rides from the itinerary ride manager. 
 * 
 * @author lancewf
 */
public class ItineraryRideTable extends Table 
implements DataChangeListener
{
   // --------------------------------------------------------------------------
   // Private Instance Data
   // --------------------------------------------------------------------------

   private ItineraryRideSorterManager itineraryRideSorterManager;
   private CQImageBundle cqImageBundle;
   private HashMap<ItineraryRide, ItineraryRideRow> itineraryRideRowTable = 
      new HashMap<ItineraryRide, ItineraryRideRow>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ItineraryRideTable(ItineraryRideManagerable itineraryRideManager, 
                             CQImageBundle cqImageBundle)
   {
      this.cqImageBundle = cqImageBundle;
      this.itineraryRideSorterManager = 
         new ItineraryRideSorterManager(itineraryRideManager);
      
      itineraryRideSorterManager.addDataChangeListener(this);
      
      updateTable();
   }
   
   // --------------------------------------------------------------------------
   // DataChangeListener Members
   // --------------------------------------------------------------------------
   
   @Override
   public void onDataChange()
   {
      updateTable();
   }
   
   // --------------------------------------------------------------------------
   // Table Members
   // --------------------------------------------------------------------------
   
   @Override
   protected void columnHeaderClicked(int column)
   {
      if (column == ItineraryRideRow.NAME_COLUMN)
      {
         itineraryRideSorterManager.setSortBy(
            ItineraryRideSorterManager.RIDE_NAME);

         updateTable();
      }
      else if (column == ItineraryRideRow.LOCATION_COLUMN)
      {
         itineraryRideSorterManager.setSortBy(
            ItineraryRideSorterManager.LAND_NAME);

         updateTable();
      }
      else if (column == ItineraryRideRow.CURRENT_WAIT_COLUMN)
      {
         itineraryRideSorterManager.setSortBy(
            ItineraryRideSorterManager.CURRENT_TIME);

         updateTable();
      }
   }
   
   @Override
   protected Row getHeaderRow()
   {
      return new ItineraryRideHeaderRow();
   }
   
   @Override
   protected List<Row> getRows()
   {
      List<Row> itineraryRideRows = new ArrayList<Row>();
      
      for(ItineraryRide itineraryRide : itineraryRideSorterManager
            .getItineraryRides())
      {
         ItineraryRideRow itineraryRideRow = getRow(itineraryRide);
         
         itineraryRideRows.add(itineraryRideRow);
      }
      
      return itineraryRideRows;
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private ItineraryRideRow getRow(ItineraryRide itineraryRide)
   {
      ItineraryRideRow itineraryRideRow = 
         itineraryRideRowTable.get(itineraryRide);
      
      if(itineraryRideRow == null)
      {
         itineraryRideRow = 
            new ItineraryRideRow(itineraryRide, 
               itineraryRideSorterManager, cqImageBundle);
         
         itineraryRideRowTable.put(itineraryRide, itineraryRideRow);
      }
      return itineraryRideRow;
   }
}