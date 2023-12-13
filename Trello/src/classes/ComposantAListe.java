package classes;

import java.util.ArrayList;

public abstract class ComposantAListe<T> extends Composant {

    protected ArrayList<T> liste;


    ComposantAListe(String n) {
        super(n);
        liste = new ArrayList<>();
    }


}
