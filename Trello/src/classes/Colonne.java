package classes;

import java.util.ArrayList;

public class Colonne extends Composant<Tache> {

    Colonne(String n) {
        super(n);
    }

    public void ajouterTache(Tache t){
        this.liste.add(t);
    }
    public void supprimerTache(Tache t){
        this.liste.remove(t);
    }
    public void modifierDureeTache(int duree, String nomTache){
        for (Composant c : this.liste){
            if (c.nom.equals(nomTache)){

            }
        }
    }


    public ArrayList<Tache> getTaches() {
        return liste;
    }

}
