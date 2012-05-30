package com.coasterq.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.coasterq.client.connection.FriendStatusUpdateAgent;
import com.coasterq.client.connection.FriendStatusesRetriever;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.FacebookConnectionStatus;

public class FriendStatusManager
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private List<FriendStatus> friendStatuses = new ArrayList<FriendStatus>();
   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();
   private FriendStatusUpdateAgent friendStatusUpdateAgent;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatusManager(FriendStatusesRetriever friendStatusesRetriever, 
                              FacebookConnectionStatus facebookConnectionStatus)
   {
      this.friendStatusUpdateAgent = 
         new FriendStatusUpdateAgent(friendStatusesRetriever, this);
      
      friendStatusUpdateAgent.start();
      
      facebookConnectionStatus.addLoggedinListener(new DataChangeListener()
      {  
         @Override
         public void onDataChange()
         {
            friendStatusUpdateAgent.once();
         }
      });
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public List<FriendStatus> getFriendStatuses()
   {
      return friendStatuses;
   }

   public void setFriendStatuses(List<FriendStatus> friendStatuses)
   {
      this.friendStatuses = friendStatuses;
      
      Collections.sort(friendStatuses, new Comparator<FriendStatus>()
      {
         @Override
         public int compare(FriendStatus friendStatus1, 
                            FriendStatus friendStatus2)
         {
            return (-1)*friendStatus1.getDateTime().compareTo(
               friendStatus2.getDateTime());
         }

      });
      
      dataChanged();
   }

   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#addDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#removeDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.remove(dataChangeListener);
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
