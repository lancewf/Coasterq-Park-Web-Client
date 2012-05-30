package com.coasterq.client.view;

import com.coasterq.client.FacebookCoasterqConnection;
import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.connection.FriendStatusesRetriever;
import com.coasterq.client.connection.ServiceLocations;
import com.coasterq.client.connection.WaitTimeSender;
import com.coasterq.client.data.FriendStatusManager;
import com.coasterq.client.data.ItineraryRideManagerable;
import com.coasterq.client.data.ParkClock;
import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.data.ParkLocationManager;
import com.coasterq.client.data.RideManagerable;
import com.coasterq.client.view.enterwaittime.EnterWaitTimePanel;
import com.coasterq.client.view.friendstatus.FacebookPanel;
import com.coasterq.client.view.myqueue.MyQPanel;
import com.coasterq.client.view.ride.RideSearchPanel;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.LocationManager;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabPanel;

public class MainTabPanel extends TabPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
  
   private static final int MY_Q_PANEL_TAB_INDEX = 0;
   private static final int RIDE_SEARCH_PANEL_TAB_INDEX = 1;
   private static final int ENTER_WAIT_TIME_PANEL_TAB_INDEX = 2;

   private MyQPanel myQPanel;
   private RideSearchPanel rideSearchPanel;
   private EnterWaitTimePanel enterWaitTimePanel;
   private RideGraphPanel rideGraphPanel;
   private FacebookPanel facebookPanel;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public MainTabPanel(ServiceLocations serviceLocations, 
                       RideManagerable rideManager, 
                       ItineraryRideManagerable itineraryRideManager, 
                       ParkInformation parkInformation, 
                       ParkClock parkClock, 
                       CQImageBundle cqImageBundle, 
                       FacebookCoasterqConnection facebookConnectionStatus, 
                       ParkLocationManager parkLocationManager)
   {
      initialize(serviceLocations, rideManager, 
         itineraryRideManager, parkInformation, parkClock, cqImageBundle, 
         facebookConnectionStatus, parkLocationManager);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private FacebookPanel createFacebookPanel(RideManagerable rideManager, 
                                             ServiceLocations serviceLocations, 
                                             FacebookConnectionStatus facebookConnectionStatus)
   {
      FriendStatusesRetriever friendStatusesRetriever = 
         new FriendStatusesRetriever(rideManager, serviceLocations);
      
      FriendStatusManager friendStatusManager = 
         new FriendStatusManager(friendStatusesRetriever, facebookConnectionStatus);
      
      FacebookPanel facebookPanel = new FacebookPanel(friendStatusManager,
         facebookConnectionStatus);
      
      return facebookPanel;
   }
   
   private void initialize(ServiceLocations serviceLocations, 
                           RideManagerable rideManager, 
                           ItineraryRideManagerable itineraryRideManager, 
                           ParkInformation parkInformation, 
                           ParkClock parkClock, CQImageBundle cqImageBundle, 
                           FacebookCoasterqConnection facebookConnectionStatus, 
                           ParkLocationManager parkLocationManager)
   {  
      createTabs(rideManager,
         itineraryRideManager,
         serviceLocations, parkInformation, parkClock, cqImageBundle, 
         facebookConnectionStatus, parkLocationManager);      
      setAnimationEnabled(true);
      
      selectTab();
      
      addSelectionHandler(new SelectionHandler<Integer>(){
         @Override
         public void onSelection(SelectionEvent<Integer> event)
         {
            if(event.getSelectedItem() == MY_Q_PANEL_TAB_INDEX)
            {
               myQPanel.refreshView();
            }
            else if(event.getSelectedItem() == RIDE_SEARCH_PANEL_TAB_INDEX)
            {
               rideSearchPanel.refreshView();
            }
            else if(event.getSelectedItem() == ENTER_WAIT_TIME_PANEL_TAB_INDEX)
            {
               enterWaitTimePanel.refreshView();
            }
         }
      });
   }
   
   private void selectTab()
   {  
      if(getStartTab().equals("myq"))
      {
         selectTab(0);
      }
      else if(getStartTab().equals("enterridetime"))
      {
         selectTab(2);
      }
      else if(getStartTab().equals("rides"))
      {
         selectTab(1);
      }
      else
      {
         selectTab(0);
      }
   }
   
   private static native String getStartTab() 
   /*-{
      if($wnd.start_tab)
      {
         return $wnd.start_tab;
      }
      else
      {
         return "myq";
      }
   }-*/;
   
   private void createTabs(RideManagerable rideManager, 
      ItineraryRideManagerable itineraryRideManager, 
      ServiceLocations serviceLocations, ParkInformation parkInformation, 
      ParkClock parkClock, CQImageBundle cqImageBundle, 
      FacebookCoasterqConnection facebookConnectionStatus, 
      ParkLocationManager parkLocationManager)
   {
      WaitTimeSender waitTimeSender = new WaitTimeSender(serviceLocations);
      
      myQPanel = new MyQPanel(itineraryRideManager, 
         parkInformation, cqImageBundle);
      rideSearchPanel = new RideSearchPanel(rideManager, 
         itineraryRideManager, parkInformation, cqImageBundle);
      enterWaitTimePanel = new EnterWaitTimePanel(rideManager, 
         waitTimeSender, parkClock, parkInformation, serviceLocations, 
         facebookConnectionStatus, parkLocationManager);
      
      facebookPanel = createFacebookPanel(rideManager, serviceLocations, 
         facebookConnectionStatus);
      
      //rideGraphPanel = new RideGraphPanel(rideManager);
      
      add(myQPanel, myQPanel.getName());
      add(rideSearchPanel, rideSearchPanel.getName());
      add(enterWaitTimePanel, enterWaitTimePanel.getName());
      add(facebookPanel, facebookPanel.getName());
      //add(rideGraphPanel, rideGraphPanel.getName());
   }
}
