package classes;
//classe temporelle pour tester les classes

public class Main {
    public static void main(String[] args) {
        TacheMere t = new TacheMere("Hello", 7);
        TacheMere s = new TacheMere("Hi", 10);

        Tableau tab = new Tableau("Tableau");
        Colonne col = new Colonne("Colonne");
        tab.ajouterComposant(col);
        col.ajouterComposant(t);
        col.ajouterComposant(s);
        System.out.println("Liste1 : " + tab.getNom());
        System.out.println("Col "+col.getNom()+" :");
        System.out.println(col.getTaches().get(0).getNom());
        System.out.println(col.getTaches().get(1).getNom());
        VueListe vue = new VueListe();
        tab.enregistrerObservateur(vue);
        tab.notifierObservateur(vue);
    }
}
