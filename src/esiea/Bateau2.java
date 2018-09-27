package esiea;


public class Bateau2 {
    private boolean horizontal;

    private int abcisse;
    private int ordonnee;
    private int coordonnee[];
    private String etats_client[];
    private String etats_admin[];
    public Bateau2(int x, int y, boolean h,int taille) {
        this.coordonnee = new int[taille];
        this.etats_client = new String[taille];
        this.etats_admin = new String[taille];
        this.horizontal=h;
        this.abcisse = x;
        this.ordonnee = y;

        for(int t = 0; t<taille;t++){
            if(h){
                etats_admin[t]="X";
                etats_client[t]=".";
                coordonnee[t] = y+t;

            }else{
                etats_admin[t]="X";
                etats_client[t]=".";
                coordonnee[t] = x+t;

            }
        }


    }


    public void touche(int x, int y){
        if(!(this.horizontal)) {
            for (int c = 1; c <= coordonnee.length; c++) {
                if (coordonnee[c] == x && ordonnee == y) {
                    if(etats_client[c].equals(".") || etats_admin[c].equals("X") ){
                        etats_client[c] = "T";
                        etats_admin[c] = "T";
                    }else if(etats_client[c].equals("T")){
                        etats_client[c] = "T";
                    }

                }
            }
        } else{
            for (int c = 1; c <= coordonnee.length; c++) {
                if (abcisse == x && coordonnee[c] == y) {
                    if(etats_client[c].equals(".") || etats_admin[c].equals("X")){
                        etats_client[c] = "T";
                        etats_admin[c] = "T";
                    }else if(etats_client[c].equals("T")){
                        etats_client[c] = "T";
                    }

                }

            }
        }

    }

    public void estDetruit(){
        int detruit = 0;
        for (int i = 0; i < etats_client.length; i++) {
            if (etats_client[i].equals("T") || etats_admin[i].equals("T")) {
                detruit += 1;
            }
        }
        if(detruit==etats_client.length){
            for (int e = 0; e < etats_client.length; e++) {
                etats_client[e]= "C";
                etats_admin[e]= "C";
            }
        }
    }

    public String[] toStringArray_client(){

        return etats_client;
    }
    public String[] toStringArray_admin(){

        return etats_admin;
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
            if(coordonnee[j] > 9 || ordonnee > 9 || abcisse > 9 ){
                isIt=true;
                System.out.println("Impossible cette position n'est pas sur la carte");
                break;
            }
        }
        return isIt;

    }

   /* public boolean compareCoor(Bateau2 b){
        boolean isIt = false;
        int[] coor = b.getCoordonnee();
        for (int i = 0; i < coor.length; i++) {
            System.out.println(coor[i]+"="+abcisse);
            System.out.println(coor[i]+"="+ordonnee);

            if(b.horizontal){
                if(horizontal==false){
                    if(coor[i]==ordonnee){
                        for(int j = 0; j <coordonnee.length; j++){
                            if(coor[i]==coordonnee[j]){
                                isIt = true;
                                System.out.println("passe pas");
                                break;
                            }
                        }
                    }
                }
            }
            if(isIt == true){
                break;
            }


               /* if( horizontal==false){
                    if (coor[0] == ordonnee) {

                        System.out.println("Impossible le bateau "+b.toString()+" est à cette position");
                        isIt = true;
                        break;
                    }

                }else{
                    if( coor[i] == abcisse){
                        System.out.println("Impossible le bateau "+b.toString()+" est à cette position");
                        isIt = true;
                        break;
                    }

                }


            }else{
                if(horizontal){
                    if (coor[0] ==abcisse) {
                        System.out.println("Impossible le bateau "+b.toString()+" est à cette position");
                        isIt = true;
                        break;
                    }

                }else{
                    if( coor[i] == ordonnee){
                        System.out.println("Impossible le bateau "+b.toString()+" est à cette position");
                        isIt = true;
                        break;
                    }
                }
            }

        }
        return isIt;
    }*/

   public boolean compareCoor(Bateau2 b1,Carte2 carte){
       boolean isIt = false;
       int abs_b1;
       int ord_b1;
       int[] coor = coordonnee;
       String plateau[][] = carte.toStringArray();

           abs_b1 = abcisse;
           ord_b1 =ordonnee;
                   for(int k = 0; k< coor.length;k++){
                       if(horizontal){
                           System.out.println(abs_b1);
                           if(plateau[abs_b1][coor[k]].equals("X")){

                               isIt = true;
                               break;

                           }
                       }else{
                           if(plateau[coor[k]][ord_b1].equals("X")){

                               isIt = true;
                               break;

                           }

                       }
       }


       return isIt;





   }



}
