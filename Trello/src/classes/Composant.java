package classes;

import java.util.ArrayList;

public abstract class Composant<T> {
    protected String nom;
    protected ArrayList<T> liste;

    Composant(String n) {
        nom = n;
        liste = new ArrayList<>();
    }

    public void modifierNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composant composant = (Composant) o;
        return nom.equals(composant.nom);
    }

}
