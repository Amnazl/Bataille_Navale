//import com.esiea.*;
import esiea.*;

import javax.swing.*;

public class Main {
    private static Demarrage instance;

    public static void main(String[] args) {
        Flotte flotte=new Flotte();
        Carte2 carte = new Carte2();
        Croisseur c=new Croisseur(2, 3, true);
        Escorteur e=new Escorteur(5, 8, false);
        Escorteur e2=new Escorteur(8, 5, false);
        //carte.AjouterBateau(c);

        //Croisseur
        flotte.ajouterbateau(c);
        flotte.coup(2,3);
        flotte.coup(3,3);
        flotte.coup(4,3);
        //Escorteur
        flotte.ajouterbateau(e);
        flotte.coup(5,8);
        flotte.coup(5,7);

        flotte.ajouterbateau(e2);
        //Escorteur 2
        flotte.coup(8,5);
        flotte.coup(8,6);
        carte.UpdateCarte(flotte);
        System.out.println(carte);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception em) {
            em.printStackTrace();
        }
        instance = new Demarrage();
        //System.out.println(flotte);
    }
}
