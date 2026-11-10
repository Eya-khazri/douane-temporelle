package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Ecran 1: el formulaire
public class MainActivity extends AppCompatActivity {

    private EditText etNom;
    private EditText etNaissance;
    private EditText etDestination;
    private RadioGroup rgMotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nrabtou el classe bel design activity_main.xml
        setContentView(R.layout.activity_main);

        // nl9aw kol element b el ID mte3ou
        etNom = findViewById(R.id.et_nom_ea);
        etNaissance = findViewById(R.id.et_naissance_ea);
        etDestination = findViewById(R.id.et_destination_ea);
        rgMotif = findViewById(R.id.rg_motif_ea);
        Button btnInspecter = findViewById(R.id.btn_inspecter_ea);

        // ki yenzel 3al bouton -> nlansiw inspecterProfil()
        btnInspecter.setOnClickListener(v -> inspecterProfil());
    }

    // tjib el donnees, tthabet fihom, w t7el el écran 2
    private void inspecterProfil() {
        // na9raw el ktiba mel champs
        String nom = etNom.getText().toString().trim();
        String naissanceTexte = etNaissance.getText().toString().trim();
        String destinationTexte = etDestination.getText().toString().trim();

        // ken champ fer8 -> nwarriw erreur w nwa9fou
        if (TextUtils.isEmpty(nom)) {
            etNom.setError(getString(R.string.err_nom));
            return;
        }
        if (TextUtils.isEmpty(naissanceTexte)) {
            etNaissance.setError(getString(R.string.err_naissance));
            return;
        }
        if (TextUtils.isEmpty(destinationTexte)) {
            etDestination.setError(getString(R.string.err_destination));
            return;
        }

        // -1 ma3neha 7atta motif mouch m5tar
        int idMotifChoisi = rgMotif.getCheckedRadioButtonId();
        if (idMotifChoisi == -1) {
            Toast.makeText(this, R.string.err_motif, Toast.LENGTH_SHORT).show();
            return;
        }

        // n7awlou el ktiba l ar9am
        int anneeNaissance = Integer.parseInt(naissanceTexte);
        int anneeDestination = Integer.parseInt(destinationTexte);

        // na9raw el ktiba mta3 el motif li khtarneh
        RadioButton rbChoisi = findViewById(idMotifChoisi);
        String motif = rbChoisi.getText().toString();

        // Intent explicite: men écran 1 l écran 2
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // n7otou el donnees fel Intent (les cles mebniya b Cles.construireCle)
        intent.putExtra(Cles.construireCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR), nom);
        intent.putExtra(Cles.construireCle(Cles.MATRICULE, Cles.ANNEE_NAISSANCE), anneeNaissance);
        intent.putExtra(Cles.construireCle(Cles.MATRICULE, Cles.ANNEE_DESTINATION), anneeDestination);
        intent.putExtra(Cles.construireCle(Cles.MATRICULE, Cles.MOTIF), motif);

        // n7ellou el écran 2
        startActivity(intent);
    }
}