package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

// Ecran 3: el verdict (approuvé wala refusé)
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // nl9aw el elements b el IDs mte3hom
        LinearLayout layoutRacine = findViewById(R.id.layout_verdict_ea);
        TextView tvIcone = findViewById(R.id.tv_verdict_icone_ea);
        TextView tvTitre = findViewById(R.id.tv_verdict_titre_ea);
        TextView tvMessage = findViewById(R.id.tv_verdict_message_ea);
        Button btnNouveau = findViewById(R.id.btn_nouveau_voyageur_ea);

        // 1) na9raw el verdict w el esm mel Intent (b nafs les cles)
        Intent intentRecu = getIntent();
        boolean approuve = intentRecu.getBooleanExtra(Cles.construireCle(Cles.MATRICULE, Cles.VERDICT), false);
        String nom = intentRecu.getStringExtra(Cles.construireCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR));

        // 2) nbaddlou el fond w el message 7asb el verdict
        if (approuve) {
            // approuvé: a5dhar
            layoutRacine.setBackgroundColor(ContextCompat.getColor(this, R.color.vert_approuve));
            tvIcone.setText("✅");
            tvTitre.setText(R.string.titre_approuve);
            tvMessage.setText(getString(R.string.msg_approuve, nom));
        } else {
            // refusé: a7mer
            layoutRacine.setBackgroundColor(ContextCompat.getColor(this, R.color.rouge_refuse));
            tvIcone.setText("❌");
            tvTitre.setText(R.string.titre_refuse);
            tvMessage.setText(getString(R.string.msg_refuse, nom));
        }

        // 3) "Nouveau voyageur": narj3ou lel écran 1 w nsakkrou el écrans elli 9bal
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            // CLEAR_TOP: ysakker écran 2 w 3, w ya3mel écran 1 jdid (fer8)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish(); // nsakkrou hedha zeda
        });
    }
}