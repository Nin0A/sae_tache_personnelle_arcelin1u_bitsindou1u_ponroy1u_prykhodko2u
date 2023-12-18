package classes;

import java.util.ArrayList;

public class Colonne extends Composant<Tache> {

    Colonne(String n) {
        super(n);
    }

    public boolean modifierDureeTache(int duree, String nomTache){
        if (duree<0)
            return false;
        for (Tache t : this.liste){
            if (t.nom.equals(nomTache)){
                t.setDuree(duree);
            }
        }
        return true;
    }


    public void modifierNomDelaTache(String nomTacheAvant, String nomTacheApres) {
        for (Tache t : this.liste){
            if (t.nom.equals(nomTacheAvant)){
                t.setNom(nomTacheApres);
            }
        }
    }

    public void modifierAntecedent(){

    }


//    public void

    public ArrayList<Tache> getTaches() {
        return liste;
    }

}
