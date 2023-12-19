import classes.Tableau;
import classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTache {

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
         t1 = new TacheMere("Tache 1", 7);
         t2 = new TacheMere("Tache 2", 10);
         t3 = new TacheMere("Tache 3", 5);
         t4 = new TacheMere("Tache 4", 5);

         //creer 2 sous taches
        t5 = new SousTache("Tache 5", 5,t1);
        t6 = new SousTache("Tache 6", 5,t1);

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

    //teste la méthode ajouterAntecedent quand fonctionne correctement avec des taches mères
    @Test
    public void test_ajouterAntecedent_OK_tache(){

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));
    }

    //teste la méthode ajouterAntecedent quand fonctionne correctement avec des sous taches
    @Test
    public void test_ajouterAntecedent_OK_sous_taches(){

        //vérifier que les antécédents de la sous tache 6 sont bien les sous taches 5
        assertTrue(t6.getAntecedent().contains(t5));
    }

    //teste la méthode ajouterAntecedent quand la tache est déjà dans la liste des antécédents
    @Test
    public void test_ajouterAntecedent_2taches_pareilles(){

        //on ajoute la tache 1 comme antécédent de la tache 3
        t3.ajouterAntecedent(t1);

        //on vérifie que la tache 1 n'a pas été ajoutée une deuxième fois
        assertEquals(2, t3.getAntecedent().size());
    }

    //teste la méthode ajouterAntecedent quand la sous tache est déjà dans la liste des antécédents
    @Test
    public void test_ajouterAntecedent_2sous_taches_pareilles(){

        //on ajoute la sous tache 5 comme antécédent de la sous tache 6
        t6.ajouterAntecedent(t5);

        //on vérifie que la sous tache 5 n'a pas été ajoutée une deuxième fois
        assertEquals(1, t6.getAntecedent().size());
    }

    //teste la méthode supprimerAntecedent quand fonctionne correctement pour une tache
    @Test
    public void test_supprimerAntecedent_OK(){

        //on supprime la tache 1 comme antécédent de la tache 3
        t3.supprimerAntecedent(t1);

        //on vérifie que la tache 1 n'est plus dans la liste des antécédents
        assertEquals(1, t3.getAntecedent().size());
    }

    //teste la méthode supprimerAntecedent quand fonctionne correctement pour une sous tache
    @Test
    public void test_supprimerAntecedent_OK_sous_tache(){

        //on supprime la sous tache 5 comme antécédent de la sous tache 6
        t6.supprimerAntecedent(t5);

        //on vérifie que la sous tache 5 n'est plus dans la liste des antécédents
        assertEquals(0, t6.getAntecedent().size());
    }

    //teste la méthode supprimerAntecedent quand la tache n'est pas dans la liste des antécédents
    @Test
    public void test_supprimerAntecedent_tache_pas_dans_liste(){

        //on supprime la tache 4 comme antécédent de la tache 3
        t3.supprimerAntecedent(new TacheMere("Tache 4", 5));

        //on verifie qu'il n'y a pas eu de changement
        assertEquals(2, t3.getAntecedent().size());

    }

    //teste la methode supprimerAntecedent quand la tache est un antecedent d'une autre tache
    @Test
    public void test_supprimerAntecedent_tache_antecedente(){

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

        //vérifier que les antécédents de la tache 3 sont bien les taches 1 et 2
        assertTrue(t3.getAntecedent().contains(t1));
        assertTrue(t3.getAntecedent().contains(t2));
    }

    //teste la méthode getAntecedent quand la liste est vide
    @Test
    public void test_getAntecedent_liste_vide(){
        //vérifier que la liste des antécédents de la tache 1 est vide
        assertEquals(0, t1.getAntecedent().size());
    }

}
