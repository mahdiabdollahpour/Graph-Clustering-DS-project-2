package app.MylinekedList;

public class MylinkedList<T> {

    Node<T> first;
    public int length = 0;

    public void add(T element) {
        Node node = new Node();
        node.data = element;
        if (first == null) {
            first = node;
        } else {
            node.next = first;
            first = node;
        }
        length++;
    }


    public boolean contains(T element) {
        MyIterator it = new MyIterator(this);
        while (it.hasNext()) {
            T now = (T) it.next();
            if (now.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void remove(T element) {
        if (!contains(element)) {
            return;
        }
        if (first.data.equals(element)) {
            first = first.next;
            length--;
            return;
        }
        Node q = first;
        Node p = first.next;
        while (!p.data.equals(element)) {
            q = p;
            p = p.next;
        }
        q.next = p.next;
        length--;
    }

}


