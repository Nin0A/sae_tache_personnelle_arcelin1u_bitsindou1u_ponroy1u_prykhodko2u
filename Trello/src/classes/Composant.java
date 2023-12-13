package classes;

public abstract class Composant {

    //constructeur
    protected String nom; //nom du composant crée

    //constructeur
    Composant(String n) {
        nom = n;
    }

    /**
     * Méthode modifierNom qui modifie le nom d'un composant
     * @param nom nouveau nom modifié du composant
     */
    public void modifierNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode equals qui compare 2 objets de type Composant
     * @param o objet avec lequel on compare
     * @return true si les 2 objets sont pareils, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composant composant = (Composant) o;
        return nom.equals(composant.nom);
    }

}
