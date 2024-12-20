package day05;

public class Pair<K, V> {
    
    private final K left;
    private final V right;
    
    public static <K, V> Pair<K, V> createPair(K left, V right) {
        return new Pair<K, V>(left, right);
    }
    
    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }
    
    public K getleft() {
        return left;
    }
    
    public V getright() {
        return right;
    }
}
