package com.example.douanetemporelle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
// Ecran 2: el passeport (scanner mta3 el paradoxe)
public class VerificationActivity extends AppCompatActivity {
    // nesta79ou el esm ba3d bech nab3thouh lel écran 3
    private String nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
// nl9aw el elements b el IDs mte3hom
        TextView tvNom = findViewById(R.id.tv_passeport_nom_ea);
        TextView tvAge = findViewById(R.id.tv_passeport_age_ea);
        TextView tvAnnees = findViewById(R.id.tv_passeport_annees_ea);
        TextView tvMotif = findViewById(R.id.tv_passeport_motif_ea);
        TextView tvAlerte = findViewById(R.id.tv_passeport_alerte_ea);
        Button btnApprouver = findViewById(R.id.btn_approuver_ea);
        Button btnRefuser = findViewById(R.id.btn_refuser_ea);
// 1) na9raw el donnees elli jew mel écran 1 (b nafs les cles)
        Intent intentRecu = getIntent();
        nom = intentRecu.getStringExtra(Cles.construireCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR));
        int anneeNaissance = intentRecu.getIntExtra(Cles.construireCle(Cles.MATRICULE, Cles.ANNEE_NAISSANCE), 0);
        int anneeDestination = intentRecu.getIntExtra(Cles.construireCle(Cles.MATRICULE, Cles.ANNEE_DESTINATION), 0);
        String motif = intentRecu.getStringExtra(Cles.construireCle(Cles.MATRICULE, Cles.MOTIF));
// 2) n7esbou el 3omr el temporel
        int ageTemporel = anneeDestination - anneeNaissance;
// 3) n3abbiw el passeport
        tvNom.setText(getString(R.string.passeport_nom, nom));
        tvAge.setText(getString(R.string.passeport_age, ageTemporel));
        tvAnnees.setText(getString(R.string.passeport_annees, anneeNaissance, anneeDestination));
        tvMotif.setText(getString(R.string.passeport_motif, motif));
// 4) el 9a3da: "Modification du passé" W ra9m premier -> nsakkrou Approuver
        boolean estModification = getString(R.string.motif_modification).equals(motif);
        if (estModification && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false); // el bouton ma ya5demch
            btnApprouver.setAlpha(0.4f); // yban bahet
            tvAlerte.setVisibility(View.VISIBLE); // nwarriw el alerte
        }
// 5) kol bouton yab3ath verdict lel écran 3
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }
    // t7el el écran 3 w tab3athlou el verdict (true/false) w el esm
    private void envoyerVerdict(boolean approuve) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.construireCle(Cles.MATRICULE, Cles.VERDICT), approuve);
        intent.putExtra(Cles.construireCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR), nom);
        startActivity(intent);
    }
    // traja3 true ken n ra9m premier (ma yet9asamch ken 3la 1 w 3la rou7ou)
    public static boolean estPremier(int n) {
// 0, 1 w les negatifs mouch premiers
        if (n < 2) {
            return false;
        }
// njarbou kol 9asem men 2 7atta jadhr n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // l9ina 9asem -> mouch premier
            }
        }
        return true; // 7atta 9asem -> premier
    }
}