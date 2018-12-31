/**
 * Methods that all IndexSet classes must implement
 *
 * @author Seth Markarian
 */
public interface IndexSet<E extends Comparable<? super E>>
{
    /**
     * Adds element
     * 
     * @param e Element to insert
     * @return completion of adding element
     */
    public abstract boolean add(E e);
    
    /**
     * Returns Kth smallest element
     * 
     * @param k Kth
     * @return Kth smallest element
     */
    public abstract E getKth(int k);
    
    /**
     * Returns size
     * 
     * @return Size
     */
    public abstract int getSize();
}
