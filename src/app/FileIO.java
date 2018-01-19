package app;

import app.Graphs.Graph;
import app.Graphs.Listrep;
import app.Graphs.Matrixrep;
import app.MylinekedList.MylinkedList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileIO {

    public static Graph readFromFile(String address, boolean list) throws IOException {
        Graph graph;
        if (list) {
            graph = new Listrep();

        } else {

            graph = new Matrixrep();
        }
        BufferedReader bf = new BufferedReader(new FileReader(new File(address)));
        String input = null;
        ArrayList<Edge> edges = new ArrayList<>();
        int max = 0;
        while ((input = bf.readLine()) != null) {
            String[] separated = input.split(",");
            int a = Integer.parseInt(separated[0]);
            int b = Integer.parseInt(separated[1]);
            Edge edge = new Edge(a - 1, b - 1);
            //  Edge edge1 = new Edge(b - 1, a - 1);
            edges.add(edge);
            // edges.add(edge1);
            if (a > max) {
                max = a;
            } else if (b > max) {
                max = b;
            }


        }
        bf.close();
        Edge[] sparse = new Edge[edges.size()];
        sparse = edges.toArray(sparse);
        radixsort(sparse, edges.size(), max);
        edges = new ArrayList<>(Arrays.asList(sparse));

        if (list) {
            graph.list = new MylinkedList[max];
            for (int i = 0; i < graph.list.length; i++) {
                graph.list[i] = new MylinkedList<>();
            }
            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);
                graph.list[e.v1].add(e.v2);
                //   graph.list[e.v2].add(e.v1);
            }
        } else {

            for (int i = 0; i < sparse.length; i++) {
                System.out.println(sparse[i]);
            }

            graph.sparseMatrix = new ArrayList<>(Arrays.asList(sparse));
        }

        graph.e = edges.size() / 2;
        graph.v = max;
        return graph;
    }


    public static void storeResult(boolean[] marked, long readTime, long processTime, String[] command, String source) throws IOException {
        BufferedWriter bf = null;

        bf = new BufferedWriter(new FileWriter(new File(Long.toString(System.currentTimeMillis()) + ".txt")));
        bf.write("read time :" + readTime + " ms");
        bf.newLine();
        bf.write("process time :" + processTime + " ms");
        bf.newLine();
        bf.write("command : ");
        for (int i = 0; i < command.length; i++) {
            bf.write(command[i] + " ");
        }
        bf.newLine();

        bf.write("source input :" + source);
        bf.newLine();
        boolean zeroIstrue = marked[0];
        for (int i = 0; i < marked.length; i++) {
            if (zeroIstrue) {
                if (marked[i]) {
                    bf.write(i + 1 + " : " + "A");
                } else {
                    System.out.println("its B");
                    bf.write(i + 1 + " : " + "B");
                }
            } else {
                if (!marked[i]) {
                    bf.write(i + 1 + " : " + "A");
                } else {
                    System.out.println("its B");
                    bf.write(i + 1 + " : " + "B");
                }
            }
            bf.newLine();
        }

        bf.close();
    }


    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    private static void countSort(Edge arr[], int n, int keyNum, int range) {
        Edge output[] = new Edge[n]; // output array
        int i;
        int count[] = new int[range];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            if (keyNum == 1) {
                count[arr[i].v1]++;

            } else {

                count[arr[i].v2]++;
            }
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < range; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            if (keyNum == 1) {
                output[count[arr[i].v1] - 1] = arr[i];
                count[arr[i].v1]--;
            } else {
                output[count[arr[i].v2] - 1] = arr[i];
                count[arr[i].v2]--;

            }
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static void radixsort(Edge arr[], int n, int range) {
        // Find the maximum number to know number of digits
        //  int m = getMax(arr, n);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        // for (int exp = 1; m/exp > 0; exp *= 10)
        countSort(arr, n, 2, range);
        countSort(arr, n, 1, range);
        //
    }


}
