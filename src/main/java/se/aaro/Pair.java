package se.aaro;

/**
 * An immutable representation of a pair of two values.
 * Class, abstract class or interface? That is up to you, but make sure that the
 * implementation is really immutable.
*/

/* 
 * Jag gjorde Pair immutable genom att skapa en final class, med final variabler. Detta betyder att klassen inte kan �rvas och att variablerna 
 * kan ej �ndras. En abstract klass skulle inte passa i detta sammanhang d� en abtract klass anv�nds mer f�r att ut�ka en befintlig klass med en ny 
 * funktionalitet p� de existerande funktionerna. Pair klassen �r inget som �r standard implementerat i java och d�rav anv�nds final klass ist�llet
 * ,d� den skapar en ny klass. 
 */

public final class Pair<A, B> {
	
	private final A first;
	private final B second;
	
	public Pair(final A f, final B s) {
        this.first = f;
        this.second = s;
    }
	
	public A a(){
		return first;
	}
	
	public B b(){
		return second;
	}
	
	@Override public String toString() {
		return "Key: " + first +" Value: " + second;
	}
	
	@Override public boolean equals(Object o) {
		
		if(o==null){
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(o instanceof Pair){
			Pair<?,?> pair = (Pair<?, ?>) o;
			return (first.equals(pair.first) && second.equals(pair.second));
		}
		else {
			return false;
		}	
	}
	
	public static <A, B> Pair<A, B> pair(A a, B b) {
		return new Pair<A,B>(a,b);
	}
}

