import java.util.*;
import java.io.*;  

public class ModifiedAlgoMain {
    public static int findMthDigit(int number, int digit){
        return number / (int)Math.pow(10, digit - 1) % 10;
    }

    public static boolean verifyAllEqualUsingALoop(ArrayList<Integer> list) {
        for (int s : list) {
            if (s != list.get(0))
                return false;
        }
        return true;
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

    public static int findKthSmallest(int k, int[]array, int m){
        int[] arr = array;
        int n = arr.length;
        int total = 0;
        int res=0;
        ArrayList<Integer>[] buckets = new ArrayList[10];
        while (m > 0){
            int counters[] = new int[10];
            
  
            // initializing array of arraylist
            for (int i = 0; i < 10; i++) {
                buckets[i] = new ArrayList<Integer>();
            }

            // iterate through arr elements
            for (int i = 0; i < n; i++){
                int num = arr[i];
                int j = findMthDigit(num, m);
                buckets[j].add(num);
                counters[j]++;
            }

            //
            int i = -1;
            while (total < k) {
                i++;
                total = total + counters[i];
            }

            // check if the kth element has already found or not
            if (counters[i] == 1 || verifyAllEqualUsingALoop(buckets[i])) {
                res = buckets[i].get(0);
                break;
            } else {
                arr = convertIntegers(buckets[i]);
                n = arr.length;
                total -= counters[i];
                m--;
            }
        }
        return res;
    }

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
            findKthSmallest(5, array, 1);
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