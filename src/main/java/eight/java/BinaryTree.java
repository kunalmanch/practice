package eight.java;

import java.util.*;

/**
 * Created by kmishra on 5/2/2016.
 */
public class BinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        int dup;
        int leftCount;
        TreeNode next;

        TreeNode(int val) {
            this.val = val;
            dup++;
        }

        @Override
        public String toString() {return String.valueOf(val);}
    }

    private static class Index {
        int index;

        Index() {
            index = 0;
        }

        Index(int index) {
            this.index = index;
        }
    }

    static int height(TreeNode treeNode) {
        if (treeNode == null) return 0;
        return 1 + Math.max(height(treeNode.left), height(treeNode.right));
    }

    static int diameter(TreeNode treeNode) {
        if (treeNode == null) return 0;

        int lHeight = height(treeNode.left);
        int rHeight = height(treeNode.right);

        int lDiameter = diameter(treeNode.left);
        int rDiameter = diameter(treeNode.right);

        int maxDiameter = Math.max(lDiameter, rDiameter);
        return Math.max(1 + lHeight + rHeight, maxDiameter);
    }

    static void printLevelOrder(TreeNode treeNode) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(treeNode);
        int currLevel = 1, nextLevel = 0;
        int levelAt = 1;
        while (!queue.isEmpty()) {
            TreeNode treeNodeItr = queue.poll();
            System.err.print(treeNodeItr.val + " ");
            if (treeNodeItr.left != null) {
                queue.add(treeNodeItr.left);
                nextLevel++;
            }
            if (treeNodeItr.right != null) {
                queue.add(treeNodeItr.right);
                nextLevel++;
            }
            if (--currLevel == 0) {
                System.err.println("");
                System.err.println("Finished level : " + levelAt++);
                currLevel = nextLevel;
                nextLevel = 0;
            }
        }
    }

    static void printInorderStack(TreeNode treeNode) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode treeNodeItr = treeNode;
        boolean done = false;
        stack.push(treeNodeItr);
        treeNodeItr = treeNodeItr.left;
        while (!done) {
            if (treeNodeItr != null) {
                stack.push(treeNodeItr);
                treeNodeItr = treeNodeItr.left;
            } else if (!stack.isEmpty()) {
                treeNodeItr = stack.pop();
                System.err.print(treeNodeItr.val + " ");
                treeNodeItr = treeNodeItr.right;
            } else done = true;
        }
    }

    static void printKD(TreeNode treeNode, int k) {
        if (treeNode == null) return;
        if (k == 0) {
            System.err.print(treeNode.val + " ");
            return;
        }
        printKD(treeNode.left, k - 1);
        printKD(treeNode.right, k - 1);
    }

    static boolean printAncestors(TreeNode treeNode, int val) {
        if (treeNode == null) return false;
        if (treeNode.val == val) return true;
        if (printAncestors(treeNode.right, val) || printAncestors(treeNode.left, val)) {
            System.err.print(treeNode.val + " ");
            return true;
        }
        return false;
    }

    static boolean ancestors(TreeNode root, int val, LinkedList<TreeNode> list) {
        if (root == null) return false;
        if (root.val == val) return true;
        if (ancestors(root.left, val, list) || ancestors(root.right, val, list)) {
            list.addFirst(root);
            return true;
        }
        return false;
    }

    static TreeNode preOrderToBST(int bst[], Index index, int key, int min, int max) {
        if (index.index >= bst.length) return null;
        TreeNode treeNode = null;
        if (min < key && key < max) {
            treeNode = new TreeNode(key);
            index.index++;
            if (index.index < bst.length) {
                treeNode.left = preOrderToBST(bst, index, bst[index.index], min, key);
                treeNode.right = preOrderToBST(bst, index, bst[index.index], key, max);
            }
        }
        return treeNode;
    }

    static TreeNode postOrderToBST(int bst[], Index index, int key, int min, int max) {
        if (index.index < 0) return null;
        TreeNode treeNode = null;
        if (min < key && key < max) {
            treeNode = new TreeNode(key);
            index.index--;
            if (index.index >= 0) {
                treeNode.right = postOrderToBST(bst, index, bst[index.index], key, max);
                treeNode.left = postOrderToBST(bst, index, bst[index.index], min, key);
            }
        }
        return treeNode;
    }

    static TreeNode postOrderToBST(int bst[], int start, int end) {
        if (end < start) return null;
        TreeNode treeNode = new TreeNode(bst[end]);
        if (start == end) return treeNode;
        int i;
        for (i = start; i < end ; i++) {
            if (bst[i] > treeNode.val) break;
        }
        treeNode.left = postOrderToBST(bst, start, i - 1);
        treeNode.right = postOrderToBST(bst, i, end - 1);
        return treeNode;
    }

    static TreeNode preOrderToBST(int bst[], int start, int end) {
        if (end < start) return null;
        TreeNode treeNode = new TreeNode(bst[start]);
        if (start == end) return treeNode;
        int i;
        for (i = start + 1; i <= end ; i++) {
            if (bst[i] > treeNode.val) break;
        }
        treeNode.left = preOrderToBST(bst, start + 1, i - 1);
        treeNode.right = preOrderToBST(bst, i, end);
        return treeNode;
    }

    static void postOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;
        postOrderTraversal(treeNode.left);
        postOrderTraversal(treeNode.right);
        System.err.print(treeNode.val + ", ");
    }

    static void mirror(TreeNode treeNode) {
        if (treeNode == null) return;

        mirror(treeNode.left);
        mirror(treeNode.right);
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
    }

    static TreeNode createBSTFromInPre(int in[], int pre[], Index index, int lo, int hi) {
        if (lo > hi) return null;
        int i;
        TreeNode treeNode = new TreeNode(pre[index.index++]);
        for (i = lo; i <= hi; i++) {
            if (treeNode.val == in[i]) break;
        }
        treeNode.left = createBSTFromInPre(in, pre, index, lo, i - 1);
        treeNode.right = createBSTFromInPre(in, pre, index, i + 1, hi);
        return treeNode;
    }

    static int countLeafs(TreeNode treeNode) {
        if (treeNode == null) return 0;
        return (treeNode.left == null && treeNode.right == null ? 1 : 0)
                + countLeafs(treeNode.left)
                + countLeafs(treeNode.right);
    }

    static int countNodes(TreeNode treeNode) {
        if (treeNode == null) return 0;
        return 1 + countNodes(treeNode.right) + countNodes(treeNode.left);
    }

    static boolean checkChildrenSum(TreeNode treeNode) {
        if (treeNode == null || (treeNode.left == null && treeNode.right == null)) return true;
        int right = treeNode.right == null ? 0 : treeNode.right.val;
        int left = treeNode.left == null ? 0 : treeNode.left.val;
        return (treeNode.val == right + left)
                && checkChildrenSum(treeNode.right)
                && checkChildrenSum(treeNode.left);
    }

    static void printLeafPath(TreeNode treeNode, int ar[], int idx) {
        if (treeNode == null) return;
        if (treeNode.left == null && treeNode.right == null) {
            ar[idx] = treeNode.val;
            for (int i = 0; i < idx; i++) {
                System.err.print(ar[i] + "-->");
            }
            System.err.println(ar[idx]);
        } else {
            ar[idx] = treeNode.val;
            printLeafPath(treeNode.left, ar, idx + 1);
            printLeafPath(treeNode.right, ar, idx + 1);
        }
    }

    static boolean checkSumTree(TreeNode treeNode) {
        if (treeNode == null || (treeNode.left == null && treeNode.right == null)) return true;
        int lSum = treeSum(treeNode.left);
        int rSum = treeSum(treeNode.right);
        return (treeNode.val == lSum + rSum)
                && checkSumTree(treeNode.left) && checkSumTree(treeNode.right);
    }

    private static int treeSum(TreeNode treeNode) {
        if (treeNode == null) return 0;
        return treeNode.val + treeSum(treeNode.right) + treeSum(treeNode.left);
    }

    static void postFromPreIn(int in[], int pre[], int post[], Index preIdx, Index postIdx, int start, int end) {
        if (start > end || preIdx.index >= post.length) return;
        int idx;
        int key = pre[preIdx.index];
        for (idx = start; idx <= end; idx++) {
            if (in[idx] == key) break;
        }
        preIdx.index++;
        postFromPreIn(in, pre, post, preIdx, postIdx, start, idx - 1); //left
        postFromPreIn(in, pre, post, preIdx, postIdx, idx + 1, end); //right
        post[postIdx.index++] = key; //node
    }

    static void preFromPostIn(int in[], int pre[], int post[], Index preIdx, Index postIdx, int start, int end) {
        if (start > end || postIdx.index < 0) return;
        int idx;
        int key = post[postIdx.index];
        for (idx = start; idx <= end; idx++) {
            if (in[idx] == key) break;
        }
        postIdx.index--;
        preFromPostIn(in, pre, post, preIdx, postIdx, idx + 1, end); //right
        preFromPostIn(in, pre, post, preIdx, postIdx, start, idx - 1); //left
        pre[preIdx.index--] = key; //node
    }

    static void printLevelOrderRec(TreeNode treeNode) {
        int h = height(treeNode);
        boolean flag = true;
        for (int i = h; i >= 1; i--) {
            printLevelOrderRecHelper(treeNode, i, flag);
            System.err.println("");
            flag = !flag;
        }
    }

    private static void printLevelOrderRecHelper(TreeNode treeNode, int level, boolean flag) {
        if (treeNode == null) return;
        if (level == 1) {
            System.err.print(treeNode.val + " ");
        } else {
            if (flag) {
                printLevelOrderRecHelper(treeNode.left, level - 1, flag);
                printLevelOrderRecHelper(treeNode.right, level - 1, flag);
            } else {
                printLevelOrderRecHelper(treeNode.right, level - 1, flag);
                printLevelOrderRecHelper(treeNode.left, level - 1, flag);
            }
        }
    }

    static TreeNode findLCA(TreeNode root, int n1, int n2) {
        if (root == null) return null;
        if (root.val == n1 || root.val == n2) return root;

        TreeNode left = findLCA(root.left, n1, n2);
        TreeNode right = findLCA(root.right, n1, n2);

        if (left != null && right != null) return root;

        return left != null ? left : right;
    }

    public boolean isValidSerialization(String preorder) {
        String nodes[] = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            if (--diff < 0)
                return false;
            if (!node.equals("#"))
                diff += 2;
        }
        return diff == 0;
    }

    public int kthSmallest(TreeNode root, int k) {
        int kthVal = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        boolean done = false;
        while (!done) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else if (!stack.isEmpty()) {
                root = stack.pop();
                kthVal = root.val;
                if (--k == 0) done = true;
                root = root.right;
            } else done = true;
        }
        return kthVal;
    }

    // Encodes a tree to a single string.
    static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        sb.append(root.val + ",");
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.add(root.left);
                sb.append(root.left.val + ",");
            } else sb.append("null,");
            if (root.right != null) {
                queue.add(root.right);
                sb.append(root.right.val +",");
            } else sb.append("null,");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    static TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        TreeNode parent = root;
        parent.left = parent.right = null;
        String nodeStr;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        for (int j = 0; !queue.isEmpty(); j++) {
            parent = queue.poll();

            if ((2 * j) + 1 < nodes.length) {
                nodeStr = nodes[(2 * j) + 1];
                parent.left = nodeStr.endsWith("null") ? null : new TreeNode(Integer.parseInt(nodeStr));
                if (parent.left != null) queue.add(parent.left);
            }

            if ((2 * j) + 2 < nodes.length) {
                nodeStr = nodes[(2 * j) + 2];
                parent.right = nodeStr.endsWith("null") ? null : new TreeNode(Integer.parseInt(nodeStr));
                if (parent.right != null) queue.add(parent.right);
            }
        }
        return root;
    }

    static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<String>();
        binaryTreePaths(root, list, "");
        return list;
    }

    static void binaryTreePaths(TreeNode root, List<String> paths, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            path += root.val;
            paths.add(path);
        }
        binaryTreePaths(root.left, paths, path + root.val + "->");
        binaryTreePaths(root.right, paths, path + root.val + "->");
    }

    public static int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getLeafNumbers(root, list, 0);
        int sum = 0;
        for (int i : list) sum += i;
        return sum;
    }

    private static void getLeafNumbers(TreeNode root, List<Integer> list, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(sum * 10 + root.val);
            return;
        }
        getLeafNumbers(root.left, list, sum * 10 + root.val);
        getLeafNumbers(root.right, list, sum * 10 + root.val);
    }

    public static TreeNode buildTreeFromInPost(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        int[] idx = {postorder.length - 1};
        return buildTreeFromInPost(inorder, postorder, idx, 0, postorder.length - 1);
    }

    private static TreeNode buildTreeFromInPost(int[] in, int[] post, int[] idx, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(post[idx[0]--]);
        int i;
        for (i = l; i <= r; i++) {
            if (in[i] == root.val) break;
        }

        root.right = buildTreeFromInPost(in, post, idx, i + 1, r);
        root.left = buildTreeFromInPost(in, post, idx, l, i - 1);

        return root;
    }

    static int rob(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        int currLevel = 1;
        int nextLevel = 0;
        int oddLevelSum = 0, evenLevelSum = 0;
        int level = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (level % 2 == 0) evenLevelSum += node.val;
            else oddLevelSum += node.val;
            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }

            if (--currLevel == 0) {
                ++level;
                currLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return Math.max(evenLevelSum, oddLevelSum);
    }

    static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) return lists;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int currLevel = 1, nextLevel = 0;
        boolean flag = true;
        LinkedList<Integer> level = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (flag) {
                level.addLast(node.val);
            } else {
                level.addFirst(node.val);
            }
            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }
            if (--currLevel == 0) {
                flag = !flag;
                currLevel = nextLevel;
                nextLevel = 0;
                lists.add(level);
                level = new LinkedList<>();
            }
        }
        return lists;
    }

    static void connect(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int currLevel = 1, nextLevel = 0;
        TreeNode prev = null;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != root) {
                if (prev == null) prev = node;
                else {
                    prev.next = node;
                    prev = node;
                }
            }
            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }
            if (--currLevel == 0) {
                currLevel = nextLevel;
                nextLevel = 0;
                prev = null;
            }
        }
    }

    static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    static void recoverTree(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode first = null, second = null, prev = null, middle = null;
        boolean done = false;
        while (!done) {
            if (root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
            } else if (!stack.isEmpty()) {
                root = stack.pop();
                // System.out.println(root.val);
                if (prev != null && prev.val > root.val) {
                    // System.out.println("in");
                    if (first == null) {
                        first = prev;
                        middle = root;
                    } else if (second == null) second = root;
                }
                prev = root;
                root = root.right;
            } else done = true;
        }
        if (second == null) second = middle;
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        pathSum(root, sum, lists, new ArrayList<>());
        return lists;
    }

    private static void pathSum(TreeNode root, int sum, List<List<Integer>> lists, List<Integer> list) {
        if (root == null) return;
        if (sum - root.val == 0 && root.left == null && root.right == null) {
            list.add(root.val);
            lists.add(list);
            return;
        }
        list.add(root.val);
        pathSum(root.left, sum - root.val, lists, new ArrayList<>(list));
        pathSum(root.right, sum - root.val, lists, new ArrayList<>(list));
    }

    static class Value {
        int min, max;
    }

    static void printVerticalOrder(TreeNode root) {
        if (root == null) return;
        Value value = new Value();
        findMinMax(root, value, 0);
        System.err.println("Printing vertical order");
        for (int i = value.min; i <= value.max; i++) {
            printVerticalOrderHelper(root, i, 0);
            System.err.println("");
        }
    }

    private static void printVerticalOrderHelper(TreeNode root, int lineNum, int hd) {
        if (root == null) return;

        if (hd == lineNum) System.err.print(root.val + " ");

        printVerticalOrderHelper(root.left, lineNum, hd - 1);
        printVerticalOrderHelper(root.right, lineNum, hd + 1);
    }

    private static void findMinMax(TreeNode root, Value value, int hd) {
        if (root == null) return;
        value.min = Math.min(hd, value.min);
        value.max = Math.max(hd, value.max);

        findMinMax(root.left, value, hd - 1);
        findMinMax(root.right, value, hd + 1);
    }

    static class TreeNodeWidth {
        int width;
        TreeNode node;

        TreeNodeWidth(TreeNode node, int width) {
            this.node = node;
            this.width = width;
        }
    }

    private static void printVerticalOrderOpt(TreeNode root) {
        Queue<TreeNodeWidth> queue = new ArrayDeque<>();
        TreeNodeWidth rootWidth = new TreeNodeWidth(root, 0);
        queue.add(rootWidth);
        HashMap<Integer, List<TreeNodeWidth>> voMap = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int width = 0;
        List<TreeNodeWidth> list = new ArrayList<>();
        list.add(rootWidth);
        voMap.put(width, list);
        while (!queue.isEmpty()) {
            TreeNodeWidth nodeWidth = queue.poll();
            TreeNode node = nodeWidth.node;
            width = nodeWidth.width;
            if (node.left != null) {
                if ((list = voMap.get(width - 1)) == null) {
                    list = new ArrayList<>();
                    voMap.put(width - 1, list);
                }
                list.add(new TreeNodeWidth(nodeWidth.node.left, width - 1));
                min = Math.min(min, width - 1);
            }

            if (node.right != null) {
                if ((list = voMap.get(width + 1)) == null) {
                    list = new ArrayList<>();
                    voMap.put(width + 1, list);
                }
                list.add(new TreeNodeWidth(nodeWidth.node.right, width + 1));
                max = Math.max(max, width + 1);
            }
        }

        for (width = min; width <= max; width++) {
            list = voMap.get(width);
            for (TreeNodeWidth nodeWidth : list) System.err.print(nodeWidth.node.val + " ");
            System.err.println("");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.err.println(height(root));
        System.err.println(diameter(root));
        System.err.println("=========");
        printLevelOrder(root);
        System.err.println("=========");
        printInorderStack(root);
        System.err.println("\n=========");
        printKD(root, 2);
        System.err.println("\n=========");
        printAncestors(root, 5);
        System.err.println("\n=========");
        int pre[] = {10,5,1,7,40,50};
        int in[] = {1,5,7,10,40,50};
        int post[] = {1, 7, 5, 50, 40, 10};
        TreeNode treeNode;
//        treeNode = preOrderToBST(bst, new Index(), bst[0], Integer.MIN_VALUE, Integer.MAX_VALUE);
//        printLevelOrder(treeNode);
//        System.err.println("========");
//        postOrderTraversal(treeNode);
        System.err.println("\n========");
        treeNode = postOrderToBST(post, 0, post.length - 1);
        printLevelOrder(treeNode);
        System.err.println("======");
        treeNode = preOrderToBST(pre, 0, pre.length - 1);
        printLevelOrder(treeNode);
        System.err.println("======");
        mirror(treeNode);
        printLevelOrder(treeNode);
        System.err.println("============");
        treeNode = postOrderToBST(post, new Index(post.length - 1), post[post.length - 1], Integer.MIN_VALUE, Integer.MAX_VALUE);
        printLevelOrder(treeNode);
        treeNode = preOrderToBST(pre, new Index(), pre[0], Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.err.println("============");
        printLevelOrder(treeNode);
        treeNode = createBSTFromInPre(in, pre, new Index(), 0, in.length - 1);
        System.err.println("==========");
        printLevelOrder(treeNode);
        System.err.println(countLeafs(treeNode));
        System.err.println(countNodes(treeNode));

        TreeNode treeNode1 = new TreeNode(10);
        treeNode1.left = new TreeNode(8);
        treeNode1.left.right = new TreeNode(5);
        treeNode1.left.left = new TreeNode(3);
        treeNode1.right = new TreeNode(2);
        treeNode1.right.left = new TreeNode(2);
        System.err.println(checkChildrenSum(treeNode1));
        printLeafPath(treeNode1, new int[countNodes(treeNode1)], 0);
        System.err.println(treeSum(treeNode1));
        TreeNode treeNode2 = new TreeNode(26);
        treeNode2.left = new TreeNode(10);
        treeNode2.left.left = new TreeNode(4);
        treeNode2.left.right = new TreeNode(6);
        treeNode2.right = new TreeNode(3);
        treeNode2.right.right = new TreeNode(3);
        System.err.println(checkSumTree(treeNode2));
        int newPost[] = new int[post.length];
        postFromPreIn(in, pre, newPost, new Index(0), new Index(0), 0, post.length - 1);
        for (int i : newPost) System.err.print(i + " ");
        System.err.println("");
        int newPre[] = new int[post.length];
        preFromPostIn(in, newPre, newPost, new Index(post.length - 1), new Index(post.length - 1), 0, post.length - 1);
        for (int i : newPre) System.err.print(i + " ");
        System.err.println("\n=========");
        printLevelOrder(treeNode2);
        System.err.println("========");
        printLevelOrderRec(treeNode2);
        LinkedList<TreeNode> list = new LinkedList<>();
        ancestors(root, 5, list);
        for (TreeNode treeNode11 : list) System.err.print(treeNode11.val + " ");
        TreeNode treeNode11 = findLCA(root, 4, 5);
        System.err.println(treeNode11.val);
        System.err.println(new BinaryTree().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.err.println(new BinaryTree().isValidSerialization("#,#,3,5,#"));
        treeNode = preOrderToBST(pre, new Index(), pre[0], Integer.MIN_VALUE, Integer.MAX_VALUE);
        printLevelOrder(treeNode);
        System.err.println(new BinaryTree().kthSmallest(treeNode, 3));
        System.err.println(serialize(treeNode));
        TreeNode root2 = deserialize(serialize(treeNode2));
        printLevelOrder(root2);
        List<String> list1 = binaryTreePaths(root2);
        for (String s : list1) System.err.println(s);
        TreeNode treeNode3 = new TreeNode(0);
        treeNode3.left = new TreeNode(1);
        System.err.println(sumNumbers(treeNode3));
        int[] inorder = {4, 2, 5, 1, 6, 7, 3, 8};
        int[] postorder = {4, 5, 2, 6, 7, 8, 3,  1};
        TreeNode n1 = buildTreeFromInPost(inorder, postorder);
        printLevelOrder(n1);
        System.err.println(rob(n1));
        List<List<Integer>> lists = zigzagLevelOrder(n1);
        System.err.println("");
        connect(n1);
        printLevelOrder(n1);
        System.err.println(height(n1));
        System.err.println(minDepth(n1));
        TreeNode tn = new TreeNode(2);
        tn.left = new TreeNode(3);
        tn.right = new TreeNode(1);
        recoverTree(tn);
        TreeNode tn1 = new TreeNode(0);
        tn1.left = new TreeNode(1);
        recoverTree(tn1);
        TreeNode tn2 = new TreeNode(1);
        tn2.left = new TreeNode(2);
        tn2.right = new TreeNode(3);
        pathSum(tn2, 3);
        printVerticalOrder(tn2);
        System.err.println("-----------");
        printVerticalOrderOpt(tn2);
    }
}
