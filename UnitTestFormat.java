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
public class UnitTestFormat
{
    //Classfields
    private Candidate cnd;
    private SeatChallenger seat;
    private DSALinkedList<Candidate> cndList, filtList; //Test Candidate List
    private DSALinkedList<SeatChallenger> seatList;
    private String testName;
    private int testCount;
    private boolean testBool;
    
    @Before
    public void setup()
    {
        testBool = false;
        cnd = new Candidate();
        cndList = new DSALinkedList<Candidate>();
        filtList = new DSALinkedList<Candidate>();
        seat = new SeatChallenger();
        seatList = new DSALinkedList<SeatChallenger>();
        System.out.println( "Note: Please exit program at each opportunity." );
    }

    //TEST: filter by STATE WA, order by SURNAME.
    @Test
    public void testPrepareList11()
    {
        System.out.println( "==== INPUT SEQUENCE ====" );
        System.out.println( "1 - 'WA' - 1" );
        File.loadCandidates( "testLoad.txt", cndList );
        Format.prepareToList( cndList );
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received\nCARSON\nKELLY\nPEARSON." );
    }
    //Implicit tests: User.getString(); buildFilters(); sortList().

    //TEST: filter by PARTY GRN, order by DIVISION.
    @Test
    public void testPrepareList24()
    {
        System.out.println( "==== INPUT SEQUENCE ====" );
        System.out.println( "2 - 'GRN' - 4" );
        File.loadCandidates( "testLoad.txt", cndList );
        Format.prepareToList( cndList );
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received\nvan GEUNS\nABBOUD\n" +
            "PATERSON\nHOGARTH\nHODGINS-MAY\nHOAD" );
    }

    //TEST: buildFilters() - passing a string and a cndList
    @Test
    public void testBuildFilters()
    {
        System.out.println( "Select 'ACT' " );
        File.loadCandidates( "testLoad.txt", cndList );
        filtList = Format.buildFilters( "1", cndList ); //FILTER BY STATE ONLY
        cnd = (Candidate)(filtList.removeFirst());
        assertEquals( "count", 1, filtList.getCount() );
        assertEquals( "name", "BRODTMANN", cnd.getSurname() ); 
    }
}

