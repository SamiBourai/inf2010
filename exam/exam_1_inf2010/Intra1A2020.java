import java.util.ArrayList;
import java.util.List;

public class Intra1A2020 {
   
   public static void main(String args[]) {
      
      boolean partie2 = false;       // <= Mettre à true pour la partie 2      
      boolean partie3 = false;       // <= Mettre à true pour la partie 3      
      boolean useHashTable = false;  // <= Experimenter avec false et true pour la partie 3 
      boolean partie4 = false;       // <= Mettre à true pour la partie 4
      
      /**
       * Partie 1
       */
      Scrabble jeu = new Scrabble(); 
      
      List<Character> lettres;
      
      lettres = jeu.getLetters( 7 );
      
      System.out.println( "Voici les 7 lettres tirees " + lettres );
      
      /**
       * Partie 2
       */
      
      if( !partie2 )
         return;
      
      ODS dictionnaire = new ODS();
      
      String str = Util.toString(lettres);
      
      if( dictionnaire.contains( str ) ) 
         System.out.println( str + " est une combinaison valide qui vaut " + jeu.evaluate(Util.toList(str)));
      else
         System.out.println( "Malheureusement " + str + " n'est pas une combinaison valide");

      /**
       * Partie 3
       */
      
      if( !partie3 )
         return;
      
      List<Word> validCombinations = new ArrayList<Word>();
      
      Player joueur = new Player(str);
      System.out.println( "Vous pourriez essayer de jouer:" );

      for(String comb : joueur.getCombinations(useHashTable)) {
         
         if( dictionnaire.contains( comb ) ) {  
            Word word = new Word(comb, jeu.evaluate( Util.toList(comb) ));
            
            validCombinations.add(word);
            
            System.out.println( word );
         }
      }
      
      /**
       * Partie 4
       */
      
      if( !partie4 )
         return;
      
      Word[] array = new Word[validCombinations.size()];
      int i = 0;
      for(Word word : validCombinations)
         array[i++] = word;
      
      System.out.println( "Meilleur choix trouve" );
      // Décommentez une des options suivante
      // Sort.insertionSort(array);
      // Sort.mergeSort(array);
      // Sort.quickSort(array);
      System.out.println( array[array.length-1] );
      
   }
}
