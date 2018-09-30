package esiea;

import java.io.Serializable;
import java.util.ArrayList;

public class Carte2 {
    public static final int ordonne = 10;
    public static final int abscisse = 10;
    private String tab[][];
    private boolean isDetruit;

    public Carte2() {
        this.tab = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tab[i][j] = ".";
            }
        }
    }

    public void UpdateCarte(Flotte f,boolean admin) {
        ArrayList<Bateau2> b = f.getAllBateau();
        for (Bateau2 bateau : b) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (bateau.getabcisse() == i && bateau.getordonnee() == j) {
                       isDetruit = bateau.estDetruit();
                        String[] res;
                        if(admin){
                            res = bateau.toStringArray_admin();
                        }else {
                            res = bateau.toStringArray_client();
                        }

                        if(bateau.isHorizontal()){
                            for(int str = 0; str < res.length; str++){

                                tab[i][j+str] = res[str];
                            }
                        }else{
                            for(int str = 0; str < res.length; str++){

                                tab[i+str][j] = res[str];
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean getisDetruit(){
        return isDetruit;
    }

    public String toString(){
        String s="";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                s+=tab[i][j];
            }
        }
        return s;
    }


    public String[][] toStringArray(){
        return tab;
    }
}
