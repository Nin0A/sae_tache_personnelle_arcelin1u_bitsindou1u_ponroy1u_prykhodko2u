package classes;

import java.util.ArrayList;

public class Colonne extends Composant<Tache> {

    Colonne(String n) {
        super(n);
        liste = new ArrayList<Tache>();
    }


    public ArrayList<Tache> getTaches() {
        return liste;
    }

//    public void modifierDureeTache(int duree, String nomTache){
//        for (Composant c : liste){
//            if (c.nom.equals(nomTache)){
//                ((Tache)c)
//            }
//        }
//    }
}
