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
        Colonne col1 = new Colonne("Colonne 1");
        Colonne col2 = new Colonne("Colonne 2");

        Tache t1 = new TacheMere("Tache 1", col1,7, 1, 12, 2023);
        TacheMere t2 = new TacheMere("Tache 2",col1, 8, 1, 12, 2023);
        TacheMere t3 = new TacheMere("Tache 3",col1, 9, 1, 12, 2023);
        TacheMere t4 = new TacheMere("Tache 4",col1, 10, 1, 12, 2023);
        TacheMere t5 = new TacheMere("Tache 5",col1, 11, 1, 12, 2023);
        TacheMere t6 = new TacheMere("Tache 6",col1, 11, 1, 12, 2023);


        t5.ajouterSousTache(t4);
        t4.ajouterSousTache(t3);
        t3.ajouterSousTache(t2);
        t2.ajouterSousTache(t6);

        col1.ajouterTache(t5);
        Tableau tab1 = new Tableau("Tableau 1");
        tab1.ajouterColonne(col1);
        tab1.ajouterColonne(col2);
        Tache res = tab1.getTachebyId(t2.getId());
        System.out.println(res);
        res = tab1.getTachebyId(t4.getId());
        System.out.println(res);
        res = tab1.getTachebyId(t5.getId());
        System.out.println(res);
        res = tab1.getTachebyId(t1.getId());
        System.out.println(res);
        /*
        System.out.println(t1.liste);
        t5.afficher();
        tab1.archiverTache(t2);
        System.out.println(t1.liste);
        t5.afficher();
        System.out.println(t2.liste);
        tab1.getArchive().afficher();*/

    }
}
