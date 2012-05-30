package com.coasterq.client.connection;

import com.google.gwt.core.client.JavaScriptObject;

public class FriendStatusData extends JavaScriptObject
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   protected FriendStatusData() 
   {
      //
      // Do nothing
      //
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public final native int getRideId()
   /*-{
      return this.rideid;
   }-*/;
   
   public final native int getFacebookUid()
   /*-{
      return this.facebookuid;
   }-*/;
   
   public final native int get24Hour()
   /*-{
      return this.hour;
   }-*/;
   
   public final native int getMinutes()
   /*-{
      return this.minutes;
   }-*/;
   
   public final native int getDayOfMonth()
   /*-{
      return this.dayOfMonth;
   }-*/;
   
   public final native int getYear()
   /*-{
      return this.year;
   }-*/;
   
   public final native int getMonth()
   /*-{
      return this.month;
   }-*/;

   public final native int getWaitTime()
   /*-{
      return this.waittime;
   }-*/;
}
