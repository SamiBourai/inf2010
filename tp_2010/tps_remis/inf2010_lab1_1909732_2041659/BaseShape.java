package tp1;

import org.ejml.dense.row.mult.VectorVectorMult_CDRM;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape implements Cloneable {
    private Collection<Point2d> coords;

    // TODO Initialiser la liste de points.
    public BaseShape() {
        // ...

        coords = new ArrayList<>();

    }

    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
        // ...
        this.coords = new ArrayList<>();

        addAll(coords);

    }

    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public BaseShape add(Point2d coord) {
        // ...
        coords.add(coord);
        return this;
    }
    public BaseShape add(BaseShape shape) {
       coords.addAll(shape.getCoords());
        return this;
    }
    public BaseShape addAll(Collection<Point2d> coords) {

        this.coords.addAll(coords);
        return this;
    }
    public BaseShape remove(Point2d coord) {
        coords.remove(coord);
        return this;
    }
    public BaseShape remove(BaseShape shape) {
        coords.removeAll(shape.getCoords());
        return this;
    }
    public BaseShape removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
        return this;
    }

    // TODO retourner les coordonnees de la liste.
    public Collection<Point2d> getCoords() {
        ArrayList<Point2d> zebi= new ArrayList<Point2d>(coords);
        return zebi;
    }

    // TODO retourner une nouvelle liste ou tous les points sont des copy
    public Collection<Point2d> getCoordsDeepCopy() {

        return this.coords.stream()
                .map(c->c.clone())
                .collect(Collectors.toList());
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        coords.forEach(c->c.translate(point));

        return this;
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
        coords.forEach(c->c.rotate(angle));
        return this;
    }

    // TODO donner la plus grande valeur en X
    public Double getMaxX() {
        if (coords.size()==0)
            return 0.0;
        Double valeurInitiale= new ArrayList<Point2d>(coords).get(0).X();

        for (Point2d i : coords){

            if (i.X() > valeurInitiale){
                valeurInitiale=i.X();
            }
        }
        return valeurInitiale;
    }

    // TODO donner la plus grande valeur en Y
    public Double getMaxY() {
        if (coords.size()==0)
            return 0.0;
        Double valeurInitiale= new ArrayList<Point2d>(coords).get(0).Y();

        for (Point2d i : coords){

            if (i.Y() > valeurInitiale){
                valeurInitiale=i.Y();
            }
        }
        return valeurInitiale;
    }

    // TODO donner les plus grandes valeurs en X et Y
    public Point2d getMaxCoord() {
        Point2d d= new Point2d(getMaxX(),getMaxY());
        return d;
    }

    // TODO donner la plus petite valeur en X
    public Double getMinX() {
        if (coords.size()==0)
            return 0.0;
        Double valeurInitiale= new ArrayList<Point2d>(coords).get(0).X();

        for (Point2d i : coords){

            if (i.X() < valeurInitiale){
                valeurInitiale=i.X();
            }
        }
        return valeurInitiale;

    }
    // TODO donner la plus petite valeur en Y
    public Double getMinY() {
        if (coords.size()==0)
            return 0.0;

        Double valeurInitiale= new ArrayList<Point2d>(coords).get(0).Y();

        for (Point2d i : coords){

            if (i.Y() < valeurInitiale){
                valeurInitiale=i.Y();
            }
        }
        return valeurInitiale;
    }

    // TODO donner les plus petites valeurs en X et Y
    public Point2d getMinCoord() {
        Point2d d= new Point2d(getMinX(),getMinY());
        return d;

    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        BaseShape newB = new BaseShape(getCoordsDeepCopy());
        return newB;
    }
}
