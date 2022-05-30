package com.g06.bolsa.detalle_aplicacion;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DetalleAplicacion;

public class DetalleAplicacionActualizarActivity extends Activity {
    ControlBDLJ16001 DBHelper;
    TextView candidatoId;
    TextView id;
    TextView estado;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aplicacion_actualizar);

        // Instancia de la clase que controla la base de datos.
        DBHelper = new ControlBDLJ16001(this);

        // Referencia a los widgets de la interfaz.
        id = findViewById(R.id.editIdAplicacion);
        candidatoId = findViewById(R.id.editCandidatoId);
        estado = findViewById(R.id.editEstado);
    }

    public void actualizar(View v) {
        DetalleAplicacion de = new DetalleAplicacion();

        de.setId(id.getText().toString());
        de.setIdCandidato(candidatoId.getText().toString());
        de.setEstado(estado.getText().toString());

        DBHelper.abrir();
        String estado = DBHelper.actualizar(de);
        DBHelper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        estado.setText("");
    }
}