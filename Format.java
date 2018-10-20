/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the File class for the DSA assignment, with methods
   relating to file I/O.
*/

import java.util.*;

public class Format
{
/**
* FUNCTION: buildFilters
* IMPORTS: String - effectively a char array that contains filter selections.
*          Candidate [] - The array of Candidate objects.
*          DSALinkedList<Candidate> - The cndList to populate with filtered elements.
* DESCRIPTION:
*    Breaks up the string, and uses an if/else block to search for the relevant
*    attributes by which to sort. It finds all instances in the array that
*    contain these elements, and adds them to the cndList. Nice
**/

    public static void buildFilters( String userChoice, Candidate [] cndArr,
        DSALinkedList<Candidate> cndList )
    {
        boolean isValidFilter, filterState, filterParty, filterDiv;
        //Strings to hold User selections:
        String state = ""; 
        String party = "";
        int divId = 0;
        
        //Prompts to display to user for input.
        String statePrompt = "Please enter the abbreviation for the State by " +
        "which you wish to filter (i.e. 'WA'): ";
        String partyPrompt = "Please enter the abbreviation for the party by " +
        "which you wish to filter (i.e. 'GRN', 'ON'): ";
        String divPrompt = "Please enter the division ID for the division by " +
        "which you wish to filter (3-digit number): ";

        filterState = false;
        filterParty = false;
        filterDiv = false;

        //Check the string, and trigger the flags for filter processing:
        for( int ii = 0; ii < userChoice.length(); ii++ )
        {
            char filter = userChoice.charAt(ii);
            if( filter == '1' ) //Filter by STATE
            {
                filterState = true;
            }
            if( filter == '2' ) //Filter by PARTY
            {
                filterParty = true;
            }
            if( filter == '3' ) //Filter by DIVISION
            {
                filterDiv = true;
            }
        }
        //So now we have our filter selections registered. Time to filter.

        if( filterState )
        {
            state = getState( statePrompt );
        }
        if( filterParty )
        {
            party = getParty( partyPrompt );
        }
        if( filterDiv )
        {
            divId = getDiv( divPrompt );
        }

        //Apply filters to the array, and add to the cndList:
        for( int ii = 0; ii < cndArr.length; ii++ )
        {
            if( filterState )
            {
                if( cndArr[ii].getStateAb().equals( state ) )
                {
                    if( filterParty )
                    {
                        if( cndArr[ii].getPartyAb().equals( party ) )
                        {
                            if( filterDiv ) //All three filters apply
                            {
                                if( cndArr[ii].getDivID() == divId )
                                {
                                    //Keys don't matter for insertLast
                                    cndList.insertLast( cndArr[ii], state );
                                }
                            }
                            else //Only checking STATE and PARTY
                            {
                                cndList.insertLast( cndArr[ii], party );
                            }
                        }
                    }
                    else //Check if it's STATE and DIVISION
                    {
                        if( filterDiv )
                        {
                            if( cndArr[ii].getDivID() == divId )
                            {
                                cndList.insertLast( cndArr[ii], state );
                            }
                        }
                        else //Only checking for STATE
                        {
                            cndList.insertLast( cndArr[ii], state );
                        }
                    }
                }
            }
            else if( filterParty ) //Check if it's PARTY and DIVISION
            {
                if( cndArr[ii].getPartyAb().equals( party ) )
                {
                    if( filterDiv )
                    {
                        if( cndArr[ii].getDivID() == divId )
                        {
                            cndList.insertLast( cndArr[ii], party );
                        }
                    }
                    else //Only checking for PARTY
                    {
                        cndList.insertLast( cndArr[ii], party );
                    }
                }
            }
            else //Just checking for DIVISION
            {
                if( cndArr[ii].getDivID() == divId )
                {
                    cndList.insertLast( cndArr[ii], party );
                }
            }
        }//End For Loop
    }//End buildFilters()

    public static String getState( String prompt )
    {
        String state;
        state = User.getString( prompt );
        state = state.toUpperCase(); //Ensure in upper case.
        //Check that state is a valid Australian State/Territory
        if( !( Validate.validateState( state ) ) )
        {
            throw new IllegalArgumentException( "Error: Invalid state" );
        }
        return state;
    }

    public static String getParty( String prompt )
    {
        String party;
        party = User.getString( prompt );
        party = party.toUpperCase();
        if( !( Validate.validateParty( party ) ) )
        {
            throw new IllegalArgumentException( "Error: Invalid abbreviation" );
        }
        return party;
    }

    public static int getDiv( String prompt )
    {
        int divId;
        //Modest validation is performed in inInput(); again, tough to validate
        //properly before reading it in without going overboard.
        divId = User.intInput( prompt, 100, 999 );
        return divId;
    }
}//End Format Class
