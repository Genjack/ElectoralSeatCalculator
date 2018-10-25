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
* FUNCTION: prepareToList
* IMPORTS: DSALinkedList<Candidate> - List for filling with filtered objects
* HOW IT WORKS:
* HOW IT RELATES:
**/
    public static DSALinkedList<Candidate> prepareToList( 
        DSALinkedList<Candidate> cndListMain )
    {
        try
        {
            String userChoice; //will be like "12" or "1"; need to parse chars.

            String filterMenu = "Filter the list by:\n [1] State\n [2] " +
            "Party\n [3] Division\n Please enter the corresponding number of " +
            "the attribute to sort by as a single number,\n i.e. 123 to sort by"
            + " all;\n Or press 0 to skip: ";
            
            String orderMenu = "Order the list by:\n [1] Surname\n [2] State\n"+
            " [3] Party\n [4] Division\nPlease enter the corresponding numbers "
            + "as a single entry i.e. 1234 to order by all\n Or press 0 to skip: ";

            //Ask the user if they want to filter their results before listing:
            userChoice = User.getString( filterMenu );
            
            if( userChoice.charAt(0) != '0' ) //If User wants to filter:
            {
                //Parse the string to extract chars and validate them.
                Validate.validateChoice( userChoice, '3' );
                /*Choice is valid if program reaches here
                Determine what to put into the linked list based on choice: */
                cndListMain = buildFilters( userChoice, cndListMain );
            }
            //If User does not enter above block, then no filters to be applied
            //Now continue - Does the User want to order by anything?
            userChoice = User.getString( orderMenu );
            if( userChoice.charAt(0) != '0' ) //If User wants to order:
            {
                Validate.validateChoice( userChoice, '4' );
                sortList( userChoice, cndListMain );
            }
            //Print list
            Iterator<Candidate> printer = cndListMain.iterator();
            if( ! ( printer.hasNext() ) )
            {
                System.out.println( "No results found. Consider adjusting " + 
                    "search parameters." );
            }
            while( printer.hasNext() )
            {
                Candidate cnd = printer.next();
                System.out.println( cnd.toString() );
            }
        }
        catch( ArrayIndexOutOfBoundsException ae )
        {
            System.out.println( "Error: Invalid selection. Please ensure you " +
                "choose a valid option, i.e. 1, or 12, 23, 123 etc." );
            ae.printStackTrace();
            prepareToList( cndListMain );
        }
        catch( IllegalArgumentException ie )
        {
            System.out.println( "Please ensure you choose a valid option, i.e. "
                + "1, 12, 23, 123 etc." );
            ie.printStackTrace();
            prepareToList( cndListMain );
        }//End catches
        return cndListMain;
    }//End prepareToList()

//****************************************************************************//
//************************** BUILD FILTERS FUNCTION **************************//
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
        /*boolean isValidFilter, filterState, filterParty, filterDiv;*/
        boolean [] selections = new boolean[3]; //Array to store selections.

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

//MODIFICATION
System.out.println( "HERE" );
        Utility.getFilters( userChoice, selections );
System.out.println( "true? " + selections[0] );
//selections Array now contains booleans: [0] name [1] state [2] party [3] div
        /*filterState = false;
        filterParty = false;
        filterDiv = false;*/

        filtList = new DSALinkedList<Candidate>();
/*
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
        } */
        //So now we have our filter selections registered. Time to filter.

        if( selections[0] ) //STATE boolean
        {
            state = getState( statePrompt );
        }
        if( selections[1] ) //PARTY boolean
        {
            party = getParty( partyPrompt );
        }
        if( selections[2] ) //DIV boolean
        {
            divId = getDiv( divPrompt );
        }

        //Apply filters to the array, and add to the cndList:
        while( cndList.getCount() > 0 )
        {
            Candidate compCnd;
            //remove first
            compCnd = ( Candidate )( cndList.removeFirst() );
            if( selections[0] )
            {
                if( compCnd.getStateAb().equals( state ) )
                {
                    if( selections[1] )
                    {
                        if( compCnd.getPartyAb().equals( party ) )
                        {
                            if( selections[2] ) //All three filters apply
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
                        if( selections[2] )
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
            else if( selections[1] ) //Check if it's PARTY and DIVISION
            {
                if( compCnd.getPartyAb().equals( party ) )
                {
                    if( selections[2] )
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
        System.out.println( "Total Results: " + filtList.getCount() );
        return filtList;
    }//End buildFilters()

//******************************** GET STATE *********************************//

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

//******************************** GET PARTY *********************************//

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

//******************************* GET DIVISION *******************************//

    public static int getDiv( String prompt )
    {
        int divId;
        //Modest validation is performed in inInput(); again, tough to validate
        //properly before reading it in without going overboard.
        divId = User.intInput( prompt, 100, 999 );
        return divId;
    }

//****************************************************************************//
//**************************** SORT LIST FUNCTION ****************************//

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
        boolean [] selections = new boolean[4]; //Array to store selections.
        int objCount = 0; //For keeping track of array insertion indexing

//MODIFICATION BOOLEANS
        Utility.getOrder( choice, selections );

        /*for( int ii = 0; ii < choice.length(); ii++ )
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
        }*/
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
        if( selections[1] )
        {
            Sorts.mergeSortState( sortArr ); 
        }
        if( selections[3] )
        {
            Sorts.mergeSortDivision( sortArr ); 
        }
        if( selections[2] )
        {
            Sorts.mergeSortParty( sortArr );
        }
        if( selections[0] )
        {
            Sorts.mergeSortName( sortArr );
        } 
        //Array is sorted according to user selection. Rebuild list:
        for( int ii = 0; ii < sortArr.length; ii++ )
        {
            list.insertLast( sortArr[ii], sortArr[ii].getSurname() );
        }
        return list;
    }

//****************************************************************************//
//************************** PREPARE TO SEARCH *******************************//

    public static DSALinkedList<Candidate> prepareToSearch( 
        DSALinkedList<Candidate> list )
    {
        String choice;
        String filterMenu = "Filter the list by:\n [1] State\n [2] " +
            "Party\n Please enter the corresponding number of " +
            "the attribute to sort by as a single number,\n i.e. 12 to sort by"
            + " all;\n Or press 0 to skip: ";
        String statePrompt = "Please enter the abbreviation for the State by " +
        "which you wish to filter (i.e. 'WA'): ";
        String partyPrompt = "Please enter the abbreviation for the party by " +
        "which you wish to filter (i.e. 'GRN', 'ON'): ";
        String state = "";
        String party = "";

        boolean [] selections = new boolean[2]; //Array to store selections.
        //List to store filtered objects in and return to main menu.
        DSALinkedList<Candidate> filtList = new DSALinkedList<Candidate>();
        //selections[1] = State; selections[2] = Party. 

        try
        {
            //Valid choices: 1, 2, or 12.
            choice = User.getString( filterMenu );

            if( choice.charAt(0) != '0' ) //If User chooses to filter:
            {
                //String should now contain '1', '2', or '12': VALIDATE:
                //Skipping use of Validate class owing to simplicity:
                if( ! ( ( choice.equals("1") ) || ( choice.equals( "2" ) ) || 
                    ( choice.equals( "12" ) ) ) ) //INVALID
                {
                    throw new IllegalArgumentException( "Error: Choice must be "
                    + "either '1', '2', '12' or '0' to skip." );
                }
                else //VALID - choice is either, 1, 2 or both.
                {
                    Utility.getSearchFilters( choice, selections );
                    if( selections[0] )
                    {
                        state = getState( statePrompt );
                    }
                    if( selections[1] )
                    {
                        party = getParty( partyPrompt );
                    }
                    //Filters chosen and set - now to apply:
                    while( list.getCount() > 0 )
                    {
                        Candidate cnd = list.removeFirst();
                        if( selections[0] )
                        {
                            if( cnd.getStateAb().equals( state ) )
                            {
                                if( selections[1] )
                                {
                                    if( cnd.getPartyAb().equals( party ) )
                                    {
                                        filtList.insertLast( cnd, party );
                                    }
                                }
                                else //Just filter by STATE
                                {
                                    filtList.insertLast( cnd, state );
                                }
                            }
                        }
                        else 
                        {
                            if( selections[1] ) //Just filtering by Party
                            {
                                if( cnd.getPartyAb().equals( party ) )
                                {
                                    filtList.insertLast( cnd, party );
                                }
                            }
                        }
                    }//End While Loop.
                }//End If/Else
            }  
        }//End Try
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            prepareToSearch( list );
        }
        return filtList;
    }//End prepareToSearch
}//End Format Class
