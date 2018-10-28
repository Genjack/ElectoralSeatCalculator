public class test
{
    public static void main( String [] args )
    {
        DSAGraph<String> testgraph = new DSAGraph<String>();

        testgraph.addVertex( "A", "A" );
        testgraph.addVertex( "B", "B" );
        testgraph.addVertex( "C", "C" );
        testgraph.addVertex( "D", "D" );
        testgraph.addEdge("A","D","atod",1500);
        boolean b = false;
        b = testgraph.isAdjacent(testgraph.getVertex("A"),testgraph.getVertex("D"));
    }
}
