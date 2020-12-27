package tp2;

import java.util.Iterator;

public class HashMap<KeyType, DataType> implements Iterable<KeyType> {

    private static final int DEFAULT_CAPACITY = 20;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size = 0;
    private int capacity;
    private final float loadFactor; // Compression factor

    public HashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public HashMap(int initialCapacity) {
        this(initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY,
                DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        capacity = initialCapacity;
        this.loadFactor = 1 / loadFactor;
        map = new Node[capacity];
    }

    /**
     * Finds the index attached to a particular key
     * This is the hashing function ("Fonction de dispersement")
     * @param key Value used to access to a particular instance of a DataType within map
     * @return Index value where this key should be placed in attribute map
     */
    private int hash(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    /**
     * @return if map should be rehashed
     */
    private boolean needRehash() {
        return size * loadFactor > capacity;
    }

    /**
     * @return Number of elements currently in the map
     */
    public int size() {
        return size;
    }

    /**
     * @return Current reserved space for the map
     */
    public int capacity(){
        return capacity;
    }

    /**
     * @return if map does not contain any element
     */
    public boolean isEmpty() {
        return size == 0;
    }

    //prise des note de cours de 2010 // algorithme de cpomplexite O(n)

    private static int nextPrime(int n ){
        if( n % 2 == 0 )
            n++;
        for( ; !isPrime( n ); n += 2 )
            ;
        return n;
    }
    private static boolean isPrime(int n ){
        if( n == 2 || n == 3 )
            return true;
        if( n == 1 || n % 2 == 0 )
            return false;
        for(int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;
        return true;
    }



    /** TODO Average Case : O(1)
     * Find the next prime after increasing the capacity by CAPACITY_INCREASE_FACTOR (multiplication)
     */

    private void increaseCapacity() {


        capacity *=CAPACITY_INCREASE_FACTOR;
        capacity=nextPrime(capacity);


    }

    /** TODO Average Case : O(n)
     * Increases capacity by CAPACITY_INCREASE_FACTOR (multiplication) and
     * reassigns all contained values within the new map
     */
    private void rehash() { //
        increaseCapacity();
        Node <KeyType,DataType> [] new_Map = new Node[capacity];

        for (int i = 0; i< map.length;i++){
            Node <KeyType,DataType> node_Index= map[i];


            while(node_Index!=null){
                if ( new_Map[hash(node_Index.key)]==null ){
                    new_Map[hash(node_Index.key)]=node_Index;

                }else{
                    Node <KeyType,DataType> node_Y= new_Map[hash(node_Index.key)];


                    while(node_Y.next!=null){
                        node_Y=node_Y.next;

                    }
                    node_Y.next=node_Index;

                }
                Node <KeyType,DataType> node_X=null;
                if ( node_Index.next!=null ){
                    node_X=new Node<KeyType,DataType>(node_Index.next.key,node_Index.next.data);
                    node_X.next=node_Index.next.next;
                }

                node_Index.next=null; // reset le voisin
                node_Index=node_X;

            }


        }

        map = new_Map;
    }

    /** TODO Average Case : O(1)
     * Finds if map contains a key
     * @param key Key which we want to know if exists within map
     * @return if key is already used in map
     */
    public boolean containsKey(KeyType key) {
        Node <KeyType,DataType> node= map[hash(key)];

        while(node!=null){
            if (node.key.equals(key))
                return true;
            node=node.next;
        }

        return false;

    }

    /** TODO Average Case : O(1)
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {

        Node <KeyType,DataType> node=map[hash(key)];
        while(node!=null){
            if (node.key.equals(key))
                return node.data;
            node=node.next;
        }
        return null;

    }

    /** TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {

        DataType newDataType=null;
        size++;
        if (containsKey(key)){
            Node <KeyType,DataType> node=map[hash(key)];
            while(!node.key.equals(key)){

                node=node.next;

            }
            newDataType= node.data;
            node.data=value;
            size--;

        }else if (!needRehash()){ // boucle
            // regarder si le node courant == key si oui changer la valeur sinon regarder si node.next = null et on met le node a cette endroit
            // sinon si node.next est null node.courant = node.next
            Node <KeyType,DataType> node=map[hash(key)];
            if ( node==null )
                map[hash(key)]=new Node<KeyType,DataType>(key, value);
            else {
                while(node.next!=null){

                    node=node.next;
                }
            node.next=new Node<KeyType,DataType>(key, value);
            }

        }
        else {
            size--;

            rehash();
            newDataType =put(key, value);

        }



        return newDataType;
    }

    /** TODO Average Case : O(1)
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
        DataType newDataType=null;
        Node <KeyType,DataType> node= map[hash(key)];

            if (node!=null && node.key.equals(key) ){
                map[hash(key)]=node.next; // ca prend le prochain directement // autre logique//
                newDataType = node.data;
                size--;

            }
            else {

                while (node!=null && node.next!=null && !node.next.key.equals(key) ) {
                    node = node.next;
                }
                if (node!=null && node.next!=null && node.next.key.equals(key) ){
                    newDataType = node.next.data;
                    node.next = node.next.next; // pour pouvoir return
                    size--;
                }
            }

        return newDataType;
    }

    /** TODO Worst Case : O(1)
     * Removes all nodes contained within the map
     */
    public void clear() {

        map = new Node[capacity];
        size=0;

    }

    static class Node<KeyType, DataType> {
        final KeyType key;
        DataType data;
        Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }
    }

    @Override
    public HashMap<KeyType, DataType>.HashMapIterator iterator() {
        return new HashMapIterator();
    }

    // Iterators are used to iterate over collections like so:
    // for (Key key : map) { doSomethingWith(key); }
    private class HashMapIterator implements Iterator<KeyType> {
        // TODO: Add any relevant data structures to remember where we are in the list.
            int positionCourante=0;
        /** TODO Worst Case : O(n)
         * Determine if there is a new element remaining in the hashmap.
         */
        public boolean hasNext() {
            if ( map[positionCourante].next !=null )
                return true;
            for (int i=positionCourante+1;i< map.length;i++ ){
                if(map[i]!=null){
                    return true;
                }

            }

            return false;
        }

        /** TODO Worst Case : O(n)
         * Return the next new key in the hashmap.
         */
        public KeyType next() {
            if ( map[positionCourante].next !=null )
                return map[positionCourante].next.key;
            for (int i=positionCourante+1;i< map.length;i++ ) {
                if (map[i] != null) {
                    positionCourante=i;
                    return map[i].key;
                }
            }
            return null;
        }
    }
}
