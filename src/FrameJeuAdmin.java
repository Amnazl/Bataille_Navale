import esiea.Carte2;
import esiea.Flotte;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrameJeuAdmin extends JFrame {


    private JFrame jeu_Admin = new JFrame();
    private Carte2 carte  = new Carte2();
    private Flotte flotte =new Flotte();
    private Client c;
    public static String str;


    public FrameJeuAdmin(Carte2 carte){

      //  Client c = new Client("localhost",18000,"Admin2");
       //new ListenFromServer().start();
       // str = c.getMessage2();
       // System.out.println("dernier test "+ Client.m);
        jeu_Admin.setTitle("Jeu - Admin");
        jeu_Admin.setSize(650, 500);


        jeu_Admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jeu_Admin.setLocationRelativeTo(null);
        jeu_Admin.setLayout(new BorderLayout());



        JPanel panelHaut = new JPanel();
        panelHaut.setBorder(BorderFactory.createTitledBorder("Carte"));
        panelHaut.setPreferredSize(new Dimension(340, 280));

      /*  JPanel panelBas = new JPanel();
        panelBas.setBorder(BorderFactory.createTitledBorder("Scores des joueurs : "));
        panelBas.setPreferredSize(new Dimension(100,200));*/


        ///// POUR LA GRILLE //////

        JPanel contentGrille = new JPanel();
        panelHaut.setLayout(new BorderLayout());

        contentGrille.setLayout(new GridLayout(10, 10));//on définit la taille de la grille de 7 sur 7
        contentGrille.setPreferredSize(new Dimension(20,20));


        creationGrille_2(contentGrille, carte);
        contentGrille.setBounds(150,150,150,150);

        panelHaut.add(contentGrille);

        ///// FIN AJOUT GRILLE //////
       // System.out.println("Nouvelle carte " + c.getMessage2());
       /* System.out.println("Nouvelle carte " + c.getMessage2());
        System.out.println("Nouvelle carte " + c.getMessage2());
        System.out.println("Nouvelle carte " + c.getMessage2());
        System.out.println("Nouvelle carte " + c.getMessage2());*/

       /* while(true){
            if(!(str==null)){
                if(str.contains("Carte")){
                    System.out.println("Nouvelle carte " + str);
                    break;
                }
            }

        }*/





        jeu_Admin.add(panelHaut, BorderLayout.NORTH);
        //jeu_Admin.add(panelBas, BorderLayout.SOUTH);


        jeu_Admin.setVisible(true);

    }


    public void creationGrille_2(JPanel contentGrille, Carte2 carte){

        //carte.UpdateCarte(flotte,true);


        JPanel cell[][]= new JPanel[10][10];
        String plateau = carte.toString();

        int len = plateau.length();
        String[] result = new String[len];

        for(int a = 0; a < len; a++ ){
            result[a] = plateau.substring(a,a+1);
        }


        for(int i=0; i<cell.length; i++){

            for(int j=0; j<cell.length; j++){
                //System.out.println((i+1)*(j+1));
                JLabel lettre = new JLabel(result[(i*cell.length)+j]);
               // System.out.println(result[(i*cell.length)+j]);
                cell[i][j]= new JPanel();
                cell[i][j].setSize(new Dimension(10, 10));
                cell[i][j].add(lettre);
                if ((i + j) % 2 == 0) {
                    cell[i][j].setBackground(Color.gray);
                } else {
                    cell[i][j].setBackground(Color.white);
                }
                if("X".equals(lettre.getText())){
                    cell[i][j].setBackground(Color.green);
                }else if("T".equals(lettre.getText())){
                    cell[i][j].setBackground(Color.orange);

                }else if("C".equals(lettre.getText())) {
                    lettre.setForeground(Color.white);
                    cell[i][j].setBackground(Color.red);
                }
                contentGrille.add(cell[i][j]);
            }
        }
    }
   /* class ListenFromServer extends Thread{
        public void run() {
            while(true) {
               // stem.out.println("XWhil"+c.getMessage2());
                str = c.m;

            }
        }

        public  String getFromServer(){
            return str;
        }
    }*/

}
