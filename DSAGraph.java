/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Content: Java code for DSAGraph Class, which will have a linked list to 
   store the nodes, and a linked list within each node to store the 
   adjacency list. 

   Note: DSAGraph (this main one) has a LinkedList for the vertices.
   Then, each DSAListNode inside this LinkedList has an Object, which will
   be a DSAGraphVertex Class Object with a label and links of their own.
*/

import java.util.*;

public class DSAGraph<E>
{

//********************* PRIVATE CLASS - DSAGraphVertex *********************//

    private class DSAGraphVertex
    {
        //Classfields
        private String m_label;
        private E m_value;
        private DSALinkedList<DSAGraphEdge> m_edgeList;
        private boolean m_visited; //true if visited, false otherwise.   
        
        //ALTERNATE CONSTRUCTOR
        public DSAGraphVertex( String inLabel, E inVal )
        {
            m_label = inLabel;
            m_value = inVal;
            m_edgeList = new DSALinkedList<DSAGraphEdge>();
            m_visited = false; //default.
        }

    //****************************** ACCESSORS *****************************//

        public String getLabel()
        {
            return m_label;
        }

        public E getValue()
        {
            return m_value;
        }

        public DSALinkedList<DSAGraphEdge> getAdjacentEdges()
        {
            return m_edgeList;
        }

        public boolean getVisited()
        {
            return m_visited;
        }
        public DSAGraphVertex getNextUnvisited()
        {
            DSALinkedList<DSAGraphEdge> edgelist;
            DSAGraphEdge nextEdge;
            Iterator<DSAGraphEdge> rator;
            DSAGraphVertex nextUnvisited = null;
            boolean exists = false;

            rator = m_edgeList.iterator();
            nextEdge = rator.next();
            while( nextEdge != null && !exists )
            {
                if( ! ( nextEdge.getVertTo().getVisited() ) )
                {
                    nextUnvisited = nextEdge.getVertTo();
                    exists = true;
                }
                if( !exists )
                {
                    nextEdge = rator.next();
                }
            }
            return nextUnvisited;
        }

    //****************************** MUTATORS ******************************//

        public void addEdge( DSAGraphEdge inEdge )
        {
            m_edgeList.insertSorted( inEdge, inEdge.getLabel() );
        }

        public void setVisited()
        {
            m_visited = true;
        }
    
        public void clearVisited()
        {
            m_visited = false;
        }
        
    //****************************** TOSTRING ******************************//

        public String toString()
        {
            DSAGraphVertex currTex; //current vertex for the iterator to track.
            DSAGraphEdge currEdge;
            String vertexDetails; 
            String edges = null;
            Iterator<DSAGraphEdge> rator = m_edgeList.iterator(); 

            while( rator.hasNext() )
            {
                currEdge = rator.next(); 
                edges += currEdge.getLabel(); //The current vertex is added to output
            }//End while.
            if( edges == null )
            {
                edges = "N/A";
            }
            vertexDetails = m_value.toString(); //Not sure if this works.
            vertexDetails += "Label: " + m_label + ";\n Visited: " + m_visited;
            vertexDetails += ";\n Adjacent edges: " + edges;
            return vertexDetails;
        }
    }
    //************************** END DSAGraphVertex Class ********************//
//****************************************************************************//

    //*************************** DSAGraphEdge Class *************************//

    private class DSAGraphEdge
    {
        //Classfields
        private int e_weight;
        private String e_label;
        private boolean e_visited;
        private DSAGraphVertex e_vertFrom, e_vertTo;

        //ALTERNATE CONSTRUCTOR
        public DSAGraphEdge( String inLabel, int inWeight )
        {
            e_weight = inWeight;
            e_label = inLabel;
            e_visited = false;
            e_vertFrom = null;
            e_vertTo = null;
        }

        //Getters
        public String getLabel()
        {
            return e_label;
        }

        public int getWeight()
        {
            return e_weight;
        }

        public boolean getVisited()
        {
            return e_visited;
        }

        public DSAGraphVertex getVertFrom()
        {
            return e_vertFrom;
        }

        public DSAGraphVertex getVertTo()
        {
            return e_vertTo;
        }

        //MUTATORS - ADDING REFERENCES TO FROM AND TO
        public void setTo( DSAGraphVertex inVert )
        {
            e_vertTo = inVert;
        }

        public void setFrom( DSAGraphVertex inVert )
        {
            e_vertFrom = inVert;
        }
        
        //toString
        public String toString()
        {
            return ( "Label: " + e_label + ";\n Distance: " + e_weight + ";\n "
                + "Visited: " + e_visited + ";\n From: " + e_vertFrom.getLabel()
                + ";\n To: " + e_vertTo.getLabel() + ";" );
        }
    }
    //************************ END DSAGRAPH EDGE CLASS ***********************//

//**************************** RESUME DSAGraph Class *************************//

    //DSAGraph Classfields
    private DSALinkedList<DSAGraphVertex> vertices;
    private DSALinkedList<DSAGraphEdge> edges;
    private int edgeCount;
    
    //DEFAULT CONSTRUCTOR
    public DSAGraph()
    {
        vertices = new DSALinkedList<DSAGraphVertex>();
        edges = new DSALinkedList<DSAGraphEdge>();
        edgeCount = 0;
    }

    //Alternate Constructor will be in File Class of some description.

//********************************* ACCESSORS ******************************//
    
    //Retrieve the number of vertices
    public int getVertexCount()
    {
        Iterator<DSAGraphVertex> rator;
        DSAGraphVertex vert;
        int count = 0;
        rator = vertices.iterator(); //Declare DSALinkedList iterator.
        
        while( rator.hasNext() )
        {
            count += 1;
            vert = rator.next();
        }
        return count;
    }

    public int getEdgeCount() 
    {
        return edgeCount;
    }
            
//start with A, count all; store A away; go to B, count all; if A, don't add
//store B away; go to C, do the same but miss A and B etc etc.
        //Go through, count; for each count, swap around the edges and 
        //see if you have that combo already; if yes, don't add.
    
    //Should return the value of the node, which should be a DSAGraphVertex
    public DSAGraphVertex getVertex( String inLabel )
    {
        boolean found = false;
        DSAGraphVertex outVert = null;
        DSAGraphVertex compVert;
        Iterator<DSAGraphVertex> rator = vertices.iterator();

        while( ( rator.hasNext() ) && ( !found ) ) 
        {
            compVert = (DSAGraphVertex)rator.next();
            if( compVert.getLabel().equals( inLabel ) )
            {
                outVert = compVert;
                found = true; 
            }
        }
        if( outVert == null )
        {
            throw new IllegalArgumentException( "Error: label not found." );
        }   
        return outVert;
    }

    public DSAGraphEdge getEdge( String inLabel )
    {
        boolean found = false;
        DSAGraphEdge outEdge = null;
        DSAGraphEdge comparEdge;
        Iterator<DSAGraphEdge> rator = edges.iterator();

        while( ( rator.hasNext() ) && ( found == false ) ) 
        {
            comparEdge = (DSAGraphEdge)rator.next();
            if( comparEdge.getLabel().equals( inLabel ) )
            {
                outEdge = comparEdge;
                found = true; 
            }
        }
        if( outEdge == null )
        {
            throw new IllegalArgumentException( "Error: label not found." );
        }   
        return outEdge;
    }

    //Can pass this function into, for example, a display function.
    public DSALinkedList<DSAGraphEdge> getAdjacentEdges( DSAGraphVertex inVert )
    {
        return inVert.getAdjacentEdges();
    }

    public boolean isAdjacent( DSAGraphVertex vert1, DSAGraphVertex vert2 )
    {
        boolean isAdj = false;
        DSALinkedList<DSAGraphEdge> compList;
        DSAGraphEdge compEdge;
        Iterator<DSAGraphEdge> rator;

        compList = getAdjacentEdges( vert1 );
        rator = compList.iterator();
    
        while( ( rator.hasNext() ) && ( isAdj == false ) )
        {
            compEdge = rator.next();
            if( compEdge.getVertTo().getLabel().equals( vert2.getLabel() ) )
            {
                isAdj = true;
            }
        } 
        return isAdj;
    }

//********************************* MUTATORS *********************************//

    public void addVertex( String inLabel, E inVal )
    {
        DSAGraphVertex newVert;
        if( getVertexCount() == 0 )
        {
            newVert = new DSAGraphVertex( inLabel, inVal );
            vertices.insertSorted( newVert, newVert.getLabel() );
        }
        else
        {
            if( !( exists( inLabel ) ) ) //check if label already exists.
            {
                newVert = new DSAGraphVertex( inLabel, inVal );
                vertices.insertSorted( newVert, newVert.getLabel() );
            }
        }
    }

    public void addEdge( String vert1Label, String vert2Label, String inLabel,
        int inWeight )
    {    
        DSAGraphEdge newEdgeIn, newEdgeOut;
            
        if( ( exists( vert1Label ) ) && ( exists( vert2Label ) ) )
        {
            DSAGraphVertex vert1 = getVertex( vert1Label );
            DSAGraphVertex vert2 = getVertex( vert2Label );

            if( ! ( isAdjacent( vert1, vert2 ) ) )
            {
                //Vertices exist and edge doesn't, so we can connect them:
                newEdgeIn = new DSAGraphEdge( inLabel, inWeight );
                newEdgeIn.setFrom( vert1 );
                newEdgeIn.setTo( vert2 );
                vert1.addEdge( newEdgeIn );

                newEdgeOut = new DSAGraphEdge( inLabel, inWeight );
                newEdgeOut.setFrom( vert2 );
                newEdgeOut.setTo( vert1 );
                vert2.addEdge( newEdgeOut );
                
                edges.insertSorted( newEdgeIn, inLabel ); //don't need getter here
                edgeCount++;
            }
        }   
    }

//****************************** DISPLAY METHODS *****************************//

    /* These methods are, at this stage at least, more for enabling me to see
       what's happening under the hood in testing */

    public void displayList()
    {
        Iterator<DSAGraphVertex> vertRator; 
        Iterator<DSAGraphEdge> edgeRator;
        DSALinkedList<DSAGraphEdge> compEdges;
        DSAGraphVertex currVert;
        DSAGraphEdge innerEdge;
        
        vertRator = vertices.iterator();
        while( vertRator.hasNext() )
        {
            currVert = vertRator.next();
            System.out.print( currVert.getLabel() + "|" );
            compEdges = currVert.getAdjacentEdges();
            edgeRator = compEdges.iterator();
            
            while( edgeRator.hasNext() )
            {
                innerEdge = edgeRator.next();
                System.out.print( innerEdge.getVertTo().getLabel() );
            }
            System.out.print("\n");
        }
    }

    public boolean exists( String inLabel )
    {
        Iterator<DSAGraphVertex> rator;
        DSAGraphVertex currVert;
        boolean inVertExists = false;
        
        rator = vertices.iterator();
        while( rator.hasNext() )
        {
            currVert = rator.next();
            if( currVert.getLabel().equals( inLabel ) )
            {
                inVertExists = true;
            }
        }
        return inVertExists;
    }

    public boolean edgeExists( String inLabel )
    {
        Iterator<DSAGraphEdge> rator;
        DSAGraphEdge currEdge;
        boolean exists = false;

        rator = edges.iterator();
        while( rator.hasNext() )
        {
            currEdge = rator.next();
            if( currEdge.getLabel().equals( inLabel ) )
            {
                exists = true;
            }
        }
        return exists;
    }

    /* Function: Display graph as a matrix; print out all vertices first in a 
       line, then new line, then go through each vertex in turn. For each, 
       check with each subsequent vertex for an edge; if so, print 1; if not,
       print 0. */
    public void displayMatrix()
    {
        /* iterate over vertices - print - reset - iterate - 1) get adjacent -
           iterate over internal verts - if they have a vertex, print 1, if not,
           print 0 - new line - repeat - end */
        
        Iterator<DSAGraphVertex> vertRator, ratorMain;
        DSALinkedList<DSAGraphVertex> compList;
        DSAGraphVertex currVert, miniVert;
        
        vertRator = vertices.iterator();
        System.out.print( "  " ); //Indenting top line for alignment of matrix
        while( vertRator.hasNext() )
        {
            currVert = vertRator.next();
            System.out.print( currVert.getLabel() + " " );
        }
        System.out.print("\n");
        vertRator = vertices.iterator();
        ratorMain = vertices.iterator();
        while( ratorMain.hasNext() )
        {
            currVert = ratorMain.next();
            System.out.print( currVert.getLabel() + " " );
            while( vertRator.hasNext() )
            {
                if( isAdjacent( currVert, vertRator.next() ) ) //A and B
                {
                    System.out.print( 1 + " " );
                }
                else
                {
                    System.out.print( 0 + " " );
                }
            }
            System.out.print("\n");
            vertRator = vertices.iterator();
        }
    }

    public void clearVisited()
    {
        Iterator<DSAGraphVertex> rator;
        DSAGraphVertex currVert;
        
        rator = vertices.iterator();
        while( rator.hasNext() )
        {
            currVert = rator.next();
            currVert.clearVisited();
        }
    } 

/**
* Function: depthFirstSearch()
* Description: This algorithm selects the first element in the graph based
*   on alphabetical order, and traverses the graph based on the edges, 
*   visiting each node in turn.
**/
//NOTE: post midday alterations from this point onwards.
                
    public void dfs()
    {
        DSALinkedList<DSAGraphVertex> stack = new DSALinkedList<DSAGraphVertex>();
        Iterator<DSAGraphVertex> vertRator;
        clearVisited();
        DSAGraphVertex printVert, current;

        current = vertices.peekAtFirst();

        stack.insertLast( current, current.getLabel() );
        current.setVisited();
        System.out.println( current.getLabel() );

        while( ! ( stack.isEmpty() ) )
        {
            while( stack.peekAtLast().getNextUnvisited() != null )
            {
                stack.insertLast( stack.peekAtLast().getNextUnvisited(),
                    stack.peekAtLast().getNextUnvisited().getLabel() );
                stack.peekAtLast().setVisited();
                System.out.println( stack.peekAtLast().getLabel() );
            }
            stack.removeLast();
        }
    }

    public void bfs()
    {
        DSALinkedList<DSAGraphVertex> queue;
        queue = new DSALinkedList<DSAGraphVertex>();
        DSAGraphVertex current, innerVert;

        clearVisited();
        
        current = vertices.peekAtFirst();
        queue.insertLast( current, current.getLabel() );
        current.setVisited();
        System.out.println( current.getLabel() );

        while( ! ( queue.isEmpty() ) )
        {
            innerVert = queue.removeFirst();
            for( DSAGraphEdge edge : innerVert.getAdjacentEdges() )
            {
                if( !edge.getVertTo().getVisited() )
                {
                    edge.getVertTo().setVisited();
                    queue.insertLast( edge.getVertTo(), 
                        edge.getVertTo().getLabel() );
                    System.out.println( edge.getVertTo().getLabel() );
                }
            }
        }
    }
}//End Class.
