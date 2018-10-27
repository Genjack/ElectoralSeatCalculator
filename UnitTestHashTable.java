/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Content: Java code for DSAHeap Class.
*/

import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
// import io.ConsoleRedirect; Not needed right now.
// import org.mockito.Mockito.*; //in case I need mock objects later.

@RunWith ( JUnit4.class )
public class UnitTestHashTable
{
    private DSAHashTable table;
    private int testCount;
    private int testPriority;
    private String cwgName;
    private int cwgYear;
    private TestClass testObj1, testObj2, testObj3, testObj4, testObj5, 
       testObj6, testObj7, testObj8, testObj9, testObj10, obj;
    private TestClass testGetObj;
    private boolean testContains;

    @Before
    public void setup()
    {
        table = new DSAHashTable( 10 );

        testObj1 = new TestClass( "TJ Jackson", 1863 );
        testObj2 = new TestClass( "WT Sherman", 1944 );
        testObj3 = new TestClass( "RE Lee", 1812 );
        testObj4 = new TestClass( "GB McClellan", 1975 );
        testObj5 = new TestClass( "US Grant", 1916 );
        testObj6 = new TestClass( "JEB Stuart", 1776 );
        testObj7 = new TestClass( "G Pickett", 1861 );
        testObj8 = new TestClass( "R King", 1862 );
        testObj9 = new TestClass( "JE Johnston", 1993 );
        testObj10 = new TestClass( "H Berdan", 1815 );

        testContains = false;
    }

    
    // TESTING: put() - adding a DSAHashEntry to the array.
    @Test
    public void testAddFunction()
    {
        table.put( "A", testObj1 ); //Jackson
        testCount = table.getCount();
        assertEquals( "count should be 1", 1, testCount );
    }  
    //Tested functions: Constructor, put(), hash(), stepHash()
    
    @Test
    public void testGet()
    {
        table.put( "A", testObj1 ); //Jackson
        table.put( "B", testObj2 ); //Sherman
        table.put( "C", testObj3 ); //Lee
        obj = (TestClass)(table.get( "A" ));
        assertEquals( "Jackson extricated", "TJ Jackson", obj.getName() );
        assertEquals( "Jackson extricated", 1863, obj.getNumber() );
    }

    @Test
    public void testRemove()
    {
        table.put( "A", testObj1 ); //Jackson
        table.put( "B", testObj2 ); //Sherman
        table.put( "C", testObj3 ); //Lee
        table.put( "D", testObj4 ); //McClellan
        table.put( "E", testObj5 ); //Grant
        table.put( "F", testObj6 ); //Stuart
        table.put( "G", testObj7 ); //Pickett
        table.put( "H", testObj8 ); //King
        table.put( "I", testObj9 ); //Johnston
        table.put( "J", testObj10 ); //Berdan
        obj = (TestClass)(table.remove( "F" ));
        assertEquals( "Stuart extricated", "JEB Stuart", obj.getName() );
        assertEquals( "Stuart extricated", 1776, obj.getNumber() );       
    }
    
    @Test
    public void testContainsKey()
    {
        table.put( "A", testObj1 ); //Jackson
        table.put( "B", testObj2 ); //Sherman
        table.put( "C", testObj3 ); //Lee
        table.put( "D", testObj4 ); //McClellan
        table.put( "E", testObj5 ); //Grant
        table.put( "F", testObj6 ); //Stuart
        table.put( "G", testObj7 ); //Pickett
        table.put( "H", testObj8 ); //King
        table.put( "I", testObj9 ); //Johnston
        table.put( "J", testObj10 ); //Berdan
        testContains = table.containsKey( "C" );
        assertTrue( "Table contains Lee", testContains );
    } //Find() has been tested implicitly.

    @Test
    public void testShrink()
    {
        table.put( "A", testObj1 ); //Jackson
        table.put( "B", testObj2 ); //Sherman
        table.put( "C", testObj3 ); //Lee
        table.put( "D", testObj4 ); //McClellan
        table.put( "E", testObj5 ); //Grant
        table.put( "F", testObj6 ); //Stuart
        table.put( "G", testObj7 ); //Pickett
        table.put( "H", testObj8 ); //King
        table.put( "I", testObj9 ); //Johnston
        table.put( "J", testObj10 ); //Berdan
        table.printTable();
        System.out.println( "==== ^ Pre remove == V post remove ==== " );
        table.remove( "A" );
        table.remove( "B" );
        table.remove( "C" );
        table.remove( "D" );
        table.remove( "E" );
        table.remove( "F" );
        table.remove( "G" );
        table.remove( "H" );
        table.printTable();
    }
}
