/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Contents: Test Harness for the Stack Class, Practical 3 of DSA.
*/

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
// import io.ConsoleRedirect; 
// import org.mockito.Mockito.*; //in case I need mock objects later.

@RunWith ( JUnit4.class )
public class UnitTestValidate
{
    //Classfields
    private char [] chArr;
    private String testChoices;
    private int testCount;
    private boolean testBool;
    
    @Before
    public void setup()
    {
        testBool = false;
    }

    //TEST: validateChoice - valid
    @Test
    public void testValidChoice()
    {
        Validate.validateChoice( "123", '3' );
        //Should be no exception thrown for this.
    }

    //TEST: validateChoice - invalid 1 (too many valid chars)
    @Test( expected = ArrayIndexOutOfBoundsException.class )
    public void testInvalidChoice1()
    {
        Validate.validateChoice( "111222333", '3' );
    }
    
    //TEST: validateChoice - invalid 2 (correct length, wrong chars)
    @Test( expected = IllegalArgumentException.class )
    public void testInvalidChoice2()
    {
        Validate.validateChoice( "hey", '3' );
    }
    
    //TEST: validateChoice - invalid 3 (one wrong char)
    @Test( expected = IllegalArgumentException.class )
    public void testInvalidChoice3()
    {
        Validate.validateChoice( "1204", '4' );
    }
}

