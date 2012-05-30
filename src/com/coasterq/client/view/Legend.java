package com.coasterq.client.view;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.ParkInformation;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Legend extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public Legend(ParkInformation parkInformation, CQImageBundle cqImageBundle)
   {
      createPanel(parkInformation, cqImageBundle);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void createPanel(ParkInformation parkInformation, 
                            CQImageBundle cqImageBundle)
   {
      addStyleName("cq-Legend");
      
      Label mark = new Label("*");

      mark.addStyleName("cq-MarkLabel");
      
      add(mark);
      
      if(parkInformation.getIsFasspassAvailable())
      {
         HorizontalPanel fastpassPanel = createFastpassPanel(cqImageBundle);
      
         add(fastpassPanel);
      }
      
      HorizontalPanel heightRequirementPanel = 
         createHeightRequirementPanel(cqImageBundle);
      
      add(heightRequirementPanel);
   }
   
   private HorizontalPanel createHeightRequirementPanel(
                                                   CQImageBundle cqImageBundle)
   {
      HorizontalPanel heightRequirementPanel = new HorizontalPanel();
      
      AbstractImagePrototype heightRequirementImgPrototype = 
         cqImageBundle.heightRequirementIcon();
      
      Image image = heightRequirementImgPrototype.createImage();
      
      heightRequirementPanel.add(image);
      
      Label heightRequirement  = new Label("Height Requirement");
      
      heightRequirement.addStyleName("cq-Label");
      
      heightRequirementPanel.add(heightRequirement);
      
      return heightRequirementPanel;
   }

   private HorizontalPanel createFastpassPanel(CQImageBundle cqImageBundle)
   {
      HorizontalPanel fastpassPanel = new HorizontalPanel();
      
      AbstractImagePrototype fasspassImgPrototype = 
         cqImageBundle.fasspassIcon();
      
      Image image = fasspassImgPrototype.createImage();
      
      fastpassPanel.add(image);
      
      Label fastpass = new Label("Fastpass Available");

      fastpass.addStyleName("cq-Label");
      
      fastpassPanel.add(fastpass);
      return fastpassPanel;
   }
}
