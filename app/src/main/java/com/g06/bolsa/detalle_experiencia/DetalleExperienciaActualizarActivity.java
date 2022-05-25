package com.g06.bolsa.detalle_experiencia;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlDBLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;

public class DetalleExperienciaActualizarActivity extends Activity {
    ControlDBLJ16001 helper;
    TextView candidatoId;
    TextView id;
    TextView lugar;
    TextView puesto;
    TextView tiempo;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_experiencia_actualizar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlDBLJ16001(this);

        // Referencia a los widgets de la interfaz.
        id = findViewById(R.id.editIdDetalleExperiencia);
        candidatoId = findViewById(R.id.edit_detalle_experiencia_idcandidato);
        lugar = findViewById(R.id.editLugar);
        puesto = findViewById(R.id.editPuesto);
        tiempo = findViewById(R.id.editTiempo);
    }

    public void actualizar(View v) {
        DetalleExperiencia de = new DetalleExperiencia();

        de.setId(id.getText().toString());
        de.setIdCandidato(candidatoId.getText().toString());
        de.setLugarExperiencia(lugar.getText().toString());
        de.setPuestoExperiencia(puesto.getText().toString());
        de.setTiempoExperiencia(tiempo.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(de);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        lugar.setText("");
        puesto.setText("");
        tiempo.setText("");
    }
}