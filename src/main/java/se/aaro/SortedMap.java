package se.aaro;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
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

/*
 * Samma motivering till valet av att använda final klassen som för pair.
 * 
 * För att kunna göra en immutable SortedMap behövs det en underliggande mappstruktur som kan hålla alla par av värden som SortedMap innehåller.
 * Eftersom SortedMap ska vara en sorterad mapp användes TreeMap som den underliggande mappstrukturen. I en TreeMap sorteras paren av värden
 * efter sin naturliga ordning, dvs 1,2,3.. / a,b,c.
 * 
 * För att förhindra att förändringar kan ske på mappen är det viktig att mappen kopieras till annan mapp innan använding så att ingen referens 
 * till original mappen finns. Sedan returneras det en ny SortedMap som innehåller mappkopian. För att kopiera en mapp, flyttas alla värden från 
 * original mappen till en ny mapp. Oftast behövs det en deep copy för att garantera att värderna inte har en referens till original 
 * värdena som kan användas för att manipulera dom. I detta fall räcker den kopierings metod som används i klassen, för att värdena är satta till 
 * final och är oförändringsbara.
 */
public final class SortedMap<K extends Comparable<K>, V>
        implements Iterable<Pair<K, V>> {

	private final TreeMap<K,V> SMap = new TreeMap<K,V>();
	
	//Används för att instansiera den underliggande mappen med en befintlig mapp.
	private SortedMap(Map<K,V> newmap) {
        this.SMap.putAll(newmap);
    }
	private SortedMap() {
    }
	
    /**
     * The number of elements in the map.
     */
    public int size() {
    	TreeMap<K,V> copyMap = new TreeMap<K,V>();
    	copyMap.putAll(SMap);
    	return copyMap.size();
    }
    
    /**
     * Is the map empty?
     */
    public boolean isEmpty() {
    	return (this.size() == 0);
    }
    
    /**
     * Returns a sorted map where <code>key</code> is associated with
     * <code>value</code>.
     */
    public SortedMap<K,V> put(K key, V value){
    	TreeMap<K,V> copyMap = new TreeMap<K,V>();
    	copyMap.putAll(SMap);
    	copyMap.put(key, value);
    	SortedMap<K,V> newmap = new SortedMap<K,V>(copyMap);
    	return newmap;
    }
    	
	/**
     * Finds the value associated with <code>key</code>, if it exists.
     */
 
    public Optional<V> get(K a){
    	Map<K,V> copyMap = new TreeMap<K,V>();
    	copyMap.putAll(SMap);
    	return Optional.ofNullable(copyMap.get(a));
    }
 
    /**
     * Returns a map where all values have been translated using the function
     * <code>f</code>.
     */
    public <C> SortedMap<K, C> map(Function<? super V, ? extends C> f){
    	TreeMap<K,V> copyMap = new TreeMap<K,V>();
    	TreeMap<K,C> newMap = new TreeMap<K,C>();
    	copyMap.putAll(SMap);
    	
    	for (Map.Entry<K,V> entry: copyMap.entrySet()) {
    		newMap.put(entry.getKey(), f.apply(entry.getValue()));
    	}
    	
    	SortedMap<K,C> newSMap = new SortedMap<K,C>(newMap);
    	return newSMap;
    }

    /**
     * Returns a map containing only the keys that satisfies
     * the predicate <code>p</code>.
     */
    
    public SortedMap<K, V> filter(Predicate<? super K> p) {
    	TreeMap<K,V> copyMap = new TreeMap<K,V>();
    	TreeMap<K,V> newMap = new TreeMap<K,V>();
    	copyMap.putAll(SMap);
    	for (Map.Entry<K,V> entry: copyMap.entrySet()) {
    		if(p.test(entry.getKey())) {
    			newMap.put(entry.getKey(), entry.getValue());
    		}	
    	}
    	SortedMap<K,V> newSMap = new SortedMap<K,V>(newMap);
    	return newSMap;
    }
    
    
    @Override public String toString() {
    	Iterator<Pair<K, V>> map = this.iterator();	
    	String stringifiedMap = "";
	     while(map.hasNext()){
	    	 stringifiedMap += "[" + map.next().toString() + "]," ;
	     }
    	return stringifiedMap;
    }
    
    @Override public boolean equals(Object o) {
		if(o==null){
			return false;
		}
		
		if(o instanceof SortedMap){
			SortedMap<K,V> tempSMap = (SortedMap<K, V>) o;
			if(tempSMap.size() == this.size()){
			 Iterator<Pair<K, V>> a = tempSMap.iterator(); 
		     Iterator<Pair<K, V>> b = this.iterator();	
		     while(a.hasNext()){
		    	 if(!a.next().equals(b.next())) {
		    		 return false;
		    	 }
		     }
		     return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
    }
    
    /**
     * Implement this constructor or create concrete subtypes of SortedMap.
     * You can change SortedMap to an interface or a concrete class if you
     * like. Defend you design choices in javadoc.
     */
    public static <A extends Comparable<A>, B> SortedMap<A, B> empty() {
        return new SortedMap<A, B>();
    }
    
	@Override
	public Iterator<Pair<K, V>> iterator() {
		return new SortedMapIterator();
	}
	
	/*
	 *  Detta är en egengjord iterator för iterera över Pairs. Itererar över mappens värden och returnerar ett pair med den nuvarande iteratorns
	 *  värden.
	 */
	class SortedMapIterator implements Iterator<Pair<K, V>>{

		Map.Entry<K, V> entry = null;
		
		@Override
		public boolean hasNext() {
			Iterator<Map.Entry<K, V>> entries = SMap.entrySet().iterator();
			if(entry == null && entries != null)
			{
				return true;
			}
			else if(entry != null) {
				return (SMap.higherEntry(entry.getKey()) != null);
			}
				
			return false;
		}

		@Override
		public Pair<K, V> next() {
			if(entry == null)
			{
				Iterator<Map.Entry<K, V>> entries = SMap.entrySet().iterator();
				entry = entries.next();
				return Pair.pair(entry.getKey(), entry.getValue());
			}
			else {
				entry = SMap.higherEntry(entry.getKey());
				return Pair.pair(entry.getKey(), entry.getValue());
			}
		}
	}

}
