/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the Validate class, containing methods associated with
   validation:
   
    > validateChoice( String ): Ensures that the User has entered a valid digit
      that will not break the program.
   
   Note: Exceptions thrown need to be caught elsewhere.
*/

import java.util.*;

public class Validate
{
    //EXCEPTIONS CAUGHT IN: prepareToList -> Menu.java
    public static void validateChoice( String choices )
    {
        /*Declare char array to store parsed strings. If an exception is thrown
          here, it could be because there's too many choices in the string - we
          should return the User to prepareToList(), where he/she can have
          another crack. */
        char [] options = new char[4];

        //Potential error: ArrayIndexOutOfBoundsException, if string > 4 chars.
        for( int ii = 0; ii < choices.length(); ii++ )
        {
            options[ii] = choices.charAt(ii);
        }
        if( choices.length() < 4 ) //Means this instance is for filter menu.
        {
            //Loop through length of char array (1-3) to ensure valid choices:
            for( int ii = 0; ii < options.length; ii++ )
            {
                if( ( options[ii] != '1' ) || ( options[ii] != '2' ) 
                    || ( options[ii] != '3' ) )
                {
                    //Entry here means an invalid entry for filtering.
                    throw new IllegalArgumentException("Error: invalid choice");
                }
            }
        }
        else //choices copy into char array is valid, so must be length 4:
        {
            for( int ii = 0; ii < options.length; ii++ )
            {
                if( ( options[ii] != '1' ) || ( options[ii] != '2' ) 
                    || ( options[ii] != '3' ) || ( options[ii] != '4' ) )
                {
                    //Entry here means an invalid entry for ordering.
                    throw new IllegalArgumentException("Error: invalid choice");
                }
            }
        }
    }//End validateChoice()

    //FUNCTION: Returns true if inState is equal to an Australian state/territory
    public static boolean validateState( String inState )
    {
        boolean isState = false;
        String [] states = {"WA", "NT", "SA", "QLD", "NSW", "VIC", "TAS", "ACT"};

        for( int ii = 0; ii < states.length; ii++ )
        {
            if( inState.equals( states[ii] ) )
            {
                isState = true;
            }
        }
        return isState;
    }//End validateState()
    
    //FUNCTION: Returns true if inParty is equal to an Australian party
    public static boolean validateParty( String inParty )
    {
        /*Most we can do here, short of hardcoding the entire list of about 46
          political parties, is check to see that the string is between 2 and 4
          characters long, which all party abbreviations adhere to; this at
          least reduces the chance we're searching through the list in vain.*/
        boolean isGoodLen = false;
        
        if( ( inParty.length() >= 2 ) && ( inParty.length() <= 4 ) )
        {
            isGoodLen = true;
        }
        return isGoodLen;
    }
}//End Validate Class
