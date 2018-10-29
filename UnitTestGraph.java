/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Contents: Test Harness for DSAGraph Class, Practical 6 of DSA.
*/

import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
// import io.ConsoleRedirect; Not needed right now.
// import org.mockito.Mockito.*; //in case I need mock objects later.

@RunWith ( JUnit4.class )
public class UnitTestGraph 
{
    //Classfields
    private DSAGraph<String> testGraph;
    private int testCount;
    private boolean testBool;
    private String testOutput;

    @Before
    public void setup()
    {
        testGraph = new DSAGraph<String>();
        testBool = false;
    }
    
    // TESTING: vertex Count function
    @Test
    public void testVertexCount()
    {
        testCount = testGraph.getVertexCount();
        assertEquals( "vertices list should be empty", 0, testCount ); 
    }

    //TESTING: edge count
    @Test
    public void testEdgeCount()
    {
        testCount = testGraph.getEdgeCount();
        assertEquals( "no edges yet", 0, testCount );
    }

    // TESTING: Add a Vertex to the graph.
    @Test
    public void testAddEdge()
    {
        testGraph.addVertex( "A", "A" );
        testGraph.addVertex( "B", "B" );
        testGraph.addVertex( "C", "C" );     
        testGraph.addEdge( "A", "B", "AYETUHBEE", 50 );
        testBool = testGraph.edgeExists( "AYETUHBEE" );
        assertTrue( testBool );
    }

    //TESTING: isAdjacent and getVertex
    @Test
    public void testIsAdjacent()
    {
        testGraph.addVertex( "A", "A" );
        testGraph.addVertex( "B", "B" );
        testGraph.addVertex( "C", "C" );
        testGraph.addVertex( "D", "D" );
        testGraph.addEdge( "A", "D", "atod", 1500 );
        testBool = testGraph.isAdjacent( testGraph.getVertex("A"),
            testGraph.getVertex("D") );
        assertTrue( testBool );
    }

    //TESTING: get Edge count with edges
    @Test
    public void testPositiveEdgeCount()
    {
        testGraph.addVertex( "A", "A" );
        testGraph.addVertex( "B", "B" );
        testGraph.addVertex( "C", "C" );
        testGraph.addVertex( "D", "D" );
        testGraph.addVertex( "E", "E" );
        testGraph.addEdge( "A", "B", "atob", 1500 );
        testGraph.addEdge( "A", "E", "atoe", 1500 );
        testGraph.addEdge( "B", "A", "btoa", 1500 );
        testGraph.addEdge( "B", "C", "btoc", 1500 );
        testGraph.addEdge( "B", "E", "btoe", 1500 );
        assertEquals( "4 edges", 4, testGraph.getEdgeCount() );
    }
    
    //TESTING: display List
    @Test
    public void testDisplayList()
    {
        testGraph.addVertex( "A", "A" );
        testGraph.addVertex( "B", "B" );
        testGraph.addVertex( "C", "C" );
        testGraph.addVertex( "D", "D" );
        testGraph.addVertex( "E", "E" );
        testGraph.addEdge( "A", "B", "atob", 1500 );
        testGraph.addEdge( "A", "E", "atoe", 1500 );
        testGraph.addEdge( "B", "A", "btoa", 1500 );
        testGraph.addEdge( "B", "C", "btoc", 1500 );
        testGraph.addEdge( "B", "E", "btoe", 1500 );
        testGraph.addEdge( "B", "D", "btod", 1500 );
        testGraph.addEdge( "C", "B", "ctob", 1500 );
        testGraph.addEdge( "C", "D", "ctod", 1500 );
        testGraph.addEdge( "D", "B", "dtob", 1500 );
        testGraph.addEdge( "D", "C", "dtoc", 1500 );
        testGraph.addEdge( "E", "A", "etoa", 1500 );
        testGraph.addEdge( "E", "B", "etob", 1500 );
        testGraph.displayList();
    }
}//End Test Harness. 
