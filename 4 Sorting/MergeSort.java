import java.util.*;
import java.io.*;  

/* Java program for Merge Sort */
class MergeSort {
	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	void merge(int arr[], int l, int m, int r)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/*Copy data to temp arrays*/
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(int arr[], int l, int r)
	{
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

    // source: https://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array
    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

	// Driver code
	public static void main(String args[]) throws IOException
	{
		// list that holds strings of a file
        List<Integer> listOfInt
            = new ArrayList<Integer>();
       
        // load data from file
        BufferedReader bf = new BufferedReader(
            new FileReader("tc28.txt"));
       
        // read entire line as string
        String line = bf.readLine();
       
        // checking for end of file
        while (line != null) {
            listOfInt.add(Integer.parseInt(line));
            line = bf.readLine();
        }
       
        // closing bufferreader object
        bf.close();
       
        // storing the data in arraylist to array
        int[] array
            = convertIntegers(listOfInt);
       
        long timeTotal = 0;
        int numOfExperiments = 25;
		
        while (numOfExperiments > 0) {
            long start = System.currentTimeMillis();
			MergeSort ob = new MergeSort();
            ob.sort(array, 0, array.length - 1);
            long end = System.currentTimeMillis();
            //System.out.println(res);
            long execution = end - start;
            timeTotal += execution;
            System.out.println("Execution time: " + execution + " nanoseconds");
            numOfExperiments--;
        }
        
        double meanResult = ((double)timeTotal)/25;
        System.out.println("Mean of 25 Executions time: " + meanResult);
	}
}
/* This code is contributed by Rajat Mishra */
