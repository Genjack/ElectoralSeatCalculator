/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Contents: Test Harness for the Stack Class, Practical 3 of DSA.
*/

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
// import io.ConsoleRedirect; Not needed right now.
// import org.mockito.Mockito.*; //in case I need mock objects later.

@RunWith ( JUnit4.class )
public class DSATestHarness
{
    //Classfields
    private Candidate defCnd, cnd; //Test Candidate object.
    private Candidate [] testArr;
    int testCount;
    boolean testBool;
    String testString;
    
    @Before
    public void setup()
    {
        testBool = false;
        defCnd = new Candidate();
    }

    //TEST: Alt constructor w/ setters/getters for all data types.
    @Test
    public void testAltCon()
    {
        cnd = new Candidate( "WA", 200, "Curtin University", "MRLP", 
            "Monster Raving Loony Party", 12345, "Cleese", "John", 
            true, true );
        testCount = cnd.getCndID();
        testString = cnd.getPartyName();
        testBool = cnd.getElected();
        assertEquals( "candidate ID", 12345, testCount );
        assertEquals( "party name", "Monster Raving Loony Party", testString );
        assertTrue( "elected", testBool );
    }

    //TEST: Just checking that an empty string throws an exception.
    @Test( expected = IllegalArgumentException.class )
    public void testArray()
    {
        cnd = new Candidate( "", 200, "Curtin University", "MRLP", 
            "Monster Raving Loony Party", 12345, "Cleese", "John", 
            true, true );
    }
}
