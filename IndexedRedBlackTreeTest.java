
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class IndexedRedBlackTreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IndexedRedBlackTreeTest
{
    @Test
    public void testAdd() {
        IndexedRedBlackTree irb = new IndexedRedBlackTree();
        boolean theAns = irb.add(3);
        boolean trueAns = true;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testFindKth() {
        IndexedRedBlackTree<Integer> irb = new IndexedRedBlackTree<Integer>();
        irb.add(3);
        irb.add(2);
        irb.add(5);
        irb.add(9);
        irb.add(8);
        int theAns = irb.findKth(4);
        int trueAns = 8;
        assertEquals(theAns, trueAns);
    }
    
    @Test
    public void testFindMin() {
        IndexedRedBlackTree<Integer> irb = new IndexedRedBlackTree<Integer>();
        irb.add(3);
        irb.add(2);
        irb.add(5);
        irb.add(9);
        irb.add(8);
        int theAns = irb.findMin();
        int trueAns = 2;
        assertEquals(theAns, trueAns);
    }
    
    @Test
    public void testFindMax() {
        IndexedRedBlackTree<Integer> irb = new IndexedRedBlackTree<Integer>();
        irb.add(3);
        irb.add(2);
        irb.add(5);
        irb.add(9);
        irb.add(8);
        int theAns = irb.findMax();
        int trueAns = 9;
        assertEquals(theAns, trueAns);
    }
    
    @Test
    public void testFindFound() {
        IndexedRedBlackTree<Integer> irb = new IndexedRedBlackTree<Integer>();
        irb.add(3);
        irb.add(2);
        irb.add(5);
        irb.add(9);
        irb.add(8);
        int theAns = irb.find(5);
        int trueAns = 5;
        assertEquals(theAns, trueAns);
    }
    
    @Test
    public void testMakeEmpty() {
        IndexedRedBlackTree<Integer> irb = new IndexedRedBlackTree<Integer>();
        irb.add(3);
        irb.add(2);
        irb.add(5);
        irb.add(9);
        irb.add(8);
        irb.makeEmpty();
        boolean theAns = irb.isEmpty();
        boolean trueAns = true;
        assertEquals(theAns, trueAns);
    }
}
