public class Joueur {
    private final String id;
    private int score;
    private boolean administrateur;

    public Joueur(String id,int score,boolean administrateur){
        this.id = id;
        this.score = score;
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

    public void setAdministrateur(boolean administrateur) {

        this.administrateur = administrateur;
    }



}
