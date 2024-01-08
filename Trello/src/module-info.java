module classes {
// ajoute les modules necessaires : ici javaFX
    requires javafx . controls ;
    requires javafx . base ;
// ouvre le package courant a l’exterieur
    exports classes ;
    exports test ;
}