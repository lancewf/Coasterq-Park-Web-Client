package com.coasterq.client.view.enterwaittime;

import java.util.List;

import com.coasterq.client.connection.ServiceLocations;
import com.coasterq.client.connection.WaitTimeSender;
import com.coasterq.client.data.ParkClock;
import com.coasterq.client.data.ParkInformation;
import com.coasterq.client.data.ParkLocationManager;
import com.coasterq.client.data.Ride;
import com.coasterq.client.data.RideManagerable;
import com.coasterq.client.data.RideSorter;
import com.coasterq.client.data.RideWaitEntry;
import com.coasterq.client.view.LocationPanel;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.FacebookConnectionStatus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EnterWaitTimePanel extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private RideManagerable rideManager;
   private ListBox rideListBox;
   private WaitTimeSender waitTimeSender;
   private WaitTimeControl waitTimeControl;
   private TimeOfDayControl timeSelections;
   private ParkClock parkClock;
   private VerticalPanel openPanel;
   private VerticalPanel closedPanel;
   private DateControl dateControl;
   private SubmitPagePanel submitPagePanel;
   private ServiceLocations serviceLocations;
   private ParkInformation parkInformation;
   private FacebookConnectionStatus facebookConnectionStatus;
   private Widget facebookNotice;
   private LocationPanel locationPanel;
   private ParkLocationManager parkLocationManager;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public EnterWaitTimePanel(RideManagerable rideManager, 
                             WaitTimeSender waitTimeSender, 
                             ParkClock parkClock, 
                             ParkInformation parkInformation,
                             ServiceLocations serviceLocations, 
                             FacebookConnectionStatus facebookConnectionStatus, 
                             ParkLocationManager parkLocationManager)
   {
      this.parkClock = parkClock;
      this.rideManager = rideManager;
      this.waitTimeSender = waitTimeSender;
      this.serviceLocations = serviceLocations;
      this.parkInformation = parkInformation;
      this.facebookConnectionStatus = facebookConnectionStatus;
      this.parkLocationManager = parkLocationManager;
      
      initialize(parkClock);
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
  
   public String getName()
   {
      return "Enter Wait Time";
   }
   
   
   public void refreshView()
   {
      timeSelections.setToCurrentTime();
      
      if(parkClock.isOpen())
      {
         closedPanel.setVisible(false);
         openPanel.setVisible(true);
      }
      else
      {
         closedPanel.setVisible(true);
         openPanel.setVisible(false);
      }
      
      dateControl.setCurrentDate();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void initialize(ParkClock parkClock)
   {
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      openPanel = createOpenPanel(parkClock);
      
      closedPanel = createClosedPanel();
      
      submitPagePanel = new SubmitPagePanel();
      
      submitPagePanel.setVisible(false);
      
      facebookConnectionStatus.addLoggedinListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            if(facebookConnectionStatus.isloggedin())
            {
               facebookNotice.setVisible(false);
            }
            else
            {
               facebookNotice.setVisible(true);
            }
         }
      });
      
      add(submitPagePanel);
      add(closedPanel);
      add(openPanel);
      
      refreshView();
   }

   private VerticalPanel createClosedPanel()
   {
      VerticalPanel closedPanel = new VerticalPanel();
      
      closedPanel.addStyleName("enterWaitTimePanel");
      
      closedPanel.add(createClosedLabel());
      
      return closedPanel;
   }
   
   private VerticalPanel createOpenPanel(ParkClock parkClock)
   {
      VerticalPanel openPanel = new VerticalPanel();
      
      openPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      openPanel.addStyleName("enterWaitTimePanel");
      
      locationPanel = new LocationPanel(parkLocationManager);
      rideListBox = createRideSelection();
      waitTimeControl = new WaitTimeControl();
      timeSelections = new TimeOfDayControl(parkClock);
      dateControl = new DateControl(parkClock);
      facebookNotice = createFacebookNotice();
      
      if(facebookConnectionStatus.isloggedin())
      {
         facebookNotice.setVisible(false);
      }
      
      openPanel.add(locationPanel);
      openPanel.add(rideListBox);
      openPanel.add(dateControl);
      openPanel.add(createTimeOfDayLabel());
      openPanel.add(timeSelections);
      openPanel.add(createWaitTimeLabel());
      openPanel.add(waitTimeControl);
      openPanel.add(createSubmitButton());
      openPanel.add(facebookNotice);
      
      return openPanel;
   }
   
   private Widget createFacebookNotice()
   {
      return new Label("* To share with friends on Facebook, log in to Facebook on CoasterQ's site by clicking the button at the top of the page.");
   }
   
   private Widget createSubmitButton()
   {
      VerticalPanel buttonPanel = new VerticalPanel();
      
      Button button = new Button("Submit");
      
      button.setStyleName("cq-Button");
      
      buttonPanel.addStyleName("enterWaitTimeSubPanel");
      
      buttonPanel.add(button);
      
      button.addClickHandler(new ClickHandler()
      {
         @Override
         public void onClick(ClickEvent event)
         {
            RideWaitEntry rideWaitEntry = getRideWaitEntry();
            
            if(facebookConnectionStatus.isloggedin())
            {
               String caption = "{*actor*} rode on the "
                     + rideWaitEntry.getRide().getName() + " ride at "
                     + parkInformation.getParkName();

               FacebookConnectionStatus.publishToUserWall("Is riding "
                     + rideWaitEntry.getRide().getName(),
                  "http://apps.facebook.com/coasterq",
                  caption,
                  serviceLocations.getHomeUrl() + "/img/facebookpicture.png",
                  "http://apps.facebook.com/coasterq",
                  "Tell your friends",
                  "Visit CoasterQ",
                  "http://apps.facebook.com/coasterq");
            }
            
            waitTimeSender.update(rideWaitEntry);
            
            submitPagePanel.setRideName(rideWaitEntry.getRide().getName());
            
            closedPanel.setVisible(false);
            openPanel.setVisible(false);
            submitPagePanel.setVisible(true);
            
            Timer timer = new Timer()
            {
               @Override
               public void run()
               {
                  submitPagePanel.setVisible(false);
                  refreshView();
               }
            };
            
            timer.schedule(7000);
         }
      });
      
      return buttonPanel;
   }
   
   private RideWaitEntry getRideWaitEntry()
   {
      RideWaitEntry rideWaitEntry = new RideWaitEntry();
      
      Ride selectedRide = getSelectedRide();
      
      rideWaitEntry.setRide(selectedRide);
      
      int totalMins = waitTimeControl.getWaitTimeMin();
      
      rideWaitEntry.setWaitTimeMin(totalMins);
      
      rideWaitEntry.setTimeHour(timeSelections.getHour());
      
      rideWaitEntry.setTimeMin(timeSelections.getMin());
      
      rideWaitEntry.setDayOfMonth(timeSelections.getDayOfMonth());
      
      rideWaitEntry.setMonth(timeSelections.getMonth());
      
      rideWaitEntry.setYear(timeSelections.getYear());
      
      rideWaitEntry.setIsInsidePark(parkLocationManager.isInisidePark());
      
      rideWaitEntry.setLatitude(parkLocationManager.getLatitude());
      
      rideWaitEntry.setLongitude(parkLocationManager.getLongitude());
      
      return rideWaitEntry;
   }

   private Ride getSelectedRide()
   {
      int selectedIndex = rideListBox.getSelectedIndex();
      
      String rideIdString = rideListBox.getValue(selectedIndex);
      
      int rideId = Integer.parseInt(rideIdString);
      
      Ride ride = rideManager.getRide(rideId);
      
      return ride;
   }
  
   private Widget createClosedLabel()
   {
      VerticalPanel labelPanel = new VerticalPanel();
      
      Label datelabel = new Label("Sorry, the park is currently closed.");
      
      labelPanel.add(datelabel);
      
      labelPanel.addStyleName("enterWaitTimeSubPanel");
      
      return labelPanel;
   }
   
   private Widget createTimeOfDayLabel()
   {
      VerticalPanel labelPanel = new VerticalPanel();
      
      Label datelabel = new Label("Time Of Day");
      
      labelPanel.add(datelabel);
      
      labelPanel.addStyleName("enterWaitTimeSubPanel");
      
      return labelPanel;
   }
   
   private Widget createWaitTimeLabel()
   {
      VerticalPanel labelPanel = new VerticalPanel();
      
      Label datelabel = new Label("Wait Time");
      
      labelPanel.add(datelabel);
      
      labelPanel.addStyleName("enterWaitTimeSubPanel");
      
      return labelPanel;
   }

   private ListBox createRideSelection()
   {
      ListBox rideCombobox = new ListBox();
      
      for(Ride ride : getSortedRides())
      {
         rideCombobox.addItem(ride.getName(), ride.getId() + "");
      }
      
      return rideCombobox;
   }

   private List<Ride> getSortedRides()
   {
      RideSorter rideSorter = new RideSorter();
      List<Ride> rides = rideManager.getRides();
      
      rideSorter.setRides(rides);
      
      return rideSorter.sort(RideSorter.RIDE_NAME);
   }
}
