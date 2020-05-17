package se.aaro;

/**
 * An immutable representation of a pair of two values.
 * Class, abstract class or interface? That is up to you, but make sure that the
 * implementation is really immutable.
 */
public abstract class PairOrig<A, B> {
	
	public abstract A a();
	
	public abstract B b();
	
	@Override public abstract String toString();
	
	@Override public abstract boolean equals(Object o);
	
	public static <A, B> PairOrig<A, B> pair(A a, B b) {
		throw new RuntimeException("not implemented");
	}
}
