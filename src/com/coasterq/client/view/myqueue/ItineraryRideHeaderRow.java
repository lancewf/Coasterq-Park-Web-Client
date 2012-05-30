package com.coasterq.client.view.myqueue;

import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.Row;

public class ItineraryRideHeaderRow extends Row
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ItineraryRideHeaderRow()
   {
      addColumn(createLegendColumn());
      addColumn(createNameColumn());
      addColumn(createLocationColumn());
      addColumn(createCurrentWaitColumn());
      addColumn(createRemoveColumn());
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private Column createRemoveColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Remove");
      column.setStyleName("tableHeader");
      column.setIndex(ItineraryRideRow.REMOVE_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createCurrentWaitColumn()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("Current Wait");
      column.setStyleName("tableHeader");
      column.setIndex(ItineraryRideRow.CURRENT_WAIT_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createLocationColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Location");
      column.setStyleName("tableHeader");
      column.setIndex(ItineraryRideRow.LOCATION_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createNameColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Name");
      column.setStyleName("tableHeader");
      column.setIndex(ItineraryRideRow.NAME_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createLegendColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("*");
      column.setStyleName("tableHeader");
      column.setIndex(ItineraryRideRow.LEGEND_COLUMN);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
}
