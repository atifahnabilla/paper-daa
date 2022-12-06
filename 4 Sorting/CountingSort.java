// Java implementation of Counting Sort

import java.io.*;
import java.util.*;

class CountingSort {
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

    void countSort(int array[], int size) {
      int[] output = new int[size + 1];
  
      // Find the largest element of the array
      int max = array[0];
      for (int i = 1; i < size; i++) {
        if (array[i] > max)
          max = array[i];
      }
      int[] count = new int[max + 1];
  
      // Initialize count array with all zeros.
      for (int i = 0; i < max; ++i) {
        count[i] = 0;
      }
  
      // Store the count of each element
      for (int i = 0; i < size; i++) {
        count[array[i]]++;
      }
  
      // Store the cummulative count of each array
      for (int i = 1; i <= max; i++) {
        count[i] += count[i - 1];
      }
  
      // Find the index of each element of the original array in count array, and
      // place the elements in output array
      for (int i = size - 1; i >= 0; i--) {
        output[count[array[i]] - 1] = array[i];
        count[array[i]]--;
      }
  
      // Copy the sorted elements into original array
      for (int i = 0; i < size; i++) {
        array[i] = output[i];
      }
    }
  
    // Driver code
    public static void main(String args[]) throws IOException{
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
  int size = array.length;
 
  long timeTotal = 0;
  int numOfExperiments = 25;

  while (numOfExperiments > 0) {
      long start = System.currentTimeMillis();
      CountingSort cs = new CountingSort();
      cs.countSort(array, size);
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