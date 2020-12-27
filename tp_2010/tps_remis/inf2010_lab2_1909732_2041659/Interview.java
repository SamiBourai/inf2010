package tp2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Interview {

    /** TODO Worst Case : O(n)
     * Is valid if you flip the number upside down.
     */


    public static boolean isValidFlipped(String listOfNumbers) {

        for (int i=0; i<listOfNumbers.length();i++ ) {
            if ( listOfNumbers.charAt(i) == '3' || listOfNumbers.charAt(i) == '4' || listOfNumbers.charAt(i) == '7' ) {
                return false;
            }
            if ( listOfNumbers.charAt(i) == listOfNumbers.charAt(listOfNumbers.length() - i - 1) ) {


                if ( listOfNumbers.charAt(i) == '6' || listOfNumbers.charAt(i) == '9' ) {
                    return false;
                } else if ( (i != listOfNumbers.length() - i - 1) ) {
                    continue;
                }
            }

            if ( listOfNumbers.charAt(i) == '6' && listOfNumbers.charAt(listOfNumbers.length() - i - 1) != '9' ) {
                return false;
            }
            if ( listOfNumbers.charAt(i) == '9' && listOfNumbers.charAt(listOfNumbers.length() - i - 1) != '6' ) {
                return false;
            }

        }
        return true;
    }

    /** TODO Worst Case : O(n)
     * Could be valid if you try to flip the number upside down with one of the combinations.
     */
    public static boolean isValidFlippedWithPermutation(String listOfNumbers) {
        int [] tableau_Repetitions=new int[10];

        for(int i=0;i<listOfNumbers.length();i++){
             if ( listOfNumbers.charAt(i)=='3' ||listOfNumbers.charAt(i)=='4'||listOfNumbers.charAt(i)=='7' ){
                 return false;
             }
            tableau_Repetitions[Integer.parseInt(listOfNumbers.charAt(i)+"")]++;


        }
        boolean estImpaire=false;

        for (int j=0;j<tableau_Repetitions.length-1;j++){
            if ( j!=6 ){
                if ( tableau_Repetitions[j]%2!=0){
                    if ( estImpaire ){
                        return false;
                    }
                    estImpaire =true;
                }
            }else {
                if ( tableau_Repetitions[j]!=tableau_Repetitions[9])
                    return false;
                else if ( (tableau_Repetitions[j]+tableau_Repetitions[9]) % 2 !=0){
                    if ( estImpaire ){
                        return false;
                    }
                    estImpaire=true;
                }
            }
        }
        return true;
    }
}

