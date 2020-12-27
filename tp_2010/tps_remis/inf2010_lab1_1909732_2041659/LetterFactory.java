package tp1;

import java.math.MathContext;

public final class LetterFactory {
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;

    // TODO
    public static BaseShape create_e() {
        Ellipse mainBody = new Ellipse(maxWidth, maxHeight);
        Ellipse middleToRemove = new Ellipse(maxWidth - stripeThickness,
                maxHeight - stripeThickness);
        Rectangle barreHorizontale= new Rectangle(maxWidth,stripeThickness);
        BaseShape rightToRremove = new Rectangle(halfMaxWidth, halfMaxHeight/2).translate(new Point2d(halfMaxWidth/2, halfStripeThickness));

        mainBody.remove(middleToRemove);
        mainBody.remove(rightToRremove);
        mainBody.add(barreHorizontale);
        return mainBody;


    }

    // TODO
    public static BaseShape create_a() {
        Ellipse mainBody = new Ellipse(maxWidth,maxHeight);
        Ellipse supprimerInt= new Ellipse(1.5 *halfMaxWidth,1.5*halfMaxHeight);
        Rectangle barreVerticale= new Rectangle(halfStripeThickness,maxHeight).translate (new Point2d(halfMaxWidth,0.0));
        mainBody.remove(supprimerInt);
        mainBody.add(barreVerticale);
        return mainBody;
    }

    public static BaseShape create_C() {
        Ellipse mainBody = new Ellipse(maxWidth, maxHeight);
        Ellipse middleToRemove = new Ellipse(maxWidth - stripeThickness, maxHeight - stripeThickness);
        BaseShape rightToRremove = new Rectangle(halfMaxWidth, maxHeight/1.5).translate(new Point2d(halfMaxWidth/2, 0.0));

        mainBody.remove(middleToRemove);
        mainBody.remove(rightToRremove);

        return mainBody;
    }

    // TODO
    public static BaseShape create_l() {

        Rectangle barreHorizontale= new Rectangle(halfStripeThickness,maxHeight);

        return barreHorizontale;
    }

    // TODO
    public static BaseShape create_i() {

        Rectangle barreHorizontale= new Rectangle(halfStripeThickness,maxHeight);
        Rectangle partieSupprimee= new Rectangle(halfStripeThickness,2*stripeThickness).translate (new Point2d(0.0,-(halfMaxHeight-stripeThickness)));


        Ellipse pointI= new Circle(stripeThickness).translate(new Point2d(0.0,-(halfMaxHeight-halfStripeThickness)));

        barreHorizontale.remove(partieSupprimee);
        barreHorizontale.add(pointI);


        return barreHorizontale;
    }

    // TODO
    public static BaseShape create_A() {


        BaseShape A = create_V();
        Rectangle barreA= new Rectangle(1.5*halfStripeThickness,halfStripeThickness);
        A.rotate(Math.toRadians(180.0 )).translate(new Point2d(0.0,stripeThickness));
        A.add(barreA);
        return A;
    }

    // TODO
    public static BaseShape create_V() {
        Rectangle barre_Gauche = new Rectangle(halfStripeThickness,maxHeight).rotate(Math.toRadians(-8)).translate(new Point2d(-halfMaxWidth/2,0.0));
        Rectangle barre_Droite = new Rectangle(halfStripeThickness,maxHeight).rotate(Math.toRadians(8)).translate(new Point2d(halfMaxWidth/2,0.0));
        barre_Droite.add(barre_Gauche);
        return barre_Droite;
    }

    // TODO
    public static BaseShape create_n() {

        Rectangle barre_Verticale = new Rectangle(halfStripeThickness,maxHeight);
        Rectangle barre_Verticale_Droite = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(83.0,0.0));
        BaseShape rond_R= new Circle(maxHeight/2).translate(new Point2d(maxWidth/2 ,-halfMaxWidth));



        BaseShape rond_R_Supprimee= new Circle(maxHeight/2-stripeThickness).translate(new Point2d(maxWidth/2 ,-halfMaxWidth));
        BaseShape carre_A_Supp = new Square(maxHeight/2).translate(new Point2d(maxHeight/2,0.0));
        BaseShape carre_A_Supp_2 = new Square(maxHeight/2);
        BaseShape supp_Rectangle_Droite= new Square(halfMaxHeight).translate(new Point2d(maxWidth,-halfMaxHeight));
        Rectangle barre_Verticale_Supp_Gauche = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(-halfStripeThickness ,0.0));



        rond_R.remove(carre_A_Supp);
        rond_R.remove(carre_A_Supp_2);
        rond_R.remove(rond_R_Supprimee);
        rond_R.remove(barre_Verticale_Supp_Gauche);
        barre_Verticale_Droite.remove(supp_Rectangle_Droite);

        barre_Verticale.add(barre_Verticale_Droite);
        barre_Verticale.add(rond_R);

        return barre_Verticale;
    }

    // TODO
    public static BaseShape create_r() {
        Rectangle barre_Verticale = new Rectangle(halfStripeThickness,maxHeight);
        BaseShape rond_R= new Circle(maxHeight/2).translate(new Point2d(maxWidth/2 ,-halfMaxWidth));
        BaseShape rond_R_Supprimee= new Circle(maxHeight/2-stripeThickness).translate(new Point2d(maxWidth/2 ,-halfMaxWidth));
        BaseShape carre_A_Supp = new Square(maxHeight/2).translate(new Point2d(maxHeight/2,0.0));
        BaseShape carre_A_Supp_2 = new Square(maxHeight/2);


        Rectangle barre_Verticale_Supp_Gauche = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(-halfStripeThickness ,0.0));
        rond_R.remove(carre_A_Supp);
        rond_R.remove(carre_A_Supp_2);
        rond_R.remove(rond_R_Supprimee);
        rond_R.remove(barre_Verticale_Supp_Gauche);

        barre_Verticale.add(rond_R);
        return barre_Verticale;
    }

    // TODO
    public static BaseShape create_B() {
        Rectangle barre_Verticale = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(1.5*halfStripeThickness,0.0));
        BaseShape cercle_Haut_B = new Circle(maxHeight/2).translate(new Point2d(halfMaxWidth/2,halfMaxHeight/2));
        BaseShape cercle_Bas_B = new Circle(maxHeight/2).translate(new Point2d(halfMaxWidth/2,-halfMaxHeight/2));
        BaseShape cercle_Haut_B_Supp = new Circle(maxHeight/2 - stripeThickness).translate(new Point2d(halfMaxWidth/2,halfMaxHeight/2));
        BaseShape cercle_Bas_B_Supp = new Circle(maxHeight/2 - stripeThickness).translate(new Point2d(halfMaxWidth/2,-halfMaxHeight/2));

        Rectangle barre_Verticale_Supprime_A_Droite = new Rectangle(4*stripeThickness,maxHeight).translate(new Point2d(-1.5*stripeThickness,0.0));
        cercle_Bas_B.remove(cercle_Bas_B_Supp);
        cercle_Haut_B.remove(cercle_Haut_B_Supp);


        barre_Verticale.add(cercle_Bas_B);
        barre_Verticale.add(cercle_Haut_B);
        barre_Verticale.remove(barre_Verticale_Supprime_A_Droite);
        return barre_Verticale;

    }
}
