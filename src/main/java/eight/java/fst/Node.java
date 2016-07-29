package eight.java.fst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmishra on 4/8/2016.
 */
public class Node {

    char c;
    int value;
    List<Node> children;

    Node() {}

    Node(char c) {
        this.c = c;
        this.value = 0;
        this.children = new ArrayList<>(127);
    }

    Node getChild(char c) {
        for (Node child : children) {
            if (child.c == c) return child;
        }
        return null;
    }
}
