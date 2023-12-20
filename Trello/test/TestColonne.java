import classes.Tableau;
import classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
<<<<<<< HEAD
        t5 = new SousTache("Tache 5", 5, 1 , 1, 1);
        t6 = new SousTache("Tache 6", 5, 1 , 1, 1);
=======
        t5 = new SousTache("Tache 5", 5, t1);
        t6 = new SousTache("Tache 6", 5, t1);
>>>>>>> 73d393a59a4d0472fe25f00fba38a8038b467654

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
    public void test_supprimerAntecedent_tache_mere_avec_sous_taches() { //test 1

        //on vérifie que la tache 1 a bien 2 sous taches
        assertEquals(2, t1.getSousTaches().size());

        //on supprime la tache 1 de la colonne
        col.supprimerTache(t1);

        //on vérifie que la tache 1 n'est plus dans la liste de taches de la colonne
        assertEquals(false,col.getTaches().contains(t1)); //il reste 1 sous tache

        //on vérifie que les sous taches 5 et 6 de la tache 1 sont bien supprimées de la colonne
        assertEquals(0,t1.getSousTaches().size());
    }

    //teste la methode supprimerTache quand la tache mere à supprimer n'a pas de sous taches
    @Test
    public void test_supprimerTtache_mere_sans_sous_taches() { //test 2

        //on vérifie que la tache 4 n'a pas de sous taches
        assertEquals(0, t4.getSousTaches().size());

        //on supprime la tache 4 de la colonne
        col.supprimerTache(t4);

        //on vérifie que la tache 4 n'est plus dans la liste de taches de la colonne
        assertEquals(false,col.getTaches().contains(t4));
    }

    //teste la methode supprimerTache quand c'est une sous tache d'une tache mere qui est supprimée
    @Test
    public void test_supprimerTache_sous_tache() { //test 3

        //on vérifie que la tache 6 est bien une sous tache de la tache 1
        assertTrue(t1.getSousTaches().contains(t6));

        //on verifie que la tache 6 est bien dans la liste de taches de la colonne
        assertTrue(col.getTaches().contains(t6));

        //on supprime la tache 6 de la colonne
        col.supprimerTache(t6);

        //on verifie que la tache 6 est bien dans la liste de taches de la colonne
        assertFalse(col.getTaches().contains(t6));

        //on vérifie que la tache 6 n'est plus dans la liste de sous taches de la tache 1
        assertFalse(t1.getSousTaches().contains(t6));

        //on verifie que la tache 5 n'est plus dans la liste de antécédents de la tache 6
        assertFalse(t6.getAntecedent().contains(t5));
        col.afficher();
    }

    //teste la methode supprimerTache quand la sous tache à supprimer à des antecedents
    @Test
    public void test_supprimerTache_sous_tache_avec_antecedents() { //test 4

        //on supprime la tache 5 de la colonne
        col.supprimerTache(t5);

        //on vérifie que la tache 5 n'est plus dans la liste de taches de la colonne
        assertFalse(col.getTaches().contains(t5));

        //on vérifie que la tache 5 n'est plus dans la liste de sous taches de la tache 1
        assertFalse(t1.getSousTaches().contains(t5));

        //on vérifie que la tache 5 n'est plus dans la liste de antécédents de la tache 6
        assertEquals(0,t6.getAntecedent().size());
    }

    //teste la méhtode modifierNomDelaTache quand la tache à modifier est une tache mere
    @Test
    public void test_modifierNomDelaTache_tache_mere() { //test 5

        //on modifie le nom de la tache 1
        col.modifierNomDelaTache("Tache 1", "Tache 1 modifiée");

        //on vérifie que le nom de la tache 1 a bien été modifié
        assertEquals("Tache 1 modifiée", t1.getNom());
    }

    //teste la méhtode modifierNomDelaTache quand la tache à modifier est une sous tache
    @Test
    public void test_modifierNomDelaTache_sous_tache() { //test 6

        //on modifie le nom de la sous tache 5
        col.modifierNomDelaTache("Tache 5", "Tache 5 modifiée");

        //on vérifie que le nom de la sous tache 5 a bien été modifié
        assertEquals("Tache 5 modifiée", t5.getNom());
    }

    //teste quand la tache à modifier n'est pas dans la liste de taches de la colonne
    @Test
    public void test_modifierNomDelaTache_tache_pas_dans_liste() { //test 7

        //on vérifie que la tache 7 genere une exception et donc qu'elle ne peut pas être modifiée
        assertThrows(IllegalArgumentException.class, () -> {
            col.modifierNomDelaTache("Tache 7", "Tache 7 modifiée");
        });
    }

    //teste quand la tache à modifier est null
    @Test
    public void test_modifierNomDelaTache_tache_null() { //test 8

        //on vérifie que la tache null genere une exception et donc qu'elle ne peut pas être modifiée
        assertThrows(IllegalArgumentException.class, () -> {
            col.modifierNomDelaTache(null, "Tache 7 modifiée");
        });
    }

    //teste la méthode toString quand la colonne existe
    @Test
    public void test_toString() { //test 9

        //on vérifie que la méthode toString retourne bien le nom de la colonne
        assertEquals("Colonne A", col.toString());
    }

    //teste la méthode getTaches quand la colonne existe
    @Test
    public void test_getTaches() { //test 10

        //on vérifie que la méthode getTaches retourne bien la liste de taches de la colonne
        assertEquals(6, col.getTaches().size());
    }

    //teste la méthode getTaches quand la colonne ets vide
    @Test
    public void test_getTaches_vide() { //test 11

        //on vérifie que la méthode getTaches retourne bien la liste de taches de la colonne
        assertEquals(0, new Colonne("Colonne vide").getTaches().size());
    }

    //teste la methode afficher quand la colonne existe
    @Test
    public void test_afficher() { //test 12

        //on vérifie que la méthode afficher affiche bien toutes les taches de la colonne
        col.afficher();
    }

    //teste la methode afficher quand la colonne est vide
    @Test
    public void test_afficher_vide() { //test 13

        //on vérifie que la méthode afficher affiche bien toutes les taches de la colonne
        new Colonne("Colonne vide").afficher();
    }

    //teste la methode ajouterTache quand la tache à ajouter est une tache mere
    @Test
    public void test_ajouterTache_tache_mere() { //test 14

        //on crée une nouvelle tache mere
        TacheMere tache = new TacheMere("Tache 7", 5);

        //on ajoute la tache mere à la colonne
        col.ajouterTache(tache);

        //on vérifie que la tache mere a bien été ajoutée à la colonne
        assertTrue(col.getTaches().contains(tache));
    }

    //teste la methode ajouterTache quand la tache à ajouter est une sous tache
    @Test
    public void test_ajouterTache_sous_tache() { //test 15

        //on crée une nouvelle sous tache
        SousTache tache = new SousTache("Tache 7", 5, t1);

        //on ajoute la sous tache à la colonne
        col.ajouterTache(tache);

        //on vérifie que la sous tache a bien été ajoutée à la colonne
        assertTrue(col.getTaches().contains(tache));
    }




}
