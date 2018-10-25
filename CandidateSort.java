/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
        boolean sorted;
        int pass = 0;
        do
        {
            sorted = true;
            for( int ii = 0; ii < A.length - 1 - pass; ii++ )
            {
                if( A[ii] > A[ii+1])
                {
                    int temp = A[ii];
                    A[ii] = A[ii+1];
                    A[ii+1] = temp;
                    sorted = false;
                }
            }
            pass += 1;
        } while( ! ( sorted ) );
    }//bubbleSort()

    // selection sort
    public static void selectionSort(int[] A)
    {
        for( int pass = 0; pass < A.length - 1; pass++ )
        {
            int minIndex = pass;
            for( int ii = pass+1; ii < A.length - 1; ii++ )
            {
                if( A[ii] < A[minIndex] )
                {
                    minIndex = ii; //Taking note of where min value is so far.
                }
            }
            int temp = A[minIndex]; //This is the smallest unsorted value.
            A[minIndex] = A[pass]; //The old smallest value is given to the middle...
            A[pass] = temp; //... And the smallest unsorted value is put into position.
        }
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
        for( int ii = 1; ii < A.length; ii++ )
        {
            int num = ii;
        
            while ( ( num > 0 ) && ( A[num-1] > A[num] ) )
            {
                int temp = A[num];
                A[num] = A[num-1];
                A[num-1] = temp;

                num = num - 1;
            }
        }
    }// insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
        int leftIdx = 0;
        int rightIdx = A.length - 1;
        for( int ii = 0; ii < A.length; ii++ )
        {
            System.out.println( A[ii] );    
        }
        mergeSortRecurse( A, leftIdx, rightIdx );
         
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = (leftIdx + rightIdx) / 2;

            mergeSortRecurse( A, leftIdx, midIdx );
            System.out.println( "here" );
            mergeSortRecurse( A, midIdx + 1, rightIdx );

            merge( A, leftIdx, midIdx, rightIdx );
        }
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
        int [] tempArr = new int[rightIdx-leftIdx +1];
        int leftFront, rightFront, nextFreeTempIdx;

        leftFront = leftIdx; //ii in pseudo
        rightFront = midIdx+1; //jj in pseudo
        nextFreeTempIdx = 0; //kk in pseudo

        while( ( leftFront <= midIdx ) && ( rightFront <= rightIdx ) )
        {
            //Merge sub-arrays into tempArr

            if( A[leftFront] <= A[rightFront] )
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

/*
    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSortCnd( Candidate [] A )
    {
        int leftIdx, rightIdx;
        leftIdx = 0;
        rightIdx = A.length - 1;

        quickSortCndRecurse( A, leftIdx, rightIdx );
    }

    private static void quickSortCndRecurse( Candidate [] A, int leftIdx, int rightIdx)
    {
        int pivotIdx, newPivotIdx;
        if( rightIdx > leftIdx ) //convergence
        {
            pivotIdx = ( leftIdx + rightIdx ) / 2; //Grabs the middle index
            newPivotIdx = doPartitioning( A, leftIdx, rightIdx, pivotIdx );

            quickSortCndRecurse( A, leftIdx, newPivotIdx-1 );
            quickSortCndRecurse( A, newPivotIdx + 1, rightIdx );
        }
    }//quickSortRecurse()

    private static int doPartitioning( Candidate [] A, int leftIdx, int rightIdx, int pivotIdx)
    {
        int pivotVal, currIdx, temp, newPivotIdx;
            
        pivotVal = A[pivotIdx]

		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning

    
*/
}//end Sorts calss
