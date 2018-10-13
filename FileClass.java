/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   Tutors: Unknown
*/

import java.util.*;
import java.io.*;

public class FileClass
{
    public static int [] countPlaces( String fileName, int [] counters )
    {
        // counters[0]: Total number of places;
        // counters[1]: Total number of countries;
        // counters[2]: number of states;
        // counters[3]: number of locations.
        String line, placeType;

        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        try
        {
            fileStream = new FileInputStream( fileName );
            rdr = new InputStreamReader( fileStream );
            bufRdr = new BufferedReader( rdr );
            
            line = bufRdr.readLine();
            
            while( ( line != null ) && (!( line.equals("") ) ) )
            { 
                placeType = processLocation( line );
                if( placeType != null )
                {
                    if( placeType.equals( "COUNTRY" ) )
                    {
                        counters[0] += 1;
                        counters[1] += 1;
                    }
                    else 
                    {
                        if( placeType.equals( "STATE" ) )
                        {
                            counters[0] += 1;
                            counters[2] += 1;
                        }
                        else
                        {
                            if (placeType.equals( "LOCATION" ) )
                            {
                                counters[0] += 1;
                                counters[3] += 1;
                            }
                            else
                            {
                                fileStream.close();
                                throw new IOException( "Invalid file.");
                            }
                        }
                    }
                }
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
            System.out.println( "Error: " + e.getMessage() );
        }
        return counters;
    }//End Submodule.

/*******************************************************************************
SUBMODULE: readFile
IMPORT: fileName (String), mainCountriesArr (ARRAY OF CountryClass),
    mainStatesArr (ARRAY OF StateClass), mainLocationsArr (ARRAY OF 
    LocationClass)
EXPORT: None.
DESCRIPTION: This submodule reads the file again, and creates the objects based
    on how the lines are split. Once they are created, it delegates the storage
    of these objects into an array and returns to main.
*******************************************************************************/

    public static void readFile( String fileName, CountryClass []
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

/*******************************************************************************
SUBMODULE: processLocation
IMPORT: line (String)
EXPORT: location (String)
DESCRIPTION: This method takes in the line being processed, and splits it
    according to commas, to return the first element in the array - the
    location.
*******************************************************************************/
    
    public static String processLocation( String line )
    {
        String location;
        String [] lineArray;
        
        lineArray = line.split( ":" );
        location = lineArray[0];

        return location;
    }
 

/*******************************************************************************
SUBMODULE: processString
IMPORT: line (String), details (ARRAY OF String)
EXPORT: None. (Pass by reference)
DESCRIPTION: This method takes in the line being processed, and breaks it up to
    return a string of the properties delimited by colons in the csv file.
*******************************************************************************/

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

        
/*******************************************************************************
SUBMODULE: makeNation
IMPORT: details (ARRAY OF String), numStates (Integer)
EXPORT: nation (CountryClass)
DESCRIPTION: This method takes in the required fields from the line previously
    processed and store in the details array, as well as the reference to the 
    countries storage array. It formats the data and creates a CountryClass
    object using the alternate constructor, before returning it. The numStates
    integer determines the length of the required classfield StateClass array,
    and is designed this way in order to avoid hardcoding (too many) numbers.
*******************************************************************************/

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

/*******************************************************************************
SUBMODULE: makeState
IMPORT: details (ARRAY OF String), numLocations (Integer)
EXPORT: state (StateClass)
DESCRIPTION: This method takes in the required fields from the line previously
    processed and store in the details array, as well as the reference to the 
    countries storage array. It formats the data and creates a StateClass
    object using the alternate constructor, before returning it. The 
    numLocations integer determines the length of the required classfield 
    LocationClass array. 
*******************************************************************************/

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

/*******************************************************************************
SUBMODULE: makeLocation
IMPORT: details (ARRAY OF String)
EXPORT: location (LocationClass)
DESCRIPTION: This method takes in the required fields from the line previously
    processed and stored in the details array. It formats the data and creates a 
    LocationClass object using the alternate constructor, before returning it. 
*******************************************************************************/

    public static LocationClass makeLocation( String [] details )
    {
        LocationClass location = new LocationClass( details[2], details[4], 
            details[8], details[10] );
        return location;
    }

/*****************************************************************************
SUBMODULE: writeFile
IMPORT: mainArr (ARRAY OF CountryClass)
EXPORT: None.
DESCRIPTION: The current array objects are written to file and saved, in csv
             format.
*****************************************************************************/

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

/*****************************************************************************
SUBMODULE: saveCountries
IMPORT: containerArr (ARRAY OF CountryClass)
EXPORT: None.
DESCRIPTION: This is the serialization method for the existing place objects.
             The User enters a file name, and the objects are serialized and 
             saved to this file.
*****************************************************************************/

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

/*****************************************************************************
SUBMODULE: load
IMPORT: None.
EXPORT: containerArr (ARRAY OF CountryClass)
DESCRIPTION: This is the serialization method for the existing place objects.
             The User enters a file name, and the objects are de-serialised 
             and returned.
*****************************************************************************/

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
    }
}
