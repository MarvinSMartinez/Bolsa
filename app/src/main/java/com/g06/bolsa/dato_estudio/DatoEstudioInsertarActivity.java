package com.g06.bolsa.dato_estudio;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import com.g06.bolsa.R;
import com.g06.bolsa.ControlDBLJ16001;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;

public class DatoEstudioInsertarActivity extends Activity {
    ControlDBLJ16001 DBHelper;
    EditText estudioNivel;
    EditText candidatoId;
    EditText  institucionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_estudio_insertar);
        DBHelper = new ControlDBLJ16001(this);

        estudioNivel = findViewById(R.id.editEstudiNivel);
        candidatoId = findViewById(R.id.editCandidatoId);
        institucionId = findViewById(R.id.editInstitucionId);
    }

    public void insertar(View v) {
        String sid = estudioNivel.getText().toString();
        String scandidatoId = candidatoId.getText().toString();
        String sinstitucionId = institucionId.getText().toString();

        String regInsertados;

        DatoEstudio de = new DatoEstudio();
        de.setEstudioNivel(sid);
        de.setIdCandidato(scandidatoId);
        de.setIdInstitucion(sinstitucionId);

        DBHelper.abrir();
        regInsertados = DBHelper.insertar(de);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        estudioNivel.setText("");
        candidatoId.setText("");
        institucionId.setText("");
    }
}