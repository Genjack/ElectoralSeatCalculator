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
*    find it, replace it. Then create a Candidate object, store and return list.
*    Checking for "Shooters, Farmers".
* REFERENCE NOTES:
*    Implementation borrowed from my OOPD assignment, Semester one.
**/
    public static DSALinkedList<Candidate> loadCandidates( String fileName,
        DSALinkedList<Candidate> cndList )
    {
        String line, trash; //First two lines are descriptive; discard.
        String [] splitArr = new String[10]; //Array to store csv information.
        Candidate cnd; //Declaration of Candidate to be constructed each line.

        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        try
        {
            fileStream = new FileInputStream( fileName );
            rdr = new InputStreamReader( fileStream );
            bufRdr = new BufferedReader( rdr ); //File I/O boilerplate code
           
            //Read first two lines and leave in an unused string.
            for( int ii = 0; ii < 2; ii++ )
            {
                trash = bufRdr.readLine();
            }

            line = bufRdr.readLine();
            //Check for empty file - somewhat redundant as I hardcode it in
            if( line == null )
            {
                throw new IllegalArgumentException( "Error: File is empty." );
            }
            while( line != null )
            {
                splitLine( line, splitArr ); //Split line on ","
                
                //Assemble Candidate object and store in array.
                cnd = makeCandidate( splitArr, 0 );
                //Store the Candidate in the list...
                cndList.insertLast( cnd, cnd.getSurname() );
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
                    //Empty (nothing else to do)
                }
            }
            e.printStackTrace( System.out );
        }
        //... And return the list.
        return cndList;
    }//End Submodule.

/**
* FUNCTION: splitLine
* IMPORTS: String - line of the csv file to be split up.
* PURPOSE:
*    To parse a specific line of a file, ensure it is formatted correctly and
*    return the data via an array to loadCandidates() for further processing.
* HOW IT WORKS:
*    Splits the line on ","; checks if the "Shooters, Farmers and Fishers" party
*    unfortunately is a thing; if it unfortunately is a thing, then we replace
*    the line with a name with no comma to enable it to work with our methods.
*    Then we return the array for use in making a Candidate/SeatChallenger
**/

    private static String [] splitLine( String line, String [] arr )
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
*    int bp: The ballot position, not contained in hc.csv; imported so the 
*    method can be reused for hs.csv.
* PURPOSE:
*    To create and store a Candidate Object from file.
* HOW IT WORKS:
*    Takes in the array and ballot position, formats the Strings to fit their
*    actual data types, and then creates a Candidate object to store.
**/
    public static Candidate makeCandidate( String [] attr, int bp )
    {
        int divID;
        boolean elected;
        boolean histElected;
        int cndID;
        Candidate cnd;
        
        //parse booleans for whether Candidate is and/or was elected.
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
        //Turn the Division IDs and Candidate IDs into integers.
        divID = Integer.parseInt( attr[1] );
        cndID = Integer.parseInt( attr[5] );

        /* Candidate Creation - Attribute array index listings:
            [0] = State Abbreviation; [1] = Division ID; [2] = Division name;
            [3] = Party Abbreviation; [4] = Party Name; [5] = Candidate ID;
            [6] = Surname; [7] = Given name; [8] = Elected Y/N;
            [9] = Historically Elected Y/N.
           Adding a value of 0 to account for ballot position.*/
        cnd = new Candidate( attr[0], divID, attr[2], attr[3], attr[4], cndID, 
            attr[6], attr[7], elected, histElected, bp );
        return cnd;
    }

//**************************** LOAD SEATS FROM FILE **************************//

/**
* FUNCTION: loadSeats
* PURPOSE: Similar to loadCandidates(), but facilitates the creation of 
*    SeatChallenger objects instead.
**/
    public static DSALinkedList<SeatChallenger> loadSeats( String file, 
        DSALinkedList<SeatChallenger> list )
    {
        int ballotPosition;
        //Record disdainful expressions of apathetic idiocy:
        int totalInformalVotes = 0;
        int informalOccurrences = 0;

        String line, trash;
        String [] splitArr = new String[15]; //All Seat & candidate info.
        String [] makeArr = new String[10]; //For using in makeCandidate()
        //Need a linked list because I don't know the size of the file
        //Just storing in here while the file is read in.
        Candidate cnd;
        SeatChallenger seat;

        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        try
        {
            fileStream = new FileInputStream( file );
            rdr = new InputStreamReader( fileStream );
            bufRdr = new BufferedReader( rdr );
           
            for( int ii = 0; ii < 2; ii++ ) //Discard top two descriptive lines
            {
                trash = bufRdr.readLine();
            }

            line = bufRdr.readLine();
            
            while( line != null )
            {
                splitLine( line, splitArr );
                
                //Turn ballot position index into integer for processing:
                ballotPosition = Integer.parseInt( splitArr[8] );

                //Need to reformat array indexes to match makeCandidate():
                for( int ii = 0; ii < 3; ii++ )
                {
                    //state abbreviation, div ID, div Name
                    makeArr[ii] = splitArr[ii]; 
                }
                makeArr[3] = splitArr[11]; //party abbreviation
                makeArr[4] = splitArr[12]; //party name
                for( int ii = 5; ii < 8; ii++ )
                {
                    //Candidate ID, surname, given name
                    makeArr[ii] = splitArr[ii];
                }
                makeArr[8] = splitArr[9]; //Elected Y/N
                makeArr[9] = splitArr[10]; //Elected before Y/N
                //Check for informal votes/squandering of democratic privilege
                if( makeArr[6].equals( "Informal" ) )
                {
                    totalInformalVotes += Integer.parseInt( splitArr[13] );
                    informalOccurrences++; //record and display, don't use
                }
                else
                {
                    //Assemble Candidate object and store in array.
                    cnd = makeCandidate( makeArr, ballotPosition );
                    seat = makeSeat( splitArr, cnd );
                    list.insertLast( seat, seat.getPollName() );
                }

                line = bufRdr.readLine(); 
            }
            //Display number of insults to Pericles
            System.out.println( "Total informal votes found: " +
                totalInformalVotes + "\nTotal occurrences: " +
                informalOccurrences );
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
        return list;
    }//End Submodule.       

//****************************** MAKE SEATCHALLENGER *************************//

    //FUNCTION: Create SeatChallenger object and return.
    public static SeatChallenger makeSeat( String [] arr, Candidate cnd )
    {
        int votes, pollID;
        double swing;
        /*arr contents: [3] pollID; [4] pollName; [14] votes; [15] swing */
        SeatChallenger seat;
        swing = Double.parseDouble( arr[14] );
        votes = Integer.parseInt( arr[13] );
        pollID = Integer.parseInt( arr[3] );
        
        seat = new SeatChallenger( cnd, pollID, arr[4], votes, swing );
        return seat;
    }
} //END FILE CLASS
