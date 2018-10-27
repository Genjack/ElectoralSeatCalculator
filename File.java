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
        Candidate cnd;

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
            if( line == null )
            {
                throw new IllegalArgumentException( "Error: File is empty." );
            }
            while( line != null )
            {
                splitLine( line, splitArr );
                
                //Assemble Candidate object and store in array.
                cnd = makeCandidate( splitArr, 0 );
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
* HOW IT RELATES:
**/
    public static Candidate makeCandidate( String [] attr, int bp )
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
            [9] = Historically Elected Y/N.
           Adding a value of 0 to account for ballot position.*/
        cnd = new Candidate( attr[0], divID, attr[2], attr[3], attr[4], cndID, 
            attr[6], attr[7], elected, histElected, bp );
        return cnd;
    }

//**************************** LOAD SEATS FROM FILE **************************//

    public static DSALinkedList<SeatChallenger> loadSeats( String file, 
        DSALinkedList<SeatChallenger> list )
    {
        int ballotPosition;
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
           
            for( int ii = 0; ii < 2; ii++ )
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
                    makeArr[ii] = splitArr[ii];
                }
                makeArr[3] = splitArr[11];
                makeArr[4] = splitArr[12];
                for( int ii = 5; ii < 8; ii++ )
                {
                    makeArr[ii] = splitArr[ii];
                }
                makeArr[8] = splitArr[9];
                makeArr[9] = splitArr[10];
                //Check for informal votes/squandering of democratic privilege
                if( makeArr[6].equals( "Informal" ) )
                {
                    totalInformalVotes += Integer.parseInt( splitArr[13] );
                    informalOccurrences++;
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
