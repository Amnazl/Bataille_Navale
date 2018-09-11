import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.*;
import java.applet.*;

public class Demarrage
{

    public static void main(String[] args)
    {
        JFrame fenetre_lancement = new JFrame ("Jeu de Bataille Navale" );
        fenetre_lancement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        int gameHeight = (int) (Math.round(ySize * 0.80));
        int gameWidth = (int) (Math.round(xSize * 0.50));

        fenetre_lancement.setPreferredSize(new Dimension(gameWidth, gameHeight));
        fenetre_lancement.pack();
        fenetre_lancement.setLocationRelativeTo(null);


        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();
        //Définition de sa couleur de fond
        pan.setBackground(Color.ORANGE);
        //On prévient notre JFrame que notre JPanel sera son content pane
        fenetre_lancement.setContentPane(pan);

        fenetre_lancement.setVisible(true);



    }
}
