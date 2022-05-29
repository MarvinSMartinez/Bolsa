package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.g06.bolsa.clases_auxiliares.Puesto;
import com.g06.bolsa.clases_auxiliares.Evaluacion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EvaluacionInsertarActivity extends Activity {

    ControlBDCD17008 helper;
    EditText editIdEvaluacion;
    EditText editIdAplicacion;
    EditText editTipo;
    EditText editFecha;
    EditText editEstado;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_insertar);
        helper = new ControlBDCD17008(this);
        editIdEvaluacion = (EditText) findViewById(R.id.editIdEvaluacion);
        editIdAplicacion = (EditText) findViewById(R.id.editIdApp);
        editTipo = (EditText) findViewById(R.id.editTipoEva);
        editFecha = (EditText) findViewById(R.id.editFechaEva);
        editEstado = (EditText) findViewById(R.id.editEstadoEva);
    }

    public void insertarEvaluacion(View v) {
        String idEva=editIdEvaluacion.getText().toString();
        String idApp=editIdAplicacion.getText().toString();
        String tipo = editTipo.getText().toString();
        String fecha = editFecha.getText().toString();
        String estado = editEstado.getText().toString();

        String regInsertados;
        Evaluacion eva = new Evaluacion();
        eva.setId(idEva);
        eva.setIdAplicacion(idApp);
        eva.setTipo(tipo);
        eva.setFecha(fecha);
        helper.abrir();
        regInsertados=helper.insertarEvaluacion(eva);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdEvaluacion.setText("");
        editIdAplicacion.setText("");
        editTipo.setText("");
        editFecha.setText("");
        editEstado.setText("");
    }
}