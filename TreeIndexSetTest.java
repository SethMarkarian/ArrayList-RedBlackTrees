
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TreeIndexSetTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TreeIndexSetTest
{
    @Test
    public void testAdd() {
        TreeIndexSet<Integer> tis= new TreeIndexSet<Integer>();
        boolean theAns = false;
        theAns = tis.add(4);
        theAns = tis.add(5);
        theAns = tis.add(9);
        boolean trueAns = true;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testGetKth() {
        TreeIndexSet<Integer> tis= new TreeIndexSet<Integer>();
        tis.add(3);
        tis.add(6);
        tis.add(9);
        tis.add(29);
        tis.add(55);
        int theAns = tis.getKth(3);
        int trueAns = 9;
        assertEquals(theAns, trueAns);
    }
    
    @Test
    public void testGetSize() {
        TreeIndexSet<Integer> tis= new TreeIndexSet<Integer>();
        tis.add(3);
        tis.add(6);
        tis.add(9);
        tis.add(29);
        tis.add(55);
        int theAns = tis.getSize();
        int trueAns = 5;
        assertEquals(theAns, trueAns);
    }
}
