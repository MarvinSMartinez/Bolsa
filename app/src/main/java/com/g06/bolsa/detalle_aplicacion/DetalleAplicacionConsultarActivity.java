package com.g06.bolsa.detalle_aplicacion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DetalleAplicacion;

public class DetalleAplicacionConsultarActivity extends Activity {
    ControlBDLJ16001 DBHelper;
    TextView candidatoId;
    TextView id;
    TextView estado;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aplicacion_consultar);

        DBHelper = new ControlBDLJ16001(this);

        id = findViewById(R.id.editIdAplicacion);
        candidatoId = findViewById(R.id.editCandidatoId);
        estado = findViewById(R.id.editEstado);

    }

    public void consultar(View v) {
        DBHelper.abrir();
        DetalleAplicacion de = DBHelper.consultarDetalleAplicacion(id.getText().toString());
        DBHelper.cerrar();
        if(de == null)
            Toast.makeText(this, "Aplicación no registrada",
                    Toast.LENGTH_LONG).show();
        else{
            candidatoId.setText(String.valueOf(de.getIdCandidato()));
            estado.setText(String.valueOf(de.getEstado()));
        }
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        estado.setText("");
    }
}