package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class DetalleOfertaEliminarActivity extends Activity {

    EditText editIdDetalleOferta;
    ControlBDMH18083 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oferta_eliminar);
        controlhelper=new ControlBDMH18083 (this);
        editIdDetalleOferta=(EditText)findViewById(R.id.editIdDetalleOferta);
    }

    public void eliminarDetalle(View v){
        String regEliminadas;
        DetalleOferta detalleOferta=new DetalleOferta();
        detalleOferta.setIdDetalleOferta(editIdDetalleOferta.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(detalleOferta);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}