package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class DetalleOfertaActualizarActivity extends AppCompatActivity {

    ControlBDMH18083 helper;
    EditText editIdDetalle;
    EditText editIdOferta;
    EditText editPerfil;
    EditText editSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oferta_actualizar);
        helper = new ControlBDMH18083(this);
        editIdDetalle = (EditText) findViewById(R.id.editIdDetalleOferta);
        //editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editPerfil = (EditText) findViewById(R.id.editPerfil);
        editSalario = (EditText) findViewById(R.id.editSalario);
    }

    public void actualizarDetalle(View v) {
        DetalleOferta detalleOferta = new DetalleOferta();
        detalleOferta.setIdDetalleOferta(editIdDetalle.getText().toString());
        detalleOferta.setPerfil(editPerfil.getText().toString());
        detalleOferta.setSalarioOferta(editSalario.getText().toString());
        helper.abrir();
        String estado = helper.actualizarDetalle(detalleOferta);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdDetalle.setText("");
        //editIdEmpresa.setText("");
        editPerfil.setText("");
        editSalario.setText("");
        //editNombreOferta.setText("");
    }

}