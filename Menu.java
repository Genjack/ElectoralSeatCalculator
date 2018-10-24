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
        int keyPress;
        //hc.csv is hardcoded in.
        String fileName = "hc.csv";
        String menuMain = "Welcome. What would you like to do?\n [1]: List "
            + " Nominees\n [2]: Search Nominees\n [3]: List By Margin\n" +
            " [4]: Itinerary by Margin\n [5]: Exit";
        String c1Menu = "Would you like to Filter results?\n";

        DSALinkedList<Candidate> cndListMain; //List to read Candidates in to.
        
        do
        {
            try
            {
                keyPress = User.intInput( menuMain, 1, 5 );
                
                switch( keyPress )
                {
                    case 1:
                    {
                        //Create linked list, read file contents into it.
                        cndListMain = new DSALinkedList<Candidate>();
                        File.loadCandidates( fileName, cndListMain );
                        prepareToList( cndListMain );
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

/**************************** MOVE TO FORMAT.JAVA **************************
* FUNCTION: prepareToList
* IMPORTS: DSALinkedList<Candidate> - List for filling with filtered objects
* HOW IT WORKS:
* HOW IT RELATES:
**/
    public static void prepareToList( DSALinkedList<Candidate> cndListMain )
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
            
            if( userChoice.charAt(0) != '0' ) //If User wants to filter:
            {
                //Parse the string to extract chars and validate them.
                Validate.validateChoice( userChoice );
                /*Choice is valid if program reaches here
                Determine what to put into the linked list based on choice: */
                cndListMain = Format.buildFilters( userChoice, cndListMain );
            }
            //If User does not enter above block, then no filters to be applied
            //Now continue - Does the User want to order by anything?
            userChoice = User.getString( orderMenu );
            if( userChoice.charAt(0) != '0' ) //If User wants to order:
            {
                Validate.validateChoice( userChoice );
                Format.sortList( userChoice, cndListMain );

            
        }
        catch( ArrayIndexOutOfBoundsException ae )
        {
            System.out.println( "Error: Invalid selection. Please ensure you " +
                "choose a valid option, i.e. 1, or 12, 23, 123 etc." );
            ae.printStackTrace();
            prepareToList( cndListMain );
        }
        catch( IllegalArgumentException ie )
        {
            System.out.println( "Please ensure you choose a valid option, i.e. "
                + "1, 12, 23, 123 etc." );
            ie.printStackTrace();
            prepareToList( cndListMain );
        }//End catches
    }//End prepareToList()
} //End Class.
