package main.java.classes;

import java.util.ArrayList;

public abstract class ComposantAListe extends Composant {

    protected ArrayList<Composant> liste;


    ComposantAListe(String n) {
        super(n);
        liste = new ArrayList<>();
    }

    public void supprimerComposant(Composant c)
    {
        liste.remove(c);
    }
    public void ajouterComposant(Composant c)
    {
        liste.add(c);
    }
    /*public void modifierComposant(Composant c)
    {
        //TODO
    }
*/
}
