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
public class UnitTestUtility
{
    //Classfields
    private Candidate cnd;
    private SeatChallenger seat;
    private DSALinkedList<Candidate> cndList, matches; //Test Candidate List
    private DSALinkedList<SeatChallenger> seatList;
    private DSALinkedList<String> log;
    private int testCount;
    private boolean [] boolArr;
    private SeatChallenger [] seatArr;
    private boolean bool1, bool2, bool3, bool4;
    private String testName;
    private DSAHashTable table;
    
    @Before
    public void setup()
    {
        bool1 = false;
        bool2 = false;
        bool3 = false;
        bool4 = false;
        boolArr = new boolean[4];
        boolArr[0] = bool1;
        boolArr[1] = bool2;
        boolArr[2] = bool3;
        boolArr[3] = bool4;
        cnd = new Candidate();
        cndList = new DSALinkedList<Candidate>();
        matches = new DSALinkedList<Candidate>(); //For searchList()
        seat = new SeatChallenger();
        seatList = new DSALinkedList<SeatChallenger>();
        ConsoleRedirect.captureOutput();
    }

    //TEST: getFilters() - for 123, all choices
    @Test
    public void testGetFilters()
    {
        Utility.getFilters( "123", boolArr );
        assertTrue( boolArr[0] );
        assertTrue( boolArr[1] );
        assertTrue( boolArr[2] );
    }

    @Test
    public void testGetOneFilter()
    {
        Utility.getFilters( "1", boolArr );
        assertTrue( boolArr[0] );
        assertFalse( boolArr[1] );
        assertFalse( boolArr[2] );
    }

    //TEST: getOrder - for 1234, all choices
    @Test
    public void testGetOrder()
    {   
        Utility.getOrder( "1234", boolArr );
        assertTrue( boolArr[0] );
        assertTrue( boolArr[1] );
        assertTrue( boolArr[2] );
        assertTrue( boolArr[3] );
    }

    public void testGetOneOrder()
    {
        Utility.getFilters( "3", boolArr );
        assertFalse( boolArr[0] );
        assertFalse( boolArr[1] );
        assertTrue( boolArr[2] );
        assertFalse( boolArr[3] );
    }
    //Assumption: If both of these functions work, with the same format, then
    //by extension, getSearchFilters will also work as it is basically the same

    //TEST: searchList - Finding using letter 'e'
    @Test
    public void testSearchList()
    {
        File.loadCandidates( "testLoad.txt", cndList );
        matches = Utility.searchList( "e", cndList ); //Should find EARLEY
        cnd = ( Candidate )( matches.removeFirst() );
        assertEquals( "Earley", "EARLEY", cnd.getSurname() );
        assertEquals( "matches should be empty", 0, matches.getCount() );
    }
}
