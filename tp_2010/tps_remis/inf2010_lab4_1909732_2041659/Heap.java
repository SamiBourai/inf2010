package tp4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {
    private ArrayList<ValueType> data;
    private int it =0;
    private boolean isMin;

    // O(1)
    public Heap() {
    	this(true);
    	
    }

    // O(1): construction sans donnees initiales
    public Heap(boolean isMin) {
        // TODO
    	this.isMin=isMin;
    	data=new ArrayList<ValueType>();
    	data.add(0,null);
    }

    // O(n)
    public Heap(Collection<ValueType> data) {
        this(true, data);
    }

    // O(n): construction avec donnees initiales, allez voir le lien dans la description pour vous aider
    public Heap(boolean isMin, Collection<ValueType> data) {
       // TODO
    	this.isMin=isMin;
    	this.data=new ArrayList<ValueType>();
    	this.data.add(0,null);
    	 ArrayList<ValueType> save=new ArrayList<ValueType>();
    	 save.addAll(data);
    	 
    	for(int i=0;i<save.size();i++) 
    		insert(save.get(i));
    }

    // O(1): on retourne le nombre d'elements dans la liste
    public int size() {
        // TODO
        return this.data.size()-1;
    }

    // O(1): on compare deux elements en fonction du type de monceau
    private boolean compare(ValueType first, ValueType second) {
        // TODO
    	if(isMin) {
    		if(first.compareTo(second)>0) {
    			//on cherche a faire monter les plus petite valeur
    			return true;
    			
    		}else return false;
    	}
    	else  {
    		//on cherche a faire monter les plus grande valeur
    		if(first.compareTo(second)<0) {
    			return true;
    			
    		}else return false;
    	}
    
    }

    // O(1): on donne l'indice du parent
    private int parentIdx(int idx) {
        // TODO
    	if(idx/2!=0)
        return idx/2;
    	else return -1;
    }

    // O(1): on donne l'indice de l'enfant de gauche
    private int leftChildIdx(int idx) {
        // TODO
    	if(idx*2<=size())
    		return idx*2;
        else return -1;

    }

    // O(1): on donne l'indice de l'enfant de droite
    private int rightChildIdx(int idx) {
        // TODO
    	if(idx*2+1<=size())
    		return idx*2+1;
    	else return -1;
    }

    // O(1): on echange deux elements dans le tableau
    private void swap(int firstIdx, int secondIdx) {
        // TODO
    	ValueType save= this.data.get(firstIdx);
    	this.data.set(firstIdx, this.data.get(secondIdx));
    	this.data.set(secondIdx, save);
    }

    // O(log(n)): l'index courant represente le parent, on s'assure que le parent soit le min/max avec ses enfants
    // De facon visuelle, ceci ammene un noeud le plus haut possible dans l'arbre
    // Par exemple: si le min/max est une feuille, on appelera resursivement log(n) fois la methode pour monter le noeud
    private void heapify(int idx) {
        // TODO
    	int child=leftChildIdx(idx);
    	int parent=parentIdx(idx);
    	
    	if(child!=-1) {
    		if(compare(this.data.get(idx),this.data.get(child))) {
    			swap(idx, child);
    			if(parent!=-1)
    				heapify(parent);
    			
    			return;
    		}
    	}
    	
    	child=rightChildIdx(idx);
    	parent=parentIdx(idx);
    	if(child!=-1) {
    		if(compare(this.data.get(idx),this.data.get(child))) {
    			swap(idx, child);	
    			if(parent!=-1)
    				heapify(parent);
    			
    			return;
    		}
    	}
    	
    }

    // O(log(n)): on ajoute un element et on preserve les proprietes du monceau
    public void insert(ValueType element) {
        // TODO
    	this.data.add(element);
    	
    	if(size()!=1)
    		heapify(parentIdx(this.data.size()-1));
    	
    	
  
    }

    // O(n): on s'assure que tous les elements sont bien places dans le tableau,
    // allez voir le lien dans la description pour vous aider
    public void build() {
        // TODO
    	int currentIdx=1;
    	
    	while(this.data.get(rightChildIdx(currentIdx))!=null) {
    		
    	}
    	
    }

    // O(log(n)): on retire le min ou le max et on preserve les proprietes du monceau
    public ValueType pop() {
        // TODO
    	ValueType save=this.data.get(1);
    	
    	this.data.set(1, this.data.get(size()));
    	this.data.remove(size());
    	
    	//percolation vers le bas (solut. iterative)
    	int focusIdx=1;
    	while(focusIdx*2<=size()) {
    		// si la percolation se fait avec le right node
    		if(focusIdx*2+1 <= size() && 
    		   !compare(this.data.get(focusIdx*2+1),this.data.get(focusIdx*2)) && 
    		   compare(this.data.get(focusIdx),this.data.get(focusIdx*2+1))){
    		    swap(focusIdx,focusIdx*2+1);
    			focusIdx=focusIdx*2+1;
    		}
    		// si la percolation se fait avec le left node
    		else if(compare(this.data.get(focusIdx),this.data.get(focusIdx*2))) {
    			swap(focusIdx,focusIdx*2);
    			focusIdx=focusIdx*2;
    		}
    		// si il n'y a plus lieux de faire de percolation
    		else {
    			break;
    		}
    	}
        return save;
    }

    // O(1): on retourne sans retirer le plus petit ou plus grand element.
    public ValueType peek() {
        // TODO
        return this.data.get(1) ;
    }

    // O(nlog(n)): On applique l'algorithme Heap Sort, on s'attend a ce que le monceau soit vide a la fin.
    public List<ValueType> sort() {
        // TODO
    	   ArrayList<ValueType> sortData=new ArrayList<ValueType>();
    	   int sortSize=size();
    	   for(int i=0;i<sortSize;i++) 
    		   sortData.add(pop());
    	   
        return sortData;
    }

    // Creation d'un iterateur seulement utilise dans les tests, permet de faire des boucles "for-each"
    @Override
    public Iterator<ValueType> iterator() {
        // TODO
    	it=0;
        return new HeapIterator();
    }
    
    private class HeapIterator implements Iterator<ValueType> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return it<size();
		}

		@Override
		public ValueType next() {
			// TODO Auto-generated method stub
			
			if(hasNext()) {
				it++;
			return data.get(it);
			}else return null;
		}
    	
    	
    }
}
