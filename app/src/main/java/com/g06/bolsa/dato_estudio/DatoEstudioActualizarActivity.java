package com.g06.bolsa.dato_estudio;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;


public class DatoEstudioActualizarActivity extends Activity {

    ControlBDLJ16001 helper;
    EditText estudioNivel;
    EditText candidatoId;
    EditText  institucionId;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_estudio_actualizar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlBDLJ16001(this);

        // Referencia a los widgets de la interfaz.
        estudioNivel = findViewById(R.id.editEstudiNivel);
        candidatoId = findViewById(R.id.editCandidatoId);
        institucionId = findViewById(R.id.editInstitucionId);

    }

    public void actualizar(View v) {
        DatoEstudio de = new DatoEstudio();

        de.setEstudioNivel(estudioNivel.getText().toString());
        de.setIdCandidato(candidatoId.getText().toString());
        de.setIdInstitucion(institucionId.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(de);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        estudioNivel.setText("");
        candidatoId.setText("");
        institucionId.setText("");
    }

}