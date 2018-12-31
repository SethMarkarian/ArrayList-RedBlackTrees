

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ListIndexSetTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ListIndexSetTest
{
    @Test
    public void testAdd() {
        ListIndexSet<Integer> lis= new ListIndexSet<Integer>();
        boolean theAns = false;
        theAns = lis.add(4);
        theAns = lis.add(5);
        theAns = lis.add(9);
        boolean trueAns = true;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testGetKth() {
        ListIndexSet<Integer> lis= new ListIndexSet<Integer>();
        lis.add(3);
        lis.add(6);
        lis.add(9);
        lis.add(29);
        lis.add(55);
        int theAns = lis.getKth(3);
        int trueAns = 9;
        assertEquals(theAns, trueAns);
    }
    
    @Test
    public void testGetSize() {
        ListIndexSet<Integer> lis= new ListIndexSet<Integer>();
        lis.add(3);
        lis.add(6);
        lis.add(9);
        lis.add(29);
        lis.add(55);
        int theAns = lis.getSize();
        int trueAns = 5;
        assertEquals(theAns, trueAns);
    }
}
