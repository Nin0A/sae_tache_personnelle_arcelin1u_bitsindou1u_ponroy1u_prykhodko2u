package classes;

//Classe éphémère pour tester les classes

public class Main {
    public static void main(String[] args) {
        TacheMere t = new TacheMere("Tache t", 7, 1, 12, 2023);
        TacheMere s = new TacheMere("Tache s", 10, 3, 12, 2023);
        TacheMere s2 = new TacheMere("Tache s2", 10, 4, 12 , 2023);
        TacheMere s3 = new TacheMere("Tache s3", 10, 4, 12 , 2023);
        TacheMere s4 = new TacheMere("Tache s4", 10, 4, 12 , 2023);
        SousTache st = new SousTache("Soustache st", 10, 4, 12 , 2023);
        t.ajouterSousTache(st);
        t.ajouterSousTache(s);
        s.ajouterSousTache(s2);

        Tableau tab = new Tableau("Tableau");
        Colonne col = new Colonne("Colonne");
        Colonne col2 = new Colonne("Colonne2");
        tab.ajouterColonne(col);
        tab.ajouterColonne(col2);
        col.ajouterTache(t);
        col.ajouterTache(s3);
        col2.ajouterTache(s4);
        VueGantt vue = new VueGantt();
        tab.enregistrerObservateur(vue);
        tab.notifierObservateur(vue);
        System.out.println(s.getAntecedent());
    }
}
