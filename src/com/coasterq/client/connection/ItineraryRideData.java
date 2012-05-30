package com.coasterq.client.connection;

import com.google.gwt.core.client.JavaScriptObject;

public class ItineraryRideData extends JavaScriptObject
{
   protected ItineraryRideData() 
   {
      
   }
   
   public final native int getPriority()
   /*-{
      return this.priority;
   }-*/;
   
   public final native int getRideId()
   /*-{
      return this.rideId;
   }-*/;
}
