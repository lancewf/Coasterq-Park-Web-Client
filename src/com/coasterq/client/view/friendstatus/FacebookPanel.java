package com.coasterq.client.view.friendstatus;

import com.coasterq.client.data.FriendStatusManager;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.FacebookConnectionStatus;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FacebookPanel extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private FriendStatusManager friendStatusManager;
   private FacebookConnectionStatus facebookConnectionStatus;
   private FriendStatusTable friendStatusTable;
   private VerticalPanel emptyItinerary;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public FacebookPanel(FriendStatusManager friendStatusManager, 
                        FacebookConnectionStatus facebookConnectionStatus)
   {
      this.friendStatusManager = friendStatusManager;
      this.facebookConnectionStatus = facebookConnectionStatus;
      
      initialize();
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public String getName()
   {
      return "Friends' Status";
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void initialize()
   {
      friendStatusTable = new FriendStatusTable(friendStatusManager, 
         facebookConnectionStatus);
      emptyItinerary = createEmptyPanel();
      
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      add(friendStatusTable);
      add(emptyItinerary);
      
      friendStatusManager.addDataChangeListener(new DataChangeListener()
      {
         @Override
         public void onDataChange()
         {
            setActivePanel();
         }
      });
      
      setActivePanel();
   }
   
   private void setActivePanel()
   {
      if(friendStatusManager.getFriendStatuses().size() > 0)
      {
         friendStatusTable.setVisible(true);
         emptyItinerary.setVisible(false);
      }
      else 
      {
         friendStatusTable.setVisible(false);
         emptyItinerary.setVisible(true);
      }
   }
   
   private VerticalPanel createEmptyPanel()
   {
      VerticalPanel emptyPanel = new VerticalPanel();
      
      emptyPanel.addStyleName("enterWaitTimePanel");
      
      emptyPanel.add(createClosedLabel());
      
      return emptyPanel;
   }
   
   private Widget createClosedLabel()
   {
      VerticalPanel labelPanel = new VerticalPanel();
      
      Widget shareButton = 
         facebookConnectionStatus.createShareButton("http://www.coasterq.com");
      
      Label emptylabel1 = new Label(
         "This page will display the ride status of your Facebook friends. " +
         "Tell your friends to enter ride wait times on CoaserQ the next time " +
         "they visit a theme park. ");
      
      emptylabel1.setStyleName("cq-Label");
      
      labelPanel.add(emptylabel1);
      
      labelPanel.setHorizontalAlignment(ALIGN_CENTER);
      
      labelPanel.addStyleName("enterWaitTimeSubPanel");
      
      labelPanel.add(shareButton);
      
      return labelPanel;
   }
}
