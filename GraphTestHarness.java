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
public class GraphTestHarness 
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
}//End Test Harness. 
