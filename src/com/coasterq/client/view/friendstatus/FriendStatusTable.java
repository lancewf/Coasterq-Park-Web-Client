package com.coasterq.client.view.friendstatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coasterq.client.data.FriendStatus;
import com.coasterq.client.data.FriendStatusManager;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.Row;
import com.finfrock.client.Table;

public class FriendStatusTable extends Table
{
   // --------------------------------------------------------------------------
   // Private Instance Data
   // --------------------------------------------------------------------------
   
   private HashMap<FriendStatus, FriendStatusRow> friendStatusRowTable = 
      new HashMap<FriendStatus, FriendStatusRow>();
   
   private FriendStatusManager friendStatusManager;
   
   private FacebookConnectionStatus facebookConnectionStatus;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatusTable(FriendStatusManager friendStatusManager, 
                            FacebookConnectionStatus facebookConnectionStatus)
   {
      this.friendStatusManager = friendStatusManager;
      this.facebookConnectionStatus = facebookConnectionStatus;
      
      friendStatusManager.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            updateTable();
         }
      });
      
      updateTable();
   }

   // --------------------------------------------------------------------------
   // Table Members
   // --------------------------------------------------------------------------
   
   @Override
   protected Row getHeaderRow()
   {
      return new FriendStatusHeaderRow();
   }

   @Override
   protected List<Row> getRows()
   {
      List<Row> friendStatusRows = new ArrayList<Row>();
      
      for(FriendStatus friendStatus : friendStatusManager.getFriendStatuses())
      {
         FriendStatusRow friendStatusRow = getRow(friendStatus);
         
         friendStatusRows.add(friendStatusRow);
      }
      
      return friendStatusRows;
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private FriendStatusRow getRow(FriendStatus friendStatus)
   {
      FriendStatusRow friendStatusRow = friendStatusRowTable.get(friendStatus);
      
      if(friendStatusRow == null)
      {
         friendStatusRow = new FriendStatusRow(friendStatus, 
            facebookConnectionStatus);
         
         friendStatusRowTable.put(friendStatus, friendStatusRow);
      }
      
      return friendStatusRow;
   }
}
