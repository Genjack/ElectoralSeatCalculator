/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Tutors: Valerie, Jason and Simeon

   File Contents: Menu.java
   Description: This is the main file for my DSA Assignment, containing the 
   main menu which will greet the user when they run the program. Much of the
   code here has been adapted from my Geo.java file I created for Practical 1
   of DSA, which in turn was taken in part from my OOPD assignment.
*/

import java.util.*;

public class Menu
{
    public static void run()
    {
        boolean exitNow = false; //Set to true when 4 is selected from menu.
        boolean fileRead = false;
        int keyPress, numCandidates;
        String fileName = "hc.csv"; 
        String menuMain = "Welcome. What would you like to do?\n [1]: List "
            + " Nominees\n [2]: Search Nominees\n [3]: List By Margin\n" +
            " [4]: Itinerary by Margin\n [5]: Exit";
        String c1Menu = "Would you like to Filter results?\n";

        Candidate [] cndArr; //The array to fill with Candidates (case 1).
        DSALinkedList<Candidate> cndList; //The list to filter Candidates into.
        
        do
        {
            try
            {
                keyPress = User.intInput( menuMain, 1, 5 );
                
                switch( keyPress )
                {
                    case 1:
                    {
                        //hc.csv is hardcoded in.
                        numCandidates = File.countCandidates( fileName );
                        //Declare array to store candidates from file.
                        cndArr = new Candidate[numCandidates];

                        File.getCandidates( fileName, numCandidates, cndArr );
                        cndList = new DSALinkedList<Candidate>();
                        prepareToList( cndArr, cndList );
                        //Array complete.
                    }                
                    break;
                    case 5:
                    {
                        exitNow = true;
                        System.out.println( "Goodbye." );
                        break;
                    }
                }
            }
            catch ( NullPointerException e )
            {
                e.printStackTrace( System.out );
            }
        } while ( exitNow == false );
    }

/**
* FUNCTION: prepareToList
* IMPORTS: Candidate [] - Array of Candidate objects (populated)
*          DSALinkedList<Candidate> - List for filling with filtered objects
* HOW IT WORKS:
* HOW IT RELATES:
**/
    public static void prepareToList( Candidate [] cndArr,
        DSALinkedList<Candidate> cndList )
    {
        try
        {
            String userChoice; //will be like "12" or "1"; need to parse chars.

            String filterMenu = "Filter the list by:\n [1] State\n" + " [2] Party\n "
            + "[3] Division\n Please enter the corresponding number of the attribute "
            + "to sort by as a single number,\n i.e. 123 to sort by all;\n Or press 0 "
            + "to skip: ";
            
            String orderMenu = "Order the list by: [1] Surname\n [2] State\n [3] " +
            "Party\n [4] Division\n Please enter the corresponding numbers as a " +
            "single entry i.e. 1234 to order by all\n Or press 0 to skip: ";

            //Ask the user if they want to filter their results before listing:
            userChoice = User.getString( filterMenu );
            
            if( userChoice.charAt(0) != '0' ) //If User does not skip:
            {
                //Parse the string to extract chars and validate them.
                Validate.validateChoice( userChoice );
                /*Choice is valid if program reaches here
                Determine what to put into the linked list based on choice: */
                Format.buildFilters( userChoice, cndArr, cndList );
            }
/*            else //User wants to skip filtering - so return list of all
            {
                //Maybe even just skip building a list completely, and sort array as is
                Format.buildUnfiltered( cndArr, cndList );
            }
            //Now continue - Does the User want to order by anything?
*/
        }
        catch( ArrayIndexOutOfBoundsException ae )
        {
            System.out.println( "Error: Invalid selection. Please ensure you " +
                "choose a valid option, i.e. 1, or 12, 23, 123 etc." );
            ae.printStackTrace();
            prepareToList( cndArr, cndList );
        }
        catch( IllegalArgumentException ie )
        {
            System.out.println( "Please ensure you choose a valid option, i.e. "
                + "1, 12, 23, 123 etc." );
            ie.printStackTrace();
            prepareToList( cndArr, cndList );
        }//End catches
    }//End prepareToList()

/*    
SUBMODULE: popLocArrays
IMPORT: containerArr (ARRAY OF StateClass), fillerArr (ARRAY OF LocationClass)
EXPORT: None (Pass by reference)
DESCRIPTION: This submodule takes in the array of StateClass objects and the
    array of all LocationClass objects, in order to put the LocationClass 
    objects in their correct State's classfield arrays through the use of 
    nested for loops. It's not very efficient due to redundant checks, but it
    works.

    public static void popLocArrays( StateClass [] containerArr, LocationClass 
        [] fillerArr )
    {
        
        for( int ii = 0; ii < containerArr.length; ii++ )
        {
            //Create temp array to store objects in
            LocationClass [] tempArr = containerArr[ii].getLocationsArr();
            //Create counter to keep track of objects in temp
            int placeCount = 0;
            for( int jj = 0; jj < fillerArr.length; jj++ )
            {
                if( fillerArr[jj].getState().equals(
                    containerArr[ii].getShortName() ) )
                {
                    tempArr[placeCount] = fillerArr[jj];
                    placeCount++;
                }
            }
            //Assign objects and reset for next loop:
            containerArr[ii].setLocationsArr( tempArr );
        }
    }

SUBMODULE: popStateArrays
IMPORT: containerArr (ARRAY OF CountryClass), fillerArr (ARRAY OF StateClass)
EXPORT: None (Pass by reference)
DESCRIPTION: This submodule takes in the array of CountryClass objects and the
    array of all StateClass objects, in order to put the StateClass objects 
    in their correct Country's classfield arrays through the use of nested for 
    loops. 

    public static void popStateArrays( CountryClass [] containerArr, 
        StateClass [] fillerArr )
    {
        
        for( int ii = 0; ii < containerArr.length; ii++ )
        {
            //Create temp array to store objects in
            StateClass [] tempArr = containerArr[ii].getStatesArr();
            //Create counter to keep track of objects in temp
            int placeCount = 0;
            for( int jj = 0; jj < fillerArr.length; jj++ )
            {
                if( fillerArr[jj].getCountry().equals(
                    containerArr[ii].getName() ) )
                {
                    tempArr[placeCount] = fillerArr[jj];
                    placeCount++;
                }
            }
            //Assign objects and reset for next loop:
            containerArr[ii].setStatesArr( tempArr );
        }
    }

SUBMODULE: postReadPhase
IMPORT: menuTwo (String), mainCountriesArr (ARRAY OF CountryClass)
EXPORT: None.
DESCRIPTION: This submodule is triggered once the file is read in, and
             launches a new menu post-read to give the User the option of
             outputting the objects to file or serialising.

    public static void postReadPhase( String menuTwo, CountryClass []
        mainCountriesArr )
    {
        boolean exitNow = false;
        do
        {
            int keyPress = User.intInput( menuTwo, 1, 4 );
            switch( keyPress )
            {
                case 1:
                {
                    FileClass.writeFile( mainCountriesArr );
                }
                break;
                case 2:
                {
                    for( int ii = 0; ii < mainCountriesArr.length; ii++ )
                    {
                        System.out.println( mainCountriesArr[ii] );
                    }
                }
                break;
                case 3:
                {
                    FileClass.saveCountries( mainCountriesArr );
                }
                break;
                case 4:
                {
                    System.out.println( "Exiting." );
                    exitNow = true;
                }
                break;                
            }
        } while( exitNow == false );
    }*/
} //End Class.
