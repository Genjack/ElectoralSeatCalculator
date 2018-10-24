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

    public static DSALinkedList<Candidate> buildFilters( String userChoice, 
        DSALinkedList<Candidate> cndList )
    {
        boolean isValidFilter, filterState, filterParty, filterDiv;
        DSALinkedList<Candidate> filtList; //list to store filtered candidates
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

        filtList = new DSALinkedList<Candidate>();

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
        while( cndList.getCount() > 0 )
        {
            Candidate compCnd;
            //remove first
            compCnd = ( Candidate )( cndList.removeFirst() );
            if( filterState )
            {
                if( compCnd.getStateAb().equals( state ) )
                {
                    if( filterParty )
                    {
                        if( compCnd.getPartyAb().equals( party ) )
                        {
                            if( filterDiv ) //All three filters apply
                            {
                                if( compCnd.getDivID() == divId )
                                {
                                    //Keys don't matter for insertLast
                                    filtList.insertLast( compCnd, state );
                                }
                            }
                            else //Only checking STATE and PARTY
                            {
                                filtList.insertLast( compCnd, party );
                            }
                        }
                    }
                    else //Check if it's STATE and DIVISION
                    {
                        if( filterDiv )
                        {
                            if( compCnd.getDivID() == divId )
                            {
                                filtList.insertLast( compCnd, state );
                            }
                        }
                        else //Only checking for STATE
                        {
                            filtList.insertLast( compCnd, state );
                        }
                    }
                }
            }
            else if( filterParty ) //Check if it's PARTY and DIVISION
            {
                if( compCnd.getPartyAb().equals( party ) )
                {
                    if( filterDiv )
                    {
                        if( compCnd.getDivID() == divId )
                        {
                            filtList.insertLast( compCnd, party );
                        }
                    }
                    else //Only checking for PARTY
                    {
                        filtList.insertLast( compCnd, party );
                    }
                }
            }
            else //Just checking for DIVISION
            {
                if( compCnd.getDivID() == divId )
                {
                    filtList.insertLast( compCnd, party );
                }
            }
        }//End For Loop
        return filtList;
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

    public static DSALinkedList<Candidate> sortList( String choice,
        DSALinkedList<Candidate> list )
    {
/*When sorting, I will always start with the largest domain, so the order
  is as follows: 
        > STATE
        > DIVISION
        > PARTY
        > NAME
    Any variants on the selection will follow this format accordingly. */
        Candidate [] sortArr; //Array to fill, sort, and put back to list.
        boolean isValidOrder, ordName, ordState, ordPty, ordDiv;
        int objCount = 0; //For keeping track of array insertion indexing
        
        ordName = false;        
        ordState = false;        
        ordPty = false;        
        ordDiv = false;        

        for( int ii = 0; ii < choice.length(); ii++ )
        {
            char order = choice.charAt(ii);
            if( order == '1' ) //Order by SURNAME
            {
                ordName = true;
            }
            if( order == '2' ) //Order by STATE
            {
                ordState = true;
            }
            if( order == '3' ) //Order by PARTY
            {
                ordPty = true;
            }
            if( order == '4' ) //Order by DIVISION
            {
                ordDiv = true;
            }
        }
        sortArr = new Candidate[list.getCount()]; //Array size of list.
        //Order selection/s registered. 
        //Sorting begins - outside the loop in case the User (accidentally or
        // otherwise enters a sort category twice (i.e. '1123', '22' etc ).
        while( list.getCount() > 0 )
        {
            Candidate cnd = ( Candidate )( list.removeFirst() );
            sortArr[objCount] = cnd;
            objCount++;
        }
        //Array now contains filtered list.
        if( ordState )
        {
            Sorts.mergeSortState( sortArr ); 
        }
        if( ordDiv )
        {
            Sorts.mergeSortDivision( sortArr ); 
        }
        if( ordPty )
        {
            Sorts.mergeSortParty( sortArr );
        }
        if( ordName )
        {
            Sorts.mergeSortName( sortArr );
        } 
        for( int ii = 0; ii < sortArr.length; ii++ )
        {
            System.out.println( sortArr[ii].toString() );
        }
        return list;
    }
}//End Format Class
