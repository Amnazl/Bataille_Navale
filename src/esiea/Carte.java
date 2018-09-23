package esiea;



public class Carte {
    public static final int ordonne = 10;
    public static final int abscisse = 10;
    private  Bateau tab[][];


    public Carte(){
        this.tab = new Bateau[10][10];
        for(int i = 0; i< 10;i++){
            for(int j = 0; j< 10;j++){
                tab[i][j]= new Bateau(i,j,true,1);
            }
        }
    }

    public int  getOrdonne(){
        return ordonne;
    }
    public int getAbscisse(){
        return abscisse;
    }

    public Bateau[][] getTab() {
        return tab;
    }

    public void setTab(Bateau tab[][]){
        this.tab = tab;
    }
    public void AjouterBateau(Bateau b){
        this.tab[b.getabcisse()][b.getordonnee()] = b;
    }
    public void SupprimerBateau(Bateau e){
        this.tab[e.getabcisse()][e.getordonnee()] = null;
    }

    public String toString(){
        String s = " " ;
        for(int i = 0; i<abscisse; i++){
            for(int j = 0; j<ordonne; j++){
                s.concat(tab[i][j].toString());
            }
        }
        return s;
    }




}
