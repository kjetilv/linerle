package vkode.linerle.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mapper<K, V> {

    private final Map<K, V> buildMap = new HashMap<K, V>();
    
    public Mapper<K, V> put(K key, V value) {
        buildMap.put(key, value);
        return this;
    }
    
    public Map<K, V> get() {
        return Collections.unmodifiableMap(buildMap);
    }
}
