package classes;

import java.util.ArrayList;

public class Colonne extends Composant {

    Colonne(String n) {
        super(n);
        liste = new ArrayList<Tache>();
    }

//    public void modifierDureeTache(int duree, String nomTache){
//        for (Composant c : liste){
//            if (c.nom.equals(nomTache)){
//                ((Tache)c)
//            }
//        }
//    }
}
