package app;


import java.util.ArrayList;

public class test {
    public static void main(String[] args) {


        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            edges.add(new Edge(i, i));

        }
        //for (int i = 0; i < 10; i++) {
          Edge e = edges.get(6);
            edges.remove(e);
        //}
        System.out.println(edges.size());
    }
}
