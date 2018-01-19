package app;


import app.Graphs.Graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        String c = "RUN linkedlist optimum insertion ";
        String address = "test8.txt";
        try {
            bf.write("input :" + address);
            bf.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 20; i <= 30; i++) {
            String command = c.concat(String.valueOf(i));
            String[] commands = command.split(" ");
            if (commands[1].toLowerCase().charAt(0) == 'l') {
                process(address, commands, true);
            } else {
                process(address, commands, false);
            }
        }
        try {
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static BufferedWriter bf = null;

    static {

        try {
            bf = new BufferedWriter(new FileWriter(String.valueOf(System.currentTimeMillis()) + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
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
        long readTime = end - start;
        //  g.print();
        // System.out.println("/////////////////");
        //  g.test();
        start = System.currentTimeMillis();
        Edge[] edges;// edges we want to sort according to cij
        edges = g.calcCij();
        if (command.length == 5) {//sorting
            Sort.sort(edges, g.start, edges.length - 1, command[2].toLowerCase().charAt(0), command[3].toLowerCase().charAt(0), Integer.parseInt(command[4]));
        } else {
            Sort.sort(edges, g.start, edges.length - 1, command[2].toLowerCase().charAt(0));
        }
        end = System.currentTimeMillis();
        long processTime = end - start;
        try {
            System.out.println("hi");
            bf.write("read time :" + readTime);
            bf.newLine();
            bf.write("process time :" + processTime);
            bf.newLine();
            bf.write("command : ");
            for (int i = 0; i < command.length; i++) {
                bf.write(command[i] + " ");

            }
            bf.newLine();
            bf.write("///////////");
            bf.newLine();
            //  bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
