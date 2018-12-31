import java.util.*;
/**
 * Holds ArrayList data structure
 * 
 * @author Seth Markarian
 */
public class ListIndexSet<E extends Comparable<E>> implements IndexSet<E>
{
    private ArrayList<E> arr;

    /**
     * Constructor for ArrayList
     */
    public ListIndexSet() {
        arr = new ArrayList<E>();
    }

    /**
     * Adds element to ArrayList
     * 
     * @param e Element to insert
     * @return completion of adding element
     */
    public boolean add(E e) {
        int i = Collections.binarySearch(arr,  e);
        if(i < 0) {
            arr.add(-i - 1, e);
            return true;
        }
        else {
            return false;
        } 
    }

    /**
     * Returns Kth smallest element in ArrayList
     * 
     * @param k Kth
     * @return Kth smallest element
     */
    public E getKth(int k) {
        return arr.get(k - 1);
    }

    /**
     * Returns size of ArrayList
     * 
     * @return Size of ArrayList
     */
    public int getSize() {
        return arr.size();
    }
}
