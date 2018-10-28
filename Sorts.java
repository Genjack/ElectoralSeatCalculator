/* Name: Tim J. Dempsey
   Student ID: 19390664
   Unit and Time: DSA @ Wednesdays, 12pm
   File Name: File.java
   Contents: Java for the Sorts class of my DSA Assignment, containing the
   following methods:
    
    > mergeSortName( Candidate [] )
    > mergeSortState( Candidate [] )
    > mergeSortDivision( Candidate [] )
    > mergeSortParty( Candidate [] )
    > mergeSortPtySeat( SeatChallenger [] )
    > mergeSortDivSeat( SeatChallenger [] )

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
            else if( A[leftFront].getSurname().compareTo( A[rightFront].getSurname() ) == 0 )
            {
                //Same surname - check firstname
                if( A[leftFront].getGivenName().compareTo( A[rightFront].getGivenName() ) <= 0 )
                {
                    tempArr[nextFreeTempIdx] = A[leftFront];
                    leftFront += 1;
                }
                else
                {
                    tempArr[nextFreeTempIdx] = A[rightFront];
                    rightFront += 1;
                }
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

//***************************** SEAT SORTS ***********************************//    

    //SORTING BY PARTY NAME ALPHABETICALLY
    public static void mergeSortPtySeat( SeatChallenger [] A )
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        mergeSortSeatRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortSeatRecurse( SeatChallenger [] A, int leftIdx, 
        int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortSeatRecurse( A, leftIdx, midIdx );
            mergeSortSeatRecurse( A, midIdx + 1, rightIdx );

            mergeSeat( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void mergeSeat( SeatChallenger [] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        SeatChallenger [] tempArr = new SeatChallenger[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        SeatChallenger midIdxSeat = A[midIdx];

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( A[leftFront].getCnd().getPartyAb().compareTo( A[rightFront].getCnd().getPartyAb() ) <= 0 )
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

//****************************** SORT BY DIVISION ****************************//

    //SORTING BY DIVISION ID
    public static void mergeSortDivSeat( SeatChallenger [] A )
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        mergeSortDivSeatRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortDivSeatRecurse( SeatChallenger [] A, int leftIdx, 
        int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortDivSeatRecurse( A, leftIdx, midIdx );
            mergeSortDivSeatRecurse( A, midIdx + 1, rightIdx );

            mergeSeatDiv( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void mergeSeatDiv( SeatChallenger [] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        SeatChallenger [] tempArr = new SeatChallenger[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        SeatChallenger midIdxSeat = A[midIdx];

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( ( A[leftFront].getCnd().getDivID()) <= ( A[rightFront].getCnd().getDivID() ) )
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
