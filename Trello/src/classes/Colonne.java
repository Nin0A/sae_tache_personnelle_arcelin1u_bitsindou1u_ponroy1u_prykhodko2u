package classes;

import java.util.ArrayList;

public class Colonne extends Composant<Tache> {

    Colonne(String n) {
        super(n);
    }

<<<<<<< HEAD
    public void modifierDureeTache(int duree, String nomTache){
        for (Composant c : this.liste){
            if (c.nom.equals(nomTache)){

            }
        }
    }
=======

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
>>>>>>> 572643629a2cf5464c13e3f62796a7b9b02657aa
}
