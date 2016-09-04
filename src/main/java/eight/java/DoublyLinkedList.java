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
        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        if (last == null) last = head;
    }

    public void addLast(int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        if (last != null) {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
    }

    public void remove(DoublyListNode node) {
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
    }

    public void removeHead() {
        if (head.next == null) {
            last = null;
        } else {
            head.next.prev = null;
        }
        head = head.next;
    }

    public void removeLast() {
        if (head.next == null) {
            head = null;
        } else {
            last.prev.next = null;
        }
        last = last.prev;
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

    static void printReverse(DoublyListNode node) {
        if (node == null) return;
        printReverse(node.next);
        System.err.println(node.val);
    }

    public static void main(String[] args) {
        DoublyLinkedList ll = new DoublyLinkedList();
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        ll.addLast(6);
        ll.reverse();
        printReverse(ll.head);
    }
}
