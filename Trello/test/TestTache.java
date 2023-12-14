import classes.Colonne;
import classes.Tableau;
import classes.Tache;
import classes.TacheMere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTache {
    private TacheMere t1, t2, t3;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        Tableau tab = new Tableau("Tableau n°1");

        //créer une colonne
        Colonne col = new Colonne("Colonne A");

        //créer 3 taches
         t1 = new TacheMere("Tache 1", 7);
         t2 = new TacheMere("Tache 2", 10);
         t3 = new TacheMere("Tache 3", 5);
    }

    //test la méthode ajouterAntecedents
    @Test
    public void test_ajouterAntecedents(){
        //ajouter t1 comme antécédent de t2
        //t2.ajouterAntecedents(t1);
        //ajouter t2 comme antécédent de t3
        //t3.ajouterAntecedents(t2);

        //vérifier que t1 est bien un antécédent de t2
        //assertTrue(t2.getAntecedents().contains(t1));
        //vérifier que t2 est bien un antécédent de t3
        //assertTrue(t3.getAntecedents().contains(t2));
    }

}
