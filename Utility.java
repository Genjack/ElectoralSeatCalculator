/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the File class for the DSA assignment, with methods
   relating to file I/O.

   REFERENCING: None, this is all original.
*/

import java.util.*;

public class Utility
{

//**************************** GET FILTERS ***********************************//

    //Function: Parse user choice string and adjust array of booleans.
    public static void getFilters( String choices, boolean [] arr )
    {
        boolean stateBool, partyBool, divBool; 

        stateBool = false;
        partyBool = false;
        divBool = false;       

        //Check the string, and trigger the flags for filter processing:
        for( int ii = 0; ii < choices.length(); ii++ )
        {
            char filter = choices.charAt(ii);
            if( filter == '1' ) //Filter by STATE
            {
                stateBool = true;
            }
            if( filter == '2' ) //Filter by PARTY
            {
                partyBool = true;
            }
            if( filter == '3' ) //Filter by DIVISION
            {
                divBool = true;
            }
        }    

        arr[0] = stateBool;
        arr[1] = partyBool;
        arr[2] = divBool;
    } //Pass by reference.

//******************************* GET ORDER **********************************//

    public static void getOrder( String choices, boolean [] arr )
    {
        boolean nameBool, stateBool, partyBool, divBool; 

        nameBool = false;
        stateBool = false;
        partyBool = false;
        divBool = false;       

        //Check the string, and trigger the flags for filter processing:
        for( int ii = 0; ii < choices.length(); ii++ )
        {
            char filter = choices.charAt(ii);
            if( filter == '1' ) //Filter by STATE
            {
                nameBool = true;
            }
            if( filter == '2' ) //Filter by PARTY
            {
                stateBool = true;
            }
            if( filter == '3' ) //Filter by DIVISION
            {
                partyBool = true;
            }
            if( filter == '4' )
            {
                divBool = true;
            }
        }    

        arr[0] = nameBool;
        arr[1] = stateBool;
        arr[2] = partyBool;
        arr[3] = divBool;
    }

//************************** GET SEARCH FILTERS ******************************//

    //Function: Parse user choice string and adjust array of booleans.
    public static void getSearchFilters( String choices, boolean [] arr )
    {
        boolean stateBool, partyBool;

        stateBool = false;
        partyBool = false;

        //Check the string, and trigger the flags for filter processing:
        for( int ii = 0; ii < choices.length(); ii++ )
        {
            char filter = choices.charAt(ii);
            if( filter == '1' ) //Filter by STATE
            {
                stateBool = true;
            }
            if( filter == '2' ) //Filter by PARTY
            {
                partyBool = true;
            }
        }    

        arr[0] = stateBool;
        arr[1] = partyBool;
    }

//******************************* SEARCH LIST *******************************//

    public static DSALinkedList<Candidate> searchList( String term, 
        DSALinkedList<Candidate> list )
    {
        boolean found = false;
        boolean passed = false;
        Candidate cnd = null;
        char firstChar;
        DSALinkedList<Candidate> matches = new DSALinkedList<Candidate>();
        Iterator rator = list.iterator();

        term = term.toUpperCase(); //Ensure in uppercase.
        
        while( ( rator.hasNext() ) && ( ( !found ) || ( !passed ) ) )
        {
            cnd = ( Candidate )( rator.next() ); 
            firstChar = cnd.getSurname().charAt(0); //i.e. A in Abbot
            if( firstChar == term.charAt(0) )
            {
                found = true; //In alphabetical, first instance of letter found
            }
            //So if the first letter of surname equals first letter of search
            //term, AND the whole search term is contained in the surname:
            if( ( cnd.getSurname().charAt(0) == ( term.charAt(0) ) &&
                ( cnd.getSurname().contains( term ) ) ) )
            {
                //Then add it.
                matches.insertLast( cnd, cnd.getSurname() );
            }
            if( ( firstChar != term.charAt(0) ) && found ) 
            {
                passed = true; //Passed all potential search matches; end.
            } //Just saves searching an alphabetical list redundantly.
        }
        if( matches.getCount() == 0 )
        {
            System.out.println( "Error: No results found." );
        }
        else
        {
            Iterator ratorM = matches.iterator();
            while( ratorM.hasNext() )
            {
                cnd = ( Candidate )( ratorM.next() );
                System.out.println( cnd.toString() );
            }
        }
        return matches;
    } 

//************************** GET MARGINAL SEATS ******************************//

    /* Function: Searches the array of seats for a particular party using a 
       hash table, and calculates any marginal seats by division.*/
    public static DSALinkedList<String> getMarginalSeats( SeatChallenger [] arr,
        DSAHashTable table )
    {
        String party;
        int ptyIdx, divID;
        int ii = 0;
        double votesFor, votesAgainst, threshold;
        DSALinkedList<Integer> divsToCheck = new DSALinkedList<Integer>();
        DSALinkedList<String> marginLog = new DSALinkedList<String>();
        String partyPrompt = "Please enter the abbreviation for the party" +
        " (i.e. 'GRN', 'ON'): ";
        String marginPrompt = "Please enter the margin threshold as a number\n" + 
            " Or press 0 for default of +/- 6: ";

        try
        {
            party = User.getString( partyPrompt );
            threshold = User.realInput( marginPrompt, -50.0, 50.0 );
            if( Math.abs( threshold ) == 0 )
            {   
                threshold = 6.0;
            }

            ptyIdx = (int)(table.get( party )); //Test: GRN index = 64.
            while( arr[ptyIdx].getCnd().getPartyAb().equals( party ) )
            {
                //Finds all instances of Green party candidates in divisions.
                divID = arr[ptyIdx].getCnd().getDivID();
                if( ( divsToCheck.getCount() == 0 ) || 
                    ( (int)(divsToCheck.peekAtLast() ) != divID) )
                {
                    divsToCheck.insertLast( divID, party );
                }
                ptyIdx++;
            }
            //Sort array by division
            Sorts.mergeSortDivSeat( arr );
            /* Go through array, checking the divs stored in the list, and 
               tallying the votes for the party chosen and others. Then,
               if the div changes, add up the previous div's votes and check if
               it's a marginal seat - if so, add to the marginalList. */
            Iterator rator = divsToCheck.iterator();
            while( rator.hasNext() )
            {
                votesFor = 0.0;
                votesAgainst = 0.0;
                int div = (int)(rator.next()); //Say it's 200
                while( ( ii < arr.length ) && ( arr[ii].getCnd().getDivID() == div ) )
                {
                    if( arr[ii].getCnd().getPartyAb().equals( party ) )
                    {
                        votesFor += arr[ii].getVotes();
                    }
                    else
                    {
                        votesAgainst += arr[ii].getVotes();
                    }
                    ii++;
                }
                //Div finished; print this set of results:
                double totalPct = ( (votesFor/(votesFor+votesAgainst))*100.0);
                double margin = totalPct - 50.0; 
                if( Math.abs( margin ) < Math.abs( threshold ) )
                {
                    String log = "===DIVISION " + div + "===\nMarginal Seat " +
                        "for: " + party +"\nTotal votes: " + (int)votesFor +
                        "\nMargin: " + margin + "%\n";
                    marginLog.insertLast( log, log );
                    System.out.println( log );
                }
                else
                {
                    System.out.print( "Checking division " + div + "..." );
                    System.out.println( " Outside Threshold." );
                }
            }
            
        }
        catch( IllegalArgumentException ie )
        {
            System.out.println( "Error: Party not found. Please try again." );
            getMarginalSeats( arr, table );
        }
        return marginLog;
    }
}
