package esiea;


public class Bateau2 {
    private boolean horizontal;

    private int abcisse;
    private int ordonnee;
    private String etat;

    private int coordonnee[];
    private String etats[];
    public Bateau2(int x, int y, boolean h,int taille) {
        this.coordonnee = new int[taille];
        this.etats = new String[taille];
        this.horizontal=h;
        this.abcisse = x;
        this.ordonnee = y;
        for(int t = 0; t<taille;t++){
            if(h){
                etats[t]=".";
                coordonnee[t] = x+t;

            }else{
                etats[t]=".";
                coordonnee[t] = y+t;

            }
        }
    }


    public void touche(int x, int y){
        if(this.horizontal) {
            for (int c = 0; c < coordonnee.length; c++) {
                if (coordonnee[c] == x && ordonnee == y) {
                    if(etats[c].equals(".")){
                        etats[c] = "T";
                    }else if(etats[c].equals("T")){
                        etats[c] = "T";
                    }

                }
            }
        } else{
            for (int c = 0; c < coordonnee.length; c++) {
                if (abcisse == x && coordonnee[c] == y) {
                    if(etats[c].equals(".")){
                        etats[c] = "T";
                    }else if(etats[c].equals("T")){
                        etats[c] = "T";
                    }

                }

            }
        }

    }

    public void estDetruit(){
        int detruit = 0;
        for (int i = 0; i < etats.length; i++) {
            if (etats[i].equals("T")) {
                detruit += 1;
            }
        }
        if(detruit==etats.length){
            for (int e = 0; e < etats.length; e++) {
                etats[e]= "C";
            }
        }
    }

    public String[] toStringArray(){

        return etats;
    }

    public int getabcisse(){
        return abcisse;
    }
    public int getordonnee(){
        return ordonnee;
    }

    public boolean isHorizontal(){
        return horizontal;
    }

    public int[] getCoordonnee(){
        return this.coordonnee;
    }

    public String toString(){
        return this.getClass().getName();
    }

    public boolean sortDeLaCarte(){
        boolean isIt = false;
        for(int j = 0; j < coordonnee.length;j++){
            if(coordonnee[j] > 9 ||ordonnee > 9 || abcisse > 9 ){
                isIt=true;
                System.out.println("Impossible cette position n'est pas sur la carte");
                break;
            }
        }
        return isIt;

    }

    public boolean compareCoor(Bateau2 b){
        boolean isIt = false;
        int[] coor = b.getCoordonnee();
        for (int i = 0; i < coor.length; i++) {
            if (coor[i] == abcisse || coor[i] == ordonnee) {
                System.out.println("Impossible le bateau "+b.toString()+" est à cette position");
                isIt = true;
                break;
            }
        }
        return isIt;
    }



}
