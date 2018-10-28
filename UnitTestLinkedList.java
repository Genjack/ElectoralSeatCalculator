/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Contents: Test Harness for DSALinkedList Class, Practical 4 of DSA.
*/

import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
// import io.ConsoleRedirect; Not needed right now.
// import org.mockito.Mockito.*; //in case I need mock objects later.

@RunWith ( JUnit4.class )
public class UnitTestLinkedList 
{
    //Classfields
    private DSALinkedList<TestClass> testList; //Declaration of test object
    boolean testEmpty, testHasNext;
    private TestClass testObj1, testObj2, testObj3, testObj4;
    private TestClass compObj, objToFind; //Retrieving and comparing objects
    private String testName;
    private int testNumber;
    private Iterator rator;

    @Before
    public void setup()
    {
        testEmpty = false;

        testObj1 = new TestClass("Jackson", 1863 );
        testObj2 = new TestClass( "Sherman", 1944 );
        testObj3 = new TestClass( "Lee", 1812 );
        testObj4 = new TestClass( "McClellan", 1975 );

        testList = new DSALinkedList<TestClass>();
    }
    
    // TESTING: isEmpty()
    @Test
    public void testEmpty()
    {
        testEmpty = testList.isEmpty();
        assertTrue( "Head is empty", testEmpty );
    }

    // TESTING: insertFirst(), peekAtFirst()
    @Test
    public void testInsertFirst()
    {
        testList.insertFirst( testObj1, testObj1.getName() );
        testList.insertFirst( testObj2, testObj2.getName());
        //Testing setContent() as well
        //compObj = (TestClass)(testList.peekAtFirst());  
        compObj = testList.peekAtFirst();
        testName = compObj.getName();
        testNumber = compObj.getNumber(); 
        assertEquals( "first node = testObj1", "Sherman", testName );
        assertEquals( "first node = testObj1", 1944, testNumber );
    }
    
    //TESTING: insertLast(), peekAtLast()
    @Test
    public void testInsertLast()
    {
        testList.insertFirst( testObj4, testObj4.getName() ); 
        testList.insertLast( testObj3, testObj3.getName() ); 
        compObj = testList.peekAtLast();
        testName = compObj.getName();
        testNumber = compObj.getNumber();
        assertEquals("last node = testObj3", "Lee", testName ); 
        assertEquals("last node = testObj3", 1812, testNumber );
    }

    // TESTING: removeFirst() 
    @Test 
    public void testPeekAtLast()
    {
        testList.insertFirst( testObj1, testObj1.getName() );
        testList.insertFirst( testObj4, testObj4.getName() ); 
        testList.insertLast( testObj3, testObj3.getName() );
        testList.removeFirst();

        //peekAtLast - testObj3
        compObj = testList.peekAtLast();
        testName = compObj.getName();
        testNumber = compObj.getNumber();
        assertEquals( "testObj3 is last", "Lee", testName );
        assertEquals( "testObj3 is last", 1812, testNumber );
        
        //removeFirst - testObj1
        compObj = testList.peekAtFirst();
        testName = compObj.getName();
        testNumber = compObj.getNumber();
        assertEquals( "testObj4 is gone", "Jackson", testName );
        assertEquals( "testObj4 is gone", 1863, testNumber );
    }

    //TESTING: removeLast() ************** FAILURES ***************
    @Test
    public void testRemoveLast()
    {        
        testList.insertFirst( testObj1, testObj1.getName() );
        testList.insertFirst( testObj4, testObj4.getName() ); 
        testList.insertLast( testObj3, testObj3.getName() );
        testList.removeLast(); //Should remove testObj3.
       
        compObj = testList.peekAtLast();
        testName = compObj.getName();
        testNumber = compObj.getNumber(); 
        assertEquals( "testObj3 is gone", "Jackson", testName );
        assertEquals( "testObj3 is gone", 1863, testNumber );
    }

    //TESTING: iterator construction
    @Test
    public void testIteratorBuild()
    {
        testList.insertFirst( testObj1, testObj1.getName() );
        rator = testList.iterator(); //Returns instance DSALinkedListIterator
        testHasNext = rator.hasNext(); //Should be true.
        assertTrue( "Rator has more!", testHasNext );
    }

    //TESTING: iterator is empty
    @Test
    public void testIteratorEmpty()
    {
        rator = testList.iterator();
        assertFalse( "Rator got nothin'", rator.hasNext() );
    }

    //TESTING: iterator next()
    @Test
    public void testIterNext()
    {
        testList.insertFirst( testObj1, testObj1.getName() );
        compObj = testList.peekAtFirst();
        testName = compObj.getName();
        testNumber = compObj.getNumber(); 
        rator = testList.iterator();
        assertEquals( "testObj3 is gone", "Jackson", testName );
        assertEquals( "testObj3 is gone", 1863, testNumber );
    }

    //TESTING: iterator remove()
    @Test( expected = UnsupportedOperationException.class )
    public void testIterRemove()
    {
        rator = testList.iterator();
        rator.remove();
    }
}//End Test Harness. 
