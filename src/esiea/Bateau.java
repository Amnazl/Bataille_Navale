package esiea;


public class Bateau {
    private boolean horizontal;
    //private Element[] element;
    private int abcisse;
    private int ordonnee;
    private String etat;
    private String etat_neutre;
    private String etat_touche;
    private String etat_coule;
    private int coordonnee[];
    public Bateau(int x, int y, boolean h,int taille) {
        this.coordonnee = new int[taille];
        this.etat="";
        this.etat_neutre="";
        this.etat_touche="";
        this.etat_coule="";
        this.horizontal=h;
        this.abcisse = x;
        this.ordonnee = y;

        for(int t = 0; t<taille;t++){
            if(h){
                etat_neutre +=".";
                etat_touche +="T";
                etat_coule  +="C";
            coordonnee[t] = x+t;
            etat+=".";
            }else{
                etat_neutre +=".";
                etat_touche +="T";
                etat_coule  +="C";

               /* if(t == taille){
                    etat_neutre +=".";
                    etat_touche +="T";
                    etat_coule  +="C";
                }else{
                    etat_neutre +=".\n";
                    etat_touche +="T\n";
                    etat_coule  +="C\n";
                }*/

                coordonnee[t] = y+t;

            }
        }
        etat = etat_neutre;

    }
       /* element=new Element[taille];
        if(horizontal){
            for(int i=0;i<taille;i++){
                element[i]=new Element(x+i,y);
            }
        }else{
            for(int i=0;i<taille;i++){
                element[i]=new Element (x,y+i);
            }
        }*/


    public void touche(int x, int y){
            if(this.horizontal) {
                for (int c = 0; c < coordonnee.length; c++) {
                    if (coordonnee[c] == x && ordonnee == y) {
                        if (etat.equals(etat_neutre)) {
                            etat = etat_touche;
                        } else if (etat.equals(etat_touche)) {

                            etat = etat_coule;
                        }

                    }
                }
            } else{
                for (int c = 0; c < coordonnee.length; c++) {
                    if (abcisse == x && coordonnee[c] == y) {
                        if (etat.equals(etat_neutre)) {
                            etat = etat_touche;
                        } else if(etat.equals(etat_touche)) {

                            etat = etat_coule;
                        }
                    }

                }
            }
       /* for(int i=0;i<element.length;i++){
            if(this.horizontal){
                element[i].touche(x+i,y);
            }
            else{
                element[i].touche(x,y+i);
            }

        }*/

    }

    /*public boolean estdetruit(){
        boolean res=false;
        int i=0;
        while((i<element.length)&&res){
            res=(res)&&(element[i].getEtat()=="C");
            i++;
        }
        return res;
    }*/
    public String[] toStringArray(){
        String [] result = new String[etat.length()];
        for(int st = 0; st <etat.length() ; st ++ ){
            result[st] = etat.substring(st,st+1);
            //System.out.println( result[st]);
        }

        return result;
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



}
