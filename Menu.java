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
        String fileName; 
        String menuMain = "Welcome. What would you like to do?\n [1]: List "
            + " Nominees\n [2]: Search Nominees\n [3]: List By Margin\n" +
            " [4]: Itinerary by Margin\n [5]: Exit";
        String c1Menu = "Would you like to Filter results?\n";

        Candidate [] cndArr; //The array to fill with Candidates (case 1).
        //maybe have sub menus for filtering result choices.
        
        do
        {
            try
            {
                keyPress = User.intInput( menuMain, 1, 5 );
                
                switch( keyPress )
                {
                    case 1:
                    {
                        fileName = User.getString( "Enter file name: ");
                        numCandidates = File.countCandidates( fileName );
                        //Declare array to store candidates from file.
                        cndArr = new Candidate[numCandidates];

                        File.getCandidates( fileName, numCandidates, cndArr );
                        //Array complete.
                    }                
                    break;
            
                    /*case 2:
                    {
                        if( fileRead )
                        {
                            System.out.println( "Error: Contents already exist.");
                        }
                        else
                        {
                            CountryClass [] nations;
                            nations = FileClass.load();
                            postReadPhase( menuTwo, nations );
                        }
                        break;
                    case 3:
                    {
                        System.out.println( "Exiting." );
                        exitNow = true;
                        break;
                    }
                    case 4:
                    {
                        break;
                    }*/
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
