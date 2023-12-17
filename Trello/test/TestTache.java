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
    private TacheMere t1, t2, t3;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        tab = new Tableau("Tableau n°1");

        //créer une colonne
        col = new Colonne("Colonne A");

        //créer 3 taches
         t1 = new TacheMere("Tache 1", 7);
         t2 = new TacheMere("Tache 2", 10);
         t3 = new TacheMere("Tache 3", 5);
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

}
