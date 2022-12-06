import java.util.*;
import java.io.*; 

class Quickselect {

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

    public static int partition(int[] arr, int low,
                                int high)
    {
        int pivot = arr[high], pivotloc = low;
        for (int i = low; i <= high; i++) {
            // inserting elements of less value
            // to the left of the pivot location
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotloc];
                arr[pivotloc] = temp;
                pivotloc++;
            }
        }
  
        // swapping pivot to the final pivot location
        int temp = arr[high];
        arr[high] = arr[pivotloc];
        arr[pivotloc] = temp;
  
        return pivotloc;
    }
  
    // finds the kth position (of the sorted array)
    // in a given unsorted array i.e this function
    // can be used to find both kth largest and
    // kth smallest element in the array.
    // ASSUMPTION: all elements in arr[] are distinct
    public static int quickselect(int[] arr, int low,
                                  int high, int k)
    {
        // find the partition
        int partition = partition(arr, low, high);
  
        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k - 1)
            return arr[partition];
  
        // if partition value is less than kth position,
        // search right side of the array.
        else if (partition < k - 1)
            return quickselect(arr, partition + 1, high, k);
  
        // if partition value is more than kth position,
        // search left side of the array.
        else
            return quickselect(arr, low, partition - 1, k);
    }
  
    // main method
    public static void main(String[] args) throws IOException {

        // list that holds strings of a file
        List<Integer> listOfInt
            = new ArrayList<Integer>();
       
        // load data from file
        BufferedReader bf = new BufferedReader(
            new FileReader("tc0.txt"));
       
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
            long start = System.nanoTime();
            // call the method
            Quickselect.quickselect(array, 0, array.length-1, 5);
            long end = System.nanoTime();
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