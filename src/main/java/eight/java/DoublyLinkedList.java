package eight.java;

/**
 * Created by kmishra on 5/16/2016.
 */
public class DoublyLinkedList {

    public static class DoublyListNode {
        int val;
        DoublyListNode prev, next;

        DoublyListNode(int val) {
            this.val = val;
        }
    }

    DoublyListNode head;
    DoublyListNode last;

    public void addFirst(int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        boolean updateLast = head == null;
        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        if (updateLast) last = head;
    }

    public void remove(DoublyListNode node) {
        if (head == null || node == null) return;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next; //head == node;
        }
        if (node.next != null) node.next.prev = node.prev;
    }

    public void addLast(int val) {
        /*DoublyListNode itr = head;
        while (itr.next != null) {
            itr = itr.next;
        }
        itr.next = new DoublyListNode(val);
        itr.next.prev = itr;*/
        DoublyListNode newNode = new DoublyListNode(val);
        if (last != null) {
            last.next = newNode;
            newNode.prev = last;
        } else {
            last = newNode;
        }
        last = last.next;
    }

    public void reverse() { //swap curr's prev and next.
        DoublyListNode curr = head;
        DoublyListNode prev = null;
        while (curr != null) {
            prev = curr.prev;
            DoublyListNode next = curr.next;
            curr.prev = next;
            curr.next = prev;
            curr = next;
        }

        if (prev != null) {
            head = prev.prev;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList ll = new DoublyLinkedList();
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        ll.addLast(6);
        ll.reverse();
        System.err.println("");
    }
}
