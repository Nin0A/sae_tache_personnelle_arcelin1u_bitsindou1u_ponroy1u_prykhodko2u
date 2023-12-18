package MVC;

//Interface Sujet
public interface Sujet {

    /**
     * Méhtode enregistrerObservateur qui enregistrera les changements de l'observateur
     * observé
     * @param o observateur observé
     */
    public void enregistrerObservateur(Observateur o);

    /**
     * Méhtode supprimerObservateur qui supprimera les changements de l'observateur
     * observé
     * @param o observateur observé
     */
    public void supprimerObservateur(Observateur o);

    /**
     * Méhtode notifierObservateur qui mettra à jour les changements de l'observateur
     * observé
     * @param o observateur observé
     */
    public void notifierObservateur(Observateur o);
}
