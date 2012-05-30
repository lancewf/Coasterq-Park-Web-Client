package com.coasterq.client.connection;

import com.finfrock.client.Returnable;
import com.finfrock.client.Sender;

/**
 * Log out the facebook user. 
 * @author lancewf
 *
 */
public class LogOutUser extends Sender
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public LogOutUser(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getUserLogOutAddress());
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void sendToServer(final Returnable<Boolean> returnable)
   {
      addReturnableListener(returnable);
      
      send();
   }

   @Override
   protected String getData()
   {
      return "";
   }
}
