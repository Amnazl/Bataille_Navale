package esiea;

public class Element {
    private int abcisse;
    private int ordonnee;
    private String etat;

    public Element(int i, int j) {
        abcisse=i;
        ordonnee=j;
        etat=".";
    }
    public int getabcisse(){
        return abcisse;
    }
    public int getordonnee(){
        return ordonnee;
    }

    public void touche(int i , int j) {
        if(abcisse==i && ordonnee==j) {
            if(etat =="."){
                etat="T";
            }else if(etat == "T"){
                etat="C";
            }

        }
    }
    public String getEtat(){
        return etat;
    }

    public String toString() {
        String s="";
        if(etat=="."){
            s=".";
        }else{
            if(etat=="T"){
                s="T";
            }else{
                s="C";
            }

        }
        return s;
    }


}
