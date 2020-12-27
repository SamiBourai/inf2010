package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {
    /** TODO
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */


    /*********************************##########################
     * on a pas eu le temps de le finir, mais je crois qu,on est proche de le reussir. on espere avoir quelque points malgres tout
     * joyeux noel
    */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
    //////////////////////////////////////// verifier si output existe ou pas en haut ///////
        Stack<ArrayList<ArrayList<Integer>>> tileToVisit= new Stack<>();
        Stack<ArrayList<ArrayList<Integer>>> tileVisited= new Stack<>();
        ArrayList<Integer> entrance = null;
        ArrayList<Integer> output = null;
        for (int i=0; i<maze.size();i++){
            for (int j=0;j<maze.get(0).size();j++){
                if ( maze.get(i).get(j).equals(Tile.Exit)){
                    if ( entrance==null ){
                        entrance=new ArrayList<>(Arrays.asList(i,j));
                    }else if(output==null){
                        output=new ArrayList<>(Arrays.asList(i,j));
                    }
                }
            }
        }
        if ( entrance==null || output==null )
            return null;


        int pathLength=maze.get(0).size()*maze.size();

        tileToVisit.add(new ArrayList<>(Arrays.asList(null,entrance)));

        while(!tileToVisit.empty()){

            ArrayList<ArrayList<Integer>> tile = tileToVisit.pop();

            if ( tile.get(0)!=null ){
                ArrayList<ArrayList<Integer>> olderTile=tileVisited.peek();
                while (olderTile.get(1).get(0)!=tile.get(0).get(0) || olderTile.get(1).get(1)!=tile.get(0).get(1)){ // pas le meme predecesseur
                    tileVisited.pop();
                    olderTile= tileVisited.peek();
                }
            }

            tileVisited.add(tile);
            if (tile.get(1).get(0)+1<maze.size() ){ // pour ajouter element en dessous
                if ( maze.get(tile.get(1).get(0)+1).get(tile.get(1).get(1))==Tile.Exit &&
                        tile.get(1).get(0)+1!=entrance.get(0)  && tile.get(1).get(1)!=entrance.get(1)){

                    if ( tileVisited.size()<pathLength)
                        pathLength=tileVisited.size();

                }else if(maze.get(tile.get(1).get(0)+1).get(tile.get(1).get(1))==Tile.Floor) {
                    tileToVisit.add(new ArrayList<>(Arrays.asList(tile.get(1),new ArrayList<>(Arrays.asList(tile.get(1).get(0)+1,tile.get(1).get(1))))));
                }
            }

            if (tile.get(1).get(0)-1>=0 ){ // pour ajouter element en dessous
                if ( maze.get(tile.get(1).get(0)-1).get(tile.get(1).get(1))==Tile.Exit &&
                        tile.get(1).get(0)-1!=entrance.get(0)  && tile.get(1).get(1)!=entrance.get(1)){
                    if ( tileVisited.size()<pathLength)
                        pathLength=tileVisited.size();

                }else if(maze.get(tile.get(1).get(0)-1).get(tile.get(1).get(1))==Tile.Floor) {
                    tileToVisit.add(new ArrayList<>(Arrays.asList(tile.get(1),new ArrayList<>(Arrays.asList(tile.get(1).get(0)-1,tile.get(1).get(1))))));
                }
            }

            if (tile.get(1).get(1)-1>=0 ){ // pour ajouter element en dessous
                if ( maze.get(tile.get(1).get(1)-1).get(tile.get(1).get(1))==Tile.Exit &&
                        tile.get(1).get(0)!=entrance.get(0)  && tile.get(1).get(1)-1!=entrance.get(1)){
                    if ( tileVisited.size()<pathLength)
                        pathLength=tileVisited.size();

                }else if(maze.get(tile.get(1).get(1)-1).get(tile.get(1).get(1))==Tile.Floor) {
                    tileToVisit.add(new ArrayList<>(Arrays.asList(tile.get(1),new ArrayList<>(Arrays.asList(tile.get(1).get(1)-1,tile.get(1).get(1))))));
                }
            }

            if (tile.get(1).get(1)+1<maze.get(0).size() ){ // pour ajouter element en dessous
                if ( maze.get(tile.get(1).get(1)+1).get(tile.get(1).get(1))==Tile.Exit &&
                        tile.get(1).get(0)!=entrance.get(0)  && tile.get(1).get(1)+1!=entrance.get(1) ){
                    if ( tileVisited.size()<pathLength)
                        pathLength=tileVisited.size();

                }else if(maze.get(tile.get(1).get(1)+1).get(tile.get(1).get(1))==Tile.Floor) {
                    tileToVisit.add(new ArrayList<>(Arrays.asList(tile.get(1),new ArrayList<>(Arrays.asList(tile.get(1).get(1)+1,tile.get(1).get(1))))));
                }
            }
        }
        return pathLength+1;

    }



    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

