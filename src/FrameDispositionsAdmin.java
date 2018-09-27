import esiea.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameDispositionsAdmin extends JFrame{


    private JFrame dispo_Admin = new JFrame();
    private JButton validationBoat;
    private JButton validationCarte;

    public FrameJeuAdmin fJeuAd;


    private String typeBateau = null;
    private int saisieAbs = 0;
    private int saisieOrd = 0;
    private boolean isHorizontale = true;
    private Flotte flotte =new Flotte();
    private Carte2 carte  = new Carte2();


    public FrameDispositionsAdmin(){

        dispo_Admin.setTitle("Initialisation - Admin");
        dispo_Admin.setSize(650, 500);


        dispo_Admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        dispo_Admin.setLocationRelativeTo(null);
        dispo_Admin.setLayout(new BorderLayout());


        JPanel panelHaut = new JPanel();
        panelHaut.setBorder(BorderFactory.createTitledBorder("Carte"));
        panelHaut.setPreferredSize(new Dimension(340, 230));

        JPanel panelCentre = new JPanel();
        panelCentre.setBorder(BorderFactory.createTitledBorder("Placement des bateaux"));
        panelCentre.setPreferredSize(new Dimension(340,230));

        JPanel panelBas = new JPanel();
        panelBas.setBorder(BorderFactory.createTitledBorder("Validation"));
        panelBas.setPreferredSize(new Dimension(100,100));


        ///// POUR LA GRILLE //////

        JPanel contentGrille = new JPanel();
        panelHaut.setLayout(new BorderLayout());

        contentGrille.setLayout(new GridLayout(10, 10));//on définit la taille de la grille de 7 sur 7
        contentGrille.setPreferredSize(new Dimension(20,20));

        creationGrille(contentGrille);
        contentGrille.setBounds(150,150,150,150);

        panelHaut.add(contentGrille);

        ///// FIN AJOUT GRILLE //////



        JComboBox<String> boatLists = new JComboBox<>();

        boatLists.addItem("Porte-avion (4 cases)");
        boatLists.addItem("Croiseur (3 cases)");
        boatLists.addItem("Escorteur (2 cases)");
        boatLists.addItem("Sous-marin (1 cases)");

        panelCentre.add(boatLists);

        JLabel titreAbs = new JLabel("Abscisse : ");
        JTextField valAbs = new JTextField();
        //valAbs.setText("    ");
        panelCentre.add(titreAbs);
        panelCentre.add(valAbs);
        valAbs.setPreferredSize(new Dimension( 20, 24 ));

        JLabel titreOrd = new JLabel("Ordonnées : ");
        JTextField valOrd = new JTextField();
        //valOrd.setText("    ");
        valOrd.setPreferredSize(new Dimension( 20, 24 ));
        panelCentre.add(titreOrd);
        panelCentre.add(valOrd);

        JLabel orientation = new JLabel("Verticale :");
        JCheckBox box_verticale = new JCheckBox();
        panelCentre.add(orientation);
        panelCentre.add(box_verticale);

        this.validationBoat = new JButton("Valider");
        validationBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                typeBateau = boatLists.getSelectedItem().toString();
                saisieAbs = Integer.parseInt(valAbs.getText());
                saisieOrd = Integer.parseInt(valOrd.getText());
                if(box_verticale.isSelected()){
                    isHorizontale = false;
                }
                valAbs.setText("");
                valOrd.setText("");
                panelHaut.removeAll();
                contentGrille.removeAll();
                instantiationBateau(typeBateau,saisieAbs,saisieOrd,isHorizontale,carte);
                carte.UpdateCarte(flotte,true);
                creationGrille_2(contentGrille);
                panelHaut.add(contentGrille);
                panelHaut.revalidate();
                System.out.println(carte);

                JOptionPane.showMessageDialog(null,typeBateau + saisieAbs + saisieOrd + isHorizontale);


            }
        });
        panelCentre.add(validationBoat);

        this.validationCarte = new JButton("Valider la disposition des bateaux");
        validationCarte.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                /*flotte.coup(2,3);
                flotte.coup(3,3);
                flotte.coup(4,3);
                flotte.coup(0,0);
                flotte.coup(1,0);
                flotte.coup(1,0);*/
                carte.UpdateCarte(flotte,true);
                System.out.println(carte);
                //frameJeu_Admin(jeu_Admin);


                fJeuAd = new FrameJeuAdmin(carte);


                //secondFrame.setVisible(false);

            }
        });



        panelBas.add(validationCarte);


        dispo_Admin.add(panelHaut, BorderLayout.NORTH);
        dispo_Admin.add(panelCentre, BorderLayout.CENTER);
        dispo_Admin.add(panelBas, BorderLayout.SOUTH);

        dispo_Admin.setVisible(true);
    }

    public void creationGrille(JPanel contentGrille){


        JPanel cell[][]= new JPanel[10][10];

        for(int i=0; i<cell.length; i++){
            for(int j=0; j<cell.length; j++){
                JLabel lettre = new JLabel("*");
                cell[i][j]= new JPanel();
                cell[i][j].setSize(new Dimension(10, 10));
                cell[i][j].add(lettre);
                if ((i + j) % 2 == 0) {
                    cell[i][j].setBackground(Color.gray);
                } else {
                    cell[i][j].setBackground(Color.white);
                }
                contentGrille.add(cell[i][j]);
            }
        }
    }

    public void creationGrille_2(JPanel contentGrille){


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
            }
        }
    }

    public void instantiationBateau(String typeBateau,int x, int y, boolean h,Carte2 carte){
        switch (typeBateau) {
            case "Croiseur (3 cases)":
                Croisseur c=new Croisseur(x, y, h);
                flotte.ajouterbateau(c,carte);
                break;
            case "Porte-avion (4 cases)":
                PorteAvion p=new PorteAvion(x, y, h);
                flotte.ajouterbateau(p,carte);
                break;
            case "Escorteur (2 cases)":
                Escorteur e=new Escorteur(x, y, h);
                flotte.ajouterbateau(e,carte);
                break;
            case "Sous-marin (1 cases)":
                SousMarin s=new SousMarin(x, y, h);
                flotte.ajouterbateau(s,carte);
                break;

        }
    }

}
