/**
 * Holds Tree data structure
 * 
 * @Author Seth Markarian
 */

public class TreeIndexSet <E extends Comparable<E>> implements IndexSet<E>
{
    private IndexedRedBlackTree<E> IRBT;

    /**
     * Constructor for TreeIndexSet
     */
    public TreeIndexSet() {
        IRBT = new IndexedRedBlackTree<E>();
    }
    
    /**
     * Adds element to IndexedRedBlackTree
     * 
     * @param e Element to insert
     * @return completion of adding element
     */
    public boolean add(E e) {
        return IRBT.add(e);
    }

    /**
     * Returns Kth smallest element in IndexedRedBlackTree
     * 
     * @param k Kth
     * @return Kth smallest element
     */
    public E getKth(int k) {
        return IRBT.findKth(k);
    }
    
    /**
     * Returns size of IndexedRedBlackTree
     * 
     * @return Size of IndexedRedBlackTree
     */
    public int getSize() {
        return IRBT.getSize();
    }
}
