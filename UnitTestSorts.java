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
public class UnitTestSorts
{
    //Classfields
    private Candidate cnd;
    private SeatChallenger seat;
    private DSALinkedList<Candidate> cndList; //Test Candidate List
    private Candidate [] carr;
    private SeatChallenger [] sarr;
    private DSALinkedList<SeatChallenger> seatList;
    private String testName;
    private int testCount;
    private boolean testBool;
    
    @Before
    public void setup()
    {
        testBool = false;
        cndList = new DSALinkedList<Candidate>();
        seatList = new DSALinkedList<SeatChallenger>();
        File.loadCandidates( "testLoad.txt", cndList );
        File.loadSeats( "testSeats.txt", seatList );
        carr = new Candidate[ cndList.getCount()];
        sarr = new SeatChallenger[ seatList.getCount()];
        for( int ii = 0; ii < carr.length; ii++ )
        {
            carr[ii] = cndList.removeFirst();
        }
        for( int ii = 0; ii < sarr.length; ii++ )
        {
            sarr[ii] = seatList.removeFirst();
        }
    }

    //TEST: mergeSort by surname A-Z for carr[], party name A-Z for sarr[]
    @Test
    public void testSortNames()
    {
        Sorts.mergeSortName( carr );
        assertEquals( "index 0", "ABBOTT", carr[0].getSurname() );
        assertEquals( "index 10", "HOCKLEY", carr[10].getSurname() );
        assertEquals( "index 20", "PASQUALI", carr[20].getSurname() );
    }

    //TEST: Sort by party name A-Z for sarr[]
    @Test
    public void testSortPartySeat()
    {
        Sorts.mergeSortPtySeat( sarr );
        // ALP < IND < LP
        assertEquals( "index 0", "KEOGH", sarr[0].getCnd().getSurname() ); 
        assertEquals( "index 10", "KERR", sarr[10].getCnd().getSurname() ); 
        assertEquals( "index 20", "MORTON", sarr[20].getCnd().getSurname() ); 

    }

    //TEST: mergeSort by State A-Z for carr[]
    @Test
    public void testSortState()
    {
        Sorts.mergeSortState( carr );
        //ACT < QLD < VIC
        assertEquals( "index 0", "BRODTMANN", carr[0].getSurname() );
        assertEquals( "index 10", "KENNEDY", carr[10].getSurname() );
        assertEquals( "index 20", "HOGARTH", carr[20].getSurname() );
    }

    //TEST: Sort by Division for sarr[]
    @Test
    public void testDivSeatSort()
    {
        Sorts.mergeSortDivSeat( sarr );
        // 235 < 248 < 305
        assertEquals( "index 0", "BUCHANAN", sarr[0].getCnd().getSurname() ); 
        assertEquals( "index 10", "JENSEN", sarr[10].getCnd().getSurname() ); 
        assertEquals( "index 20", "KEOGH", sarr[20].getCnd().getSurname() );
    }
    
    //TEST: Sort by Party and Division for carr[]
    @Test
    public void testPartyAndDivSort()
    {
        Sorts.mergeSortDivision( carr );
        Sorts.mergeSortParty( carr );
        // 235 < 248 < 305
        assertEquals( "index 0", "BRODTMANN", carr[0].getSurname() ); 
        assertEquals( "index 10", "HODGINS-MAY", carr[10].getSurname() ); 
        assertEquals( "index 20", "KELLY", carr[20].getSurname() );
    }
}

