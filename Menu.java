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
        int [] numPlaces = new int[4]; //Counter array for file reading.
        int keyPress;
        // numPlaces indexes 1-3 equal number of countries, states and locations
        // respectively.
        String fileName; 
        String menuOne = "Welcome. What would you like to do?\n [1]: Read in"
            + " the file\n [2]: De-Serialise\n [3]: Exit";
        String menuTwo = "What would you like to do?\n [1]: Write to File\n" +
        " [2]: Output to Screen\n [3]: Serialize\n [4]: Back to Main Menu";

        do
        {
            try
            {
                keyPress = User.intInput( menuOne, 1, 3 );
                
                switch( keyPress )
                {
                    case 1:
                    {
                        fileName = User.getString( "Enter file name: ");
                        FileClass.countPlaces( fileName, numPlaces );
                        System.out.println( numPlaces[1] + " countries, " + 
                            numPlaces[2] + " states and " + numPlaces[3] + 
                            " locations read in (" + numPlaces[0] +" total)");

                        //Declaration of initial storage arrays.
                        CountryClass [] mainCountriesArr =
                            new CountryClass[numPlaces[1]];
                        StateClass [] mainStatesArr =
                            new StateClass[numPlaces[2]];
                        LocationClass [] mainLocationsArr = 
                            new LocationClass[numPlaces[3]];

                        //Create objects:
                        FileClass.readFile( fileName, mainCountriesArr, 
                            mainStatesArr, mainLocationsArr );
                        
                        popLocArrays( mainStatesArr, mainLocationsArr );
                        
                        popStateArrays( mainCountriesArr, mainStatesArr );
                        fileRead = true;
                        postReadPhase( menuTwo, mainCountriesArr );
                        
                    }                
                    break;
            
                    case 2:
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
                        System.out.println( "Exiting." );
                        exitNow = true;
                        break;
                }
            }
            catch ( NullPointerException e )
            {
                e.printStackTrace( System.out );
            }
        } while ( exitNow == false );
    }
    
/*****************************************************************************
SUBMODULE: popLocArrays
IMPORT: containerArr (ARRAY OF StateClass), fillerArr (ARRAY OF LocationClass)
EXPORT: None (Pass by reference)
DESCRIPTION: This submodule takes in the array of StateClass objects and the
    array of all LocationClass objects, in order to put the LocationClass 
    objects in their correct State's classfield arrays through the use of 
    nested for loops. It's not very efficient due to redundant checks, but it
    works.
*****************************************************************************/

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

/*****************************************************************************
SUBMODULE: popStateArrays
IMPORT: containerArr (ARRAY OF CountryClass), fillerArr (ARRAY OF StateClass)
EXPORT: None (Pass by reference)
DESCRIPTION: This submodule takes in the array of CountryClass objects and the
    array of all StateClass objects, in order to put the StateClass objects 
    in their correct Country's classfield arrays through the use of nested for 
    loops. 
*****************************************************************************/

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

/*****************************************************************************
SUBMODULE: postReadPhase
IMPORT: menuTwo (String), mainCountriesArr (ARRAY OF CountryClass)
EXPORT: None.
DESCRIPTION: This submodule is triggered once the file is read in, and
             launches a new menu post-read to give the User the option of
             outputting the objects to file or serialising.
*****************************************************************************/

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
    }
} //End Class.
