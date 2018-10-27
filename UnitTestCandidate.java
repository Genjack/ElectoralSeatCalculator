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
// import org.mockito.Mockito.*; in case I need mock objects later.

@RunWith ( JUnit4.class )
public class UnitTestCandidate
{
    //Classfields
    private Candidate defCnd, cnd, cnd2; //Test Candidate object.
    int testCount;
    boolean testBool;
    private String testString, testPartyAb, testName;
    
    @Before
    public void setup()
    {
        testBool = false;
        defCnd = new Candidate();
    }

    //TEST: Alt constructor w/ getters for all data types.
    @Test
    public void testAltCon()
    {
        cnd = new Candidate( "WA", 200, "Curtin University", "MRLP", 
            "Monster Raving Loony Party", 12345, "Cleese", "John", 
            true, true, 3 );
        testCount = cnd.getCndID();
        testString = cnd.getPartyName();
        testPartyAb = cnd.getPartyAb();
        testBool = cnd.getElected();
        assertEquals( "candidate ID", 12345, testCount );
        assertEquals( "party name", "Monster Raving Loony Party", testString );
        assertTrue( "elected", testBool );
    }

    //TEST: set state - making sure a valid case can go through
    @Test
    public void testSetState()
    {
        defCnd.setStateAb( "ACT" ); // 0 < length < 3 should be valid
        assertEquals( "state abbreviation", "ACT", defCnd.getStateAb() );
    }

    //TEST: set candidate ID - using a value of 999
    @Test
    public void testCndID()
    {
        defCnd.setCndID(999);
        assertEquals( "Candidate ID", 999, defCnd.getCndID() );
    }

    //TEST: clone - checking it works.
    @Test
    public void testClone()
    {
        cnd = new Candidate( "WA", 200, "Curtin University", "MRLP", 
            "Monster Raving Loony Party", 12345, "Cleese", "John", 
            true, true, 3 );       
        Candidate cl = cnd.clone();
        assertEquals( "Candidate Surname", "Cleese", cl.getSurname() );
    }
}

