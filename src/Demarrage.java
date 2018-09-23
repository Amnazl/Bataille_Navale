import esiea.Bateau;
import esiea.Carte2;
import esiea.Croisseur;
import esiea.Flotte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Demarrage extends JFrame implements ActionListener {

    private static Demarrage instance;

    private JFrame launcherFrame = new JFrame();
    private JFrame secondFrame = new JFrame();
    private JFrame jeu_Admin = new JFrame();
    private JTextField pseudo;
    private JPasswordField mdp;
    private JButton jouer;
    private JButton quitter;
    private JButton validationBoat;
    private JButton validationCarte;

    private JProgressBar pb;
    private ImagePanel panel_ImageTitre= new ImagePanel(new ImageIcon("images/bataille_navale.png").getImage());
    private ImagePanel panel_BattleIcon = new ImagePanel(new ImageIcon("images/battleship.png").getImage());
    private ImagePanel panel_Swords_left = new ImagePanel(new ImageIcon("images/swords.png").getImage());
    private ImagePanel panel_Swords_right = new ImagePanel(new ImageIcon("images/swords.png").getImage());


    private String username = null;
    private String password = null;

    private String typeBateau = null;
    private int saisieAbs = 0;
    private int saisieOrd = 0;
    private boolean isHorizontale = true;
    private Flotte flotte =new Flotte();
    private Carte2 carte  = new Carte2();



    public Demarrage() {
        //Flotte flotte=new Flotte();
        //Carte2 carte = new Carte2();
        launcherFrame.setTitle("Launcher");
        launcherFrame.setSize(850, 500);
        launcherFrame.setUndecorated(true); //Cacher les contours de la fenêtre
        launcherFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        launcherFrame.setLocationRelativeTo(null);
        launcherFrame.setLayout(null);
        launcherFrame.getContentPane().setBackground(new Color(62, 0, 50));


        //Grand titre bataille navale
        panel_ImageTitre.setBounds(55, 15, 850, 100);
        launcherFrame.getContentPane().add(panel_ImageTitre);

        //Icone bataille navale
        panel_BattleIcon.setBounds( 360, 150, 850,100);
        launcherFrame.getContentPane().add(panel_BattleIcon);

        //Epée gauche
        panel_Swords_left.setBounds(95,280, 850,100);
        launcherFrame.getContentPane().add(panel_Swords_left);

        //Epée droite
        panel_Swords_right.setBounds(620,280, 850,100);
        launcherFrame.getContentPane().add(panel_Swords_right);

        pseudo = new JTextField("Pseudo");
        pseudo.setBounds(350, 275, 150, 20);
        launcherFrame.add(pseudo);

        mdp = new JPasswordField("Mot de Passe");
        mdp.setBounds(350, 305, 150, 20);
        launcherFrame.add(mdp);

        jouer = new JButton("Jouer !");
        jouer.addActionListener(this);
        jouer.setBounds(350, 335, 150, 20);
        launcherFrame.add(jouer);

        quitter = new JButton("Quitter");
        quitter.addActionListener(this);
        quitter.setBounds(350,365,150,20);
        launcherFrame.add(quitter);

        pb = new JProgressBar();
        pb.setStringPainted(true);
        pb.setBounds(0, 480, 850, 20);
        launcherFrame.add(pb);

        launcherFrame.setVisible(true);

    }



    public void checkId(String username, String password){

        if (username.equals("Admin") && password.equals("Admin")) {
            JOptionPane.showMessageDialog(null,"Vous allez vous connecter en tant qu'admin.");

            for (int i = 0; i <= 100; i++) {
                final int currentValue = i;
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            pb.setValue(currentValue);
                        }
                    });
                    Thread.sleep(10);
                } catch (InterruptedException exc) {
                    JOptionPane.showMessageDialog(null, "Erreur lors du chargement du compte");
                }
            }

            frameDispositionBateaux_Admin(secondFrame);
            launcherFrame.setVisible(false);

        }
        else{
            JOptionPane.showMessageDialog(null,"Vous allez vous connecter en tant que joueur");

            secondFrame.setTitle("Jeu " + username);
            secondFrame.setSize(650, 500);


            secondFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            secondFrame.setLocationRelativeTo(null);
            //secondFrame.setLayout(null);


            secondFrame.setVisible(true);

            launcherFrame.setVisible(false);
        }


    }

    public void frameDispositionBateaux_Admin(JFrame secondFrame){



        secondFrame.setTitle("Initialisation - Admin");
        secondFrame.setSize(650, 500);


        secondFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setLayout(new BorderLayout());


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

        boatLists.addItem("Porte-avion (5 cases)");
        boatLists.addItem("Croiseur (3 cases)");
        boatLists.addItem("Contre-torpilleur (3 cases)");
        boatLists.addItem("Sous-marin (3 cases)");
        boatLists.addItem("Torpilleur (2 cases)");


        panelCentre.add(boatLists);

        JLabel titreAbs = new JLabel("Abscisse : ");
        JTextField valAbs = new JTextField();
        //valAbs.setText("    ");
        panelCentre.add(titreAbs);
        panelCentre.add(valAbs);

        JLabel titreOrd = new JLabel("Ordonnées : ");
        JTextField valOrd = new JTextField();
        //valOrd.setText("    ");
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

                instantiationBateau(typeBateau,saisieAbs,saisieOrd,isHorizontale);





                JOptionPane.showMessageDialog(null,typeBateau + saisieAbs + saisieOrd + isHorizontale);


            }
        });
        panelCentre.add(validationBoat);

        this.validationCarte = new JButton("Valider la disposition des bateaux");
        validationCarte.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                flotte.coup(2,3);
                flotte.coup(3,3);
                flotte.coup(4,3);
                carte.UpdateCarte(flotte);
                System.out.println(carte);
                frameJeu_Admin(jeu_Admin);



                //secondFrame.setVisible(false);

            }
        });



        panelBas.add(validationCarte);


        secondFrame.add(panelHaut, BorderLayout.NORTH);
        secondFrame.add(panelCentre, BorderLayout.CENTER);
        secondFrame.add(panelBas, BorderLayout.SOUTH);

        secondFrame.setVisible(true);
    }

    public void frameJeu_Admin(JFrame jeu_Admin){

        jeu_Admin.setTitle("Jeu - Admin");
        jeu_Admin.setSize(650, 500);


        jeu_Admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jeu_Admin.setLocationRelativeTo(null);
        jeu_Admin.setLayout(new BorderLayout());


        JPanel panelHaut = new JPanel();
        panelHaut.setBorder(BorderFactory.createTitledBorder("Carte"));
        panelHaut.setPreferredSize(new Dimension(340, 280));

        JPanel panelBas = new JPanel();
        panelBas.setBorder(BorderFactory.createTitledBorder("Scores des joueurs : "));
        panelBas.setPreferredSize(new Dimension(100,200));


        ///// POUR LA GRILLE //////

        JPanel contentGrille = new JPanel();
        panelHaut.setLayout(new BorderLayout());

        contentGrille.setLayout(new GridLayout(10, 10));//on définit la taille de la grille de 7 sur 7
        contentGrille.setPreferredSize(new Dimension(20,20));

        creationGrille_2(contentGrille);
        contentGrille.setBounds(150,150,150,150);

        panelHaut.add(contentGrille);

        ///// FIN AJOUT GRILLE //////



        jeu_Admin.add(panelHaut, BorderLayout.NORTH);
        jeu_Admin.add(panelBas, BorderLayout.SOUTH);

        jeu_Admin.setVisible(true);

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
                JLabel lettre = new JLabel(result[(j*cell.length)+i]);
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

    public void actionPerformed(ActionEvent e) {

        /*Thread t = new Thread() {
            @Override
            public void run() {
                // Ici on appellera les methodes de la class Launcher
            }
        };
        t.start();*/


        if(e.getSource() == jouer){
            this.username = pseudo.getText();
            char[] mdpTab  = mdp.getPassword();
            this.password = new String(mdpTab);


            checkId(this.username, this.password);

        }

        else if(e.getSource() == quitter){
            System.exit(1);
        }

    }

    public void instantiationBateau(String typeBateau,int x, int y, boolean h){
        if(typeBateau.equals("Croiseur (3 cases)")){
            Croisseur c=new Croisseur(x, y, h);
            flotte.ajouterbateau(c);
        }
    }



    /*public static void main(String[] args) {

        // Astuce pour avoir le style visuel du systeme hôte
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        instance = new Demarrage();
    }*/

    /*// Retourne l'instance de Demarrage
    public static Demarrage getInstance() {
        return instance;
    }

    // Retourne l'instance de notre progress bar
    public JProgressBar getProgressBar() {
        return pb;
    }*/






}





