import esiea.Carte2;
import esiea.Croisseur;
import esiea.Flotte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameJeuClient extends JFrame {

    private JFrame jeu_Client = new JFrame();

    private JButton validationChoix;

    private int saisieAbs = 0;
    private int saisieOrd = 0;
    private Carte2 carte  = new Carte2();
    private Flotte flotte = new Flotte();

    public FrameJeuClient(String nom){


        Joueur j = new Joueur(nom, false);

        Croisseur c=new Croisseur(0 , 0, true);
        flotte.ajouterbateau(c,carte);

        jeu_Client.setTitle("Jeu - Client");
        jeu_Client.setSize(650, 500);


        jeu_Client.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jeu_Client.setLocationRelativeTo(null);
        jeu_Client.setLayout(new BorderLayout());


        JPanel panelHaut = new JPanel();
        panelHaut.setBorder(BorderFactory.createTitledBorder("Carte"));
        panelHaut.setPreferredSize(new Dimension(340, 280));

        JPanel panelCentre = new JPanel();
        panelCentre.setBorder(BorderFactory.createTitledBorder("Jeu du joueur"));
        panelCentre.setPreferredSize(new Dimension(340,280));

        JPanel panelBas = new JPanel();
        panelBas.setBorder(BorderFactory.createTitledBorder("Scrore du joueur"));
        panelBas.setPreferredSize(new Dimension(100, 100));


        JLabel titreAbs = new JLabel("Abscisse : ");
        JTextField valAbs = new JTextField();
        valAbs.setPreferredSize(new Dimension(20,24));
        panelCentre.add(titreAbs);
        panelCentre.add(valAbs);

        JLabel titreOrd = new JLabel("Ordonnées : ");
        JTextField valOrd = new JTextField();
        valOrd.setPreferredSize(new Dimension(20,24));
        panelCentre.add(titreOrd);
        panelCentre.add(valOrd);

        ///// POUR LA GRILLE //////

        JPanel contentGrille = new JPanel();
        panelHaut.setLayout(new BorderLayout());

        contentGrille.setLayout(new GridLayout(10, 10));//on définit la taille de la grille de 7 sur 7
        contentGrille.setPreferredSize(new Dimension(20,20));

        creationGrille_2(contentGrille);
        contentGrille.setBounds(150,150,150,150);

        panelHaut.add(contentGrille);

        ///// FIN AJOUT GRILLE //////


        this.validationChoix = new JButton("Valider");
        validationChoix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                saisieAbs = Integer.parseInt(valAbs.getText());
                saisieOrd = Integer.parseInt(valOrd.getText());

                //// A TESTER
                valAbs.setText("");
                valOrd.setText("");
                boolean coup = flotte.coup(saisieAbs,saisieOrd);
                if(coup){
                    j.setScore(5);
                }
                carte.UpdateCarte(flotte,false);
                if(carte.getisDetruit()){
                    j.setScore(5);
                }

                System.out.println("Score du joeur : " +j.getScore());
                panelHaut.removeAll();
                contentGrille.removeAll();
                creationGrille_2(contentGrille);
                panelHaut.add(contentGrille);
                panelHaut.revalidate();
                System.out.println(carte);

                JOptionPane.showMessageDialog(null, saisieAbs + saisieOrd);


            }
        });
        panelCentre.add(validationChoix);






        jeu_Client.add(panelHaut, BorderLayout.NORTH);
        jeu_Client.add(panelCentre, BorderLayout.CENTER);
        jeu_Client.add(panelBas,BorderLayout.SOUTH);


        jeu_Client.setVisible(true);
    }


    public void creationGrille_2(JPanel contentGrille){



        System.out.println("----Client---- " + carte);

        carte.UpdateCarte(flotte, false);

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
                cell[i][j]= new JPanel();
                cell[i][j].setSize(new Dimension(10, 10));
                cell[i][j].add(lettre);
                if ((i + j) % 2 == 0) {
                    cell[i][j].setBackground(Color.gray);
                } else {
                    cell[i][j].setBackground(Color.white);
                }
                contentGrille.add(cell[i][j]);
                contentGrille.revalidate();
            }
        }
    }
}
