package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

import java.util.Date;

public class OfertaLaboralInsertarActivity extends Activity {

    ControlBDMH18083 helper;
    EditText editIdOferta;
    EditText editIdEmpresa;
    EditText editInicioOferta;
    EditText editFinOferta;
    EditText editNombreOferta;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_laboral_insertar);
        helper = new ControlBDMH18083(this);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editIdEmpresa = (EditText) findViewById(R.id.editIdEmpresa);
        editInicioOferta = (EditText) findViewById(R.id.editInicioOferta);
        editFinOferta = (EditText) findViewById(R.id.editFinOferta);
        editNombreOferta = (EditText) findViewById(R.id.editNombreOferta);
    }
    public void insertarOferta(View v) {
        String idOferta=editIdOferta.getText().toString();
        String idEmpresa=editIdEmpresa.getText().toString();
        String inicioOferta= editInicioOferta.getText().toString();
        String finOferta = editFinOferta.getText().toString();
        String nombreOferta=editNombreOferta.getText().toString();
        String regInsertados;
        OfertaLaboral ofertaLaboral=new OfertaLaboral();
        ofertaLaboral.setIdOferta(idOferta);
        ofertaLaboral.setIdEmpresa(idEmpresa);
        ofertaLaboral.setInicioOferta(inicioOferta);
        ofertaLaboral.setFinOferta(finOferta);
        ofertaLaboral.setNombreOferta(nombreOferta);
        helper.abrir();
        regInsertados=helper.insertar(ofertaLaboral);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdOferta.setText("");
        editIdEmpresa.setText("");
        editInicioOferta.setText("");
        editFinOferta.setText("");
        editNombreOferta.setText("");
    }

}