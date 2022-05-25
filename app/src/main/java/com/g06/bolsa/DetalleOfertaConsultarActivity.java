package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.DetalleOferta;

public class DetalleOfertaConsultarActivity extends Activity {

    ControlBDMH18083 helper;
    EditText editIdDetalle;
    EditText editIdOferta;
    EditText editPerfil;
    EditText editSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oferta_consultar);
        helper = new ControlBDMH18083(this);
        editIdDetalle = (EditText) findViewById(R.id.editIdDetalleOferta);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editPerfil = (EditText) findViewById(R.id.editPerfil);
        editSalario = (EditText) findViewById(R.id.editSalario);
    }

    public void consultarDetalle(View v) {
        helper.abrir();
        DetalleOferta detalleOferta =
                helper.consultarDetalle(editIdDetalle.getText().toString());
        helper.cerrar();
        if(detalleOferta == null)
            Toast.makeText(this, "Detalle con id " + editIdDetalle.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdDetalle.setText(detalleOferta.getIdDetalleOferta());
            editIdOferta.setText(detalleOferta.getIdOferta());
            editPerfil.setText(detalleOferta.getPerfil());
            editSalario.setText(detalleOferta.getSalarioOferta());
        }

    }

    public void limpiarTexto(View v) {
        editIdDetalle.setText("");
        editIdOferta.setText("");
        editPerfil.setText("");
        editSalario.setText("");
    }
}