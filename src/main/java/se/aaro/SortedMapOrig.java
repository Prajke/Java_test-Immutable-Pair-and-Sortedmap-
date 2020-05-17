package se.aaro;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A simple immutable sorted map.
 * 
 * The operations put, get, map and filter are expected to have O(n)
 * performance, linear with the size of the data structure, both in the worst
 * and in the average case. Dont't spend time on optimizations; instead you
 * should make the code clean and simple.
 * 
 * More information about the expected results of the abstract methods can be
 * deduced from TestSortedMap.java.
 *
 * Feel free to rewrite, refactor this code and add or rewrite as many tests
 * as you wish, but remember to keep it simple, make sure that the result is
 * really immutable and that it does what it is supposed to do.
 */
public abstract class SortedMapOrig<K extends Comparable<K>, V>
        implements Iterable<Pair<K, V>> {

    /**
     * The number of elements in the map.
     */
    public abstract int size();
    
    /**
     * Is the map empty?
     */
    public abstract boolean isEmpty();
    
    /**
     * Returns a sorted map where <code>key</code> is associated with
     * <code>value</code>.
     */
    public abstract SortedMapOrig<K, V> put(K key, V value);
    
    /**
     * Finds the value associated with <code>key</code>, if it exists.
     */
    public abstract Optional<V> get(K a);
    
    /**
     * Returns a map where all values have been translated using the function
     * <code>f</code>.
     */
    public abstract <C> SortedMapOrig<K, C> map(Function<? super V, ? extends C> f);

    /**
     * Returns a map containing only the keys that satisfies
     * the predicate <code>p</code>.
     */
    public abstract SortedMapOrig<K, V> filter(Predicate<? super K> p);
    
    
    @Override public abstract String toString();
    
    @Override public abstract boolean equals(Object o);
    
    /**
     * Implement this constructor or create concrete subtypes of SortedMap.
     * You can change SortedMap to an interface or a concrete class if you
     * like. Defend you design choices in javadoc.
     */
    public static <A extends Comparable<A>, B> SortedMapOrig<A, B> empty() {
        throw new RuntimeException("not implemented");
    }
}
