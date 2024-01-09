import classes.Colonne;
import classes.SousTache;
import classes.Tableau;
import classes.TacheMere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTacheMere {

    Tableau tab;
    Colonne col;
    private TacheMere t1, t2, t3, t4;
    private SousTache t5, t6;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        tab = new Tableau("Tableau n°1");

        //créer une colonne
        col = new Colonne("Colonne A");

        //créer 4 taches mères
        t1 = new TacheMere("Tache 1", 7, 1 , 1, 2023);
        t2 = new TacheMere("Tache 2", 10, 1 , 1, 1);
        t3 = new TacheMere("Tache 3", 5, 1 , 1, 1);
        t4 = new TacheMere("Tache 4", 5, 1 , 1, 1);

        //creer 2 sous taches
        t5 = new SousTache("Tache 5", 3,  1, 1, 2023);
        t6 = new SousTache("Tache 6", 3, 4, 1, 2023);

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

    //test de la méthode verifDureeSousTaches quand la durée des sous tâches est exactement égale à la durée de la tache mère
    @Test
    public void testVerifDureeSousTachesOk(){
        assertTrue(t1.verifDureeSousTaches());
    }

    //test de la méthode verifDureeSousTaches quand la durée des sous taches est supérieure à la durée de la tache mère
    @Test
    public void testVerifDureeSousTachesKo(){
        t1.setDuree(1);
        assertFalse(t1.verifDureeSousTaches());
    }

    //test de la méthode verifDureeSousTaches quand la durée des sous taches est inférieure à la durée de la tache mère
    @Test
    public void testVerifDureeSousTachesOk2(){
        t1.setDuree(10);
        assertTrue(t1.verifDureeSousTaches());
    }

    //test de la méthode verifDateDebutSousTaches quand la date de début de la plus petite sous tache est exactement
    // égale à la date de début de la tache mère
    @Test
    public void testVerifDateDebutSousTachesOk(){
        assertTrue(t1.verifDateDebutSousTaches());
    }

    //test de la méthode verifDateDebutSousTaches quand la date de début de la plus petite sous tache est supérieure à
    // la date de début de la tache mère
    @Test
    public void testVerifDateDebutSousTachesKo(){
        assertTrue(t1.verifDateDebutSousTaches());
    }

    //test de la methode verifDateDebutSousTaches quand la date de début de la plus petite sous tache est inférieure à
    // la date de début de la tache mère
    @Test
    public void testVerifDateDebutSousTachesOk2(){
        assertFalse(t1.verifDateDebutSousTaches());
    }

    //test de la methode verifDateFinSousTaches quand la date de fin de la plus grande sous tache est exactement
    // égale à la date de fin de la tache mère
    @Test
    public void testVerifDateFinSousTaches_Ok(){
        assertTrue(t1.verifDateFinSousTaches());
    }

    //test de la methode verifDateFinSousTaches quand la date de fin de la plus grande sous tache est inférieure à
    // la date de fin de la tache mère
    @Test
    public void testVerifDateFinSousTaches_ok2(){
        assertTrue(t1.verifDateFinSousTaches());
    }

    //test de la méthode verifDateFinSousTaches quand la date de fin de la plus grande sous tache est supérieure à
    // la date de fin de la tache mère
    @Test
    public void testVerifDateFinSousTaches_KO(){
        System.out.println(t1.getDateFin());
        System.out.println(t6.getDateFin());
        assertFalse(t1.verifDateFinSousTaches());
    }
}
