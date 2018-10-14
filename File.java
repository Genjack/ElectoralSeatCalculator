/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the File class for the DSA assignment, with methods
   relating to file I/O.
*/

import java.util.*;
import java.io.*;


public class File
{

/**
* FUNCTION: sanitiseAndCount
* IMPORTS: String - filename;
* PURPOSE:
* HOW IT WORKS:
*    After I retrieve each line, I need to check for the substring, and if I 
*    find it, replace it. Then count the line. 
*    Checking for "Shooters, Farmers".
*    Try reading, checking, changing and printing each line to a new file.
* HOW IT RELATES:
**/
    public static int sanitiseAndCount( String fileName )
    {
        String line;
        int count = 0;

        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        try
        {
            fileStream = new FileInputStream( fileName );
            rdr = new InputStreamReader( fileStream );
            bufRdr = new BufferedReader( rdr );
            
            line = bufRdr.readLine();
            
            while( ( line != null ) )
            {
                splitLine( line, splitArr );
                //do stuff
                line = bufRdr.readLine(); 
            }
        }
        catch( IOException e )
        {
            if( fileStream != null )
            {
                try
                {
                    fileStream.close();
                }
                catch( IOException e2 )
                {
                    //Empty.
                }
            }
            e.printStackTrace( System.out );
        }
        return count;
    }//End Submodule.

    /*public static void readFile( String fileName, CountryClass []
        mainCountriesArr, StateClass [] mainStatesArr, LocationClass []
        mainLocationsArr )
    {
        //Initialisation of Array to store split Strings from csv file:
        String [] details = new String [8]; 
        //Initialisation of second array to split for a second time:
        String [] detailsTwo = new String[15];
        //Declaration of String for csv file line:
        String line;

        //Initialisation of counters to keep track of where each object will
        //be stored in the appropriate array after creation.
        int countryCount = 0;
        int stateCount = 0;
        int locationCount = 0;
        
        //Initialisation of integers denoting length of required classfield
        //arrays, to import and aid in construction of place objects.
        int numStates = mainStatesArr.length;
        int numLocations = mainLocationsArr.length;

        FileInputStream flStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        try
        {
            flStrm = new FileInputStream( fileName );
            rdr = new InputStreamReader( flStrm );
            bufRdr = new BufferedReader( rdr );

            line = bufRdr.readLine();

            while( ( line != null ) && (!( line.equals("") ) ) )
            {
                processString( line, details, ":" );
                //Split string again on equals sign
                processStringTwo( details, detailsTwo );

                if( details[0].equals( "COUNTRY" ) )
                {
                    //makeNation( line, details );
                    CountryClass newNation = makeNation(detailsTwo, numStates);
                    mainCountriesArr[countryCount] = newNation;
                    countryCount++;
                }
                else
                {
                    if( details[0].equals( "STATE" ) )
                    {
                        StateClass newState = makeState( detailsTwo,
                            numLocations );
                        mainStatesArr[stateCount] = newState;
                        stateCount++;
                    }
                    else
                    {
                        LocationClass newLocation = makeLocation( detailsTwo );
                        mainLocationsArr[locationCount] = newLocation;
                        locationCount++;
                    }
                }
                line = bufRdr.readLine();
            } //End of while loop.
            flStrm.close();
        }//End Try
        catch( IOException e )
        {
            if( flStrm != null )
            {
                try
                {
                    flStrm.close();
                }
                catch( IOException e2 )
                {
                    //Empty.
                }
            }
            e.printStackTrace( System.out );
        }
    }//After completion, all objects should be created and stored.


************ PROCESS LOCATION ***********


    public static String processLocation( String line )
    {
        String location;
        String [] lineArray;
        
        lineArray = line.split( ":" );
        location = lineArray[0];

        return location;
    }
 

************ PROCESS STRING ***********

    public static String [] processString( String line, String [] details, 
        String delimiter )
    {
        String [] lineArr;
        lineArr = line.split( delimiter );
        
        for( int ii = 0; ii < lineArr.length; ii++ )
        {
            details[ii] = lineArr[ii];
        }
        return details;
    }


************ PROCESS STRING TWO ***********

    public static void processStringTwo( String [] details, String [] 
        detailsTwo )
    {
        String tempStore = "";

        for( int ii = 0; ii < details.length; ii++ )
        {
            tempStore += details[ii];
            tempStore += "=";
        }
        processString( tempStore, detailsTwo, "=" );
    }


************ MAKE NATION ***********

    public static CountryClass makeNation( String [] details, int numStates )
    {
        double area;
        int population;
        StateClass [] statesArr = new StateClass [numStates];

        area = Double.parseDouble( details[8] );
        population = Integer.parseInt( details[10] );

        CountryClass nation = new CountryClass( details[2], details[6], area,
            population, details[12], statesArr );
        return nation;
    }

************ MAKE STATE ***********

    public static StateClass makeState( String [] details, int numLocations )
    {
        double area;
        int population;
        LocationClass [] locationsArr = new LocationClass [numLocations];

        area = Double.parseDouble( details[8] );
        population = Integer.parseInt( details[12] );

        StateClass state = new StateClass( details[2], details[4], details[6], 
            area, population, details[14], locationsArr );
        return state;
    }

************ MAKE LOCATION ***********

    public static LocationClass makeLocation( String [] details )
    {
        LocationClass location = new LocationClass( details[2], details[4], 
            details[8], details[10] );
        return location;
    }

************ WRITE FILE ***********

    public static void writeFile( CountryClass [] mainArr )
    {
        String fileName;

        fileName = User.getString( "Please enter a name for your file:" );
        
        FileOutputStream flStrm = null;
        PrintWriter pw;
        
        try
        {
            flStrm = new FileOutputStream( fileName );
            pw = new PrintWriter( flStrm );
            
            for( int ii = 0; ii < mainArr.length; ii++ )
            {
                pw.println( mainArr[ii] );
            }
            pw.close();
            flStrm.close();
        }
        catch( IOException e )
        {
            if( flStrm != null )
            {
                try
                {
                    flStrm.close();
                }
                catch( IOException e2 )
                {
                    //Empty.
                }
            }
            System.out.println( "Exception: " + e.getMessage() );
        }
    }//End Submodule.

    SAVE COUNTRIES***********************

    public static void saveCountries( CountryClass [] containerArr )
    {
        FileOutputStream flStrm;
        ObjectOutputStream objStrm;

        try
        {
            String fileName = User.getString( "Please enter a file name: ");
            
            flStrm = new FileOutputStream( fileName );
            objStrm = new ObjectOutputStream( flStrm );
            
            objStrm.writeObject( containerArr );
            objStrm.close();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

SUBMODULE: load
IMPORT: None.
EXPORT: containerArr (ARRAY OF CountryClass)
DESCRIPTION: This is the serialization method for the existing place objects.
             The User enters a file name, and the objects are de-serialised 
             and returned.

    public static CountryClass [] load() throws IllegalArgumentException
    {
        FileInputStream flStrm;
        ObjectInputStream objStrm;
        CountryClass [] rebuiltNations = null;

        try
        {
            String fileName = User.getString( "Please enter a file name: ");
            
            flStrm = new FileInputStream( fileName );
            objStrm = new ObjectInputStream( flStrm );
            
            rebuiltNations = (CountryClass [])objStrm.readObject();
            
            objStrm.close();
        }
        catch( ClassNotFoundException e )
        {
            System.out.println( "Error: Class not found" );
            e.printStackTrace();
        }
        catch( Exception e2 )
        {
            throw new IllegalArgumentException( "Unable to load." );
        }
        return rebuiltNations;
    } */
}
