package com.example.douanetemporelle;

/*
 * Classe Cles: hna n7otou les cles mta3 putExtra.
 * Kol cle tebda b e5er 4 ar9am mel carte etudiant.
 * Ma nektbouch el cle kemla bel yed, nebniwha b methode statique.
 */
public final class Cles {

    // e5er 4 ar9am mel carte etudiant (baddel 1234 bel ra9m el s7i7)
    public static final String MATRICULE = "1234";

    // el jez2 elli yji ba3d el matricule fel cle
    public static final String NOM_VOYAGEUR = "NOM_VOYAGEUR";
    public static final String ANNEE_NAISSANCE = "ANNEE_NAISSANCE";
    public static final String ANNEE_DESTINATION = "ANNEE_DESTINATION";
    public static final String MOTIF = "MOTIF";
    public static final String VERDICT = "VERDICT";

    // 7atta 7ad ma ya3mel "new Cles()"
    private Cles() { }

    // methode statique tebni el cle: "1234" + "_" + "NOM_VOYAGEUR" = "1234_NOM_VOYAGEUR"
    public static String construireCle(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }
}