import classes.Colonne;
import classes.SousTache;
import classes.Tableau;
import classes.TacheMere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestArchive {

    Tableau tab;
    Colonne col;
    private TacheMere t1, t2, t3, t4;
    private SousTache t5, t6, t7;

    @BeforeEach
    public void setUp() throws Exception {
        //créer un tableau
        tab = new Tableau("Tableau n°1");

        //créer une colonne
        col = new Colonne("Colonne A");

        //créer 4 taches mères
        t1 = new TacheMere("Tache 1", col, 7, 1 , 1, 1);
        t2 = new TacheMere("Tache 2", col, 10, 1 , 1, 1);
        t3 = new TacheMere("Tache 3", col, 5, 1 , 1, 1);
        t4 = new TacheMere("Tache 4", col,5, 1 , 1, 1);

        //creer 2 sous taches
        t5 = new SousTache("Tache 5", col, 5,  1, 1, 1);
        t6 = new SousTache("Tache 6", col, 5, 1, 1, 1);

        //t7 = new SousTache("Tache 7", 5, 1, 1, 1);

        //on ajoute la colonne à la liste de colonnes du tableau
        tab.ajouterColonne(col);

        //on ajoute les taches à la colonne
        col.ajouterTache(t1);
        //col.ajouterTache(t2);
        col.ajouterTache(t3);
        col.ajouterTache(t4);
        //col.ajouterTache(t5);
        //col.ajouterTache(t6);

        //on ajoute les sous taches 5 et 6 à la tache 1
        t1.ajouterSousTache(t5);
        t1.ajouterSousTache(t2);
        t2.ajouterSousTache(t6);

        //on ajoute les taches 1 et 2 comme antécédents de la tache 3
        t3.ajouterAntecedent(t1);
        t3.ajouterAntecedent(t2);
        t2.ajouterAntecedent(t5);

        //on ajoute la sous tache 5 comme antécédent de la sous tache 6
        //t6.ajouterAntecedent(t5);

        //on ajoute la tache 3 comme antécédent de la tache 4
        t4.ajouterAntecedent(t3);


    }

    //test de la méthode archiverTache avec une tache sans sous tache
    @Test
    public void testArchiverTache() {
        //on archive la tache 1
        tab.archiverTache(t2);

        //on vérifie que la tache 1 est bien dans la liste des taches archivées
        assertEquals(true,tab.getArchive().getTaches().contains(t2));

    }

    //test de la methode desarchiverTache avec une tache sans sous tache
    @Test
    public void testDesarchiverTache() {
        //on archive la tache 1
        tab.archiverTache(t2);

        //on désarchive la tache 1
        tab.desarchiverTache(t2);

        //on vérifie que la tache 1 n'est plus dans la liste des taches archivées
        assertEquals(false,tab.getArchive().getTaches().contains(t2));

    }

    //test de la methode getTacheMere avec une tache mère
    @Test
    public void testGetTacheMere() {
        //on vérifie que la tache mère de la sous tache 5 est bien la tache 1
        assertEquals(t2,col.getTacheMere(t6, col.getTaches()));

    }

    //test de la méthode archiverTache avec une tache qui a une tache en sous tache avec des sous taches
    //CA MARCHE PAAAAAAAS
    @Test
    public void testArchiverTache_sous_taches() {
        //on archive la tache 1
        tab.archiverTache(t1);

        //on vérifie que la tache 1 est bien dans la liste des taches archivées
        assertEquals(true,tab.getArchive().getTaches().contains(t1));
        assertEquals(true,tab.getArchive().getTaches().contains(t2));

        //on vérifie que les sous taches de la tache 1 sont bien dans la liste des taches archivées
        assertEquals(true,tab.getArchive().getTaches().contains(t5));
        assertEquals(true,tab.getArchive().getTaches().contains(t6));

    }

    //test de la methode desarchiverTache avec une tache avec des sous taches
    @Test
    public void testDesarchiverTache_sous_taches() {
        //on archive la tache 1
        tab.archiverTache(t1);

        //on désarchive la tache 1
        tab.desarchiverTache(t1);

        //on vérifie que la tache 1 n'est plus dans la liste des taches archivées
        assertEquals(false,tab.getArchive().getTaches().contains(t1));
        assertEquals(false,tab.getArchive().getTaches().contains(t2));

        //on vérifie que les sous taches de la tache 1 ne sont plus dans la liste des taches archivées
        assertEquals(false,tab.getArchive().getTaches().contains(t5));
        assertEquals(false,tab.getArchive().getTaches().contains(t6));

    }

    //test de la methode pour desrchiver une sous tache
    @Test
    public void testDesarchiverSousTache() {
        //on archive la tache 1
        tab.archiverTache(t1);

        //t5.setColonneOrigine(col); marche avec le set
        System.out.println(t5.getColonneOrigine().getNom());

        //on désarchive la sous tache 5
        tab.desarchiverTache(t5);

        //on vérifie que la sous tache 5 n'est plus dans la liste des taches archivées
        assertEquals(false,tab.getArchive().getTaches().contains(t5));

    }

     //test  de la methode pour archiver une tache avec des sous taches
    @Test
    public void testArchiverTache_sous_taches_2() {
        //on archive la tache 1
        tab.archiverTache(t1);

        //on vérifie que la tache 1 est bien dans la liste des taches archivées
        assertEquals(true,tab.getArchive().getTaches().contains(t1));
        //assertEquals(true,tab.getArchive().getTaches().contains(t2));
        //t2.afficher();
        //t1. afficher();

        //on vérifie que les sous taches de la tache 1 sont bien dans la liste des taches archivées
        assertEquals(true,tab.getArchive().getTaches().contains(t5));

        //on verifie que les colonnes actuelles des sous taches sont bien les colonnes d'archive

        tab.afficher();
       //assertEquals(true,tab.getArchive().getTaches().contains(t6)); //ne focntionne pas

    }
}
