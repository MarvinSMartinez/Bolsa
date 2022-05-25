package com.g06.bolsa.dato_estudio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlDBLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;

public class DatoEstudioConsultarActivity extends Activity {

    ControlDBLJ16001 helper;
    EditText estudioNivel;
    EditText candidatoId;
    EditText institucionId;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_estudio_consultar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlDBLJ16001(this);

        // Referencia a los widgets de la interfaz.
        estudioNivel = findViewById(R.id.editEstudiNivel);
        candidatoId = findViewById(R.id.editCandidatoId);
        institucionId = findViewById(R.id.editInstitucionId);

    }

    public void consultar(View v) {
        helper.abrir();
        DatoEstudio de = helper.consultarDatoEstudio(estudioNivel.getText().toString());
        helper.cerrar();

        if (de == null)
            Toast.makeText(this, "Dato de estudio no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            candidatoId.setText(String.valueOf(de.getIdCandidato()));
            institucionId.setText(String.valueOf(de.getIdInstitucion()));
        }
    }

    public void limpiarTexto(View v) {
        estudioNivel.setText("");
        candidatoId.setText("");
        institucionId.setText("");
    }
}