/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Contents: Test Harness for the Stack Class, Practical 3 of DSA.
*/

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import io.ConsoleRedirect; 
import java.io.*;
// import org.mockito.Mockito.*; //in case I need mock objects later.

@RunWith ( JUnit4.class )
public class UnitTestFile
{
    //Classfields
    private Candidate cnd;
    private SeatChallenger seat;
    private DSALinkedList<Candidate> cndList; //Test Candidate List
    private DSALinkedList<SeatChallenger> seatList;
    private int testCount;
    private boolean testBool;
    private String testName;
    
    @Before
    public void setup()
    {
        testBool = false;
        cnd = new Candidate();
        cndList = new DSALinkedList<Candidate>();
        seat = new SeatChallenger();
        seatList = new DSALinkedList<SeatChallenger>();
        ConsoleRedirect.captureOutput();
    }

    //TEST: loadCandidates() - test file, valid, 10 Candidates.
    @Test
    public void testLoad()
    {
        File.loadCandidates( "testLoad.txt", cndList );
        testCount = cndList.getCount(); //Should be 25.
        cnd = ( Candidate )( cndList.removeFirst() );
        testName = cnd.getSurname(); //Should be ABBOTT
        assertEquals( "List count", 25, testCount );
        assertEquals( "Candidate Surname", "ABBOTT", testName );
    }
    //Methods implictly tested: splitLine(); makeCandidate();

    //TEST: loadCandidates() - empty file
    @Test( expected = IllegalArgumentException.class )
    public void testEmptyLoad()
    {
        File.loadCandidates( "empty", cndList );
    }

    //TEST: loadSeats() - test file, valid, 22 candidates, 3 informal.
    @Test
    public void testLoadSeats()
    {
        File.loadSeats( "testSeats.txt", seatList );
        seat = ( SeatChallenger )( seatList.removeFirst() ); //BUCHANAN
        assertEquals( "list count", 21, seatList.getCount() ); //22 - 1
        assertEquals( "first seat", "BUCHANAN", seat.getCnd().getSurname() );
    }
    //Methods implicitly tested: makeSeat();
}

