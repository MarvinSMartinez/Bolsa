package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Evaluacion;


public class EvaluacionConsultarActivity extends Activity {

    ControlBDCD17008 helper;
    EditText editIdEvaluacion;
    EditText editIdApp;
    EditText editTipoEva;
    EditText editFechaEva;
    EditText editEstadoEva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_consultar);
        helper = new ControlBDCD17008(this);

        editIdEvaluacion = (EditText) findViewById(R.id.editIdEvaluacion);
        editIdApp = (EditText) findViewById(R.id.editIdApp);
        editTipoEva = (EditText) findViewById(R.id.editTipoEva);
        editFechaEva = (EditText) findViewById(R.id.editFechaEva);
        editEstadoEva = (EditText) findViewById(R.id.editEstadoEva);
    }

    public void consultarEvaluacion(View v) {
        helper.abrir();
        Evaluacion evaluacion =
                helper.consultarEvaluacion(editIdEvaluacion.getText().toString());
        helper.cerrar();
        if(evaluacion == null)
            Toast.makeText(this, "Evaluacion con Id " + editIdEvaluacion.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdEvaluacion.setText(evaluacion.getId());
            editIdApp.setText(evaluacion.getIdAplicacion());
            editTipoEva.setText(evaluacion.getTipo());
            editFechaEva.setText(evaluacion.getFecha());
            editEstadoEva.setText(evaluacion.getEstado());
        }

    }

    public void limpiarTexto(View v) {
        editIdEvaluacion.setText("");
        editIdApp.setText("");
        editTipoEva.setText("");
        editFechaEva.setText("");
        editEstadoEva.setText("");
    }
}