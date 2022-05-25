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

public class DetalleExperienciaConsultarActivity extends Activity {

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
        setContentView(R.layout.activity_detalle_experiencia_consultar);
        helper = new ControlDBLJ16001(this);

        id = findViewById(R.id.editIdDetalleExperiencia);
        candidatoId = findViewById(R.id.edit_detalle_experiencia_idcandidato);
        lugar = findViewById(R.id.editLugar);
        puesto = findViewById(R.id.editPuesto);
        tiempo = findViewById(R.id.editTiempo);
    }

    public void consultar(View v) {
        helper.abrir();
        DetalleExperiencia de = helper.consultarDetalleExperiencia(id.getText().toString());
        helper.cerrar();
        if(de == null)
            Toast.makeText(this, "Experiencia no registrada",
                    Toast.LENGTH_LONG).show();
        else{
            candidatoId.setText(String.valueOf(de.getIdCandidato()));
            lugar.setText(String.valueOf(de.getLugarExperiencia()));
            puesto.setText(String.valueOf(de.getPuestoExperiencia()));
            tiempo.setText(String.valueOf(de.getTiempoExperiencia()));
        }
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        lugar.setText("");
        puesto.setText("");
        tiempo.setText("");
    }
}