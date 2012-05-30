package com.coasterq.client.view.ride;

import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.Row;

public class RideHeaderRow extends Row
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public RideHeaderRow()
   {
      addColumn(createLegendColumn());
      addColumn(createNameColumn());
      addColumn(createLocationColumn());
      addColumn(createCurrentWaitColumn());
      addColumn(createAddToItineraryControl());
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private Column createLegendColumn()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("*");
      column.setStyleName("tableHeader");
      column.setIndex(RideRow.LEGEND_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);

      return column;
   }
   
   private Column createCurrentWaitColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Current Wait");
      column.setStyleName("tableHeader");
      column.setIndex(RideRow.CURRNET_RIDE_WAIT);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createLocationColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Location");
      column.setStyleName("tableHeader");
      column.setIndex(RideRow.LOCATION_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createNameColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Name");
      column.setStyleName("tableHeader");
      column.setIndex(RideRow.RIDE_NAME);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createAddToItineraryControl()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("Q");
      column.setStyleName("tableHeader");
      column.setIndex(RideRow.ADD_TO_MY_Q);
      column.setValueType(Column.TEXT_COLUMN_TYPE);

      return column;
   }
}
