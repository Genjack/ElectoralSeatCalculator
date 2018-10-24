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
* FUNCTION: loadCandidates
* IMPORTS: String - filename;
*          DSALinkedList<Candidate> - List to load candidates into.
* PURPOSE:
*    To load the list of candidates from an imported .csv file.
* HOW IT WORKS:
*    After I retrieve each line, I need to check for the substring, and if I 
*    find it, replace it. Then count the line. 
*    Checking for "Shooters, Farmers".
* HOW IT RELATES:
**/
    public static DSALinkedList<Candidate> loadCandidates( String fileName,
        DSALinkedList<Candidate> cndList )
    {
        String line, trash;
        String [] splitArr = new String[10];

        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        try
        {
            fileStream = new FileInputStream( fileName );
            rdr = new InputStreamReader( fileStream );
            bufRdr = new BufferedReader( rdr );
           
            for( int ii = 0; ii < 2; ii++ )
            {
                trash = bufRdr.readLine();
            }

            line = bufRdr.readLine();
            
            while( line != null )
            {
                splitLine( line, splitArr );
                
                //Assemble Candidate object and store in array.
                makeCandidate( splitArr, cndList );
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
        return cndList;
    }//End Submodule.

/**
* FUNCTION: splitLine
* IMPORTS: String - line of the csv file to be split up.
* PURPOSE:
*    To parse a specific line of a file, ensure it is formatted correctly and
*    return the data via an array to loadCandidates() for further processing.
* HOW IT WORKS:
* HOW IT RELATES:
**/

    public static String [] splitLine( String line, String [] arr )
    {
        String sillyParty = "hooters, F";
        String slightlyBetter = "hooters F";
        String [] lineArr, shootersArr;
        
        if( line.contains( sillyParty ) )
        {
            line = line.replaceAll( sillyParty, slightlyBetter );
        }
        lineArr = line.split( "," ); //Should populate arr with candidate info
        for( int ii = 0; ii < lineArr.length; ii++ )
        {
            arr[ii] = lineArr[ii];
        }
        return arr;
    }

/**
* FUNCTION: makeCandidate
* IMPORTS: String [] - Array of csv comma-split attributes for a Candidate Obj
*          DSALinkedList<Candidate> - List to store Candidates in
* PURPOSE:
*    To create and store a Candidate Object from file.
* HOW IT WORKS:
* HOW IT RELATES:
**/
    public static void makeCandidate( String [] attr, 
        DSALinkedList<Candidate> list )
    {
        int divID;
        boolean elected;
        boolean histElected;
        int cndID;
        Candidate cnd;
        
        //parse booleans
        if( attr[8].equals( "Y" ) )
        {
            elected = true;
        }
        else
        {
            elected = false;
        }

        if( attr[9].equals( "Y" ) )
        {
            histElected = true;
        }
        else
        {
            histElected = false;
        }
        divID = Integer.parseInt( attr[1] );
        cndID = Integer.parseInt( attr[5] );

        /* Candidate Creation - Attribute array index listings:
            [0] = State Abbreviation; [1] = Division ID; [2] = Division name;
            [3] = Party Abbreviation; [4] = Party Name; [5] = Candidate ID;
            [6] = Surname; [7] = Given name; [8] = Elected Y/N;
            [9] = Historically Elected Y/N. */
        cnd = new Candidate( attr[0], divID, attr[2], attr[3], attr[4], cndID, 
            attr[6], attr[7], elected, histElected );

        list.insertLast( cnd, cnd.getSurname() );
    }
/*
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
