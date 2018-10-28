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
        String choice;
        try
        {
            String filterMenu = "Filter the list by:\n [1] State\n [2] " +
                "Party\n [3] Division\n Please enter the corresponding number of " +
                "the attribute to sort by as a single number,\n i.e. 123 to sort by"
                + " all;\n Or press 0 to skip: ";
            
            String orderMenu = "Order the list by:\n [1] Surname\n [2] State\n"+
                " [3] Party\n [4] Division\nPlease enter the corresponding numbers "
                + "as a single entry i.e. 1234 to order by all\n Or press 0 to skip: ";
                
            choice = User.getString( filterMenu );
            if( choice.charAt(0) != '0' ) //If User wants to filter:
            {
                //Parse the string to extract chars and validate them.
                Validate.validateChoice( choice, '3' );
                /*Choice is valid if program reaches here
                Determine what to put into the linked list based on choice: */
                cndListMain = buildFilters( choice, cndListMain );
            }
            //If User does not enter above block, then no filters to be applied
            //Now continue - Does the User want to order by anything?
            choice = User.getString( orderMenu );
            if( choice.charAt(0) != '0' ) //If User wants to order:
            {
                Validate.validateChoice( choice, '4' );
                sortList( choice, cndListMain );
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
                + "WA-NT-QLD-VIC-ACT-NSW-TAS-SA" );
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

        Utility.getFilters( userChoice, selections );
//selections Array now contains booleans: [0] name [1] state [2] party [3] div
        filtList = new DSALinkedList<Candidate>();

        if( selections[0] ) //User wants to filter by STATE
        {
            state = getState( statePrompt );
        }
        if( selections[1] ) //User wants to filter by PARTY
        {
            party = getParty( partyPrompt );
        }
        if( selections[2] ) //User wants to filter by DIVISION
        {
            divId = getDiv( divPrompt );
        }

        //Apply filters to the array, and add to the filtList:
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
        int objCount = 0; //For keeping track of array insertLastion indexing

//MODIFICATION BOOLEANS
        Utility.getOrder( choice, selections );


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

/**
* FUNCTION: prepareToSearch
* PURPOSE: To prepare the imported list for searching for a Candidate (Option 2)
*    The User enters a search term, and the list - filtered OR unfiltered - is
*    searched for any Candidates with a surname matching the key, which may be
*    a full name or partial. Any results are stored in a list, printed and 
*    returned.
**/
    public static DSALinkedList<Candidate> prepareToSearch( 
        DSALinkedList<Candidate> list )
    {
        String choice;
        String filterMenu = "Filter the list by:\n [1] State\n [2] " +
            "Party\n Please enter the corresponding number of " +
            "the attribute to sort by as a single number,\n i.e. 12 to sort by"
            + " all;\n Or press 0 to skip: ";

        int objCount = 0;
        //Create array for sorting of the list:
        Candidate [] sortArr = new Candidate[list.getCount()];

        //List to store filtered objects in and return to main menu.
        DSALinkedList<Candidate> list2= new DSALinkedList<Candidate>();
        //selections[1] = State; selections[2] = Party. 

        try
        {
            //Valid choices: 1, 2, or 12.
            choice = User.getString( filterMenu );

            if( choice.charAt(0) != '0' ) //If User chooses to filter:
            {
                filter( choice, list, list2 );
            }
            else //Skip filtering, return entirety (in IF/ELSE BLOCK )
            {
                while( list.getCount() > 0 )
                {
                    Candidate cnd = ( Candidate )( list.removeFirst() );
                    sortArr[objCount] = cnd;
                    objCount++;
                }//Array filled; send to Sort. SORT ALPHABETICALLY BY SURNAME
                Sorts.mergeSortName( sortArr );
                //Put back into list:
                for( int ii = 0; ii < sortArr.length; ii++ )
                {
                    list2.insertLast( sortArr[ii], sortArr[ii].getSurname() );
                }
            }  
        }//End Try
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
            prepareToSearch( list );
        }
        return list2;
    }//End prepareToSearch

//**************************** FILTERED SEARCH *******************************//
/**
* FUNCTION: filter
* PURPOSE: Similar to above, but filters before sorting and returning.
*    It works because it is called inside an if/else blcok; so if this is called,
*    the other sorting doesn't occur and vice versa. The end result is a sorted
*    list being returned to Menu.
**/
    public static DSALinkedList<Candidate> filter( String choice, 
        DSALinkedList<Candidate> list, DSALinkedList<Candidate> filtList )
    {
        //Already in a try catch from above
        Candidate [] sortArr;
        boolean [] selections = new boolean[2]; //Array to store selections.
        int objCount = 0;
        
        String statePrompt = "Please enter the abbreviation for the State by " +
        "which you wish to filter (i.e. 'WA'): ";
        String partyPrompt = "Please enter the abbreviation for the party by " +
        "which you wish to filter (i.e. 'GRN', 'ON'): ";
        String state = "";
        String party = "";
        
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
                                filtList.insertLast( cnd, cnd.getSurname() );
                            }
                        }
                        else //Just filter by STATE
                        {
                            filtList.insertLast( cnd, cnd.getSurname() );
                        }
                    }
                }
                else 
                {
                    if( selections[1] ) //Just filtering by Party
                    {
                        if( cnd.getPartyAb().equals( party ) )
                        {
                            filtList.insertLast( cnd, cnd.getSurname() );
                        }
                    }
                }
            }//End While Loop.
            sortArr = new Candidate[filtList.getCount()];
            while( filtList.getCount() > 0 )
            {
                Candidate cnd = ( Candidate )( filtList.removeFirst() );
                sortArr[objCount] = cnd;
                objCount++;
            }//Array filled; send to Sort.
            Sorts.mergeSortName( sortArr );
            //Put back into list:
            for( int ii = 0; ii < sortArr.length; ii++ )
            {
                filtList.insertLast( sortArr[ii], sortArr[ii].getSurname() );
            }
        }//End If/Else 
        return filtList;
    }

//****************************** MAKE SEAT ARRAY *****************************//

    public static DSAHashTable makeSeatArray( SeatChallenger [] masterArr,
        DSALinkedList<SeatChallenger> list )
    {
        int objCount = 0;
        String pty;
        DSAHashTable table = new DSAHashTable();
        Iterator<SeatChallenger> rator = list.iterator();

        //1. Transfer the list to the array.
        while( rator.hasNext() )
        {
            SeatChallenger seat = rator.next();
            masterArr[objCount] = seat;
            objCount++;
        }
        //2. Sort the array alphabetically by party name.
        Sorts.mergeSortPtySeat( masterArr );

        /*3. Make a pass through the array, retrieving the index of the first
             instance of each party, and creating a new hash table entry with
             this index and the associated party name abbreviation. */
        pty = masterArr[0].getCnd().getPartyAb(); 
        table.put( pty, 0 );

        for( int ii = 1; ii < masterArr.length; ii++ )
        {
            //Check if previous index is same as current; keep going until diff
            pty = masterArr[ii].getCnd().getPartyAb(); //i.e. 'LP'.
            if( !( pty.equals( masterArr[ii-1].getCnd().getPartyAb() ) ) )
            {
                //New party found due to alphabetical order - add to table.
                table.put( pty, ii );
            }
        }
        return table;
    } //End makeSeatArray
}//End Format Class
