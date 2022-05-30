package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;
import com.g06.bolsa.clases_auxiliares.Puesto;

public class PuestoEliminarActivity extends AppCompatActivity {

    EditText editIdPuesto;
    ControlBDCD17008 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puesto_eliminar);
        controlhelper=new ControlBDCD17008 (this);
        editIdPuesto=(EditText)findViewById(R.id.editIdPuesto);
    }

    public void eliminarPuesto(View v){
        String regEliminadas;
        Puesto puesto = new Puesto();
        puesto.setId(editIdPuesto.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarPuesto(puesto);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}