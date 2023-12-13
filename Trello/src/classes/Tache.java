package classes;

public class Tache extends Composant {
    private int status;
    private double duree;

    Tache(String desc, int duree) {
        super(desc);
        this.duree = duree;
        this.status = status;
    }

    public void archiver() {
        //TODO
    }

}
