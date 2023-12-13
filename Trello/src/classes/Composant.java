package classes;


import java.util.ArrayList;

public abstract class Composant<T> {

    protected ArrayList<T> liste;

    //constructeur
    protected String nom; //nom du composant crée

    //constructeur
    Composant(String n) {
        nom = n;
        liste = new ArrayList<>();
    }

    /**
     * Méthode modifierNom qui modifie le nom d'un composant
     * @param nom nouveau nom modifié du composant
     */
    public void modifierNom(String nom) {
        this.nom = nom;
    }

<<<<<<< HEAD
    public String getNom() {
        return nom;
    }



=======
    /**
     * Méthode equals qui compare 2 objets de type Composant
     * @param o objet avec lequel on compare
     * @return true si les 2 objets sont pareils, sinon false
     */
>>>>>>> c8d1fb3ab3242f93ca84c759f4d786823783ca1b
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composant composant = (Composant) o;
        return nom.equals(composant.nom);
    }

}
