package classes;

//Classe éphémère pour tester les classes

public class Main {
    public static void main(String[] args) {
        TacheMere t = new TacheMere("Hello", 7);
        TacheMere s = new TacheMere("Hi", 10);
        TacheMere s2 = new TacheMere("Hi2", 10);
        Tableau tab = new Tableau("Tableau");
        Colonne col = new Colonne("Colonne");
        Colonne col2 = new Colonne("Colonne2");
        tab.ajouterColonne(col);
        tab.ajouterColonne(col2);
        col.ajouterTache(t);
        col.ajouterTache(s);
        col2.ajouterTache(s2);
        VueListe vue = new VueListe();
        tab.enregistrerObservateur(vue);
        tab.notifierObservateur(vue);

    }
}
