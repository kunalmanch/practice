package eight.java;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kmishra on 5/12/2016.
 */
public class LRUCache<K, V> {

    LinkedHashMap<K, V> cache;

    public LRUCache(int maxSize) {
        cache = new LinkedHashMap<K,V>(maxSize + 1, 0.75f, true) {
            @Override
            public boolean removeEldestEntry(Map.Entry<K,V> edlest) {
                return size() > maxSize;
            }
        };
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public int size() {
        return cache.size();
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.get(1);
        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.put(4,4);
        System.err.println(cache.size());
        System.err.println(cache.get(1));
        System.err.println(cache.get(2));
        System.err.println(cache.get(3));
        System.err.println(cache.get(4));
    }
}
