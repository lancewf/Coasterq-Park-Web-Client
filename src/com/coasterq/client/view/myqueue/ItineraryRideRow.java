package com.coasterq.client.view.myqueue;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ItineraryRide;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.view.CurrentWaitTimeControl;
import com.coasterq.client.view.RideLegendControl;
import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.Row;

public class ItineraryRideRow extends Row
{
   // --------------------------------------------------------------------------
   // Public Class Data
   // --------------------------------------------------------------------------

   public static final int LEGEND_COLUMN = 0;
   public static final int NAME_COLUMN = 1;
   public static final int LOCATION_COLUMN = 2;
   public static final int CURRENT_WAIT_COLUMN = 3;
   public static final int REMOVE_COLUMN = 4;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ItineraryRideRow(ItineraryRide itineraryRide, 
                           ItineraryRideManagerable itineraryRideManager, 
                  CQImageBundle cqImageBundle)
   {
      addColumn(createLegendColumn(itineraryRide, cqImageBundle));
      addColumn(createNameColumn(itineraryRide));
      addColumn(createLocationColumn(itineraryRide));
      addColumn(createCurrentWaitColumn(itineraryRide));
      addColumn(createRemoveColumn(itineraryRide, itineraryRideManager));
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private Column createRemoveColumn(ItineraryRide itineraryRide,
                                     ItineraryRideManagerable itineraryRideManager)
   {
      CommonColumn column = new CommonColumn();
      
      ItineraryRideDeleteButton itineraryRideDeleteButton = 
         new ItineraryRideDeleteButton(itineraryRideManager, itineraryRide);
      
      column.setValue(itineraryRideDeleteButton);
      column.setStyleName("buttonColumn");
      column.setIndex(REMOVE_COLUMN);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }

   private Column createCurrentWaitColumn(ItineraryRide itineraryRide)
   {
      CommonColumn column = new CommonColumn();
      
      CurrentWaitTimeControl currentWaitTimeControl = 
         new CurrentWaitTimeControl(itineraryRide.getRide());
      
      column.setValue(currentWaitTimeControl);
      column.setStyleName("tableNumericColumn");
      column.setIndex(CURRENT_WAIT_COLUMN);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }

   private Column createLocationColumn(ItineraryRide itineraryRide)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(itineraryRide.getRide().getLocation());
      column.setIndex(LOCATION_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createNameColumn(ItineraryRide itineraryRide)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(itineraryRide.getRide().getName());
      column.setIndex(NAME_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createLegendColumn(ItineraryRide itineraryRide,
                                           CQImageBundle cqImageBundle)
   {
      CommonColumn column = new CommonColumn();
      
      RideLegendControl legendPanel = 
         new RideLegendControl(itineraryRide.getRide(), cqImageBundle);
      
      column.setValue(legendPanel);
      column.setStyleName("cq-legendColumn");
      column.setIndex(LEGEND_COLUMN);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }
}
