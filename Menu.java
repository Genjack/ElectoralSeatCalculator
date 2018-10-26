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
import java.io.*;

public class Menu
{
    public static void run()
    {
        boolean exitNow = false; //Set to true when 4 is selected from menu.
        boolean cndFileRead = false;
        boolean seatFileRead = false;
        int keyPress;

        String candidateFile = "hc.csv";
        String seatFile = "hs.csv";
        String menuMain = "Welcome. What would you like to do?\n [1]: List "
            + " Nominees\n [2]: Search Nominees\n [3]: List By Margin\n" +
            " [4]: Itinerary by Margin\n [5]: Exit";
        String searchTerm = "";
        String searchPrompt = "Please enter all or part of the nominee's "
            + "surname: ";
        //Part 1/2
        DSALinkedList<Candidate> cndListAll; //List to read Candidates in to.
        DSALinkedList<Candidate> tempAll; //List for copying to preserve Master
        DSALinkedList<Candidate> cndListFilter; //List to filter into.
        cndListAll = new DSALinkedList<Candidate>();    //Master list.
        cndListFilter = new DSALinkedList<Candidate>(); //For part 1.
        DSALinkedList<Candidate> searches; //For part 2.

        //Part 3
        DSALinkedList<SeatChallenger> seatListAll; //Master Seat list.
        DSALinkedList<SeatChallenger> seatListMargins; //For party selection + filter
        seatListAll = new DSALinkedList<SeatChallenger>();
        marginList = new DSALinkedList<SeatChallenger>();
        
        do
        {
            try
            {
                keyPress = User.intInput( menuMain, 1, 5 );
                
                switch( keyPress )
                {
                    case 1: //**** READ IN FILE/LIST NOMINEES ****//
                    {
                        if( ! ( cndFileRead ) )
                        {
                            //Create linked list, read file contents into it.
                            File.loadCandidates( /*candidateFile*/"testLoad.txt", cndListAll );
                        }
                        //Copy contents to filter list for sorting, so that
                        //the main list is intact and can be manipulated later:
                        Iterator<Candidate> rator = cndListAll.iterator();
                        while( rator.hasNext() )
                        {
                            Candidate cnd = rator.next();
                            cndListFilter.insertLast( cnd, cnd.getSurname() );

                        }
                        cndListFilter = Format.prepareToList( cndListFilter );
                        //Need:
                            //1. Option to save cndListMain to file;
                        optionalSave( cndListFilter, "Filtered List" );                        
                        while( cndListFilter.getCount() > 0 )
                        {
                            cndListFilter.removeFirst(); //Cleanse list.
                        }
                        cndFileRead = true;
                    } 
                    break;
                    case 2: //**** NOMINEE SEARCH ****//
                    {
                        if( !( cndFileRead ) )
                        {
                            System.out.println( "Error: Read in file first by "
                                + "selecting option 1." );
                        }
                        else
                        {
                            searches = new DSALinkedList<Candidate>();
                            tempAll = new DSALinkedList<Candidate>();
                            Iterator<Candidate> rator = cndListAll.iterator();
                            while( rator.hasNext() )
                            {
                                Candidate cnd = rator.next();
                                tempAll.insertLast( cnd, cnd.getSurname() );
                            }
                            cndListFilter = Format.prepareToSearch( tempAll );
                            //^ Should now be filtered and sorted alphabetically.
                            //Get user input of a string, and search list for matches.
                            searchTerm = User.getString( searchPrompt );
                            searches = Utility.searchList( searchTerm, cndListFilter );
                            optionalSave( searches, "Searched Candidates" );
                            while( cndListFilter.getCount() > 0 )
                            {
                                cndListFilter.removeFirst(); //Cleanse list.
                            }
                        }
                    }
                    break;
                    case 3: //**** LIST BY MARGIN ****//
                    {
                        if( !( seatFileRead ) )
                        {
                            File.loadSeats( /*seatFile*/"testSeats.txt", seatListAll );
                        }
                        //File is read in. User should select a party to view:
                        Iterator<SeatChallenger> rator = seatListAll.iterator();
                        while( rator.hasNext() )
                        {
                            SeatChallenger seat = rator.next();
                            marginList.insertLast( seat, seat.cnd.getPartyAb() );
                        }
                        Utility.getMarginalSeats( marginList );
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

//CODE TAKEN FROM Practical 1, FileClass.java - writeFile()
    public static void optionalSave( DSALinkedList<Candidate> list, String type )
    {
        int choice;
        String fileName;
        String savePrompt = "Would you like to write this result to file?\n" +
        "[1] Yes\n[2] No";

        choice = User.intInput( savePrompt, 1, 2 );

        if( choice == 1 )
        {
            fileName = User.getString( "Please enter a name for your file:" );
        
            FileOutputStream flStrm = null;
            PrintWriter pw;
        
            try
            {
                flStrm = new FileOutputStream( fileName );
                pw = new PrintWriter( flStrm );

                pw.println( type );
                while( list.getCount() > 0 )
                {
                    Candidate cnd = ( Candidate )( list.removeFirst() );
                    pw.println( cnd.toString() );
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
        }
    }//End Submodule.           
} //End Class.
