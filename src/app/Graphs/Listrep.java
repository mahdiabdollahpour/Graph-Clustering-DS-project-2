package app.Graphs;

import app.Edge;
import app.MylinekedList.MyIterator;

import java.util.ArrayList;


public class Listrep extends Graph {

    public Edge[] calcCij() {
        //  System.out.println("number od edges = " + e);
        Edge[] cijs = new Edge[e];
        int index = 0;

        for (int i = 0; i < list.length; i++) {
            MyIterator it = new MyIterator(list[i]);

            while (it.hasNext()) {
                Integer now = (Integer) it.next();
                if (now < i) {
                    continue;
                }
                int numberoftriangels = 0;

                int ki = list[i].length;
                int kj = list[now].length;
                int makhraj = Math.min(ki - 1, kj - 1);
                if (makhraj == 0) {
                    cijs[index] = new Edge(1000000, i, now, numberoftriangels);
                    index++;
                    continue;

                }

                MyIterator pointer1 = new MyIterator(list[i]);
                MyIterator pointer2 = new MyIterator(list[now]);
                int p1 = 0;
                int p2 = 0;
                if (pointer1.hasNext() && pointer2.hasNext()) {

                    p1 = (int) pointer1.next();
                    p2 = (int) pointer2.next();
                }
                while (pointer1.hasNext() && pointer2.hasNext()) {
                    if (p1 == p2) {
                        p1 = (int) pointer1.next();
                        p2 = (int) pointer2.next();
                        numberoftriangels++;
                    } else if (p1 < p2) {
                        p2 = (int) pointer2.next();
                    } else {
                        p1 = (int) pointer1.next();
                    }

                }

                cijs[index] = new Edge((float) (((numberoftriangels + 1) * 1.0) / makhraj), i, now, numberoftriangels);
                index++;

            }


        }
        return arr = cijs;
    }

    @Override
    public void print() {
        for (int i = 0; i < list.length; i++) {
            MyIterator it = new MyIterator(list[i]);
            System.out.print(i + 1 + " -> ");
            while (it.hasNext()) {
                System.out.print(((int) it.next() + 1) + " ");
            }
            System.out.println();
        }
    }


    @Override
    public void removeEdge(int a, int b) {
        start++;
        list[a].remove(b);
        list[b].remove(a);
        e--;
    }

    @Override
    public void fixCijs(int i, int j) {
        int count = 0;
        ArrayList<Integer> common = new ArrayList<>();
        ArrayList<Integer> onlywithi = new ArrayList<>();
        ArrayList<Integer> onlywithj = new ArrayList<>();
        MyIterator pointer1 = new MyIterator(list[i]);
        MyIterator pointer2 = new MyIterator(list[j]);
        int p1 = 0;
        int p2 = 0;
        if (pointer1.hasNext() && pointer2.hasNext()) {

            p1 = (int) pointer1.next();
            p2 = (int) pointer2.next();
        }
        while (pointer1.hasNext() && pointer2.hasNext()) {
            if (p1 == p2) {
                common.add(p1);
                p1 = (int) pointer1.next();
                p2 = (int) pointer2.next();
            } else if (p1 < p2) {
                onlywithj.add(p2);
                p2 = (int) pointer2.next();
            } else { // p1 > p2
                onlywithi.add(p1);
                p1 = (int) pointer1.next();
            }

        }

        while (pointer1.hasNext()) {
            onlywithi.add((Integer) pointer1.next());
        }
        while (pointer2.hasNext()) {
            onlywithj.add((Integer) pointer2.next());
        }

        for (int counter = 0; counter < common.size(); counter++) {
            int comm = common.get(counter);

            for (int i1 = start; i1 < arr.length; i1++) {
                Edge e = arr[i1];

                if ((e.v1 == comm && e.v2 == i) || (e.v2 == comm && e.v1 == i)) {
                    arr[i1].trangle--;
                    int ki = list[i].length;
                    int kj = list[comm].length;
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                        //  (arr[i1].trangle + 1) * 1.0) / makhraj);
                    }
                    arr[i1].cij = (float) (((arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                } else if ((e.v1 == comm && e.v2 == j) || (e.v2 == comm && e.v1 == j)) {
                    int ki = list[j].length;
                    int kj = list[comm].length;
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                        //  (arr[i1].trangle + 1) * 1.0) / makhraj);
                    }
                    arr[i1].trangle--;
                    arr[i1].cij = (float) (((arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                }
            }
        }
        for (int k = 0; k < onlywithi.size(); k++) {
            int comm = onlywithi.get(k);
            for (int i1 = start; i1 < arr.length; i1++) {
                Edge e = arr[i1];
                if ((e.v1 == comm && e.v2 == i) || (e.v2 == comm && e.v1 == i)) {
                    int ki = list[i].length;
                    int kj = list[comm].length;
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                        //  (arr[i1].trangle + 1) * 1.0) / makhraj);
                    }
                    arr[i1].cij = (float) (((arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                }
            }
        }
        System.out.println("commmon : " + common);
        System.out.println("only with i : " + onlywithi);
        System.out.println("only with j : " + onlywithj);

        for (int k = 0; k < onlywithj.size(); k++) {
            int comm = onlywithj.get(k);
            for (int i1 = start; i1 < arr.length; i1++) {
                Edge nowInArr = arr[i1];
                if ((nowInArr.v1 == comm && nowInArr.v2 == j) || (nowInArr.v2 == comm && nowInArr.v1 == j)) {
                    int kj = list[comm].length;
                    int ki = list[j].length;
                    int makhraj = Math.min(ki - 1, kj - 1);
                    if (makhraj == 0) {
                        arr[i1].cij = (float) 10000.00;
                        //  (arr[i1].trangle + 1) * 1.0) / makhraj);
                    }
                    arr[i1].cij = (float) (((arr[i1].trangle + 1) * 1.0) / makhraj);
                    count++;
                }
            }
        }
        System.out.println("fixed :" + count);
    }

    @Override
    public boolean isThereaPath(int s, int d) {
        ArrayList<Integer> q = new ArrayList<>();
        q.add(s);
        marked = new boolean[v];
        while (!q.isEmpty()) {
            int a = q.remove(0);
            if (marked[a]) {
                continue;
            }
            marked[a] = true;
            if (a == d) {
                return true;
            }
            MyIterator it = new MyIterator(list[a]);
            while (it.hasNext()) {
                int now = (int) it.next();
                q.add(now);
            }

        }
        return false;
    }

}
