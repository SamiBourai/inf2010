package tp1;

import java.util.*;

public final class PointOperator {

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void translate(Double[] vector, Double[] translateVector) {
        for (int debut= 0; debut < vector.length; debut++)
        {
            vector[debut]= vector[debut]+translateVector[debut];
        }

    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void rotate(Double[] vector, Double[][] rotationMatrix) {


        Double[] newVector = new Double[vector.length];
        for(int i= 0; i < vector.length; i++){
            newVector[i]= vector[i];
        }
        for (int i= 0; i < vector.length; i++)
        {
            vector[i]=0.0;
            for (int j= 0; j < rotationMatrix[i].length; j++)
                vector[i] += newVector[j]*rotationMatrix[i][j];
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void divide(Double[] vector, Double divider) {
        // ...
        for (int debut= 0; debut < vector.length; debut++)
        {
            vector[debut]= vector[debut]/divider;
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void multiply(Double[] vector, Double multiplier) {
        // ...
        for (int debut= 0; debut < vector.length; debut++)
        {
            vector[debut]= vector[debut]*multiplier;
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void add(Double[] vector, Double adder) {
        // ...
        for (int debut= 0; debut < vector.length; debut++)
        {
            vector[debut]= vector[debut]+adder;
        }
    }
}
