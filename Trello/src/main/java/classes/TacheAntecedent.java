package classes;

public class TacheAntecedent extends Tache{

    private Tache tache;

    TacheAntecedent(String nom, Tache t){
        super(nom);
        tache = t;
    }
}
