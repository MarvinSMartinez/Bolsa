package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class DetalleOfertaInsertarActivity extends AppCompatActivity {

    ControlBDMH18083 helper;
    EditText editIdDetalle;
    EditText editIdOferta;
    EditText editPerfil;
    EditText editSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oferta_insertar);
        helper = new ControlBDMH18083(this);
        editIdDetalle = (EditText) findViewById(R.id.editIdDetalleOferta);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editPerfil = (EditText) findViewById(R.id.editPerfil);
        editSalario = (EditText) findViewById(R.id.editSalario);
    }

    public void insertarDetalle(View v) {
        String idDetalle=editIdDetalle.getText().toString();
        String idOferta=editIdOferta.getText().toString();
        String perfil= editPerfil.getText().toString();
        String salario = editSalario.getText().toString();
        String regInsertados;
        DetalleOferta detalleOferta=new DetalleOferta();
        detalleOferta.setIdOferta(idOferta);
        detalleOferta.setIdDetalleOferta(idDetalle);
        detalleOferta.setPerfil(perfil);
        detalleOferta.setSalarioOferta(salario);
        helper.abrir();
        regInsertados=helper.insertar(detalleOferta);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdOferta.setText("");
        editIdDetalle.setText("");
        editPerfil.setText("");
        editSalario.setText("");
    }

}