package backend;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A class that maps inventory items to their amounts as listed on the database.
 * This is intended to simplify retrieving an entire list of inventory items.
 */
public class InventoryList implements Map<String, Integer> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public Integer get(Object o) {
        return null;
    }

    @Override
    public Integer put(String s, Integer integer) {
        return null;
    }

    @Override
    public Integer remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Integer> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Integer> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Integer>> entrySet() {
        return null;
    }
}
