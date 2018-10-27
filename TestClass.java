/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Contents: A skeleton class for testing my DSAStack Class for Prac 3. I tried
             mocking the Object class with Mockito - I don't think this is
             allowed/recognised, so I'm going to create a quick class to help
             populate the stack for JUnit.
*/

public class TestClass
{
    //Classfields
    private String name; //Will just be named after Civil War Generals
    private int number; //Arbitrary four-digit numbers

    //CONSTRUCTOR - Just an alternate one, to have varieties
    public TestClass( String inName, int inNumber )
    {
        name = inName;
        number = inNumber;
    }
    
    //ACCESSORS
    public String getName()
    {
        return name;
    }
        
    public int getNumber()
    {
        return number;
    }
    
    //EQUALS METHOD
    public boolean equal( Object inObject )
    {
        boolean isEqual = false;

        if( inObject instanceof TestClass )
        {
            TestClass testObj = ( TestClass )( inObject );
            isEqual = ( ( name.equals( testObj.getName() ) ) &&
                      ( number == testObj.getNumber() ) );
        }
        return isEqual;
    }

    //TOSTRING METHOD
    public String toString()
    {
        return ( name + ", " + number );
    }
} //End TestClass 
