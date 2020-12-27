import java.util.LinkedList;
import java.util.Queue;

public class TopologicalOrder {
   
   private void findTopologicalOrder() {
      
      int[] inDegree = ((DirectedGraph)G).getInDegree();
      
      Queue<Integer> q = new LinkedList<Integer>();
      
      for(int node = 0; node<G.V(); node++) {   // ajouter tous les vertex avec  degres   0
         if(inDegree[node] == 0) {
            q.add(node);
         }
      }
      
      int counter = 0;

      while (!q.isEmpty()) {
         Integer v = q.poll();
         order[v] = ++counter;

         for(Integer w: G.adj(v)) {
            if(--inDegree[w] == 0)
               q.add(w);
         }
      }
      
      // Completer
      if ( counter != G.V() ) {   // si counter!= nombre de vertex alors il y a un cycle.
         hasOrder =false;            // donc pas d'ordre topologique
         //throw new UnderflowException(); // larguer l'exception

      } ////// sinon
      else {
         hasOrder = true;   //// il y a un ordre
      }
   }

   int[]     order; 
   boolean   hasOrder;
   Graph     G;

   TopologicalOrder(DirectedGraph G){
      order     = new int[G.V()];
      this.G    = G;
      
      findTopologicalOrder();
   }
   
   public int[] getOrder() {
      int[] rOrder = new int[G.V()]; 
      System.arraycopy(order, 0, rOrder, 0, G.V());
      return rOrder;
   }
   
   public boolean admitsOrder() {
      return hasOrder;
   }
}
