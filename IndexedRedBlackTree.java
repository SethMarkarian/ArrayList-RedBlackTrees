//package weiss.nonstandard;

// RedBlackTree class
//
// CONSTRUCTION: with no parameters
//
// ******************PUBLIC OPERATIONS*********************
// void add( x )          --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print all items
// ******************ERRORS********************************
// Exceptions are thrown by insert if warranted and remove.

/**
 * Implements a red-black tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class IndexedRedBlackTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public IndexedRedBlackTree( )
    {
        nullNode = new RedBlackNode<AnyType>( null );
        nullNode.left = nullNode.right = nullNode;
        nullNode.size = 0;
        header      = new RedBlackNode<AnyType>( null );
        header.left = header.right = nullNode;
    }

    /**
     * Compare item and t.element, using compareTo, with
     * caveat that if t is header, then item is always larger.
     * This routine is called if is possible that t is header.
     * If it is not possible for t to be header, use compareTo directly.
     */
    private final int compare( AnyType item, RedBlackNode<AnyType> t )
    {
        if( t == header )
            return 1;
        else
            return item.compareTo( t.element );    
    }

    /**
     * Add an element into the tree, same as insert but with size change
     * @param item the item to insert.
     * @return true if item is inserted, false if item is already present.
     */
    public boolean add( AnyType item )
    {
        current = parent = grand = header;
        nullNode.element = item;

        while( compare( item, current ) != 0 )
        {
            great = grand; grand = parent; parent = current;
            current = compare( item, current ) < 0 ?
                current.left : current.right;

            // Check if two red children; fix if so
            if( current.left.color == RED && current.right.color == RED )
                handleReorient( item );
        }

        // Insertion fails if already present
        if( current != nullNode )
            return false;

        current = new RedBlackNode<AnyType>( item, nullNode, nullNode );
        current.setSize();

        // Attach to parent
        if( compare( item, parent ) < 0 )
            parent.left = current;
        else
            parent.right = current;

        // ADD YOUR CODE, to increase the size of current and its ancestors by 1
        sizeHelper(item);
        handleReorient( item );

        return true;
    }

    /**
     * Helps fix sizes
     * 
     * @param x Element
     */
    private void sizeHelper(AnyType x) {
        nullNode.element = x;
        current = header.right;

        for( ; ; )
        {
            if( x.compareTo( current.element ) < 0 ) {
                current.size++; 
                current = current.left;
            }
            else if( x.compareTo( current.element ) > 0 ) {
                current.size++;
                current = current.right;
            }
            else if( current != nullNode ) {
                return;
            }
            else {
                return;
            }
        }
    }

    /**
     * Find the kth smallest item in the tree.
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the tree.
     */
    public AnyType findKth( int k )
    {
        if(k <= 0 || k > header.right.size) {
            return null;
        }
        int sizeLeft;
        if(header.right.left == null) {
            sizeLeft = 0;
        }
        else {
            sizeLeft = header.right.left.size;
        }
        return findKthLeft( k, header.right, sizeLeft + 1).element;
    }

    /**
     * Helps find Kth element in the left child
     * @param k Kth element
     * @param t Left chold
     * @param parent Element of Parent node
     * 
     * @return Node for recursive call
     */
    private RedBlackNode<AnyType> findKthLeft( int k, RedBlackNode<AnyType> t, int value)
    {
        if( t == null)
            throw new IllegalArgumentException( );

        // ADD YOUR CODE, replace the following line to return kth smallest item in the subtree rooted at t    
        if(value > k) {
            return findKthLeft(k, t.left, value - t.left.right.size - 1);
        }
        else if(value < k) {
            return findKthRight(k, t.right, value);
        }
        else {
            return t;
        }
    }

    /**
     * Helps find Kth element in the right child
     * @param k Kth element
     * @param t Right chold
     * @param parent Element of Parent node
     * 
     * @return Node for recursive call
     */
    private RedBlackNode<AnyType> findKthRight(int k, RedBlackNode<AnyType> t, int parent) {
        if( t == null)
            throw new IllegalArgumentException( );

        // ADD YOUR CODE, replace the following line to return kth smallest item in the subtree rooted at t
        int val;
        if(t.left == null) {
            val = 0;
        }
        else {
            val = t.left.size;
        }

        int value = parent + val + 1;
        if(value > k) {
            int i;
            if(t.left.right == null) {
                i = 0;
            }
            else {
                i = t.left.right.size;
            }
            return findKthLeft(k, t.left, value - i - 1);
        }
        else if(value < k) {
            return findKthRight(k, t.right, value);
        }
        else {
            return t;
        }
    }

    /**
     * Remove from the tree.
     * @param x the item to remove.
     * @throws UnsupportedOperationException if called.
     */
    public void remove( AnyType x )
    {
        throw new UnsupportedOperationException( );
    }

    /**
     * Find the smallest item  the tree.
     * @return the smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            return null;

        RedBlackNode<AnyType> itr = header.right;

        while( itr.left != nullNode )
            itr = itr.left;

        return itr.element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            return null;

        RedBlackNode<AnyType> itr = header.right;

        while( itr.right != nullNode )
            itr = itr.right;

        return itr.element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public AnyType find( AnyType x )
    {
        nullNode.element = x;
        current = header.right;

        for( ; ; )
        {
            if( x.compareTo( current.element ) < 0 )
                current = current.left;
            else if( x.compareTo( current.element ) > 0 ) 
                current = current.right;
            else if( current != nullNode )
                return current.element;
            else
                return null;
        }
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        header.right = nullNode;
    }

    /**
     * Print all items.
     */
    public void printTree( )
    {
        printTree( header.right );
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( RedBlackNode<AnyType> t )
    {
        if( t != nullNode )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return header.right == nullNode;
    }

    /**
     * Internal routine that is called during an insertion
     * if a node has two red children. Performs flip and rotations.
     * @param item the item being inserted.
     */
    private void handleReorient( AnyType item )
    {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if( parent.color == RED )   // Have to rotate
        {
            grand.color = RED;
            if( ( compare( item, grand ) < 0 ) !=
            ( compare( item, parent ) < 0 ) )
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate( item, great );
            current.color = BLACK;
        }
        header.right.color = BLACK; // Make root black
    }

    /**
     * Internal routine that performs a single or double rotation.
     * Because the result is attached to the parent, there are four cases.
     * Called by handleReorient.
     * @param item the item in handleReorient.
     * @param parent the parent of the root of the rotated subtree.
     * @return the root of the rotated subtree.
     */
    private RedBlackNode<AnyType> rotate( AnyType item, RedBlackNode<AnyType> parent )
    {
        if( compare( item, parent ) < 0 )
            return parent.left = compare( item, parent.left ) < 0 ?
                rotateWithLeftChild( parent.left )  :  // LL
            rotateWithRightChild( parent.left ) ;  // LR
        else
            return parent.right = compare( item, parent.right ) < 0 ?
                rotateWithLeftChild( parent.right ) :  // RL
            rotateWithRightChild( parent.right );  // RR
    }

    /**
     * Rotate binary tree node with left child.
     */
    private static <AnyType> RedBlackNode<AnyType> rotateWithLeftChild( RedBlackNode<AnyType> k2 )
    {
        RedBlackNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        // ADD YOUR CODE, to set sizes of k2 and k1
        k2.setSize();
        k1.setSize();

        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private static <AnyType> RedBlackNode<AnyType> rotateWithRightChild( RedBlackNode<AnyType> k1 )
    {
        RedBlackNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        // ADD YOUR CODE, to set sizes of k1 and k2
        k1.setSize();
        k2.setSize();

        return k2;
    }

    private static class RedBlackNode<AnyType>
    {
        // Constructors
        RedBlackNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        RedBlackNode( AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            color    = IndexedRedBlackTree.BLACK;
            size     = 1;
        }

        // set the size of the node 
        void setSize() {
            // ADD YOUR CODE, to compute the size of this code based on sizes of its children
            size = left.size + right.size + 1;
        }

        AnyType               element;    // The data in the node
        RedBlackNode<AnyType> left;       // Left child
        RedBlackNode<AnyType> right;      // Right child
        int                   color;      // Color
        int                   size;       // number of items in the subtree rooted at the node, including self
    }

    private static class DuplicateItemException extends RuntimeException
    {
        /**
         * Construct this exception object.
         */
        public DuplicateItemException( )
        {
            super( );
        }

        /**
         * Construct this exception object.
         * @param message the error message.
         */
        public DuplicateItemException( String message )
        {
            super( message );
        }
    }

    public int getSize() {
        return header.right.size;
    }

    private RedBlackNode<AnyType> header;
    private RedBlackNode<AnyType> nullNode;

    private static final int BLACK = 1;    // BLACK must be 1
    private static final int RED   = 0;

    // Used in insert routine and its helpers
    private RedBlackNode<AnyType> current;
    private RedBlackNode<AnyType> parent;
    private RedBlackNode<AnyType> grand;
    private RedBlackNode<AnyType> great;

    // Test program

    public static void main( String [ ] args )
    {
        IndexedRedBlackTree<Integer> t = new IndexedRedBlackTree<Integer>( );
        final int NUMS = 400000;
        final int GAP  =  35461;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.add( i );

        if( t.findMin( ) != 1 || t.findMax( ) != NUMS - 1 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 1; i < NUMS; i++ )
            if( t.find( i ) != i )
                System.out.println( "Find error1!" );
    }

}