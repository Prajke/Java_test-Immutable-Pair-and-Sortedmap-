package se.aaro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Iterator;

import org.junit.Test;

public class TestSortedMap {

    @Test
    public void testPut() {
        SortedMap<Integer, String> map = SortedMap.<Integer, String>empty()
                .put(1, "a").put(2, "b").put(2, "c");
        Iterator<Pair<Integer, String>> iterator = map.iterator();
        assertEquals(iterator.next(), Pair.pair(1, "a"));
        assertEquals(iterator.next(), Pair.pair(2, "c"));
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testGet() {
        SortedMap<Integer, String> map = SortedMap.<Integer, String>empty()
                .put(2, "b").put(1, "a").put(3, "c");
        assertEquals(map.get(1).get(), "a");
        assertEquals(map.get(2).get(), "b");
        assertEquals(map.get(3).get(), "c");
        assertFalse(map.get(4).isPresent());
    }
    
    @Test
    public void testSorting() {
        SortedMap<Integer, String> map = SortedMap.<Integer, String>empty()
                .put(2, "b").put(1, "a").put(3, "c");
        Iterator<Pair<Integer, String>> iterator = map.iterator();
        assertEquals(iterator.next(), Pair.pair(1, "a"));
        assertEquals(iterator.next(), Pair.pair(2, "b"));
        assertEquals(iterator.next(), Pair.pair(3, "c"));
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testImmutability() {
        SortedMap<Integer, String> a = SortedMap.<Integer, String>empty()
                .put(1, "a");
        SortedMap<Integer, String> b = a.put(2, "b");
        Iterator<Pair<Integer, String>> as = a.iterator();
        Iterator<Pair<Integer, String>> bs = b.iterator();
        assertEquals(as.next(), bs.next());
        assertFalse(as.hasNext());
        assertEquals(bs.next(), Pair.pair(2, "b"));
        assertFalse(bs.hasNext());
        assertEquals(a.size(), 1);
        assertEquals(b.size(), 2);
    }
    
    @Test
    public void testFilter() {
        SortedMap<Integer, String> a = SortedMap.<Integer, String>empty()
                .put(1, "a").put(2, "b");
        SortedMap<Integer, String> b = a.put(3, "c");
        SortedMap<Integer, String> c = b.filter(n -> n <= 2);
        assertEquals(a, c);
        assertNotEquals(b, c);
    }
   
 	@Test
    public void testMap() {
        SortedMap<Integer, String> a = SortedMap.<Integer, String>empty()
                .put(2, "b").put(1, "a").put(3, "c");
        SortedMap<Integer, Integer> b = a.map(s -> s.charAt(0)+1);
        assertEquals(a.size(), b.size());
        for (Pair<Integer, String> p : a) {
            assertEquals(b.get(p.a()).get().intValue(), p.b().charAt(0) + 1);
        }
    }
}
