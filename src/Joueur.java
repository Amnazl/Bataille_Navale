public class Joueur {
    private final String id;
    private int score;
    private boolean administrateur;

    public Joueur(String id,boolean administrateur){
        this.id = id;
        this.score = 0;
        this.administrateur = administrateur;
    }

    public String getId() {

        return id;
    }

    public int getScore() {

        return score;
    }

    public boolean isAdministrateur(){

        return administrateur;
    }

    public void setScore(int score){

        this.score = score;
    }

    public void setAdministrateur(boolean administrateur)
    {

        this.administrateur = administrateur;
    }


    public int scoreJoueur(){

        return 1;

    }

    public void tourDeJeu(){

    }

    public String toString(){

        String s = "";
        return s;

    }



}
