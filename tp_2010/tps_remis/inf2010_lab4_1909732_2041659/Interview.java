package tp4;

import java.util.*;


public final class Interview {
    /**
     * @param circleSize le nombre d'amis que l'on doit inclure dans le cercle
     * @param centers les indices des centres dans "points"
     * @param points la liste des individus
     * @return les indices dans "points" qui creent l'intersection de deux cercles d'amis.
     *
     * On veut que vous indiquiez la complexite sur chaque ligne en fonction de:
     *  - circleSize -> O(a) a est pour le nombre d' "amis" communs
     *  - centers -> O(c) c est pour le nombre de "centres"
     *  - points -> O(n) n est simplement le nombre de d'individus dans la population
     * Vous n'avez pas besoin d'indiquer la complexite des lignes etant en O(1)
     * Lorsque vous avez des boucles, indiquez clairement la complexite de la boucle, par exemple:
     *   for (Integer p1 : points) { // O(n) * O(n^2*log(n)) -> O(n^3*log(n))
     *     for (Integer p2 : points) { // O(n) * O(n*log(n) -> O(n^2*log(n))
     *       Collections.sort(points); // O(n*log(n)
     *     }
     *   }
     * Ceci est un exemple, on voit clairement que la boucle sur "p2" est en O(n) et puisque son interieur est
     * en O(n*log(n), la complexite totale sera la multiplication, O(n^2*log(n)).
     *
     * O(n^2 * log(n)): ceci est la complexite en "worst case" ou 'a' et 'c' tendent vers 'n'
     * Il est peut etre possible d'avoir une meilleure complexite, soyez clair dans vos explications si vous croyez
     * avoir trouve :)
     */
    public static List<Integer> getFriendsToRemove(Integer circleSize, List<Integer> centers, List<Point> points) {
        // TODO
    	Point pts=new Point(0,0);
    	ArrayList<Integer> friends= new ArrayList<>();
    	Heap<Point> save = new Heap<>(true);
    	ArrayList<Point> cercle= new ArrayList<>(circleSize);
    	ArrayList<List<Point>> cercles= new ArrayList<>(centers.size());
    	Heap<Integer> sort;
    	
    		for(int i=0;i<centers.size();i++) {  // O(c) * O(n)* O(a) * log (c) -> O(n)* O(a) * O(c)log (c)
    			cercle.clear();
    			save = new Heap<>();
    			points.get(centers.get(i)).setPlace(true);
    			cercle.add(points.get(centers.get(i)));
    			cercles.add(cercle);
    			
    			for(int j=0;j<points.size();j++) { //O(n)
    				points.get(j).setIdx(j);
    			
    				if(j!=centers.get(i)) {
    					points.get(j).setDist(points.get(centers.get(i)));
    					save.insert(points.get(j));	
    				}
    			}
    		
    			for(int j=0;j<circleSize;j++) { // O(a) * log (c)
    				pts=save.pop();             // log (c)
    				if(pts.placed==true && pts.placed1==false) {
    					if(!pts.equals(points.get(centers.get(i)))) {
    						friends.add(pts.getIdx());
    						pts.setPlace1(true);
    					}
    				}
    			
    					points.get(pts.getIdx()).setPlace(true);
    					cercles.get(i).add(pts);
    				
    			}
    		}
    		
    		int saveSize=friends.size();
    		sort=new Heap<>(true,friends);
    		friends.clear();
    		
    		for(int i=0;i<saveSize;i++) //O(a) * O(n) log (n)
    			friends.add(sort.pop());  //(n) log (n)
    		
    		
    		
        return friends;
    
    }
}
