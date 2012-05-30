package com.coasterq.client;

import com.coasterq.client.data.GUIBuilder;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Coasterqclient implements EntryPoint
{
   /**
    * This is the entry point method.
    */
   public void onModuleLoad()
   {    
      GUIBuilder guiBuilder = new GUIBuilder();
      
      guiBuilder.build();
   }
}
