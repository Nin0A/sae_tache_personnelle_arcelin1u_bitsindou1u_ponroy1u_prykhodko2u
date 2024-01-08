package classes;

//Classe éphémère pour tester les classes

public class Main {
    public static void main(String[] args) {

        /*TacheMere t = new TacheMere("Tache t", 7);
        TacheMere s = new TacheMere("Tache s", 10);
        TacheMere s2 = new TacheMere("Tache s2", 10);
        TacheMere s3 = new TacheMere("Tache s3", 10);
        TacheMere s4 = new TacheMere("Tache s4", 10);
        SousTache st = new SousTache("Soustache st", 10, t);
*//*
        TacheMere t = new TacheMere("Tache t", 7, 1, 12, 2023);
        TacheMere s = new TacheMere("Tache s", 10, 7, 12, 2023);
        TacheMere s2 = new TacheMere("Tache s2", 5, 7, 12 , 2023);
        TacheMere s3 = new TacheMere("Tache s3", 15, 4, 12 , 2023);
        TacheMere s4 = new TacheMere("Tache s4", 17, 4, 12 , 2023);
        SousTache st = new SousTache("Soustache st", 2, t,2, 12 , 2023);

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
        col2.ajouterTache(s4);*/
        Tache t1 = new TacheMere("Tache 1", 7, 1, 12, 2023);
        Tache t2 = new TacheMere("Tache 1", 8, 1, 12, 2023);
        System.out.println(t1.equals(t2));
        Colonne col1 = new Colonne("Colonne t1");
        Colonne col2 = new Colonne("Colonne t2");
        Tableau tab1 = new Tableau("Tableau 1");
        Tableau tab2 = new Tableau("Tableau 2");
        tab1.ajouterColonne(col1);
        tab2.ajouterColonne(col2);
        col2.ajouterTache(new TacheMere("Tache 1", 7, 1, 12, 2023));
        Systeme sys = new Systeme();
        sys.ajouterTab(tab1);
        sys.ajouterTab(tab2);
        System.out.println(sys.getTableauCourant());
        System.out.println(sys.getTableaux().size());
        System.out.println(sys.getTableaux());
        sys.changerTableauCourrant(tab2);
        System.out.println(sys.getTableauCourant());
        sys.changerTableauCourrant(null);
        System.out.println(sys.getTableauCourant());
        sys.changerTableauCourrant(new Tableau("Tableau 3"));
        System.out.println(sys.getTableauCourant());



        /*VueListe vue = new VueListe();
        tab.enregistrerObservateur(vue);
        tab.notifierObservateur(vue);
        System.out.println(s.getAntecedent());*/
    }
}
