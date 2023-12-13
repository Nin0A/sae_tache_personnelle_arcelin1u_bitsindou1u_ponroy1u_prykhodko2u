package classes;

import java.util.ArrayList;

public class Colonne extends Composant<Tache> {

    Colonne(String n) {
        super(n);
    }

    public void modifierDureeTache(int duree, String nomTache){
        for (Composant c : this.liste){
            if (c.nom.equals(nomTache)){

            }
        }
    }
}
