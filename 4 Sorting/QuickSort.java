// Java implementation of QuickSort
import java.util.*;
import java.io.*;

class QuickSort {

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

	// A utility function to swap two elements
	static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/* This function takes last element as pivot, places
	the pivot element at its correct position in sorted
	array, and places all smaller (smaller than pivot)
	to left of pivot and all greater elements to right
	of pivot */
	static int partition(int[] arr, int low, int high)
	{

		// pivot
		int pivot = arr[high];

		// Index of smaller element and
		// indicates the right position
		// of pivot found so far
		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {

			// If current element is smaller
			// than the pivot
			if (arr[j] < pivot) {

				// Increment index of
				// smaller element
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	/* The main function that implements QuickSort
			arr[] --> Array to be sorted,
			low --> Starting index,
			high --> Ending index
	*/
	static void quickSort(int[] arr, int low, int high)
	{
		if (low < high) {

			// pi is partitioning index, arr[p]
			// is now at right place
			int pi = partition(arr, low, high);

			// Separately sort elements before
			// partition and after partition
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	// Function to print an array
	static void printArray(int[] arr, int size)
	{
		for (int i = 0; i < size; i++)
			System.out.print(arr[i] + " ");

		System.out.println();
	}

	// Driver Code
	public static void main(String[] args) throws IOException
	{

		// list that holds strings of a file
        List<Integer> listOfInt
            = new ArrayList<Integer>();
       
        // load data from file
        BufferedReader bf = new BufferedReader(
            new FileReader("tc24.txt"));
       
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
		int n = array.length;
       
        long timeTotal = 0;
        int numOfExperiments = 25;

        while (numOfExperiments > 0) {
            long start = System.currentTimeMillis();
            quickSort(array, 0, n - 1);
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

// This code is contributed by Ayush Choudhary
