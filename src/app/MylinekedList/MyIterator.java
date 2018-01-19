package app.MylinekedList;

public class MyIterator {
    Node p;
    // boolean end = false;

    public MyIterator(MylinkedList list) {
        p = list.first;
    }

    public Object next() {
        if (p == null) {
            return null;
        }

        Node q = p;
        p = p.next;
        return q.data;
    }

    public boolean hasNext() {
        if (p == null) {
            return false;
        }
        return true;
    }
}
