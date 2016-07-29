package eight.java.fst;

import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kmishra on 4/8/2016.
 */
public class FST {

    private static final Node START = new Node();
    private static final Node END = new Node();

    private final Map<Character, Node> nodeMap = new HashMap<>();

    {
        for (int i = 0 ; i <= 127; i++) {
            nodeMap.put((char) i, new Node((char) i));
        }
    }

    public void put(String key, int value) {

    }

    public int get(String key) {
        return 0;
    }

    public static void main(String[] args) {
        FST fst = new FST();
        System.err.println(GraphLayout.parseInstance(fst).toFootprint());
    }
}
