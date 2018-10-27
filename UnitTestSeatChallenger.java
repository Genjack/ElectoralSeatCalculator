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
public class UnitTestSeatChallenger
{
    //Classfields
    private SeatChallenger seat, defSeat;
    private Candidate altCnd, cnd;
    int testCount;
    boolean testBool;
    private String testString, testPartyAb, testName;
    
    @Before
    public void setup()
    {
        testBool = false;
        defSeat = new SeatChallenger();
    }

    //TEST: Alt constructor w/ getters for all data types.
    @Test
    public void testAltCon()
    {
        Candidate cnd = new Candidate();
        seat = new SeatChallenger( cnd, 12345, "Gettysburg", 20, -7.0 );
        assertEquals( "candidate ID", 12345, seat.getPollID() );
        assertEquals( "party name", "Gettysburg", seat.getPollName() );
    }

    //TEST: set state - making sure a valid case can go through
    @Test
    public void testSetCnd()
    {
        altCnd = new Candidate( "WA", 200, "Curtin University", "MRLP", 
            "Monster Raving Loony Party", 12345, "Cleese", "John", 
            true, true, 3 );
        defSeat.setCnd( altCnd );
        assertEquals( "surname", "Cleese", defSeat.getCnd().getSurname() );
    }
}

