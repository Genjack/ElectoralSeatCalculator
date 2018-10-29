 /* Name: Tim J. Dempsey
    Student ID: 19390664
    Unit and Time: DSA @ Wednesdays, 12pm
    File Name: SeatChallenger
    Contents: Java for the creation of a SeatChallenger object, with class fields to
    store a political candidate.
    
    REFERENCING: The template for this code is loosely based on my MeatClass for  
    OOPD Assignment, Semester One 2018.  
    Date Last Modified: 26th October, 2018
*/

import java.util.*;

public class SeatChallenger
{
    /********************* Private class fields **************************/
    //Candidate - The particular person challenging for the seat.
    private Candidate cnd;

    //Poll ID - 4 or 5 digit integer representing polling place within state.
    private int pollID;

    //Poll name - String representing name of state poll place.
    private String pollName;

    //Ordinary Votes - Number of votes this Challenger received for the seat.
    private int ordVotes;

    //Swing - Real number representing the change in voter support between elections.
    private double swing;

/**
* DEFAULT CONSTRUCTOR
* Purpose: Creates a default instance of SeatChallenger.
**/
    public SeatChallenger()
    {
        cnd = new Candidate();
        pollID = 0000;
        pollName = "null";
        ordVotes = 0;
        swing = 0.0;
    }

/**
* ALTERNATE CONSTRUCTOR
* Purpose: Creates a SeatChallenger using imported values.
**/

    public SeatChallenger( Candidate inCnd, int inPollID, String inPollName, 
        int inOrdVotes, double inSwing )
    {
        setCnd( inCnd );
        setPollID( inPollID );
        setPollName( inPollName );
        setVotes( inOrdVotes );
        setSwing( inSwing );
    }

//Won't need a copy constructor - no point duplicating a unique candidate

//********************************** ACCESSORS *******************************//

    public Candidate getCnd()
    {
        return cnd.clone();
    }

    public int getPollID()
    {
        return pollID;
    }

    public String getPollName()
    {
        return pollName;
    }
    
    public int getVotes()
    {
        return ordVotes;
    }

    public double getSwing()
    {
        return swing;
    }

//********************************** MUTATORS *******************************//
/* Why have a mutator? Because I haven't really though through my TDD, so not
   currently sure how validation's going to work. I'll build some in here now,
   so that if they try to create an imitation file, it will handle it. */

    public void setCnd( Candidate inCnd )
    {
        String stateAb, divName, partyAb, partyName, surname, firstName;
        int divID, cndID, ballotPos;
        boolean elected, histElected;

        stateAb = inCnd.getStateAb();
        divID = inCnd.getDivID();
        divName = inCnd.getDivName();
        partyAb = inCnd.getPartyAb();
        partyName = inCnd.getPartyName();
        cndID = inCnd.getCndID();
        surname = inCnd.getSurname();
        firstName = inCnd.getGivenName();
        elected = inCnd.getElected();
        histElected = inCnd.getHistElected();
        ballotPos = inCnd.getPosition();

        cnd = new Candidate( stateAb, divID, divName, partyAb, partyName, cndID,
            surname, firstName, elected, histElected, ballotPos );
    }

    public void setPollID( int inID )
    {
        //Validation: valid ID's are four or five digits.
        if( inID < 1000 || inID > 100000 )
        {
            throw new IllegalArgumentException( "Invalid Poll ID." );
        }
        else
        {
            pollID = inID;
        } //Doesn't guarantee a valid ID; but it makes it slightly more likely.
    }

    public void setPollName( String inPollName )
    {
        //Validation: checking for empty string.
        if( !( validateString( inPollName ) ) )
        {
            throw new IllegalArgumentException( "Empty Polling site name." );
        }
        else
        {
            pollName = inPollName;
        }
    }
    
    public void setVotes( int inVotes )
    {
        //Validation: Checking for empty string.
        if( inVotes < 0 )
        {
            //Generation Z apathy? 
            throw new IllegalArgumentException( "Empty party abbreviation." );
        }
        else
        {
            ordVotes = inVotes;
        }
    }
    public void setSwing( double inSwing )
    {
        //Initially had validation; but the file we're reading in has one line
        //with -168% swing so that has been put on the backburner */
      /*  if( ( inSwing > 100.0 ) || ( inSwing < -100.0 ) )//Howzat
        {
            throw new IllegalArgumentException( "Invalid swing" );
        }
        else
        {*/
            swing = inSwing;
       // }
    }

//*************************** TOSTRING MODULE *******************************//

/**
* TO STRING METHOD
* PURPOSE: To format information for the candidate in an easy-to-read manner.
**/
    public String toString()
    {
        return ( "CANDIDATE\n " + cnd.toString() + "===\n Poll ID: " +
        pollID + ";\n Poll Name: " + pollName + ";\n Votes: " + ordVotes + ";\n" 
        + " Swing: " + swing + "%.\n" );
    }


//************************ PRIVATE SUBMODULES ******************************//

    //Submodule: Checks if the String is empty or not.
    private boolean validateString ( String inString )
    {
        boolean isValid = false;
        if ( ! ( inString.length() == 0 ) )
        {
            isValid = true;
        }
        return isValid;
    }
}//END SEATCHALLENGEr
