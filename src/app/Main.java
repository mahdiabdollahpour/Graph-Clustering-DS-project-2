package app;

import app.Graphs.Graph;

import java.io.*;
import java.util.Scanner;

public class Main {

    static long readtime = 0;
    static long processTime = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command = in.next();
        //  = Arrays.copyOfRange(args, 0, args.length - 2);
        //    String address = "test1.txt";


        String[] commands = command.split("_");
        String address = commands[commands.length - 1];
        if (commands[1].toLowerCase().charAt(0) == 'l') {
            process(address, commands, true);
        } else {
            process(address, commands, false);
        }

    }


    public static void process(String address, String[] command, boolean ifList) {
        Graph g = null;
        long start = System.currentTimeMillis();
        try {
            g = FileIO.readFromFile(address, ifList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        readtime = end - start;
        g.print();
        System.out.println("/////////////////");
        //  g.test();
        start = System.currentTimeMillis();
        Edge[] edges;// edges we want to sort according to cij
        boolean flag = true;
        edges = g.calcCij();

        do {//the algo loop
//            g.print();


            if (command.length == 6) {//sorting
                Sort.sort(edges, g.start, edges.length - 1, command[2].toLowerCase().charAt(0), command[3].toLowerCase().charAt(0), Integer.parseInt(command[4]));
            } else {
                Sort.sort(edges, g.start, edges.length - 1, command[2].toLowerCase().charAt(0));
            }
            int bound = Math.min(edges.length, 300);
            //    System.out.println("start  : " + g.start);

            for (int i = g.start; i < bound && flag; i++) {
                System.out.println(edges[i]);
            }
            flag = false;
            //    System.exit(0);
            Edge e = edges[g.start];// edge with max Cij
            // System.out.println("start :" + g.start);
            System.out.println("removing " + e.toString());
            g.removeEdge(e.v1, e.v2);//removing it
            g.fixCijs(e.v1, e.v2);
            if (!g.isThereaPath(e.v1, e.v2)) {//is has been splited
                //    g.isConnected();
                break;
            }
            // System.out.println("passed one");
            System.out.println(g.e);
            System.out.println(g.start + " removed");

        } while (edges.length != 0);

        end = System.currentTimeMillis();
        processTime = end - start;

//        System.out.println(g.sparseMatrix.size());

        // storing the result
        try {
            FileIO.storeResult(g.gimmeLastMarkStat(), readtime, processTime, command, address);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
