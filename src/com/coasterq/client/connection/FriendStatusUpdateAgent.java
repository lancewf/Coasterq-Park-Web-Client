package com.coasterq.client.connection;

import java.util.List;

import com.coasterq.client.data.FriendStatus;
import com.coasterq.client.data.FriendStatusManager;
import com.finfrock.client.Loadable;
import com.finfrock.client.Returnable;
import com.google.gwt.user.client.Timer;

public class FriendStatusUpdateAgent
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private FriendStatusesRetriever friendStatusesRetriever;
   private FriendStatusManager friendStatusManager;

   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FriendStatusUpdateAgent(
                                FriendStatusesRetriever friendStatusesRetriever, 
                                FriendStatusManager friendStatusManager)
   {
      this.friendStatusManager = friendStatusManager;
      this.friendStatusesRetriever = friendStatusesRetriever;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void once()
   {
      friendStatusesRetriever.startLoad(new Returnable<Loadable>()
      {
         @Override
         public void returned(Loadable loadable)
         {
            List<FriendStatus> friendStatuses = friendStatusesRetriever
               .getObject();

            friendStatusManager.setFriendStatuses(friendStatuses);
         }
      });
   }
   
   public void start()
   {
      once();
      
      Timer t = new Timer() 
      {
        public void run() 
        {
           once();
        }
      };

      // Schedule the timer to run every 5 minutes.
      t.scheduleRepeating(300000);
   }
}
