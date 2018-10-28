/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA, Tuesdays @ 12pm
   Content: Java code for DSAHashTable Class.
   REFERENCE NOTE: This code is taken from the Hash Table practical code I 
   submitted earlier in the semester, and the code was derived with the help
   of pseudocode and notes of lecture 9.
*/

import java.util.*;

public class DSAHashTable
{
    //******************* PRIVATE CLASS - DSAHashEntry ********************//
    private class DSAHashEntry
    {
        //CLASSFIELDS
        private String m_key; //Used to find the value
        private Object m_value; //Actual data we want to store.
        private int m_state; //0 = never used; 1 = used; -1 = formerly used.

        //DEFAULT CONSTRUCTOR
        public DSAHashEntry()
        {
            m_key = "";
            m_value = null;
            m_state = 0;
        }

        //ALTERNATE CONSTRUCTOR
        public DSAHashEntry( String inKey, Object inValue )
        {
            m_key = inKey;
            m_value = inValue;
            m_state = 1; //Marked as used owing to present value.
        }

        //GETTERS
        public String getKey()
        {
            return m_key;
        }

        public Object getValue()
        {
            return m_value;
        }

        public int getState()
        {
            return m_state;
        }

        public String entryToString()
        {
            String pretty;
            pretty = "Key: " + m_key + " Value: " + m_value + " state: " +
                m_state;
            return pretty;
        }

    //************************* MUTATORS ********************************//

        public void setState( int state )
        {
            m_state = state;
        }

        public void setValue( Object inValue )
        {
            m_value = inValue;
        }

        public void setKey( String inKey )
        {
            m_key = inKey;
        }
    }

//******************************* END PRIVATE CLASS **************************//
//RESUME DSAHashTable CLASS

    //Classfields
    private DSAHashEntry [] m_hashTable;
    private int count;

    //Default Con; not needed, but JUnit complains?
    public DSAHashTable()
    {
        int tableSize = findNextPrime( 100 );
        //Initialise an empty table, then fill it with entries.
        m_hashTable = new DSAHashEntry[tableSize];
        for( int ii = 0; ii < tableSize; ii++ )
        {
            m_hashTable[ii] = new DSAHashEntry();
        }
        count = 0; //No values currently stored.
    }

    //ALT CONSTRUCTOR
    public DSAHashTable( int tableSize )
    {
        //Prime numbers are good mod values - and we mod by table length - 
        // therefore we make the table size a prime number.
        int actualSize = findNextPrime( tableSize );
        m_hashTable = new DSAHashEntry[actualSize];
        for( int ii = 0; ii < actualSize; ii++ )
        {
            m_hashTable[ii] = new DSAHashEntry();
        }
        count = 0;
    }

    public int getCount()
    {
        return count;
    }

    public int findNextPrime( int startVal )
    {
        boolean isPrime;
        double rootVal, prime; //double for no mixed-mode arithmetic (MMA)
        int ii;
        if( startVal % 2 == 0 ) //Ensure the number coming in is odd.
        {
            prime = startVal - 1; 
        }
        else
        {
            prime = startVal; 
        }
        //startVal is now an odd number.
        isPrime = false;
        do
        {
            prime += 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt( prime );
            do
            {
                if( prime % ii == 0 )
                {
                    isPrime = false;
                }
                else
                {
                    ii += 2; //Seek out the next prime by incrementing.
                }
            } while( ( ii <= rootVal ) && ( isPrime ) );
        } while( !isPrime );
        return (int)(prime);
    }
   
    //insert item into hash table.
    public void put( String inKey, Object inValue )
    {
        DSAHashEntry entry = new DSAHashEntry( inKey, inValue );
        put( entry );//TALLY-HO!
    }

    public void put( DSAHashEntry entry )
    {
        if( calcLoad() >= 0.7 ) //Table is getting full; should resize.
        {
            //Assign a bigger value for a bigger reSize value.
            reSize( m_hashTable.length*2 );
        }
        int putIdx = hash( entry.getKey() );
        int origIdx = putIdx;
        int stepSize = stepHash( putIdx ); //gives you the increment value.

        while( m_hashTable[putIdx].getState() == 1 ) //something already there
        {
            putIdx += stepSize; //each iteration adds the stepSize value 
            putIdx = putIdx % m_hashTable.length; //Ensures it stays in table
        }
        //Exit of loop means a vacant space has been found for the value
        m_hashTable[putIdx].setKey( entry.getKey() ); 
        m_hashTable[putIdx].setValue( entry.getValue() );
        m_hashTable[putIdx].setState( 1 ); //Slot in, mark visited.
        count++;
    }

    //Find a key and return the value
    public Object get( String inKey )
    {
        int reqIdx;
        Object retValue;
        
        reqIdx = find( inKey );
        retValue = m_hashTable[reqIdx].getValue();
        return retValue;
    }

    public Object remove( String inKey )
    {
        if( calcLoad() < 0.4 ) //Table can be safely shrunk.
        {
            reSize( m_hashTable.length/2 );
        }
        int reqIdx;
        Object rescuedObj;

        reqIdx = find( inKey );
        rescuedObj = m_hashTable[reqIdx].getValue();
        m_hashTable[reqIdx].setState( -1 ); //removed slot is marked prev used.
        m_hashTable[reqIdx].setValue( null ); 
        m_hashTable[reqIdx].setKey( null ); 
        count--;
        return rescuedObj;
    }

    public boolean containsKey( String inKey )
    {
        int result;
        boolean contains;
        contains = false;

        result = find( inKey );

        if( result > 0 && result <= m_hashTable.length )
        {
            contains = true;
        }
        return contains;
    }

    //Provides a rating of between 0 and 1 based on how full the table is.
    public double calcLoad()
    {
        double loadFactor;
        loadFactor = ( (double)(count) / (double)(m_hashTable.length) );
        return loadFactor;
    }

    //Creates a new hashtable of a different size, then moves the old contents
    //across to the new table.
    private void reSize( int size )
    {
        DSAHashTable newTable = new DSAHashTable( size );
        for( int ii = 0; ii < m_hashTable.length; ii++ )
        {
            if( m_hashTable[ii].getState() == 1 )
            {
                newTable.put( m_hashTable[ii] );
            }
        }
        m_hashTable = newTable.m_hashTable;
    }

    //Based on the Bernstein hash function, as described in Lecture 9 slide 71
    private int hash( String key )
    {
        int hashIdx = 0;
        for( int ii = 0; ii < key.length(); ii++ )
        {
            hashIdx = (33 * hashIdx) + key.charAt( ii );
        }
        return hashIdx % m_hashTable.length;
    }

    private int stepHash( int inKey )
    {
        return 5 - ( inKey % 5 );
    }

    //Generic find method to locate required table index.
    private int find( String inKey )
    {
        int hashIdx, originalIdx;
        boolean found = false;
        boolean giveUp = false;

        hashIdx = hash( inKey );
        originalIdx = hashIdx;

        while( !found && !giveUp )
        {
            //Give up if we hit a never used entry.
            if( m_hashTable[hashIdx].getState() == 0 )
            {
                giveUp = true;
            }
            //Next case: we find the key.
            else if( m_hashTable[hashIdx].getKey().equals( inKey ) )
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + 1) % m_hashTable.length;
                if( hashIdx == originalIdx )
                {
                    //Means we've checked all nodes - give up!
                    giveUp = true;
                }//End nested if
            }//End if
        }//End while

        if( !found )
        {
            throw new IllegalArgumentException( "Error: Key not found" );
        }
        return hashIdx;      
    }

    public void printTable()
    {
        for( int ii = 0; ii < m_hashTable.length; ii++ )
        {
            System.out.println( m_hashTable[ii].entryToString() );
        }
    }
}
