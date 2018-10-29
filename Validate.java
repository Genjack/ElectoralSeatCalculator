/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the Validate class, containing methods associated with
   validation:
   
    > validateChoice( String ): Ensures that the User has entered a valid digit
      that will not break the program.
   
   Note: Exceptions thrown need to be caught elsewhere.

   REFERENCING: All original work.
*/

import java.util.*;

public class Validate
{
    //EXCEPTIONS CAUGHT IN: prepareToList -> Menu.java

    /*FUNCTION: Validate Choices for Filters and ordering
      The imported max value will set the limit on array length based on the
      function being individually validated (i.e. filter, order etc).*/
    public static void validateChoice( String choices, char max )
    {
        int ii = 0; //Counter for checking the array.
        int maxArrLen = max - '0'; //Gives the integer value of max.
        char [] options = new char[maxArrLen]; 
        fillArr( options, choices );

        while( ( ii < options.length ) && ( options[ii] != 0 ) )
        {
            if( ( options[ii] < '1' ) || ( options[ii] > max ) )
            {
                throw new IllegalArgumentException( "Error: Invalid choice." );
            }
            ii++; //Will catch ArrayIndexOutOfBounds exceptions in Format.java
        }
    }

    private static void fillArr( char [] arr, String choices )
    {
        //Fill the options array with these choices:
        for( int ii = 0; ii < choices.length(); ii++ )
        {
            arr[ii] = choices.charAt(ii);
        }
        //Check for ArrayIndexOutOfBoundsException here.
    }   

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
