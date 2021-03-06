package app.Graphs;

import app.Edge;

import java.util.ArrayList;

public class Matrixrep extends Graph {

    int[] starts;

    public void storeStarts() {
        starts = new int[v];
        for (int i = 0; i < v; i++) {
            starts[i] = firstStart(i);
        }
    }


    @Override
    public Edge[] calcCij() {
        storeStarts();
        Edge[] cijs = new Edge[e];
        int index = 0;
        for (int i = start; i < sparseMatrix.size(); i++) {
            Edge e = sparseMatrix.get(i);
            if (e.v1 > e.v2) {
                continue;
            }
            int ki = getK(e.v1);
            //  System.out.println(" k of :" + e.v1 + " is " + ki);
            int kj = getK(e.v2);
            // System.out.println(" k of :" + e.v2 + " is " + kj);
            int makhraj = Math.min(ki - 1, kj - 1);
            if (makhraj == 0) {
                cijs[index] = new Edge(10000000, e.v1, e.v2, 0);
                index++;
                continue;
            }
            int numberOfTriangels = 0;
            int pointer1 = firstIndexOf(e.v1);
            int pointer2 = firstIndexOf(e.v2);
            //  System.out.println("pointer1 " + pointer1);
            //  System.out.println("pointer2 " + pointer2);

            while (pointer1 < sparseMatrix.size() && pointer2 < sparseMatrix.size() &&
                    sparseMatrix.get(pointer1).v1 == e.v1 && sparseMatrix.get(pointer2).v1 == e.v2) {
                Edge e1 = sparseMatrix.get(pointer1);
                Edge e2 = sparseMatrix.get(pointer2);
                if (e1.v2 == e2.v2) {
                    numberOfTriangels++;
                    pointer1++;
                    pointer2++;
                } else if (e1.v2 < e2.v2) {
                    pointer1++;
                } else {
                    pointer2++;
                }

            }
            cijs[index] = new Edge((float) (((float) (numberOfTriangels + 1) * 1.0) / makhraj), e.v1, e.v2, numberOfTriangels);
            index++;

        }

        return arr = cijs;

    }

    @Override
    public void print() {
        for (int i = 0; i < sparseMatrix.size(); i++) {
            System.out.println(i + " " + sparseMatrix.get(i));
        }
    }

    @Override
    public void removeEdge(int x, int y) {
        start++;

        for (int i = firstIndexOf(x); i < sparseMatrix.size() && sparseMatrix.get(i).v1 == x; i++) {
            Edge edge = sparseMatrix.get(i);
            if (edge.v2 == y) {
                //   System.out.println("in the if 1");
                sparseMatrix.remove(i);
                break;
            }
        }
        for (int i = x + 1; i < starts.length; i++) {
            starts[i]--;
        }
        for (int i = firstIndexOf(y); i < sparseMatrix.size() && sparseMatrix.get(i).v1 == y; i++) {
            Edge edge = sparseMatrix.get(i);
            if (edge.v2 == x) {
                // System.out.println("in the if 2");
                sparseMatrix.remove(i);
                break;
            }
        }
        for (int i = y + 1; i < starts.length; i++) {
            starts[i]--;
        }

        e--;
//        System.out.println("e :" + e);
//        System.out.println("size :" + sparseMatrix.size());
//        if (e * 2 != sparseMatrix.size()) {
//            System.out.println("mismatch in number");
//
//            System.exit(0);
//        }
    }

    @Override
    public void fixCijs(int i, int j) {
        int count = 0;
        int pointer1 = firstIndexOf(i);
        int pointer2 = firstIndexOf(j);
        ArrayList<Integer> common = new ArrayList<>();
        ArrayList<Integer> onlywithi = new ArrayList<>();
        ArrayList<Integer> onlywithj = new ArrayList<>();
        while (pointer1 < sparseMatrix.size() && pointer2 < sparseMatrix.size() &&
                sparseMatrix.get(pointer1).v1 == i && sparseMatrix.get(pointer2).v1 == j) {
            Edge e1 = sparseMatrix.get(pointer1);
            Edge e2 = sparseMatrix.get(pointer2);
            if (e1.v2 == e2.v2) {
                common.add(e1.v2);
                pointer1++;
                pointer2++;
            } else if (e1.v2 < e2.v2) {
                onlywithi.add(e1.v2);
                pointer1++;
            } else {
                onlywithj.add(e2.v2);
                pointer2++;
            }
        }
        while (pointer1 < sparseMatrix.size() && sparseMatrix.get(pointer1).v1 == i) {
            onlywithi.add(sparseMatrix.get(pointer1).v2);
            pointer1++;
        }
        while (pointer2 < sparseMatrix.size() && sparseMatrix.get(pointer2).v1 == j) {
            onlywithj.add(sparseMatrix.get(pointer2).v2);
            pointer2++;
        }


        System.out.println("commmon : " + common);
        System.out.println("onlywith i : " + onlywithi);
        System.out.println("onlywith j : " + onlywithj);
        for (int counter = 0; counter < common.size(); counter++) {
            int comm = common.get(counter);

            for (int i1 = start; i1 < arr.length; i1++) {
                Edge e = arr[i1];

                if ((e.v1 == comm && e.v2 == i) || (e.v2 == comm && e.v1 == i)) {
                    arr[i1].trangle--;
                    int ki = getK(i);
                    int kj = getK(comm);
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                    }
                    arr[i1].cij = (float) (((arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                } else if ((e.v1 == comm && e.v2 == j) || (e.v2 == comm && e.v1 == j)) {
                    int ki = getK(j);
                    int kj = getK(comm);
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;

                    }
                    arr[i1].trangle--;
                    arr[i1].cij = (float) ((((float) (arr[i1].trangle + 1) * 1.0) / makhraj));
                    count++;
                }
            }
        }

        for (int counter = 0; counter < onlywithi.size(); counter++) {
            int comm = onlywithi.get(counter);

            for (int i1 = start; i1 < arr.length; i1++) {
                Edge e = arr[i1];

                if ((e.v1 == comm && e.v2 == i) || (e.v2 == comm && e.v1 == i)) {
                    int ki = getK(i);
                    int kj = getK(comm);
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                    }
                    arr[i1].cij = (float) (((float) (arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                }
            }
        }
        for (int counter = 0; counter < onlywithj.size(); counter++) {
            int comm = onlywithj.get(counter);

            for (int i1 = start; i1 < arr.length; i1++) {
                Edge e = arr[i1];

                if ((e.v1 == comm && e.v2 == j) || (e.v2 == comm && e.v1 == j)) {
                    int ki = getK(j);
                    int kj = getK(comm);
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                    }
                    arr[i1].cij = (float) (((float) (arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                }
            }
        }

        System.out.println("fixed :" + count);

    }

    @Override
    public boolean isThereaPath(int s, int d) {
        marked = new boolean[v];
        ArrayList<Integer> q = new ArrayList<>();
        q.add(s);
        while (!q.isEmpty()) {
            int a = q.remove(0);
            if (a == d) {
                return true;
            }
            if (marked[a]) {
                continue;
            }
            marked[a] = true;
            for (int i = firstIndexOf(a); i < sparseMatrix.size() && sparseMatrix.get(i).v1 == a; i++) {
                Edge edge = sparseMatrix.get(i);
                if (!marked[edge.v2]) {
                    q.add(edge.v2);
                }
            }

        }
        return false;

    }


    // driver
    private int firstStart(int a) {
        return firstStart(a, 0, sparseMatrix.size());
    }

    //binary search
    private int firstStart(int a, int l, int r) {
        if (l > r) { // not found
            return -1;
        }
        int mid = (l + r) / 2;
        Edge e = sparseMatrix.get(mid);
        if (e.v1 > a) {
            return firstStart(a, l, mid - 1);
        } else if (e.v1 < a) {
            return firstStart(a, mid + 1, r);
        } else { // e.v1 == a
            while (mid != 0 && sparseMatrix.get(mid - 1).v1 == a) {
                mid--;
            }
            return mid;

        }

    }

    private int firstIndexOf(int a) {
        return starts[a];
        //   return firstStart(a);
    }

    public int getK(int a) {
        int idx = firstIndexOf(a);

        int n = 0;
        for (int i = idx; i < sparseMatrix.size() && sparseMatrix.get(i).v1 == a; i++) {
            n++;
        }
        return n;
    }


}
