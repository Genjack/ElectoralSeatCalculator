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
    private Candidate defCnd, cnd, cnd2; //Test Candidate object.
    private SeatChallenger seat;
    private DSALinkedList<Candidate> list; //Test Candidate List
    int testCount;
    boolean testBool;
    String testString, testPartyAb, testName;
    
    @Before
    public void setup()
    {
        testBool = false;
        defCnd = new Candidate();
        list = new DSALinkedList<Candidate>();
        seat = new SeatChallenger();
    }

    //TEST: Alt constructor w/ setters/getters for all data types.
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

//************************* FILE.JAVA CASES (3) ******************************//

    //TEST: loadCandidates() - test file, valid, 10 Candidates.
//    @Test
    public void testLoad()
    {
        File.loadCandidates( "testLoad.txt", list );
        testCount = list.getCount(); //Should be 10.
        cnd = ( Candidate )( list.removeFirst() );
        testName = cnd.getSurname(); //Should be ABBOTT
        assertEquals( "List count", 25, testCount );
        assertEquals( "Candidate Surname", "ABBOTT", testName );
    }

    //TEST: loadCandidates() - 'van GEUNS' surname (non-conventional format)
  //  @Test
    public void testGeuns()
    {
        File.loadCandidates( "testLoad.txt", list );
        cnd = ( Candidate )( list.removeLast() );
        testName = cnd.getSurname(); //Should be van GEUNS
        System.out.println( testName );
        assertEquals( "Candidate Surname", "van GEUNS", testName );
    }

    //TEST: loadCandidates() - Invalid file; No buffer lines at the top.
    //Outcome: Ignores top two lines, so first candidate should be ABLETT
   // @Test
    public void testInvalid()
    {
        File.loadCandidates( "tfNoBuffer", list );
        cnd = ( Candidate )( list.removeFirst() );
        testName = cnd.getSurname(); //Should be ABLETT
        assertEquals( "Candidate Surname", "ABLETT", testName );
    }

/* This Test Case doesn't print anything except "Test Print" - unsure why; it 
   works when running the program normally.*/
    //TEST: optionalSave() - in Menu.java.
  //  @Test
    public void testSave()
    {
        System.out.println( "TEST: Select [1] State; \"SA\"; any order" );
        File.loadCandidates( "testLoad.txt", list );
        Format.prepareToList( list );
        System.out.println( "Choose save" );
        Menu.optionalSave( list, "Test Print" );
        System.out.println( "Your file should contain three entries." );
    }

//************************* FORMAT.JAVA CASES (3) ****************************//

    //TEST: prepareToList - valid file, 10 candidates.
    //SORT: State(1). ORDER: None(0).
  //  @Test
    public void testPrepareList10()
    {
        System.out.println( "TEST: Select [1] State; \"WA\"; skip order." );
        File.loadCandidates( "testLoad.txt", list );
        Format.prepareToList( list );
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received CARSON\nPEARSON\nKELLY." );
    }

    //TEST: pTL Valid: Party(2). ORDER: Division.
  //  @Test
    public void testPrepareList24()
    {
        System.out.println( "TEST: Select [2] Party; \"GRN\"; [4] Division." );
        File.loadCandidates( "testLoad.txt", list );
        Format.prepareToList( list );
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received 1) van GEUNS\nABBOUD\n" +
            "PATERSON\nHOGARTH\nHODGINS-MAY\nHOAD" );
    }

    //TEST: pTL Valid: Division(3). ORDER: State.
  //  @Test
    public void testPrepareList32()
    {
        System.out.println( "TEST: Select [3] Division; \"101\"; [1] Name." );
        File.loadCandidates( "testLoad.txt", list );
        Format.prepareToList( list );
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received ADELAN-LANGFORD\nBRODTMANN" );
    }

    //TEST: prepareToSearch - ASSIGNMENT PART 2
    //Checking that we can search for all candidates with surnames A*
  //  @Test
    public void testPrepareToSearch()
    {
        //Need to call option 1 first to retrieve the file.
        System.out.println( "Select option 1; skip filter/order" );
        System.out.println( "When main menu displays, choose option 2, " +
            "then choose to skip ordering and search \"a\" " );       
        System.out.println( "IMPORTANT: Exit after executing to see results" ); 
        Menu.run();
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received\nABBOT\nABBOUD\nABLETT\n " +
            "ADELAN-LANGFORD\nAITCHISON" );
    }

    //TEST: prepareToSearch - Name doesn't exist
  //  @Test
    public void testPrepareToSearchNE()
    {
        System.out.println( "Select option 1; skip filter/order" );
        System.out.println( "When main menu displays, choose option 2, " +
            "then choose to skip ordering and search \"Trump\" " );       
        System.out.println( "IMPORTANT: Exit after executing to see results" ); 
        Menu.run();
        System.out.println( "=== EXPECTED === " );
        System.out.println( "Should have received\nError: Not found" );
    }

//************************* SEAT CHALLENGER TEST *****************************//

    //TEST: seatChallenger Alternate Constructor
    @Test
    public void testSCAltCon()
    {
        cnd = new Candidate( "WA", 200, "Curtin University", "MRLP", 
            "Monster Raving Loony Party", 12345, "Cleese", "John", 
            true, true, 3 );
        seat = new SeatChallenger( cnd, 1234, "Winthrop", 5000, 99.9 );
        testName = seat.getPollName();
        testCount = seat.getPollID();
        cnd2 = seat.getCnd();
        System.out.println( seat.toString() );
        assertEquals( "name", "Winthrop", testName );
        assertEquals( "poll count", 1234, testCount );
        assertEquals( "cnd name", "John", cnd2.getGivenName() );
    }
}

