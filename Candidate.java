 /* Name: Tim J. Dempsey
    Student ID: 19390664
    Unit and Time: DSA @ Wednesdays, 12pm
    File Name: Candidate
    Contents: Java for the creation of a Candidate object, with class fields to
    store a political candidate.
    Previous guidance: The template for this code is loosely based on my
    MeatClass for  OOPD Assignment, Semester One 2018.
    Date Last Modified: 16th October, 2018
*/

import java.util.*;

public class Candidate
{
    /********************* Private class fields **************************/
    //State abbreviation - i.e. WA, NSW etc.
    private String stateAb;
    //Division ID - Three digit integer representing national division.
    private int divID;
    //Division name - String representing name of national division.
    private String divName;
    //Party abbreviation - Two or three characters representing political party.
    private String partyAb;
    //Party name - full name of political party
    private String partyName;
    //Candidate ID - 5 digit integer ID (less controversial than voter ID)
    private int cndId;
    //Candidate surname and given names
    private String cndSurname, cndGivenName;
    //Boolean value denoting electoral status
    private boolean elected;
    //Boolean value denoting whether this candidate has been elected before
    private boolean histElected;

/**
* DEFAULT CONSTRUCTOR
* Purpose: Creates a default instance of Candidate.
**/
    public Candidate()
    {
        stateAb = "null";
        divID = 000;
        divName = "null";
        partyAb = "N/A";
        partyName = "null";
        cndID = 00000;
        cndSurname = "null";
        cndGivenName = "null";
        elected = false;
        histElected = false;
    }

/**
* ALTERNATE CONSTRUCTOR
* Purpose: Creates a Candidate using imported values.
**/

    public Candidate( String inStateAb, int inDivID, String inDivName, 
        String inPartyAb, String inPartyName, int inCndID, String inCndSurname,
        String inCndGivenName, boolean inElected, boolean inHistElected )
    {
        setStateAb( inStateAb );
        setDivID( inDivID );
        setDivName( inDivName );
        setPartyAb( setPartyAb );
        setPartyName( inPartyName );
        setCndID( inCndID );
        setSurname( inCndSurname );
        setGivenName( inCndGivenName );
        setElected( inElected );
        setHistElected( inHistElected );
    }

//Won't need a copy constructor - no point duplicating a unique candidate

//********************************** ACCESSORS *******************************//

    public String getStateAb()
    {
        return stateAb;
    }

    public int getDivID()
    {
        return divID;
    }

    public String getDivName()
    {
        return divName;
    }
    
    public String getPartyAb()
    {
        return partyAb;
    }

    public String getPartyName()
    {
        return partyName;
    }

    public int getCndID()
    {
        return cndID;
    }

    public String getSurname()
    {
        return cndSurname;
    }

    public String getGivenName()
    {
        return cndGivenName;
    }

    public boolean getElected()
    {
        return elected;
    }

    public boolean getHistElected()
    {
        return histElected;
    }

//********************************** MUTATORS *******************************//
/* Why have a mutator? Because I haven't really though through my TDD, so not
   currently sure how validation's going to work. I'll build some in here now,
   so that if they try to create an imitation file, it will handle it. */

    public void setStateAb( String inAb )
    {
        //Checking that the state abbreviation is a maximum of 3 letters.
        if( !( validateString( inAb ) ) || inAb.length() > 3 )
        {
            throw new IllegalArgumentException( "Empty state Abbreviation." );
        }
        else
        {
            stateAb = inAb;
        }
    }

    public void setDivId( int inID )
    {
        //Validation: valid ID's are three digit integers.
        if( inID < 100 || inID > 999 )
        {
            throw new IllegalArgumentException( "Invalid division ID." );
        }
        else
        {
            divID = inID;
        }
    }

    public void setDivName( String inDName )
    {
        //Validation: checking for empty string.
        if( !( validateString( inDName ) ) )
        {
            throw new IllegalArgumentException( "Empty division name." );
        }
        else
        {
            divName = inDName;
        }
    }

    public void setPartyName( String inPName )
    {
        //Validation: Checking for empty string.
        if( !( validateString( inPName ) ) )
        {
            throw new IllegalArgumentException( "Empty party name." );
        }
        else
        {
            partyName = inPName;
        }
    }

    public void setCndID( int inCID )
    {
        //Validation: Check that the integer is a max length of 5 digits.
        if( inCID > 99999 )
        {
            throw new IllegalArgumentException( "Invalid candidate ID." );
        }
        else
        {
            cndID = inCID;
        }
    }

    public void setSurname( String inSurname )
    {
        //Validation: valid candidates are not empty strings (empty souls maybe)
        if( !( validateString( inSurname ) ) )
        {
            throw new IllegalArgumentException( "Invalid candidate surname." );
        }
        else
        {
            cndSurname = inSurname;
        }
    }

    public void setGivenName( String inGivenName )
    {
        //Validation: Checking for empty string.
        if( !( validateString( inGivenName ) ) )
        {
            throw new IllegalArgumentException( "Invalid candidate name." );
        }
        else
        {
            cndGivenName = inGivenName;
        }
    }

    public void setElected( inElected )
    {
        elected = inElected;
    }

    public void setHistElected( inHistElected )
    {
        histElected = inHistElected;
    }

//*************************** TOSTRING MODULE *******************************//

/**
* TO STRING METHOD
* PURPOSE: To format information for the candidate in an easy-to-read manner.
**/
    public String toString()
    {
        return ( "Name: " + cndGivenName + " " + cndSurname + ";\n ID: " +
            cndID + ";\n Party: " + partyAb + " - " + partyName + 
            ";\n Division: " + divName + "( " + divID + ")\n Elected: " + 
            elected + "; Elected Previously: " + histElected + ".\n" );
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

}//END CANDIDATE
