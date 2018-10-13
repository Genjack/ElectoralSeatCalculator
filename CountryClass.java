/*******************************************************************************
 *  Name: Tim J. Dempsey                                                       *
 *  File Name: CountryClass.java                                               *
 *  Purpose: Java for the 'Country' class. Allows creation of country          *
 *           objects for the program; contains an array of State objects.      *
 *  Note on Design: Much of the formatting for this class was taken from my    *
 *                  OOPD Assignment from Semester 1.                           *
 *  Date Last Modified: 3rd August, 2018                                       *
 ******************************************************************************/

import java.util.*;
import java.io.*;

public class CountryClass implements Serializable
{
    //Private class fields
    private String name; 
    private String officialLang;
    private double area;
    private int population;
    private String dataSource;
    private StateClass [] statesArr;

/*******************************************************************************
 * DEFAULT CONSTRUCTOR                                                         *
 * IMPORT: None.                                                               *
 * ASSERTION: Default country is created to include these default values.      *
 ******************************************************************************/

    public CountryClass()
    {
        name = "null";
        officialLang = "English";
        area = 100.0;
        population = 1000;
        dataSource = "null";
        statesArr = new StateClass[50];
    }

/*******************************************************************************
 * ALTERNATE CONSTRUCTOR                                                       *
 * IMPORT: inName (String), inOfficialLang (String), inArea (Real),            *
 *         inPopulation (Integer), inDataSource (String), inStatesArr          * 
 *         (ARRAY OF StateClass)                                               *
 * ASSERTION: Creates a country object with imported values if the Strings are *
 *            not null and real values are positive/not zero.                  *
 ******************************************************************************/
   
    public CountryClass( String inName, String inOfficialLang, double inArea,
        int inPopulation, String inDataSource, StateClass [] inStatesArr )
    {
        setName( inName );
        setOfficialLang( inOfficialLang );
        setArea( inArea );
        setPop( inPopulation );
        setDataSource( inDataSource );
        setStatesArr( inStatesArr ); //Needs to clone states within array.
    }

/*******************************************************************************
 * COPY CONSTRUCTOR                                                            *
 * IMPORT: inCountry (Object)                                                  *
 * ASSERTION: New country object is created using imported object.             *
 ******************************************************************************/
    
    public CountryClass ( CountryClass inCountry )
    {
        name = inCountry.getName();
        officialLang = inCountry.getOfficialLang();
        area = inCountry.getArea();
        population = inCountry.getPop();
        dataSource = inCountry.getDataSource();
        statesArr = inCountry.getStatesArr();
    }

//******************************* ACCESSORS ********************************//
    
    public String getName()
    {
        return name;
    }

    public String getOfficialLang()
    {
        return officialLang;
    }

    public double getArea()
    {
        return area;
    }

    public int getPop()
    {
        return population;
    }

    public String getDataSource()
    {
        return dataSource;
    }
    
    public StateClass [] getStatesArr()
    {
        StateClass [] copyArray = new StateClass[statesArr.length];
        int ii = 0; //counter; trying to defeat NullPointerException
        while( statesArr[ii] != null )
        {
            copyArray[ii] = statesArr[ii].clone();
            ii++;
        }
        /*for( int ii = 0; ii < statesArr.length; ii++ )
        {
            if( statesArr[0] != null )
            {
                copyArray[ii] = statesArr[ii].clone();
            }
        }*/
        return copyArray;
    }

/* SUBMODULE COMMENTS: This while loop alteration seems to eliminate the 
NullPointerException - I wonder whether it's because the array has a length of I
think 50, but there's only one item in the array; so for the second iteration of
the for loop, an NPE is thrown because there's nothing there and the object is 
not created. Need to follow up. */

//************************* EQUALS AND TOSTRING METHODS **********************//

/*****************************************************************************
 * SUBMODULE equals                                                          *
 * IMPORT: inObject ( Object )                                               *
 * EXPORT: isEqual ( Boolean )                                               *
 * ASSERTION: Returns true if current object traits are equal to imported.   *
 ****************************************************************************/
    
    public boolean equals ( Object inObject )
    {
        boolean isEquals = false;

        if( inObject instanceof CountryClass )
        {
            CountryClass inCountry = ( CountryClass )( inObject );
            if( ( name.equals( inCountry.getName() )) &&
            ( officialLang.equals( inCountry.getOfficialLang() ) ) &&
            ( Math.abs ( area - inCountry.getArea() ) < 0.01 ) &&
            ( population == inCountry.getPop() ) &&
            ( dataSource.equals( inCountry.getDataSource() ) ) )
            {
                StateClass [] compareArray = inCountry.getStatesArr();
                int ii = 0;
                while( statesArr[ii] != null )
                //for( int ii = 0; ii < statesArr.length; ii++ )
                {
                    isEquals = statesArr[ii].equals( compareArray[ii] );
                    ii++;
                    //StateClass should have it's own equals method.
                }    
            }
        }
        return isEquals;
    }

    public String toString()
    {
        String statesList = "";
        int ii = 0; //counter
        while( statesArr[ii] != null )
        {
            statesList += ( "State [" + ii + "]: " + statesArr[ii].toString()
            + "\n");
            ii++;
        }
        return ( name + ", " + officialLang + ", " + area + ", " + 
        population + ", " + dataSource + "\n" + statesList );
    }

//***************************** MUTATORS ***********************************//

/*****************************************************************************
 * Public setName                                                            *
 * IMPORT: inName (String)                                                    *
 * EXPORT: None.                                                             *
 * ASSERTION: The name of the country is changed to inName, unless it's empty.    *
 ****************************************************************************/
    
    public void setName ( String inName )
    {
        if ( ! ( validateString( inName ) ) )
        {
            throw new IllegalArgumentException( "The nation needs a name." );
        }
        else
        {
            name = inName;
        }
    }

/*******************************************************************************
 * Public setOfficialLang                                                      *
 * IMPORT: inOfficialLang (String)                                             *
 * EXPORT: None.                                                               *
 * ASSERTION: The country's official language is set to imported if not empty. *
 ******************************************************************************/
    
    public void setOfficialLang ( String inOfficialLang )
    {  
        if ( ! ( validateString( inOfficialLang ) ) )
        {
            throw new IllegalArgumentException( "The nation needs a language.");
        }
        else
        {
            officialLang = inOfficialLang;
        }
    }

/*******************************************************************************
 * Public setArea                                                              *
 * IMPORT: inArea (Real)                                                       *
 * EXPORT: None.                                                               *
 * ASSERTION: The area of the country is set to inArea if it is positive.      *
 ******************************************************************************/

    public void setArea ( double inArea )
    {
        if( inArea <= 0.0 )
        {
            throw new IllegalArgumentException( "Area must be positive.");
        }
        else
        {
            area = inArea;
        }
    }

/*******************************************************************************
 * Public setPop                                                               * 
 * IMPORT: inPopulation (Integer)                                              *
 * EXPORT: None.                                                               *
 * ASSERTION: The population of the country is set to inPopulation if it is    *
 * positive.                                                                   *
 ******************************************************************************/

    public void setPop( int inPopulation )
    {
        if( inPopulation <= 0 )
        {
            throw new IllegalArgumentException( "Population must be positive.");
        }
        else
        {
            population = inPopulation;
        }
    }

/*******************************************************************************
 * Public setDataSource                                                        * 
 * IMPORT: inDataSource (String)                                               *
 * EXPORT: None.                                                               *
 * ASSERTION: The data source for the country is set to inDataSource.          *
 ******************************************************************************/
    
    public void setDataSource( String inDataSource )
    {
        if ( ! ( validateString( inDataSource ) ) )
        {
            throw new IllegalArgumentException( "Source of Data required." );
        }
        else
        {
            dataSource = inDataSource;
        }
    } 
 
/*******************************************************************************
 * Public setStatesArr                                                         * 
 * IMPORT: inStatesArr (ARRAY OF StateClass)                                   *
 * EXPORT: None.                                                               *
 * ASSERTION: The states array for the country is set to inStatesArr.          *
 ******************************************************************************/
   
    public void setStatesArr( StateClass [] inStatesArr )
    {
        //Logic here to check if the imported array is larger than the current
        //array; but in a constructor, there wouldn't be a pre-existing object,
        //so this is surely useless code. Commenting it out for now.
    /*
        if( inStatesArr.length > statesArr.length )
        {
            throw new IllegalArgumentException( "Imported array is too large.");
        }
        else
        { */
        statesArr = new StateClass[inStatesArr.length];

        int ii = 0;
            while( inStatesArr[ii] != null )
            {
                statesArr[ii] = inStatesArr[ii].clone();
                ii++;
            }
    } 
//******************************* CLONE ************************************//
    //Omitting the clone function for CountryClass, because it only makes 
    //sense for each country to be unique.

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

}//End CountryClass.
