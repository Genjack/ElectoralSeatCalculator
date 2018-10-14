 /* Name: Tim J. Dempsey
    Student ID: 19390664
    Unit and Time: DSA @ Wednesdays, 12pm
    File Name: Candidate
    Contents: Java for the creation of a Candidate object, with class fields to
    store a political candidate.
    Previous guidance: The template for this code is loosely based on my
    MeatClass for  OOPD Assignment, Semester One 2018.
    Date Last Modified: 14th October, 2018
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

    public String getCut()
    {
        return cut;
    }

    public double getWeight()
    {
        return weight;
    }

    public DateClass getDate()
    {
        DateClass copyDate = useByDate.clone();
        return copyDate;
    }

//*********************** EQUALS AND TOSTRING MODULES **********************//

/*****************************************************************************
 * SUBMODULE equals                                                          *
 * IMPORT: inObject ( Object )                                               *
 * EXPORT: isEqual ( Boolean )                                               *
 * ASSERTION: Returns true if current object traits are equal to imported.   *
 ****************************************************************************/
    
    public boolean equals ( Object inObject )
    {
        boolean isEquals = false;

        if ( inObject instanceof MeatClass )
        {
            MeatClass inMeat = ( MeatClass )( inObject );
            if ( super.equals( inMeat ) )
            {
                isEquals = (( cut.equals ( inMeat.getCut() )) &&
                ( Math.abs ( weight - inMeat.getWeight() ) < 0.01 ) &&
                ( useByDate.equals ( inMeat.getDate() ) ));
            }
        }
        return isEquals;
    }

    public String toString()
    {
        return ( super.toStringName() + "," + cut + "," + weight + "," + 
        super.toStringTemp() + "," + useByDate.toString() + "," + 
        super.toStringPackaging() );
    }

//***************************** MUTATORS ***********************************//

/*****************************************************************************
 * Public setCut                                                            *
 * IMPORT: inCut (String)                                                    *
 * EXPORT: None.                                                             *
 * ASSERTION: The cut of the meat is changed to inCut, unless it's empty.    *
 * NOTE ON DESIGN: The reasoning an exception is thrown here is because an   *
 *                 empty String makes it difficult later on in interpreting  * 
 *                 data.                                                     *
 ****************************************************************************/
    
    public void setCut ( String inCut )
    {
        if ( ! ( validateString( inCut ) ) )
        {
            throw new IllegalArgumentException( "Empty cut" );
        }
        else
        {
            cut = inCut;
        }
    }

/*****************************************************************************
 * Public setWeight                                                          *
 * IMPORT: inWeight (Real)                                                   *
 * EXPORT: None.                                                             *
 * ASSERTION: The weight of the meat is changed to inWeight if valid.        *
 ****************************************************************************/
    
    public void setWeight ( double inWeight )
    {  
        if (( inWeight < Constants.getMinWV() ) &&
        ( inWeight > Constants.getMaxWV() )) 
        {
            throw new IllegalArgumentException( "Error: invalid weight." );
        }
        else
        {
            weight = inWeight;
        }
    }

/*****************************************************************************
 * Public setDate                                                            *
 * IMPORT: inDate (DateClass)                                                *
 * EXPORT: None.                                                             *
 * ASSERTION: The useByDate of the meat is changed to inDate.                *
 ****************************************************************************/

    public void setDate ( DateClass inDate )
    {
        int tempDay = inDate.getDay();
        int tempMonth = inDate.getMonth();
        int tempYear = inDate.getYear();

        useByDate.setDate( tempDay, tempMonth, tempYear);
    }
 
//******************************* CLONE ************************************//

    public MeatClass clone()
    {
        MeatClass meatClone = new MeatClass( this );
        return meatClone;
    }   

/*****************************************************************************
 * SUBMODULE calcExpiry                                                      *
 * IMPORT: today (Calendar)                                                  *
 * EXPORT: isExpired (Boolean)                                               *
 * ASSERTION: Returns true if the object is expired.                         *                       
 * HOW IT WORKS: Instances of the current time are created, which are then   *
 *               used to compare with the object's DateClass classfields to  *
 *               see if the object is expired or not.                        *
 ****************************************************************************/

    public boolean calcExpiry( Calendar today ) //See interface file for guide
    {
        int currentDay, currentMonth, currentYear;
        DateClass objDate;
        boolean isExpired = false;

        currentDay = today.get( Calendar.DAY_OF_MONTH );
        currentMonth = today.get( Calendar.MONTH );
        currentYear = today.get( Calendar.YEAR );

        objDate = getDate();

        if ( ( objDate.getYear() ) == currentYear )
        {
            if ( ( objDate.getMonth() ) <= currentMonth )
            {
                if ( ( objDate.getDay() ) < currentDay )
                {
                    isExpired = true;
                }
            }
        }
        else
        {
            if ( ( objDate.getYear() ) < currentYear )
            {
                isExpired = true;
            }
        }
        return isExpired;
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

}//End MeatClass.
