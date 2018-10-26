/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the File class for the DSA assignment, with methods
   relating to file I/O.
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

    public static DSALinkedList<Candidate> searchList( String term, DSALinkedList<Candidate> 
        list )
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
}
