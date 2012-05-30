package com.coasterq.client.view.ride;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.Ride;
import com.coasterq.client.view.CurrentWaitTimeControl;
import com.coasterq.client.view.RideLegendControl;
import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.Row;

public class RideRow extends Row
{
   // --------------------------------------------------------------------------
   // Public static Data
   // --------------------------------------------------------------------------

   public static final int LEGEND_COLUMN = 0;
   public static final int RIDE_NAME = 1;
   public static final int LOCATION_COLUMN = 2;
   public static final int CURRNET_RIDE_WAIT = 3;
   public static final int ADD_TO_MY_Q = 4;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RideRow(Ride ride, ItineraryRideManagerable itineraryRideManager, 
                  CQImageBundle cqImageBundle)
   {
      addColumn(createLegendColumn(ride, cqImageBundle));
      addColumn(createNameColumn(ride));
      addColumn(createLocationColumn(ride));
      addColumn(createCurrentWaitColumn(ride));
      addColumn(createAddToItineraryControl(ride, itineraryRideManager));
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   private Column createLegendColumn(Ride ride,
                                     CQImageBundle cqImageBundle)
   {
      CommonColumn column = new CommonColumn();

      RideLegendControl legendPanel = 
         new RideLegendControl(ride, cqImageBundle);

      column.setValue(legendPanel);
      column.setStyleName("cq-legendColumn");
      column.setIndex(LEGEND_COLUMN);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);

      return column;
   }
   
   private Column createCurrentWaitColumn(Ride ride)
   {
      CommonColumn column = new CommonColumn();
      
      CurrentWaitTimeControl currentWaitTimeControl = 
         new CurrentWaitTimeControl(ride);
      
      column.setValue(currentWaitTimeControl);
      column.setStyleName("tableNumericColumn");
      column.setIndex(CURRNET_RIDE_WAIT);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createLocationColumn(Ride ride)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(ride.getLocation());
      column.setIndex(LOCATION_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createNameColumn(Ride ride)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(ride.getName());
      column.setIndex(RIDE_NAME);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createAddToItineraryControl(Ride ride,
                                     ItineraryRideManagerable itineraryRideManager)
   {
      CommonColumn column = new CommonColumn();
      
      AddToItineraryControl addToItineraryControl = new AddToItineraryControl(
         itineraryRideManager, ride);
      
      column.setValue(addToItineraryControl);
      column.setStyleName("buttonColumn");
      column.setIndex(ADD_TO_MY_Q);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }
}
