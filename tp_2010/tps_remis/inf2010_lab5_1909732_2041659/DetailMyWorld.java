package DetailMyWorld;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class DetailMyWorld {
    /** TODO
     * Returns a list of all continents with their respective countries within `world`
     * @param world 2D table of shape M x N representing the world
     *                  0 : Water Region
     *                  Positive non-null value : Country region for country of that specific value
     * @param isBreadthFirstSearch
     *              If true, algorithm used should be Breadth-First Search
     *              If false, algorithm used should be Depth-First Search
     * @return List of all continents with their respective countries
     *              Continents should be in order from left to right, top to bottom
     *              Each continent should have its countries in order
     */
    public static List<Set<Integer>> findContinents(ArrayList<ArrayList<Integer>> world, boolean isBreadthFirstSearch) {

        List<Set<Integer>> continentsContries = new ArrayList<>();
        if ( world.isEmpty() )    // cas ou y a pas de monde
            return continentsContries;


        if ( !isBreadthFirstSearch ){  //DFS
            for (int i =0; i<world.size();i++){ // O(n*n)
                for (int j=0;j<world.get(i).size();j++){
                    if ( world.get(i).get(j)>0 ){
                        continentsContries.add(doDfs(world, i, j));
                    }
                }
            }
        }else {

            for (int i = 0; i < world.size(); i++) {
                for (int j = 0; j < world.get(i).size(); j++) {
                    if ( world.get(i).get(j) > 0 ) {
                        continentsContries.add(doBfs(world, i, j));
                    }
                }
            }
        }

        return continentsContries;
    }

    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }


    public static Set<Integer> doDfs(ArrayList<ArrayList<Integer>> world, int i, int j){
        Set<Integer> countries= new HashSet<>();
        Queue<ArrayList<Integer>>regionToVisite= new LinkedBlockingQueue<>();
        regionToVisite.offer(new ArrayList<Integer>(Arrays.asList(i,j)));

        while(!regionToVisite.isEmpty()) { //

            ArrayList<Integer> fistElementadded = regionToVisite.poll();
            if ( world.get(fistElementadded.get(0)).get(fistElementadded.get(1)) > 0 ) {
                countries.add(world.get(fistElementadded.get(0)).get(fistElementadded.get(1)));
                world.get(fistElementadded.get(0)).set(fistElementadded.get(1), -1); // already visited
                // Haut  // si on deborde pas
                if ( (fistElementadded.get(0) - 1) >= 0  ) {

                    regionToVisite.offer(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0) - 1, fistElementadded.get(1))));
                }
                // bas  // deborder de la taille de first ..
                if ( (fistElementadded.get(0) + 1) < world.size() ) {

                    regionToVisite.offer(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0) + 1, fistElementadded.get(1))));
                }
                // gauche
                if ( (fistElementadded.get(1) - 1) >= 0  ) {

                    regionToVisite.offer(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0), fistElementadded.get(1) - 1)));
                }
                // droite
                if ( (fistElementadded.get(1) + 1) < world.get(fistElementadded.get(0)).size()  ) {

                    regionToVisite.offer(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0), fistElementadded.get(1) + 1)));
                }

            }


        }
        return countries;

    }





    public static Set<Integer> doBfs(ArrayList<ArrayList<Integer>> world, int i, int j){
        Set<Integer> countries= new HashSet<>();
        Stack<ArrayList<Integer>>regionToVisite= new Stack<>();
        regionToVisite.add(new ArrayList<Integer>(Arrays.asList(i,j)));

        while(!regionToVisite.isEmpty()) {

            ArrayList<Integer> fistElementadded = regionToVisite.pop();
            if ( world.get(fistElementadded.get(0)).get(fistElementadded.get(1)) > 0 ) {
                countries.add(world.get(fistElementadded.get(0)).get(fistElementadded.get(1)));
                world.get(fistElementadded.get(0)).set(fistElementadded.get(1), -1); // already visited
                // Haut  // si on deborde pas
                if ( (fistElementadded.get(0) - 1) >= 0  ) {

                    regionToVisite.add(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0) - 1, fistElementadded.get(1))));
                }
                // bas  // deborder de la taille de first ..
                if ( (fistElementadded.get(0) + 1) < world.size() ) {

                    regionToVisite.add(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0) + 1, fistElementadded.get(1))));
                }
                // gauche
                if ( (fistElementadded.get(1) - 1) >= 0  ) {

                    regionToVisite.add(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0), fistElementadded.get(1) - 1)));
                }
                // droite
                if ( (fistElementadded.get(1) + 1) < world.get(fistElementadded.get(0)).size()  ) {

                    regionToVisite.add(new ArrayList<Integer>(Arrays.asList(fistElementadded.get(0), fistElementadded.get(1) + 1)));
                }

            }


        }
        return countries;

    }
}

