package se.aaro;

/**
 * An immutable representation of a pair of two values.
 * Class, abstract class or interface? That is up to you, but make sure that the
 * implementation is really immutable.
*/

/* 
 * Jag gjorde Pair immutable genom att skapa en final class, med final variabler. Detta betyder att klassen inte kan ärvas och att variablerna 
 * kan ej ändras. En abstract klass skulle inte passa i detta sammanhang då en abtract klass används mer för att utöka en befintlig klass med en ny 
 * funktionalitet på de existerande funktionerna. Pair klassen är inget som är standard implementerat i java och därav används final klass istället
 * ,då den skapar en ny klass. 
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

