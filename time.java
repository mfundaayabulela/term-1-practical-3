// 13template.java
import java.lang.Math.*;
import java.io.*;
import java.text.*;
import java.util.Random;

class Node {
    int key;
    String data;

    Node(int k, String d) {
        key = k;
        data = d;
    }
}

public class time {

    public static int N = 30000;   // number of records

    public static void main(String args[]) {

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0, runTime2 = 0, time;
        double runTimeB = 0, runTimeB2 = 0;   // binary stats
        int n = N;
        int repetition, repetitions = 30;

        // -------- Create sorted array of Nodes --------
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node(i + 1, "Data" + i);
        }

        Random rand = new Random();

        // -------- Linear Search Timing --------
        runTime = 0;
        for (repetition = 0; repetition < repetitions; repetition++) {

            int key = rand.nextInt(n) + 1;

            start = System.currentTimeMillis();
            linearsearch(arr, key);
            finish = System.currentTimeMillis();

            time = (double)(finish - start);
            runTime += time;
            runTime2 += (time * time);
        }

        double aveRuntime = runTime / repetitions;
        double stdDeviation =
                Math.sqrt(runTime2 - repetitions * aveRuntime * aveRuntime)
                        / (repetitions - 1);

        // -------- Binary Search Timing --------
        for (repetition = 0; repetition < repetitions; repetition++) {

            int key = rand.nextInt(n) + 1;

            start = System.currentTimeMillis();
            binarysearch(arr, key);
            finish = System.currentTimeMillis();

            time = (double)(finish - start);
            runTimeB += time;
            runTimeB2 += (time * time);
        }

        double aveRuntimeB = runTimeB / repetitions;
        double stdDeviationB =
                Math.sqrt(runTimeB2 - repetitions * aveRuntimeB * aveRuntimeB)
                        / (repetitions - 1);

        // -------- Print Results --------
        System.out.println("\nStatistics");
        System.out.println("____________________________________________");
        System.out.println("Linear Avg Time  = " + fiveD.format(aveRuntime) + " ms");
        System.out.println("Linear Std Dev   = " + fourD.format(stdDeviation));

        System.out.println("Binary Avg Time  = " + fiveD.format(aveRuntimeB) + " ms");
        System.out.println("Binary Std Dev   = " + fourD.format(stdDeviationB));
        System.out.println("n = " + n);
        System.out.println("Repetitions = " + repetitions);
        System.out.println("____________________________________________");
    }

    // -------- Linear Search --------
    static int linearsearch(Node[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].key == key)
                return i;
        }
        return -1;
    }

    // -------- Binary Search --------
    static int binarysearch(Node[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].key == key)
                return mid;
            else if (arr[mid].key < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
