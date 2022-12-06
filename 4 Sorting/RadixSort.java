// Radix Sort in Java Programming

import java.util.*;
import java.io.*; 

class RadixSort {

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

  // Using counting sort to sort the elements in the basis of significant places
  public static void countingSort(int array[], int size, int place) {
    int[] output = new int[size + 1];
    int max = array[0];
    for (int i = 1; i < size; i++) {
      if (array[i] > max)
        max = array[i];
    }
    int[] count = new int[max + 1];

    for (int i = 0; i < max; ++i)
      count[i] = 0;

    // Calculate count of elements
    for (int i = 0; i < size; i++)
      count[(array[i] / place) % 10]++;

    // Calculate cumulative count
    for (int i = 1; i < 10; i++)
      count[i] += count[i - 1];

    // Place the elements in sorted order
    for (int i = size - 1; i >= 0; i--) {
      output[count[(array[i] / place) % 10] - 1] = array[i];
      count[(array[i] / place) % 10]--;
    }

    for (int i = 0; i < size; i++)
      array[i] = output[i];
  }

  // Function to get the largest element from an array
  public static int getMax(int array[], int n) {
    int max = array[0];
    for (int i = 1; i < n; i++)
      if (array[i] > max)
        max = array[i];
    return max;
  }

  // Main function to implement radix sort
  public static void radixSort(int array[], int size) {
    // Get maximum element
    int max = getMax(array, size);

    // Apply counting sort to sort elements based on place value.
    for (int place = 1; max / place > 0; place *= 10)
      countingSort(array, size, place);
  }

  // Driver code
  public static void main(String args[]) throws IOException {
    // list that holds strings of a file
    List<Integer> listOfInt
    = new ArrayList<Integer>();

    // load data from file
    BufferedReader bf = new BufferedReader(
        new FileReader("tc29.txt"));

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
        // call the method
        RadixSort.radixSort(array, array.length);
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