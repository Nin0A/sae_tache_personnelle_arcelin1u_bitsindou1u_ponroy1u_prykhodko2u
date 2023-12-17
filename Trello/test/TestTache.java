import classes.Colonne;
import classes.Tableau;
import classes.Tache;
import classes.TacheMere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTache {

    Tableau tab;
    Colonne col;
    private TacheMere t1, t2, t3, t4;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        tab = new Tableau("Tableau n°1");

        //créer une colonne
        col = new Colonne("Colonne A");

        //créer 4 taches
         t1 = new TacheMere("Tache 1", 7);
         t2 = new TacheMere("Tache 2", 10);
         t3 = new TacheMere("Tache 3", 5);
         t4 = new TacheMere("Tache 4", 5);
    }

    //teste la méthode ajouterAntecedent quand fonctionne correctement
    @Test
    public void test_ajouterAntecedent_OK(){
        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));
    }

    //test la méthode ajouterAntecedent quand la tache est déjà dans la liste des antécédents
    @Test
    public void test_ajouterAntecedent_2taches_pareilles(){
        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));

        //on ajoute la tache 1 comme antécédent de la tache 3
        t3.ajouterAntecedent(t1);

        //on vérifie que la tache 1 n'a pas été ajoutée une deuxième fois
        assertEquals(2, t3.getAntecedent().size());
    }

    //test la méthode supprimerAntecedent quand fonctionne correctement
    @Test
    public void test_supprimerAntecedent_OK(){
        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));

        //on supprime la tache 1 comme antécédent de la tache 3
        t3.supprimerAntecedent(t1);

        //on vérifie que la tache 1 n'est plus dans la liste des antécédents
        assertEquals(1, t3.getAntecedent().size());
    }

    //test la méthode supprimerAntecedent quand la tache n'est pas dans la liste des antécédents
    @Test
    public void test_supprimerAntecedent_tache_pas_dans_liste(){
        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));

        //on supprime la tache 4 comme antécédent de la tache 3
        t3.supprimerAntecedent(new TacheMere("Tache 4", 5));

        //on verifie qu'il n'y a pas eu de changement
        assertEquals(2, t3.getAntecedent().size());

    }

    //teste la methode supprimerAntecedent quand la tache est un antecedent d'une autre tache
    @Test
    public void test_supprimerAntecedent_tache_antecedente(){
        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //on ajoute la tache 3 comme antécédent de la tache 4
        t4.ajouterAntecedent(t3);

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));

        //vérifier que la tache 3 est bien un antécédent de la tache 4
        assertTrue(t4.getAntecedent().contains(t3));

        //on supprime la tache 3 comme antécédent de la tache 4
        t4.supprimerAntecedent(t3);

        //on vérifie que la tache 3 n'est plus dans la liste des antécédents de la tache 4
        assertEquals(0, t4.getAntecedent().size());

        //on vérifie que les taches 1 et 2 sont toujours dans la liste des antécédents de la tache 3 A VERIFIER AVEC LES AUTRES
        assertEquals(2, t3.getAntecedent().size());
    }

    //teste la méthode getAntecedent quand fonctionne correctement
    @Test
    public void test_getAntecedent_OK(){
        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));
    }

    //teste la méthode getAntecedent quand la liste est vide
    @Test
    public void test_getAntecedent_liste_vide(){
        //vérifier que la liste des antécédents de la tache 3 est vide
        assertEquals(0, t3.getAntecedent().size());
    }

}
