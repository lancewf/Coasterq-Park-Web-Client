package com.coasterq.client.data;

public class FuzzySet
{
// -----------------------------------------------------------------------------
// #region Private Data
// -----------------------------------------------------------------------------
   
   private double minimumZeroValueX;

   private double maximumZeroValueX;

   private double oneValueX;

// #endregion

// -----------------------------------------------------------------------------
// #region Constructor
// -----------------------------------------------------------------------------

   /**
    * Create a fuzzy set with minZeroValueX1 at min maximumZeroValueX1 at max
    * and oneValueX1 at one on the graph below
    * 
    * M 1|
    * e  |      _-|-_
    * M  |    _-  |  -_
    * B  |  _-    |    -_
    * E 0|________________
    * R  |min    one   max  : value
    * 
    */
   public FuzzySet(double minZeroValueX1, double maximumZeroValueX1,
         double oneValueX1)
   {
      minimumZeroValueX = minZeroValueX1;
      maximumZeroValueX = maximumZeroValueX1;
      oneValueX = oneValueX1;
   }

   /**
    * Creates a default fuzzy set with values min 0, one value 0.5, and max 1
    * 
    * M 1|
    * e  |      _-|-_
    * M  |    _-  |  -_
    * B  |  _-    |    -_
    * E 0|________________
    * R  |0      0.5    1  : value
    * 
    */
   public FuzzySet()
   {
      this(0, 0.5, 1);
   }
   
// #endregion
   
// -----------------------------------------------------------------------------
// #region Public Members
// -----------------------------------------------------------------------------

   /**
    * Set the value one on the graph below
    * 
    * M 1|
    * e  |      _-|-_
    * M  |    _-  |  -_
    * B  |  _-    |    -_
    * E 0|________________
    * R  |min    one    max  : value
    *  
    */
   public void setOneValueX(double oneValueX)
   {
      this.oneValueX = oneValueX;
   }
   
   /**
    * Set the minimum zero value. A value less than the minimum zero value 
    * always has a membership value of one 
    * 
    * @param minimumZeroValueX if the minimum value is 
    * negitive infinity pass in Double.MIN_VALUE, which would look like below
    * M 1|
    * e  |--------|-_
    * M  |        |  -_
    * B  |        |    -_
    * E 0|________________
    * R  |min    one   max  : value
    * 
    */
   public void setMinimumZeroValueX(double minimumZeroValueX)
   {
      this.minimumZeroValueX = minimumZeroValueX;
   }
   
   /**
    * Set the maximum zero value. This value is the point where any crisp
    * value greater than always has a membership value of one
    *  
    * @param maximumZeroValueX if the maximum value is positive infinity pass in
    * Double.MAX_VALUE, which would look link below.
    * 
    * M 1|      
    * e  |      _-|---------------
    * M  |    _-  |  
    * B  |  _-    |    
    * E 0|________________
    * R  |min    one   max  : value
    * 
    */
   public void setMaximumZeroValueX(double maximumZeroValueX)
   {
      this.maximumZeroValueX = maximumZeroValueX;
   }
   
   /**
   * This method finds the membership value from the 
   * 
   * M 1|
   * e  |      _-|-_
   * M  |    _-  |  -_
   * B  |  _-    |    -_
   * E 0|________________
   * R  |min    one   max  : value
   * 
   * @param crispValue The X value
   * @return the membership value a double between 0 and 1. 
   */
   public double getMemberValue(double crispValue)
   {
      double value = 0;
      
      if (crispValue == oneValueX)
      {
         value = 1;
      }
      else if (crispValue > oneValueX && crispValue <= maximumZeroValueX)
      {
         value = maximumMemberValue(crispValue);
      }
      else if (crispValue < oneValueX && crispValue >= minimumZeroValueX)
      {
         value = minimumMemberValue(crispValue);
      }
      else
      { //crispValue > maximumZeroValueX || crispValue < minimumZeroValueX
         value = 0;
      }
      
      return applyIndeedHedgeFunction(value);
   }

   public double SingletonValue()
   {
      return oneValueX;
   }
 
// #endregion
   
// -----------------------------------------------------------------------------
// #region Private Members
// -----------------------------------------------------------------------------
   /**
    * Indeed Hedge function. 
    * 
    * @return 
    * @param object $membershipValue
    */
   private double applyIndeedHedgeFunction(double membershipValue)
   {
      if(membershipValue <= 0.5)
      {
         return 2 * Math.pow(membershipValue, 2);
      }
      else
      {
         return 1 - 2 * Math.pow((1 - membershipValue), 2);
      }
   }
   
   /**
    * This method finds the membership value on the Left side of the one Value
    * 
    * M 1|
    * e  |      _-|-_
    * M  |    _-  |  -_
    * B  |  _-    |    -_
    * E 0|________________
    * R  |min    one   max  : value
    * 
    * @param crispValue The X value
    * @return the membership value
    */
   private double minimumMemberValue(double crispValue)
   {
      double slope;
      double y_intercept;

      if (minimumZeroValueX == Double.MIN_VALUE)
      {
         return 1;
      }
            // rise   /                run
      slope = (0 - 1) / (minimumZeroValueX - oneValueX);
      
      
      y_intercept = (-1) * slope * minimumZeroValueX;

      return slope * crispValue + y_intercept;
   }

   /**
    * This method finds the membership value on the right side of the one Value
    * 
    * M 1|
    * e  |      _-|-_
    * M  |    _-  |  -_
    * B  |  _-    |    -_
    * E 0|________________
    * R  |min    one   max  : value
    * @param crispValue The X value
    * @return the membership value
    */
   private double maximumMemberValue(double crispValue)
   {
      double slope;
      double y_intercept;

      if (maximumZeroValueX == Double.MAX_VALUE)
      {
         return 1;
      }

      slope = 1 / (oneValueX - maximumZeroValueX);
      y_intercept = (-1) * slope * maximumZeroValueX;

      return slope * crispValue + y_intercept;
   }
   
// #endregion
}
