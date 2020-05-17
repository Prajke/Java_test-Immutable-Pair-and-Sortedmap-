package se.aaro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TestPair {

    @Test
    public <A, B> void testEqual() {
        assertEquals(Pair.pair(1, "a"), Pair.pair(1,  "a"));
        assertEquals(
                Pair.pair(Integer.MAX_VALUE, new String("a")),
                Pair.pair(new Integer(Integer.MAX_VALUE), new String("a")));
        assertNotEquals(Pair.pair("a", 1), Pair.pair("a", 2));
        assertNotEquals(Pair.pair("a", 1), Pair.pair("b", 1));
    }
}
