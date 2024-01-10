import classes.Tableau;
import classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTableau {

    //attributs
    Tableau tab;
    Colonne col,col2;
    private TacheMere t1, t2, t3, t4;
    private Tache t5, t6;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        tab = new Tableau("Tableau n°1");

        //créer 2 colonnes
        col = new Colonne("Colonne A");
        col2 = new Colonne("Colonne B");


        //créer 4 taches mères
        t1 = new TacheMere("Tache 1", col, 7, 1 , 1, 1);
        t2 = new TacheMere("Tache 2", col,10, 1 , 1, 1);
        t3 = new TacheMere("Tache 3", col,5, 1 , 1, 1);
        t4 = new TacheMere("Tache 4", col,5, 1 , 1, 1);

        //creer 2 sous taches
        t5 = new SousTache("Tache 5", col,5,  1, 1, 1);
        t6 = new SousTache("Tache 6", col,5,  1, 1, 1);

        //on ajoute les colonnes à la liste de colonnes du tableau
        tab.ajouterColonne(col);
        tab.ajouterColonne(col2);

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
    //teste le constructeur de la classe Tableau
    @Test
    public void testConstructeur(){ //test 1
        //on vérifie que le nom du tableau est bien celui qu'on est crée
        assertEquals("Tableau n°1", tab.getNom());
    }

    //teste la methode ajouterColonne quand la colonne n'existe pas encore dans le tableau
    @Test
    public void testAjouterColonne_inexistante(){ //test 2

        //on crée une nouvelle colonne
        Colonne col2 = new Colonne("Colonne B");

        //on ajoute la colonne au tableau
        tab.ajouterColonne(col2);

        //on vérifie que la colonne a bien été ajoutée
        assertTrue(tab.getColonnes().contains(col2));
    }

    //teste la methode ajouterColonne quand la colonne existe déjà dans le tableau
    @Test
    public void testAjouterColonne_existante(){ //test 3

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.ajouterColonne(col));
    }

    //teste la methode ajouterColonne quand la colonne est null
    @Test
    public void testAjouterColonne_null(){ //test 4

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.ajouterColonne(null));
    }

    //teste la methode supprimerColonne quand la colonne existe dans le tableau
    @Test
    public void testSupprimerColonne_existante(){ //test 5

        //on supprime la colonne du tableau
        tab.supprimerColonne(col);

        //on vérifie que la colonne a bien été supprimée
        assertFalse(tab.getColonnes().contains(col));
    }

    //teste la methode supprimerColonne quand la colonne n'existe pas dans le tableau
    @Test
    public void testSupprimerColonne_inexistante(){ //test 6

        //on crée une nouvelle colonne
        Colonne col2 = new Colonne("Colonne B");

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.supprimerColonne(col2));
    }

    //teste la methode supprimerColonne quand la colonne est null
    @Test
    public void testSupprimerColonne_null(){ //test 7

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.supprimerColonne(null));
    }

    //teste la methode deplacerTache quand la tache existe dans la colonne de départ et que la colonne d'arrivée existe
    @Test
    public void testDeplacerTache_existante(){ //test 8

        //on déplace la tache 1 de la colonne A à la colonne B
        tab.deplacerTache(t1,col2);

        //on vérifie que la tache a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t1));

        //on vérifie que la tache n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t1));
    }

    //teste la methode deplacerTache quand la tache n'existe pas dans la colonne de départ
    @Test
    public void testDeplacerTache_tache_inexistante(){ //test 9

        //on crée une nouvelle tache
        Tache t7 = new TacheMere("Tache 7",col, 5, 1, 1, 2023);

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t7, col2));
    }

    //teste la methode deplacerTache quand la colonne de départ n'existe pas
    @Test
    public void testDeplacerTache_colonne_depart_inexistante(){ //test 10

        //on crée une nouvelle colonne
        Colonne col3 = new Colonne("Colonne C");

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t1, col2));
    }

    //teste la methode deplacerTache quand la colonne d'arrivée n'existe pas
    @Test
    public void testDeplacerTache_colonne_arrivee_inexistante(){ //test 11

        //on crée une nouvelle colonne
        Colonne col3 = new Colonne("Colonne C");

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t1,col3));
    }

    //teste la methode deplacerTache quand la tache existe dans la colonne d'arrivée
    @Test
    public void testDeplacerTache_tache_existe_colonne_arrivee(){ //test 12

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t1,col));
    }

    //teste la methode deplacerTache quand la tache est null
    @Test
    public void testDeplacerTache_tache_null(){ //test 13

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(null,col2));
    }

    //teste la methode deplacerTache quand la colonne de départ est null
    @Test
    public void testDeplacerTache_colonne_depart_null(){ //test 14

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t1, null));
    }

    //teste la methode deplacerTache quand la colonne d'arrivée est null
    @Test
    public void testDeplacerTache_colonne_arrivee_null(){ //test 15

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t1, null));
    }

    //teste la methode deplacerTache quand on deplace une sous tache
    @Test
    public void testDeplacerTache_sous_tache(){ //test 16

        //on verifie que l'excpetion a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.deplacerTache(t5, col2));
    }

    //teste la methode deplacerTache quand on deplace la tache mere avec ses sous taches
    @Test
    public void testDeplacerTache_tache_mere_avec_sous_taches(){ //test 16

        //on déplace la tache 1 de la colonne A à la colonne B
        tab.deplacerTache(t1, col2);

        //on vérifie que la tache 1 a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t1));

        //on vérifie que la tache 1 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t1));

        //on vérifie que la sous tache 5 a bien été déplacée dans la colonne B
        //assertTrue(col2.getTaches().contains(t5));

        //on vérifie que la sous tache 5 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t5));

        //on vérifie que la sous tache 6 a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t6));

        //on vérifie que la sous tache 6 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t6));
    }

    //teste la methode deplacerTache quand on deplace une tache mere avec des antecedents
    /*@Test
    public void testDeplacerTache_tache_mere_avec_antecedents(){ //test 17 A FINIR

        //on déplace la tache 3 de la colonne A à la colonne B
        tab.deplacerTache(t3, col, col2);

        //on vérifie que la tache 3 a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t3));

        //on vérifie que la tache 3 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t3));

        //on vérifie que la tache 1 a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t1));

        //on vérifie que la tache 1 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t1));

        //on vérifie que la tache 2 a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t2));

        //on vérifie que la tache 2 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t2));

        //on vérifie que la tache 4 a bien été déplacée dans la colonne B
        assertTrue(col2.getTaches().contains(t4));

        //on vérifie que la tache 4 n'est plus dans la colonne A
        assertFalse(col.getTaches().contains(t4));
    }*/

    //teste la methode archiverTache quand la tache existe dans la colonne et que c'est une tache mere avec sous tache
    @Test
    public void testArchiverTache_existante(){ //test 17

        //on archive la tache 1
        tab.archiverTache(t1);

        //on vérifie que la tache a bien été archivée
        assertTrue(tab.getArchive().getTaches().contains(t1));

        //on vérifie que la tache n'est plus dans la colonne
        assertFalse(col.getTaches().contains(t1));
    }

    //teste la methode archiverTache quand la tache n'existe pas dans la colonne
    @Test
    public void testArchiverTache_inexistante(){ //test 18

        //on crée une nouvelle tache
        Tache t7 = new TacheMere("Tache 7",col, 5, 1, 1, 2023);

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.archiverTache(t7));
    }

    //teste la methode archiverTache quand la colonne est null
    @Test
    public void testArchiverTache_colonne_null(){ //test 19

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.archiverTache(t1));
    }

    //teste la methode archiverTache quand la tache est null
    @Test
    public void testArchiverTache_tache_null(){ //test 20

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.archiverTache(null));
    }

    //teste la methode archiverTache quand la tache est une sous tache
    @Test
    public void testArchiverTache_sous_tache(){ //test 21

        //on vérifie que l'exception a bien été levée
        assertThrows(IllegalArgumentException.class, () -> tab.archiverTache(t5));
    }

    //teste la methode archiverTache quand on veut archiver une tache mere avec des sous taches
    @Test
    public void testArchiverTache_tache_mere_avec_sous_taches(){ //test 22

        //on archive la tache 1
        tab.archiverTache(t1);

        //on vérifie que la tache a bien été archivée
        assertTrue(tab.getArchive().getTaches().contains(t1));

        //on vérifie que la tache n'est plus dans la colonne
        assertFalse(col.getTaches().contains(t1));

        //on vérifie que la sous tache 5 a bien été archivée
        assertTrue(tab.getArchive().getTaches().contains(t5));

        //on vérifie que la sous tache 5 n'est plus dans la colonne
        assertFalse(col.getTaches().contains(t5));

        //on vérifie que la sous tache 6 a bien été archivée
        assertTrue(tab.getArchive().getTaches().contains(t6));

        //on vérifie que la sous tache 6 n'est plus dans la colonne
        assertFalse(col.getTaches().contains(t6));
    }

    //teste la methode archiverTache quand on deplace une tache mere avec des antecedents

}

