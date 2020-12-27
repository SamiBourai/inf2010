import java.awt.image.Kernel;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;

    public AvlTree() { }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     */
    public void add(ValueType value) {
        BinaryNode<ValueType> node= root;
        BinaryNode<ValueType> parentNode = null;

        if ( node==null ){
            root=new BinaryNode<>(value,null);
            return;
        }

        while(true){
            if ( node==null ){

                if ( value.compareTo(parentNode.value)>0 ){
                    parentNode.right = new BinaryNode<>(value, parentNode);

                }else {
                    parentNode.left= new BinaryNode<>(value, parentNode);

                }
                balance(parentNode);
                return;
            }
            if (node.value.equals(value) ){
                return; // si l<element existe, on va juste ignorer, car on veut pas de duplication.
            }
            if ( node.value.compareTo(value)>0 ){
                parentNode= node;  //devient le parent
                node=node.left;  // si node.value est < a value alors node = a celui de gauche.
            }else {
                parentNode=node;
                node=node.right;
            }
        }


    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value){
        BinaryNode<ValueType> node= root;
        BinaryNode<ValueType> parentNode = null;

        if ( root.value.equals(value) ){
            root=null;
            return;
        }

        while(true){
            if ( node==null ){ // element exite pas
                return;
            }
            if (node.value.equals(value) ){  // si on le trouve regarder nombre de voisins
                if (node.left==null && node.right==null ){ // pas de voisin;
                    if ( node==parentNode.right   ){
                        parentNode.right =null;
                    }else {
                        parentNode.left =null;
                    }

                }else if(node.left==null || node.right==null) { // cas ou on a un seul voisin
                    BinaryNode<ValueType> node1;
                    if ( node.left == null ) {
                        node1 = node.right;
                    } else {
                        node1 = node.left;
                    }
                    if ( node.equals(parentNode.right) ) { // si eagle a droite
                        parentNode.right = node1;
                        node1.parent = parentNode;
                    } else {
                        parentNode.left = node1;
                        node1.parent = parentNode;
                    }

                }else {
                    BinaryNode<ValueType> nodeMin= node.right; //on veut trouver le min de la branche de droite

                         // vu qu<ils sont triées  on va vers la gauche directement comme si les plus petits qu<on trouve
                    while(nodeMin.left!=null){  // l,element le plus bas = le plus petit
                        nodeMin=nodeMin.left;
                    }
                    if ( node.equals(parentNode.right) ) { // si eagle a droite
                        parentNode.right = nodeMin;
                        nodeMin.parent = parentNode;
                    } else {
                        parentNode.left = nodeMin;
                        nodeMin.parent = parentNode;
                    }
                }
                balance(parentNode);
                return;
            }
            if ( node.value.compareTo(value)>0 ){
                parentNode= node;  //devient le parent
                node=node.left;  // si node.value est < a value alors node = a celui de gauche.
            }else {
                parentNode=node;
                node=node.right;
            }
        }


    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        BinaryNode<ValueType> node= root;

        while (true){
            if ( node==null ) // regarder si null
                return false;
            if (node.value.equals(value))
                return true;

            if ( node.value.compareTo(value)>0 ){ // comparer si est plus petit ou plus grand pour decider d<aller a droite ou a gauche

                node=node.left;
            }else {

                node=node.right;
            }
        }

    }

    /** TODO Worst case : O( 1 )
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        if(root == null)
            return -1;

        Queue <BinaryNode<ValueType>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int height=-1;
        while(true){

            if( nodeQueue.isEmpty() )
                return height;

            int nombreP=nodeQueue.size();

            while(nombreP>0){
                BinaryNode<ValueType> nodeX= nodeQueue.poll();
                if ( nodeX.left!=null )
                    nodeQueue.add(nodeX.left);
                if ( nodeX.right!=null )
                    nodeQueue.add(nodeX.right);

                nombreP--;
            }
            height++;
        }
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        if ( root==null )
            return null;


        BinaryNode<ValueType> nodeMin= root; //on veut trouver le min de la branche de droite

        // vu qu<ils sont triées  on va vers la gauche directement comme si les plus petits qu<on trouve


        while(nodeMin.left!=null){  // l,element le plus bas = le plus petit
            nodeMin=nodeMin.left;
        }
        return nodeMin.value;

    }


    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {

        BinaryNode<ValueType> nodeCourant= root;
        LinkedList<ValueType> list= new LinkedList<>();
        if ( root==null )
            return new LinkedList<>();

        while(true){

            if ( nodeCourant.left!=null && !list.contains(nodeCourant.left.value)){
                nodeCourant=nodeCourant.left;
            }else{
                if ( nodeCourant.right!=null && !list.contains(nodeCourant.right.value)){
                    list.add(nodeCourant.value);
                    nodeCourant=nodeCourant.right;
                }else {
                    if(nodeCourant.equals(root)){
                        return list;
                    }
                    if ( !list.contains(nodeCourant.value) ){
                        list.add(nodeCourant.value);
                    }
                    nodeCourant=nodeCourant.parent;

                }
            }
        }
    }

    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        if(root == null)
            return new LinkedList<>();

        Queue <BinaryNode<ValueType>> nodeQueue = new LinkedList<>();
        List<ValueType> listNode = new LinkedList<>();
        nodeQueue.add(root);
        while(true){

            if( nodeQueue.isEmpty() )
                return listNode;

            int nombreP=nodeQueue.size();

            while(nombreP>0){
                BinaryNode<ValueType> nodeX= nodeQueue.poll();
                if ( nodeX.left!=null )
                    nodeQueue.add(nodeX.left);
                if ( nodeX.right!=null )
                    nodeQueue.add(nodeX.right);

                nombreP--;
                listNode.add(nodeX.value);
            }
        }
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Balances the whole tree
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {
        do{
            AvlTree<ValueType> gauche= new AvlTree<>();
            gauche.root=node.left;
            AvlTree<ValueType> droite= new AvlTree<>();
            droite.root=node.right;
            if ( (gauche.getHeight()-droite.getHeight())>1) {
                AvlTree<ValueType> gaucheGauche = new AvlTree<>();
                gaucheGauche.root = node.left.left;
                AvlTree<ValueType> gaucheDroite = new AvlTree<>();
                gaucheDroite.root = node.left.right;
                if ( gaucheDroite.getHeight()>gaucheGauche.getHeight() ){
                    rotateLeft(node.left);
                }// rotation double
                rotateRight(node);
            }else if((droite.getHeight()- gauche.getHeight())>1){

                AvlTree<ValueType> droiteGauche = new AvlTree<>();
                droiteGauche.root = node.right.left;
                AvlTree<ValueType> droiteDroite = new AvlTree<>();
                droiteDroite.root = node.right.right;
                if ( droiteGauche.getHeight()>droiteDroite.getHeight() ){
                    rotateRight(node.right);
                }// rotation double
                rotateLeft(node);
            }

            node=node.parent;

        }while(node!=null);
    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child

     *  Produces the following tree
     *                       3
             *             /   \
             *           1      5
             *          / \    / \
             *         0   2  4   6
     *
     *         */
    private void rotateLeft(BinaryNode<ValueType> node1){

        BinaryNode<ValueType> node1Parent=node1.parent;
        BinaryNode<ValueType> temp=node1.right;
        node1.right=temp.left;
        temp.left=node1;
        temp.parent=node1.parent;
        temp.left.parent= temp;

        if ( temp.left.right!=null )
            temp.left.right.parent=temp.left;
        if ( node1.equals(root) ){
            root=temp;
        }else if( node1Parent.value.compareTo(node1.value)>0) {
            node1Parent.left=temp;
        }else
        {
            node1Parent.right=temp;
        }
    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> node1Parent=node1.parent;
        BinaryNode<ValueType> temp=node1.left;
        node1.left=temp.right;
        temp.right=node1;
        temp.parent=node1.parent;
        temp.right.parent= temp;



        if ( temp.right.left!=null )
            temp.right.left.parent=temp.right;
        if ( node1.equals(root) ){
            root=temp;
        }else if( node1Parent.value.compareTo(node1.value)>0) {
             node1Parent.left=temp;
        }else
        {
            node1Parent.right=temp;
        }
    }

    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }
}
