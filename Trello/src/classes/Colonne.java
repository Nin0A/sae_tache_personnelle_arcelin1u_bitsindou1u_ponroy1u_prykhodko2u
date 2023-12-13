package classes;

import java.util.ArrayList;

public class Colonne extends ComposantAListe {
    private ArrayList<TacheMere> taches;

    Colonne(String n) {
        super(n);
        taches = new ArrayList<TacheMere>();
    }

    public ArrayList<TacheMere> getTaches() {
        return taches;
    }
}
