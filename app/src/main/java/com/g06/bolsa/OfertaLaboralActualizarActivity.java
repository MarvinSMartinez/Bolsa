package com.g06.bolsa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class OfertaLaboralActualizarActivity extends Activity {

    ControlBDMH18083 helper;
    EditText editIdOferta;
    EditText editIdEmpresa;
    EditText editInicioOferta;
    EditText editFinOferta;
    EditText editNombreOferta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_laboral_actualizar);
        helper = new ControlBDMH18083(this);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        //editIdEmpresa = (EditText) findViewById(R.id.editIdEmpresa);
        editInicioOferta = (EditText) findViewById(R.id.editInicioOferta);
        editFinOferta = (EditText) findViewById(R.id.editFinOferta);
        editNombreOferta = (EditText) findViewById(R.id.editNombreOferta);
    }

    public void actualizarOferta(View v) {
        OfertaLaboral ofertaLaboral = new OfertaLaboral();
        ofertaLaboral.setIdOferta(editIdOferta.getText().toString());
        ofertaLaboral.setInicioOferta(editInicioOferta.getText().toString());
        ofertaLaboral.setFinOferta(editFinOferta.getText().toString());
        ofertaLaboral.setNombreOferta(editNombreOferta.getText().toString());
        helper.abrir();
        String estado = helper.actualizarOferta(ofertaLaboral);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdOferta.setText("");
        //editIdEmpresa.setText("");
        editInicioOferta.setText("");
        editFinOferta.setText("");
        editNombreOferta.setText("");
    }

}