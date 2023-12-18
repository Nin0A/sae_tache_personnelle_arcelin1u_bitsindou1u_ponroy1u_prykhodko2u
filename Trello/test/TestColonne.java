import classes.Tableau;
import classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColonne {

    //attributs
    Tableau tab;
    Colonne col;
    private TacheMere t1, t2, t3, t4;
    private Tache t5, t6;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        tab = new Tableau("Tableau n°1");

        //créer une colonne
        col = new Colonne("Colonne A");

        //créer 4 taches mères
        t1 = new TacheMere("Tache 1", 7, 1 , 1, 1);
        t2 = new TacheMere("Tache 2", 10, 1 , 1, 1);
        t3 = new TacheMere("Tache 3", 5, 1 , 1, 1);
        t4 = new TacheMere("Tache 4", 5, 1 , 1, 1);

        //creer 2 sous taches
        t5 = new SousTache("Tache 5", 5, 1 , 1, 1);
        t6 = new SousTache("Tache 6", 5, 1 , 1, 1);

        //on ajoute la colonne à la liste de colonnes du tableau
        tab.ajouterColonne(col);

        //on ajoute les taches à la colonne
        col.ajouterTache(t1);
        col.ajouterTache(t2);
        col.ajouterTache(t3);
        col.ajouterTache(t4);
        col.ajouterTache(t5);
        col.ajouterTache(t6);

        //on ajoute les sous taches 5 et 6 à la tache 1
        t1.ajouterSousTache(t5);
        t1.ajouterSousTache(t6);

        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //on ajoute la sous tache 5 comme antécédent de la sous tache 6
        t6.ajouterAntecedent(t5);

        //on ajoute la tache 3 comme antécédent de la tache 4
        t4.ajouterAntecedent(t3);


    }

    //teste la methode supprimerTache quand la tache mere à supprimer à des sous taches
    @Test
    public void test_supprimerAntecedent_tache_mere_avec_sous_taches() { //A VOIR

        //on vérifie que la tache 1 a bien 2 sous taches
        assertEquals(2, t1.getSousTaches().size());

        //on supprime la tache 1 de la colonne
        col.supprimerTache(t1);

        //on vérifie que la tache 1 n'est plus dans la liste de taches de la colonne
        assertEquals(5, col.getTaches().size());

        //on vérifie que les sous taches 5 et 6 de la tache 1 sont bien supprimées de la colonne
        assertEquals(1, t1.getSousTaches().size()); //il reste 1 sous tache
    }
}
