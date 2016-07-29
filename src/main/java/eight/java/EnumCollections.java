package eight.java;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Created by kmishra on 3/31/2016.
 */
public class EnumCollections {

    enum Color {
        BLUE, WHITE, GREEN, RED;

        static int i = 9;
    }

    public static void main(String[] args) {
        EnumMap<Color, Integer> colorMap = new EnumMap<>(Color.class);
        colorMap.put(Color.BLUE, 1);
        colorMap.put(Color.GREEN, 2);
        colorMap.put(Color.RED, 3);
        colorMap.put(Color.WHITE, 4);

        System.err.println(colorMap.get(Color.GREEN));
        System.err.println(Color.i);

        EnumSet<Color> colorEnumSet = EnumSet.of(Color.BLUE, Color.GREEN, Color.RED);
        System.err.println(colorEnumSet.size());
        System.err.println(colorEnumSet.contains(Color.WHITE));
        System.err.println(colorEnumSet.contains(Color.RED));
    }
}
