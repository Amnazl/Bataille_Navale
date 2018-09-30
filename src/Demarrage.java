import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Demarrage extends JFrame implements ActionListener {

    private static Demarrage instance;

    private JFrame launcherFrame = new JFrame();

    private JTextField pseudo;
    private JPasswordField mdp;
    private JButton jouer;
    private JButton quitter;


    private int nbClients;

    public FrameDispositionsAdmin fDispoAd;
    public FrameJeuClient fJeuClient;


    private ImagePanel panel_ImageTitre= new ImagePanel(new ImageIcon("images/bataille_navale.png").getImage());
    private ImagePanel panel_BattleIcon = new ImagePanel(new ImageIcon("images/battleship.png").getImage());
    private ImagePanel panel_Swords_left = new ImagePanel(new ImageIcon("images/swords.png").getImage());
    private ImagePanel panel_Swords_right = new ImagePanel(new ImageIcon("images/swords.png").getImage());


    private String username = null;
    private String password = null;



    public Demarrage() {



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


        launcherFrame.setVisible(true);

    }



    public void checkId(String username, String password){

        boolean isInt;

        if (username.equals("Admin") && password.equals("Admin")) {
            JOptionPane.showMessageDialog(null,"Vous allez vous connecter en tant qu'admin.");


            do{

                try{
                    nbClients = Integer.parseInt(JOptionPane.showInputDialog(null, "Combien de joueurs voulez-vous ? :"));
                    System.out.println(nbClients);
                    isInt = true;

                }catch(NumberFormatException nbEx){
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un chiffre uniquement.");
                    isInt = false;
                }

            }while(isInt == false);

            fDispoAd = new FrameDispositionsAdmin();
            launcherFrame.setVisible(false);

    }
        else if(username.equals("Admin") && !(password.equals("Admin"))){
            JOptionPane.showMessageDialog(null,"Saisir un autre identifiant qu'Admin");
        }
        else{

            JOptionPane.showMessageDialog(null,"Vous allez vous connecter en tant que joueur");

            fJeuClient = new FrameJeuClient(username);
            launcherFrame.setVisible(false);
        }


    }

    public void actionPerformed(ActionEvent e) {

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
}





