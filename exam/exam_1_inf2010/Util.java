import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Util {
   
   private static final int MON_MATRICULE = ; // <= A COMPLETER
      
   public static Collection<String> getAllCombinations(String str){
      
      List<String> rlist = new ArrayList<String>();
      
      for(String substr : getListWithAllSubstrings(str) ) {
         rlist.addAll( getListWithAllPermutations(substr) );
      }
      
      return rlist;  
   }
   
   /**
    * Reference
    */
   public static Collection<String> getListWithAllSubstrings(String str){
      
      if(str.length() == 0)
         return new ArrayList<String>();
      
      List<String> rlist = new ArrayList<String>();
      rlist.add(str);
      
      for(int i=0; i<str.length(); i++) {
         StringBuilder sb = new StringBuilder(str);
         sb.deleteCharAt(i);
         rlist.addAll( getListWithAllSubstrings(sb.toString()) );
      }
      
      return rlist;  
   } 
   
   /**
    * Reference
    */   
   public static Collection<String> getListWithAllPermutations(String str){
      
      List<String> rlist = new ArrayList<String>();
      
      if(str.length() == 1) {
         rlist.add(str);
         return rlist;
      }
      
      for(int i=0; i<str.length(); i++) {
         
         // caractere a i
         String chari = str.substring(i, i+1);
         
         // chaine sans le caractere a i
         String rest = str.substring(0, i) + str.substring(i+1);
         
         // toutes les permutations qui debute par le caractere a i         
         for(String perm : getListWithAllPermutations(rest.toString()) ) {
            rlist.add( chari + perm );
         }
      }
      
      return rlist;  
   }   
   
   public static Collection<String> getPermutations(String str){
      
      if(str.length() == 0)
         return new QuadraticProbingHashTable<String>();
      
      QuadraticProbingHashTable<String> rtable = new QuadraticProbingHashTable<String>();
      rtable.add(str);
      
      for(int i=0; i<str.length(); i++) {
         StringBuilder sb = new StringBuilder(str);
         sb.deleteCharAt(i);
         rtable.addAll( getPermutations(sb.toString()) );
      }
      
      return rtable;   
   }
   
   public static List<Character> toList(String s){
      return s.chars().mapToObj(e->(char)e).collect(Collectors.toList());  
   }
   
   public static String toString(List<Character> list){
      return list.stream().map(e->e.toString()).reduce((acc, e) -> acc + e).get();  
   }

   private static Integer[] getArray(int n) {
      Integer[] arr = new Integer[n];
      for(int i=0; i<n; i++)
         arr[i] = i;
      return arr;
   }

   static void pAlgorithm(Integer arr[], int n) {

      Random r = new Random(MON_MATRICULE); 

      for (int i = n-1; i > 0; i--) {  
         int j = r.nextInt(i); 
         swap(arr, i, j); 
      } 
   }

   private static void swap(Integer[] arr, int i, int j) {
      Integer temp = arr[i]; 
      arr[i] = arr[j]; 
      arr[j] = temp; 
   }
   
   public static void shuffleLetters(List<Character> list){
      
      int n = list.size();      
      Integer[] position = getArray(n);
      pAlgorithm(position, n);

      for(int i=0; i<list.size(); i++) {

         int j = position[i];

         if( i != j) {
            Character ei = list.get(i);
            Character ej = list.set(j, ei);
            list.set(i, ej);
         }
      }
   }
}
