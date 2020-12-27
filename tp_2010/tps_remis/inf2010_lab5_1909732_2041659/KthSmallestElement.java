package KthSmallestElement;

import java.util.*;

public class KthSmallestElement {
    /**
     * Explication de votre complexité temporelle
     *
     *  la complexité est de O ( k log max(m,n) ), car nous visitons seulement les k case de la matrice et nous ajoutons seulement k
     *  element a parcourir dans notre priorityQueue.
     *
     * Explication de votre complexité spatiale
     * la complexité est  proportionelle a la profondeur de la recherche que nous faisons. Donc dans le pire des au pire des cas
     * elle sera de O ( max(m,n) ) ce qui veut dire qu,on va ajouter tous les elements de la matrice dans notre priorityQueue.
     *
     */
    /** TODO Worst case
     *      Time complexity : O ( k log max(m,n) )
     *      Space complexity : O ( max(m,n) )
     *
     * Returns the `k`th smallest element in `matrix`
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j + 1]
     * @param k Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k)
    {

        if ( matrix==null || k<0 || matrix.length * matrix[0].length<=k)
            return null;
        return doBfs(matrix,k+1);
    }
    public static<T extends Comparable<T>> T doBfs(final T[][] matrix, int k) {
    //(o1, o2) -> o1[0] - o2[0]
        ArrayList<Object> firstElement= new ArrayList<Object>(Arrays.asList(matrix[0][0], 0, 0));
        PriorityQueue<List<Object>> regionToVisite = new PriorityQueue<>(Comparator.comparing(t -> ((T) t.get(0))));
        HashSet<List<Object>> allVisited= new HashSet<>();

        regionToVisite.offer(firstElement);


        allVisited.add(firstElement);


        while (!regionToVisite.isEmpty()) {

            ArrayList<Object> fistElementadded = (ArrayList<Object>) regionToVisite.poll();
            k--;

            if ( k == 0 ) {
                return (T) fistElementadded.get(0);
            }
            // bas  // deborder de la taille de first ..
            if ( ((int) fistElementadded.get(1) + 1) < matrix.length ) {
                ArrayList<Object> elementToAdd= new ArrayList<Object>(Arrays.asList(matrix[(int) fistElementadded.get(1) + 1][(int) fistElementadded.get(2)],
                        (int) fistElementadded.get(1) + 1,
                        fistElementadded.get(2)));

                if ( !allVisited.contains(elementToAdd) ){
                    regionToVisite.add(elementToAdd);
                    allVisited.add(elementToAdd);
                }
            }
            // droite

            if ( ((int) fistElementadded.get(2) + 1) < matrix[(int) fistElementadded.get(1)].length ) {
                ArrayList<Object> elementToAdd = new ArrayList<Object>(Arrays.asList(matrix[(int) fistElementadded.get(1)][(int) fistElementadded.get(2) + 1],
                        (int) fistElementadded.get(1),
                        (int) fistElementadded.get(2) + 1));

                if ( !allVisited.contains(elementToAdd) ){
                    regionToVisite.add(elementToAdd);
                    allVisited.add(elementToAdd);
                }
            }


        }

        return null;
    }
}
