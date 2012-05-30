package com.coasterq.client.view.friendstatus;

import java.util.Date;

import com.coasterq.client.data.FriendStatus;
import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.Row;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;

public class FriendStatusRow extends Row
{
   // --------------------------------------------------------------------------
   // Public static Data
   // --------------------------------------------------------------------------

   public static final int FRIEND_INDEX = 0;
   public static final int RIDE_NAME_INDEX = 1;
   public static final int DATE_TIME_INDEX = 2;
   public static final int WAIT_TIME_INDEX = 3;
   
   // --------------------------------------------------------------------------
   // Private static Data
   // --------------------------------------------------------------------------

   private static DateTimeFormat datetimeformat = 
      DateTimeFormat.getFormat("MMMM d, y h:mm a");
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatusRow(FriendStatus friendStatus, 
                          FacebookConnectionStatus facebookConnectionStatus)
   {
      addColumn(createFriendColumn(friendStatus, facebookConnectionStatus));
      addColumn(createDateTimeColumn(friendStatus));
      addColumn(createRideColumn(friendStatus));
      addColumn(createWaitTimeColumn(friendStatus));
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private Column createRideColumn(FriendStatus friendStatus)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(friendStatus.getRide().getName());
      column.setIndex(RIDE_NAME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createWaitTimeColumn(FriendStatus friendStatus)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(getFormatTime(friendStatus.getWaitTime()));
      column.setStyleName("tableNumericColumn");
      column.setIndex(WAIT_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createDateTimeColumn(FriendStatus friendStatus)
   {
      CommonColumn column = new CommonColumn();
      
      Date date = friendStatus.getDateTime();
      
      String text = datetimeformat.format(date);
      
      column.setValue(text);
      column.setIndex(DATE_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createFriendColumn(FriendStatus friendStatus, 
                              FacebookConnectionStatus facebookConnectionStatus)
   {
      CommonColumn column = new CommonColumn();
      
      Widget friendProfilePicture = 
         facebookConnectionStatus.createProfilePicture(
            friendStatus.getFacebookUser());
      
      column.setValue(friendProfilePicture);
      column.setStyleName("buttonColumn");
      column.setIndex(FRIEND_INDEX);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }
   
   private String getFormatTime(int timeMin)
   {
      String text = "";
      
      if (timeMin != -1)
      {
         timeMin = Math.round((timeMin / 5)) * 5;

         int hours = timeMin / 60;
         int mins = timeMin % 60;

         if (hours > 0)
         {
            text += hours + " hr ";
         }

         text += mins + " min";
      }
      else
      {
         text = "Closed";
      }
      
      return text;
   }
}
