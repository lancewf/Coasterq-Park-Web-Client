package com.coasterq.client.view;

import com.coasterq.client.connection.CQImageBundle;
import com.coasterq.client.data.Ride;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RideLegendControl extends VerticalPanel
{
   public RideLegendControl(Ride ride, 
                            CQImageBundle cqImageBundle)
   {
      if(ride.getHasFastPass())
      {
         AbstractImagePrototype fasspassImgPrototype = 
            cqImageBundle.fasspassIcon();
         
         Image image = fasspassImgPrototype.createImage();
         
         image.setTitle("There is a fastpass on the '" + ride.getName() + "' ride");
         
         add(image);
      }
      if(ride.getHeightRequirement() > 0)
      {
         AbstractImagePrototype heightRequirementImgPrototype = 
            cqImageBundle.heightRequirementIcon();
         
         Image image = heightRequirementImgPrototype.createImage();
         
         image.setTitle("There is a height requirement for the '" + ride.getName() + "' ride");
         
         add(image);
      }
   }
}
