package fr.eni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {
    private TextView etPrixParDefaut;
    private Switch swTriActif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        etPrixParDefaut = findViewById(R.id.config_et_prix_defaut);
        swTriActif = findViewById(R.id.config_switch_tri_par_prix);

        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        boolean triActif = sp.getBoolean("config_tri", false);
        String prixDefaut = sp.getString("prix_defaut",null);

        //Log.i("toto", "ConfigurationActivity - onCreate - triActif : " + triActif);
        //Log.i("toto", "ConfigurationActivity - onCreate - prixDefaut : " + prixDefaut);
        swTriActif.setChecked(triActif);
        etPrixParDefaut.setText(prixDefaut);

    }

    public void saveSharedPref(View view) {
        // Recup element, puis accéder à leur valeur
        String prixDefaut = etPrixParDefaut.getText().toString();
        boolean triActif = swTriActif.isChecked();
        Log.i("toto", "ConfigurationActivity - saveSharedPref - triActif : " + triActif);

        // Acces à l'editor de sharedpreferences
        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("prix_defaut", prixDefaut);
        editor.putBoolean("config_tri", triActif);
        editor.apply();
        Toast.makeText(this, "Configuration sauvegardée", Toast.LENGTH_SHORT).show();
        finish(); // Correspond à un clic sur bouton retour
    }
}