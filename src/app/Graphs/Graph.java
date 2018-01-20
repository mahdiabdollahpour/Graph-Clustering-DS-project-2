package app.Graphs;

import app.Edge;

import app.MylinekedList.MylinkedList;

import java.util.ArrayList;

public abstract class Graph {

    public int start = 0;
    Edge[] arr;


    public int v;
    public int e;

    public MylinkedList[] list;
    public ArrayList<Edge> sparseMatrix;

    public boolean[] marked;

    public abstract Edge[] calcCij();

    public abstract void print();

    public abstract void removeEdge(int a, int b);

    public abstract void fixCijs(int i, int j);

    public boolean[] gimmeLastMarkStat() {
        return marked;
    }

    public abstract boolean isThereaPath(int u, int v);

}
