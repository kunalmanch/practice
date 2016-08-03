package eight.java;

import java.util.List;

/**
 * Created by kmishra on 4/26/2016.
 */
public class Lists {

     public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }

         @Override
         public String toString() {
             return String.valueOf(val);
         }
     }

    /**
     * Merge two sorted lists
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode prev = head;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    prev.next = l1;
                    l1 = l1.next;
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                prev = prev.next;
            } else if (l1 == null) {
                prev.next = l2;
                break;
            } else if (l2 == null) {
                prev.next = l1;
                break;
            }
        }
        return head.next;
    }

    static ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode curr = helper.next, prev = helper;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return helper.next;
    }

    static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        helper.next = head;
        ListNode curr = helper.next, prev = helper;
        while (curr != null) {
            if (curr.val == prev.val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return helper.next;
    }

    static ListNode deleteDuplicatesII(ListNode head) {
        if (head == null) return head;
        ListNode helper = new ListNode(0);
        helper.next = head;

        ListNode prev = helper;
        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                int dup = prev.next.val;
                while (prev.next != null && prev.next.val == dup) {
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next;
            }

        }

        return helper.next;
    }

    static ListNode swapPairs(ListNode head) {
        if (head == null) return null;

        ListNode curr, prev;
        if (head.next == null) return head;
        prev = head;
        curr = head.next;

        while (curr != null) {
            int temp = prev.val;
            prev.val = curr.val;
            curr.val = temp;
            if (curr.next != null) {
                prev = curr.next;
                curr = curr.next.next;
            } else curr = null;
        }
        return head;
    }

    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lengthA = length(headA);
        int lengthB = length(headB);

        ListNode grt, les;
        if (lengthA > lengthB) {
            grt = headA;
            les = headB;
        } else {
            grt = headB;
            les = headA;
        }

        int diff = Math.abs(lengthA - lengthB);

        while (diff-- > 0) {
            grt = grt.next;
        }

        while (grt != null && les != null) {
            if (grt.val == les.val) return les;
            grt = grt.next;
            les = les.next;
        }

        return null;
    }

    private static int length(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            ++length;
        }
        return length;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null) return null;

        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static ListNode recReverse(ListNode prev, ListNode curr) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        return recReverse(prev, next);
    }

    static ListNode reverseBetween(ListNode head, int m, int n) {
        int length = length(head);

        if (length < 2 || m == n) return head;

        if (!(m < n && 1 <= m && n <= length)) return null;

        /**
         * 1. get the (m - 1)th and mth node.
         * 2. get the (n + 1)th node.
         * 3. reverse m...n + 1.
         * 4. set (m - 1).next = reverse head.
         * 5. set m.next = (n + 1)
         */
        int i = 0;
        ListNode itr = head;
        ListNode mNode = null, nNextNode, mPrevNode = null;
        boolean notfound = true;
        while (i < n) {
            if (i == m - 1) {
                mNode = itr;
                notfound = false;
            }
            if (notfound) {
                mPrevNode = itr;
            }
            itr = itr.next;
            i++;
        }
        nNextNode = itr;

        ListNode prev = null, curr = mNode;
        ListNode temp;
        while (curr != nNextNode) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        if (m == 1) {
            head = prev;
        } else {
            mPrevNode.next = prev;
        }
        mNode.next = nNextNode;

        return head;
    }

    static ListNode oddEvenList(ListNode head) {
        if (head == null) return head;

        ListNode odd = new ListNode(0);
        ListNode even = new ListNode(0);
        ListNode itr = head, evenItr = even, oddItr = odd;
        int i = 1;
        while (itr != null) {
            if (i % 2 == 0) {
                evenItr.next = new ListNode(itr.val);
                evenItr = evenItr.next;
            } else {
                oddItr.next = new ListNode(itr.val);
                oddItr = oddItr.next;
            }
            itr = itr.next;
            i++;
        }
        oddItr.next = even.next;
        return odd.next;
    }

    static boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow, fast;
        slow = head;
        fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode revHead = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = revHead;
            revHead = slow;
            slow = temp;
        }
        while (revHead != null) {
            if (head.val != revHead.val) return false;
            head = head.next;
            revHead = revHead.next;
        }
        return true;
    }

    public static BinaryTree.TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    private static BinaryTree.TreeNode sortedListToBST(ListNode left, ListNode right) {
        if (left == null) return null;

        ListNode slow = left, fast = left;
        while (slow != right && fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        BinaryTree.TreeNode treeNode = new BinaryTree.TreeNode(slow.val);

        treeNode.left = left == slow ? null : sortedListToBST(left, slow);
        treeNode.right = slow.next == right ? null : sortedListToBST(slow.next, right);

        return treeNode;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);

        n1.next = n2; n2.next = n3;

        ListNode m1 = new ListNode(2);
        ListNode m2 = new ListNode(5);
        m1.next = m2;

        ListNode m = mergeTwoLists(n1, m1);
        while (m != null) {
            System.err.print(m.val + "->");
            m = m.next;
        }
        System.err.println("");
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
        node.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next = new ListNode(7);

//        ListNode t = removeElements(node, 2);
//        ListNode t = swapPairs(node);
//        while (t != null) {
//            System.err.print(t.val + "-->");
//            t = t.next;
//        }

//        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(3);
//        node1.next = node2;
//        System.err.println("\n" + getIntersectionNode(node1, node2).val);

        ListNode node1 = new ListNode(3);
        node1.next = new ListNode(5);
        ListNode revNode = reverseBetween(node, 2, 4);
        System.err.println(length(revNode));

        ListNode ln = new ListNode(1);
        ln.next = new ListNode(2);
        ln.next.next = new ListNode(3);
        ln.next.next.next = new ListNode(3);
        ln.next.next.next.next = new ListNode(2);
        ln.next.next.next.next.next= new ListNode(1);
//        ListNode oddEvenLn = oddEvenList(ln);
//        System.err.println("");
        System.err.println(isPalindrome(ln));

        ListNode ln1 = new ListNode(1);
        ln1.next = new ListNode(2);
        ln1.next.next = new ListNode(3);
        ln1.next.next.next = new ListNode(4);
        ln1.next.next.next.next = new ListNode(5);
        BinaryTree.printLevelOrder(sortedListToBST(ln1));

        ListNode ln2 = new ListNode(1);
        ln2.next = new ListNode(2);
        ln2.next.next = new ListNode(2);
        ln2.next.next.next = new ListNode(3);
        ln2.next.next.next.next = new ListNode(3);
//        ListNode dup = deleteDuplicatesII(ln2);
        ListNode revLn2 = recReverse(null, ln2);
        System.err.println("");

    }
}
