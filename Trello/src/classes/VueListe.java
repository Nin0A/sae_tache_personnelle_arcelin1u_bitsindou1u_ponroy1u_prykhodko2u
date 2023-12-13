package classes;

public class VueListe implements Observateur{

    @Override
    public void actualiser(Sujet sujet) {
        Tableau tab = (Tableau) sujet;
        System.out.println("Liste : " + tab.getNom());
        for (Composant c : tab.getListe()) {
            System.out.println(" -->" + c.getNom()+" :");
            for (Tache tache :((Colonne)c).getTaches()) {
                System.out.println("         - " + tache.getNom());
            }
        }
    }
}
