package eight.java;

import java.util.*;

/**
 * Created by kmishra on 5/7/2016.
 */
public class Itinerary {

    private static Comparator<String[]> comparator = (s1, s2) -> {
        if (s1 == null || s2 == null) return 0;
        String s1From = s1[0];
        String s2From = s2[0];
        String s1To = s1[1];
        String s2To = s2[1];

        if (s1From.equals(s2To)) return 1;

        if (s2From.equals(s1To)) return -1;

        if (s1From.equals(s2From)) return s1To.compareTo(s2To);

        return s1From.compareTo(s2From);
    };

    public List<String> findItinerary(String[][] tickets) {
        LinkedList<String> itinerary = new LinkedList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String ticket[] : tickets) {
            PriorityQueue<String> pq;
            if ((pq = map.get(ticket[0])) == null) {
                pq = new PriorityQueue<>();
                map.put(ticket[0], pq);
            }
            map.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK", map, itinerary);
        return itinerary;
    }

    private void dfs(String from, Map<String, PriorityQueue<String>> map, LinkedList<String> itinerary) {
        PriorityQueue<String> pq = map.get(from);

        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll(), map, itinerary);
        }
        itinerary.addFirst(from);
    }

    public static void main(String[] args) {
        String s1[] = {"l","m"};
        String s2[] = {"m","a"};
        String s3[] = {"m","c"};
        String sAr[][] = {
                {"MUC", "LHR"},
                {"JFK", "MUC"},
                {"SFO", "SJC"},
                {"LHR", "SFO"}
        };
        new Itinerary().findItinerary(sAr);
    }
}
