package com.g06.bolsa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Puesto;

public class PuestoActualizarActivity extends Activity {

    ControlBDCD17008 helper;
    EditText editIdPuesto;
    EditText editIdOferta;
    EditText editIdArea;
    EditText editNombrePuesto;
    EditText editNumPuesto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puesto_actualizar);
        helper = new ControlBDCD17008(this);
        editIdPuesto = (EditText) findViewById(R.id.editIdPuesto);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editIdArea = (EditText) findViewById(R.id.editIdArea);
        editNombrePuesto = (EditText) findViewById(R.id.editNombrePuesto);
        editNumPuesto = (EditText) findViewById(R.id.editNumPuesto);
    }

    public void actualizarPuesto(View v) {
        Puesto puesto = new Puesto();
        puesto.setId(editIdPuesto.getText().toString());
        puesto.setIdOferta(editIdOferta.getText().toString());
        puesto.setIdArea(editIdArea.getText().toString());
        puesto.setNomPuesto(editNombrePuesto.getText().toString());
        puesto.setVacPuesto(editNumPuesto.getText().toString());
        helper.abrir();
        String estado = helper.actualizarPuesto(puesto);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdPuesto.setText("");
        editIdOferta.setText("");
        editIdArea.setText("");
        editNombrePuesto.setText("");
        editNumPuesto.setText("");
    }

}