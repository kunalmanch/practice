package eight.java;

import com.comscore.io.IO;
import com.comscore.io.serialize.Serializer;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.openjdk.jol.info.GraphLayout;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * FST : Finite state transducer
 * https://en.wikipedia.org/wiki/Finite_state_transducer
 */
public class FST {

    private static final int SET_SIZE = 127;

    private int[][] fst;
    private BitSet[] nodeSet;

    {
        fst = new int[SET_SIZE][SET_SIZE];
        for (int[] row : fst) Arrays.fill(row, 0);
        nodeSet = new BitSet[SET_SIZE];
        for (int i = 0; i < SET_SIZE; i++) nodeSet[i] = new BitSet();
    }

    public void put(String key, int value) {
        int row, col, currValue = value;
        row = (int)key.charAt(0) - (int)'a';
        for (int i = 1; i < key.length(); i++) {
            col = (int)key.charAt(i) - (int)'a';
            fst[row][col] = currValue - fst[row][col];
            currValue -= fst[row][col];
            row = col;
        }
    }

    public int get(String key) {
        int row, col, value = 0;
        row = (int)key.charAt(0) - (int)'a';
        for (int i = 1; i < key.length(); i++) {
            col = (int)key.charAt(i) - (int)'a';
            value += fst[row][col];
            row = col;
        }
        return value;
    }

    public static void main(String[] args) throws Exception {
        int a[] = {5,4,3,1,2,5};
        boolean[] b = new boolean[a.length];
        subsetSum(0, 5, 0, a, b);
    }

    private static void subsetSum(int index, int w, int t, int[] arr, boolean[] included) {
        if (t == w) {
            int idx = 0;
            for (boolean flg : included) {
                if (flg) System.err.print(arr[idx] + " ");
                idx++;
            }
            System.err.println();
        } else if (index < arr.length && arr[index] <= w) {
            included[index] = true;
            subsetSum(index + 1, w, t + arr[index], arr, included);
            included[index] = false;
            subsetSum(index + 1, w, t, arr, included);
        }
    }
}
