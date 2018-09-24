package esiea;

import java.util.ArrayList;
import java.util.Vector;

public class Flotte {
    private Vector bateaux;
    public Flotte() {
        bateaux=new Vector();
    }



    public void ajouterbateau(Bateau2 b) {
        boolean compareCoor = false;
        boolean SortCarte = false;
        if (!bateaux.contains(b)) {
            if (bateaux.size() == 0) {
                bateaux.add(b);
            } else {
                for (Object b2 : bateaux) {
                    compareCoor= b.compareCoor((Bateau2)(b2));
                   System.out.println("1 : "+compareCoor);
                }
                SortCarte= b.sortDeLaCarte();
                System.out.println("2 : "+SortCarte);
                if(compareCoor==false && SortCarte==false ){
                    System.out.println("ajouté");
                    bateaux.add(b);
                }

            }
        }
    }



    public void enleverbateau(Bateau2 b){
        if(bateaux.contains(b))
            bateaux.remove(b);
    }
    public void coup(int x, int y){
        for(int i=0;i<bateaux.size();i++){
            ((Bateau2)(bateaux.get(i))).touche(x,y);

        }

    }

    public Bateau2 getBateau(int x, int y){
        for(int i=0;i<bateaux.size();i++){
            Bateau2 b = (Bateau2)(bateaux.get(i));
            if(b.getabcisse() == x && b.getordonnee() == y){
                return b;
            }
        }
        return null;
    }

   public ArrayList<Bateau2> getAllBateau(){
        ArrayList<Bateau2> b = new ArrayList<Bateau2>();
       for (Object o:bateaux) {
           b.add((Bateau2) o);
       }
       return b;
   }
    public String toString(){
        String res="";
        for(int i=0;i<bateaux.size();i++)
            res+=((Bateau)(bateaux.get(i))).toString()+'\n';
        return res;
    }
}
