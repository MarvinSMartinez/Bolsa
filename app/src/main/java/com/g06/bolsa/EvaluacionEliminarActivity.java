package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.Evaluacion;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;
import com.g06.bolsa.clases_auxiliares.Puesto;

public class EvaluacionEliminarActivity extends AppCompatActivity {

    EditText editIdEvaluacion;
    ControlBDCD17008 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_eliminar);
        controlhelper=new ControlBDCD17008 (this);
        editIdEvaluacion=(EditText)findViewById(R.id.editIdEvaluacion);
    }

    public void eliminarEvaluacion(View v){
        String regEliminadas;
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(editIdEvaluacion.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarEvaluacion(evaluacion);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}