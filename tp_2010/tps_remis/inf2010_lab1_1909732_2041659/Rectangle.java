package tp1;

import java.util.ArrayList;
import java.util.Collection;

public class Rectangle extends BaseShape {
    // TODO creer un rectangle avec une largeur et une longueur.
    public Rectangle(Double width, Double height) {
        super(new ArrayList<Point2d>());
        double largeur = -width/2;
        double hauteur = -height/2;
        for(double i=largeur; i<(-1*largeur);i++){
            for (double j=hauteur;j<(-1*hauteur);j++){

                super.add(new Point2d(i,j));
            }
        }


    }

    // TODO creer un rectangle avec les dimensions contenu dans un Point.
    public Rectangle(Point2d dimensions) {
        // ...
        this(dimensions.X(), dimensions.Y());


    }

    // TODO initialiser le parent.
    private Rectangle(Collection<Point2d> coords) {
        // ...

        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Rectangle translate(Point2d point) {
        super.translate(point);

        return this;

    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Rectangle rotate(Double angle) {
        super.rotate(angle);

        return this;


    }

    // TODO retourner une nouvelle forme.
    @Override
    public Rectangle clone() {
        return new Rectangle(getCoordsDeepCopy());

    }
}
