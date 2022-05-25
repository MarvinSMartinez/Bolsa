package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class OfertaLaboralEliminarActivity extends AppCompatActivity {

    EditText editIdOferta;
    ControlBDMH18083 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_laboral_eliminar);
        controlhelper=new ControlBDMH18083 (this);
        editIdOferta=(EditText)findViewById(R.id.editIdOferta);
    }

    public void eliminarOferta(View v){
        String regEliminadas;
        OfertaLaboral ofertaLaboral=new OfertaLaboral();
        ofertaLaboral.setIdOferta(editIdOferta.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(ofertaLaboral);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}