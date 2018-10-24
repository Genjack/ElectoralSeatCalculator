/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the Sorts class of my DSA Assignment, containing the
   following methods:
    
    > sortByName( Candidate [] )

    Notes: This algorithm was adapted from the algorithm supplied in pseudocode
    during Practical 2 of DSA.
*/

import java.util.*;
import java.io.*;

public class Sorts
{

//************************ MERGE SORTING ALGORITHMS **************************//

//****************************** SURNAME SORT ********************************//
    public static void mergeSortName( Candidate [] A )
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        mergeSortNameRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortNameRecurse( Candidate [] A, int leftIdx, 
        int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortNameRecurse( A, leftIdx, midIdx );
            mergeSortNameRecurse( A, midIdx + 1, rightIdx );

            mergeName( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void mergeName( Candidate [] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate [] tempArr = new Candidate[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        Candidate midIdxCnd = A[midIdx];

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( A[leftFront].getSurname().compareTo( A[rightFront].getSurname() ) <= 0 )
            {
                tempArr[nextFreeTempIdx] = A[leftFront];
                leftFront += 1;
            }
            else
            {
                tempArr[nextFreeTempIdx] = A[rightFront];
                rightFront += 1;
            }
            nextFreeTempIdx += 1;
        }
        for( int ii = leftFront; ii <= midIdx; ii++ )
        {
            tempArr[nextFreeTempIdx] = A[ii];
            nextFreeTempIdx += 1;
        }
        for( int jj = rightFront; jj <= rightIdx; jj++ )
        {
            tempArr[nextFreeTempIdx] = A[jj];
            nextFreeTempIdx += 1;
        }
//Copy the sorted array back to the actual array.
        for( nextFreeTempIdx = leftIdx; nextFreeTempIdx <= rightIdx; 
            nextFreeTempIdx++ )
        {
            A[nextFreeTempIdx] = tempArr[nextFreeTempIdx - leftIdx];
        }
    }//merge()     
    
//***************************** STATE SORT ***********************************//    

    public static void mergeSortState( Candidate [] A )
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        mergeSortStateRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortStateRecurse( Candidate [] A, int leftIdx, 
        int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortStateRecurse( A, leftIdx, midIdx );
            mergeSortStateRecurse( A, midIdx + 1, rightIdx );

            mergeState( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void mergeState( Candidate [] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate [] tempArr = new Candidate[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        Candidate midIdxCnd = A[midIdx];

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( A[leftFront].getStateAb().compareTo( A[rightFront].getStateAb() ) <= 0 )
            {
                tempArr[nextFreeTempIdx] = A[leftFront];
                leftFront += 1;
            }
            else
            {
                tempArr[nextFreeTempIdx] = A[rightFront];
                rightFront += 1;
            }
            nextFreeTempIdx += 1;
        }
        for( int ii = leftFront; ii <= midIdx; ii++ )
        {
            tempArr[nextFreeTempIdx] = A[ii];
            nextFreeTempIdx += 1;
        }
        for( int jj = rightFront; jj <= rightIdx; jj++ )
        {
            tempArr[nextFreeTempIdx] = A[jj];
            nextFreeTempIdx += 1;
        }
//Copy the sorted array back to the actual array.
        for( nextFreeTempIdx = leftIdx; nextFreeTempIdx <= rightIdx; 
            nextFreeTempIdx++ )
        {
            A[nextFreeTempIdx] = tempArr[nextFreeTempIdx - leftIdx];
        }
    }//merge()

//***************************** DIVISION SORT ********************************// 

    public static void mergeSortDivision( Candidate [] A )
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        mergeSortDivisionRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortDivisionRecurse( Candidate [] A, int leftIdx, 
        int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortDivisionRecurse( A, leftIdx, midIdx );
            mergeSortDivisionRecurse( A, midIdx + 1, rightIdx );

            mergeDivision( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void mergeDivision( Candidate [] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate [] tempArr = new Candidate[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        Candidate midIdxCnd = A[midIdx];

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( A[leftFront].getDivID() < A[rightFront].getDivID() )
            {
                tempArr[nextFreeTempIdx] = A[leftFront];
                leftFront += 1;
            }
            else
            {
                tempArr[nextFreeTempIdx] = A[rightFront];
                rightFront += 1;
            }
            nextFreeTempIdx += 1;
        }
        for( int ii = leftFront; ii <= midIdx; ii++ )
        {
            tempArr[nextFreeTempIdx] = A[ii];
            nextFreeTempIdx += 1;
        }
        for( int jj = rightFront; jj <= rightIdx; jj++ )
        {
            tempArr[nextFreeTempIdx] = A[jj];
            nextFreeTempIdx += 1;
        }
//Copy the sorted array back to the actual array.
        for( nextFreeTempIdx = leftIdx; nextFreeTempIdx <= rightIdx; 
            nextFreeTempIdx++ )
        {
            A[nextFreeTempIdx] = tempArr[nextFreeTempIdx - leftIdx];
        }
    }//merge()

//***************************** PARTY SORT ***********************************//    

    public static void mergeSortParty( Candidate [] A )
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        mergeSortPartyRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortPartyRecurse( Candidate [] A, int leftIdx, 
        int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortPartyRecurse( A, leftIdx, midIdx );
            mergeSortPartyRecurse( A, midIdx + 1, rightIdx );

            mergeParty( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void mergeParty( Candidate [] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate [] tempArr = new Candidate[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        Candidate midIdxCnd = A[midIdx];

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( A[leftFront].getPartyAb().compareTo( A[rightFront].getPartyAb() ) <= 0 )
            {
                tempArr[nextFreeTempIdx] = A[leftFront];
                leftFront += 1;
            }
            else
            {
                tempArr[nextFreeTempIdx] = A[rightFront];
                rightFront += 1;
            }
            nextFreeTempIdx += 1;
        }
        for( int ii = leftFront; ii <= midIdx; ii++ )
        {
            tempArr[nextFreeTempIdx] = A[ii];
            nextFreeTempIdx += 1;
        }
        for( int jj = rightFront; jj <= rightIdx; jj++ )
        {
            tempArr[nextFreeTempIdx] = A[jj];
            nextFreeTempIdx += 1;
        }
//Copy the sorted array back to the actual array.
        for( nextFreeTempIdx = leftIdx; nextFreeTempIdx <= rightIdx; 
            nextFreeTempIdx++ )
        {
            A[nextFreeTempIdx] = tempArr[nextFreeTempIdx - leftIdx];
        }
    }//merge()
}//End Class
