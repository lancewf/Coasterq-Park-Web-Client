package com.coasterq.client;

import com.coasterq.client.connection.LogInUser;
import com.coasterq.client.connection.LogOutUser;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.Returnable;

public class FacebookCoasterqConnection extends FacebookConnectionStatus 
   implements DataChangeListener
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private LogInUser logInUser;
   private LogOutUser logOutUser;

   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FacebookCoasterqConnection(LogInUser logInUser, LogOutUser logOutUser)
   {
      this.logInUser = logInUser;
      this.logOutUser = logOutUser;
      
      addLoggedinListener(this);
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void logoutOfFacebook()
   {
      logOutUser.sendToServer(new Returnable<Boolean>()
      {
         @Override
         public void returned(Boolean object)
         {
            logoutAndRedirect();
         }
      });
   }

   @Override
   public void onDataChange()
   {
      String facebookUserId = getFacebookUserId() + "";
      logInUser.sendToServer(facebookUserId);
   }
}
