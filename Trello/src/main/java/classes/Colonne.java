package main.java.classes;

import java.util.ArrayList;

public class Colonne extends ComposantAListe{
    private ArrayList<Tache> taches;

    Colonne(String n) {
        super(n);
        taches = new ArrayList<Tache>();
    }

}
