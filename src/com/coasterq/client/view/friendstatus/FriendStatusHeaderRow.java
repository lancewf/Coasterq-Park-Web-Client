package com.coasterq.client.view.friendstatus;

import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.Row;

public class FriendStatusHeaderRow extends Row
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatusHeaderRow()
   {
      addColumn(createFriendColumn());
      addColumn(createDateTimeColumn());
      addColumn(createRideColumn());
      addColumn(createWaitTimeColumn());
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private Column createWaitTimeColumn()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("Wait Time");
      column.setStyleName("tableHeader");
      column.setIndex(FriendStatusRow.WAIT_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);

      return column;
   }
   
   private Column createRideColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Ride");
      column.setStyleName("tableHeader");
      column.setIndex(FriendStatusRow.RIDE_NAME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createDateTimeColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Date/Time");
      column.setStyleName("tableHeader");
      column.setIndex(FriendStatusRow.DATE_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createFriendColumn()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("Friend");
      column.setStyleName("tableHeader");
      column.setIndex(FriendStatusRow.FRIEND_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);

      return column;
   }
}
