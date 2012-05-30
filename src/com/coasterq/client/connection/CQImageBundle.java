package com.coasterq.client.connection;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface CQImageBundle extends ImageBundle
{

   /**
    * The height requirement image. 
    */
   @Resource("com/coasterq/client/images/height.png")
   public AbstractImagePrototype heightRequirementIcon();

   /**
    * The fastpass image. 
    */
   @Resource("com/coasterq/client/images/fastpass.png")
   public AbstractImagePrototype fasspassIcon();
}
