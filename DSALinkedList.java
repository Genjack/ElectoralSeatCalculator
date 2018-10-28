/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Content: Java code for DSAList class, which contains the code for both
   DSALinkedList and DSAListNode as a private class. Updated to take generics
   for increased compatibility and ease of use.
*/

import java.util.*;

public class DSALinkedList<E> implements Iterable<E>
{
    //ITERATOR CONSTRUCTION
    public Iterator<E> iterator()
    {
        return new DSALinkedListIterator<E>( this ); //Accesses the Alt Con.
    }

//****************************** ITERATOR CLASS ****************************//

    //Private Iterator class for accessing Java's iterator to access the list. 
    private class DSALinkedListIterator<E> implements Iterator<E>
    {
        //classfield
        private DSALinkedList<E>.DSAListNode<E> iterNext;

        //ALTERNATE CONSTRUCTOR
        public DSALinkedListIterator( DSALinkedList<E> theList )
        {
            iterNext = theList.head; //Starts the iterator at the head.
        }

    //************************* Iterator Methods ***************************//

        //Method to check whether there are more objects to parse.
        public boolean hasNext()
        {
            boolean isMore = false;
            if( iterNext != null )
            {
                isMore = true;
            }
            return isMore;
        }

        //Moves to the next object, and returns a copy of the current.
        public E next()
        {
            E value;
            if( iterNext == null )
            {
                value = null;
            }
            else
            {   
                value = iterNext.getContent();
                iterNext = iterNext.getNextNode();
            }
            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException( "Not supported" );
        }
    }//End Private Class

//************************* END ITERATOR CLASS *****************************//
//**************************************************************************//
//************************* DSAListNode Class ******************************//


    private class DSAListNode<E>
    {
        private E content; //Could also be public classfields.
        private DSAListNode<E> nextNode, prevNode;
        private String key; //each node imports a key

        //ALTERNATE CONSTRUCTOR - UNSORTED
        public DSAListNode( E inContent ) //No <E> declaration.
        {
            content = inContent;
            nextNode = null;
            prevNode = null;
            key = null; //unused in this version for inserting sort.
        }

        //ALTERNATE CONSTRUCTOR - SORTED
        public DSAListNode( E inContent, String inKey )
        {
            content = inContent;
            nextNode = null;
            prevNode = null;
            key = inKey;
        }

    //***************************** ACCESSORS ******************************//

        public E getContent()
        {
            return content;
        }
    
        public DSAListNode<E> getNextNode() //Returns the successor node.
        {
            return nextNode;
        }
        
        public DSAListNode<E> getPrevNode() //Returns the predecessor node.
        {
            return prevNode;
        }

        public String getKey()
        {
            return key;
        }
        
    //***************************** MUTATORS *******************************//

        public void setContent( E inContent )
        {
            content = inContent;
        }
        
        public void setNextNode( DSAListNode<E> inNewNode )
        {
            nextNode = inNewNode;
        }
        
        public void setPrevNode( DSAListNode<E> inPrevNode )
        {
            prevNode = inPrevNode;
        }
        
        public void setKey( String inKey )
        {
            key = inKey;
        }
    }
    //****************** END PRIVATE CLASS DSAListNode *********************//

//*********************** RESUME CLASS DSALinkedList ***********************//
    
    //DSALinkedList Classfield
    private DSAListNode<E> head, tail;
    private int count;

    //DEFAULT CONSTRUCTOR
    public DSALinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    //Method for checking if the list is empty or not:
    public boolean isEmpty()
    {
        boolean isEmpty;

        isEmpty = (head == null);

        return isEmpty;
    }

//******************************* ACCESSORS ********************************//

    public int getCount()
    {
        return count;
    }

    //Method for returning the first object in the list.
    public E peekAtFirst()
    {
        E nodeContent;
    
        if( isEmpty() )
        {
            throw new NullPointerException( "Error: head is null." );
        }
        else
        {
            nodeContent = head.getContent();
        }
        return nodeContent;
    }

    //Method for returning the last object of the list.
    public E peekAtLast()
    {
        E nodeContent;

        if( isEmpty() )
        {
            throw new NullPointerException( "Error: List is empty." );
        }
        else
        {
            nodeContent = tail.getContent();
        }
        return nodeContent;
    }

//******************************** MUTATORS ********************************//

    public void insertFirst( E newContent, String newKey )
    {
        DSAListNode<E> newNode;
        
        newNode = new DSAListNode<E>( newContent, newKey );
        if( isEmpty() )
        {
            head = newNode; //front of the list.
            tail = newNode; //back of the list.
        }
        else
        {
            newNode.setNextNode( head );
            head.setPrevNode( newNode );
            head = newNode;
        }
        count++;
    }

    public void insertLast( E newContent, String newKey )
    {
        DSAListNode<E> newNode;

        newNode = new DSAListNode<E>( newContent, newKey );
        if( isEmpty() )
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.setNextNode( newNode );
            newNode.setPrevNode( tail );
            tail = newNode;
        }
        count++;
    }

    public void insertSorted( E newContent, String newKey )
    {
        DSAListNode<E> newNode, currNode, prevNode, tailNode;
        String currString;

        if( isEmpty() ) //Case 1: Graph is empty, just insert.
        {
            insertFirst( newContent, newKey ); 
        }
        else //Case 2: Graph has one or more nodes, and is sorted.
        {
            newNode = new DSAListNode<E>( newContent, newKey );
            currNode = tail;
            currString = currNode.getKey(); //first key of head, i.e. "D".
            if( currString.compareTo( newKey ) < 0 ) //if new Key is smaller
            {
                insertLast( newContent, newKey );
            }
            //Case 3: Graph has one or more nodes, and is NOT sorted.
            else //Iterate through the list to find the right spot.
            {
                currNode = head; //Start at the head...
                currString = currNode.getKey(); //... get the key.
                //IF current node is smaller than the new key:
                while( currString.compareTo( newKey ) < 0 ) 
                {
                    //Move on:
                    currNode = currNode.getNextNode();
                    currString = currNode.getKey();
                    //Ensure no duplicate keys.
                    if( currString.compareTo( newKey ) != 0 )
                    {
                        if( currString.compareTo( newKey ) > 0 )
                        {
                            prevNode = currNode.getPrevNode();
                            prevNode.setNextNode( newNode );
                            currNode.setPrevNode( newNode );
                            newNode.setPrevNode( prevNode );
                            newNode.setNextNode( currNode ); 
                        }
                    }
                    else //incoming key and current key are same - invalid
                    {
                        throw new IllegalArgumentException( "Error: " + 
                            "Can't have same keys." );
                    }
                } //END WHILE LOOP
            }
        } //End If/Else blocks
        count++;
    }

    public E removeFirst()
    {
        E nodeContent = null;

        if( isEmpty() )
        {
            throw new NullPointerException( "Error: List is empty." );
        }
        else
        {
            nodeContent = head.getContent();
            if( head.getNextNode() != null )
            {
                head = head.getNextNode();
            }
            else //Remove the only node in the list:
            {
                head = null;
                tail = null;
            }
            count--;
        }
        return nodeContent;
    }

    public E removeLast()
    {
        E nodeContent;
        DSAListNode<E> prevNode, currentNode;

        if( isEmpty() )
        {
            throw new NullPointerException( "Error: List is empty." );
        }
        else
        {
            if( tail.getPrevNode() == null ) //Only one node in the list:
            {
                nodeContent = head.getContent();
                head = null;
                tail = null;
            }
            else //Multiple nodes:
            {
                nodeContent = tail.getContent();
                tail = tail.getPrevNode();
                
                tail.setNextNode( null ); 
            }
            count--;
        }
        return nodeContent;
    }
}//End Class.
