package com.coasterq.client.connection;

import com.google.gwt.core.client.JavaScriptObject;

public class RideData extends JavaScriptObject
{
   protected RideData() 
   {
      
   }
   
   public final native String getLocation()
   /*-{
      return this.location;
   }-*/;
   
   public final native int getHeightRequirement()
   /*-{
      return this.height;
   }-*/;

   public final native boolean getHasFastPass()
   /*-{
      return this.fastpass;
   }-*/;

   public final native int getId()
   /*-{
      return this.id;
   }-*/;

   public final native String getName()
   /*-{
      return this.name;
   }-*/;

   public final native int getCurrentWaitTime()
   /*-{
      return this.current;
   }-*/;
}
