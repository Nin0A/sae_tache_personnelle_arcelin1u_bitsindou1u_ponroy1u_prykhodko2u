package main.java.classes;

public abstract class Composant {
    protected String nom;

    Composant(String n){
        nom = n;
    }

   public void modifierNom(String nom){
        this.nom = nom;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composant composant = (Composant) o;
        return nom.equals(composant.nom);
    }

}
