package com.g06.bolsa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Evaluacion;

public class EvaluacionActualizarActivity extends Activity {

    ControlBDCD17008 helper;
    EditText editIdEvaluacion;
    EditText editIdApp;
    EditText editTipoEva;
    EditText editFechaEva;
    EditText editEstadoEva;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_actualizar);
        helper = new ControlBDCD17008(this);
        editIdEvaluacion = (EditText) findViewById(R.id.editIdEvaluacion);
        editIdApp = (EditText) findViewById(R.id.editIdApp);
        editTipoEva = (EditText) findViewById(R.id.editTipoEva);
        editFechaEva = (EditText) findViewById(R.id.editFechaEva);
        editEstadoEva = (EditText) findViewById(R.id.editEstadoEva);
    }

    public void actualizarEvaluacion(View v) {
        Evaluacion eva = new Evaluacion();
        eva.setId(editIdEvaluacion.getText().toString());
        eva.setIdAplicacion(editIdApp.getText().toString());
        eva.setTipo(editTipoEva.getText().toString());
        eva.setFecha(editFechaEva.getText().toString());
        eva.setEstado(editEstadoEva.getText().toString());
        helper.abrir();
        String estado = helper.actualizarEvaluacion(eva);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdEvaluacion.setText("");
        editIdApp.setText("");
        editTipoEva.setText("");
        editFechaEva.setText("");
        editEstadoEva.setText("");
    }

}