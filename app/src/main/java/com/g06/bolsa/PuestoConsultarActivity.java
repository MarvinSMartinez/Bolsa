package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Puesto;

public class PuestoConsultarActivity extends Activity {

    ControlBDCD17008 helper;
    EditText editIdPuesto;
    EditText editIdOferta;
    EditText editIdArea;
    EditText editNombrePuesto;
    EditText editNumPuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puesto_consultar);
        helper = new ControlBDCD17008(this);
        editIdPuesto = (EditText) findViewById(R.id.editIdPuesto);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editIdArea = (EditText) findViewById(R.id.editIdAreaP);
        editNombrePuesto = (EditText) findViewById(R.id.editNombrePuesto);
        editNumPuesto = (EditText) findViewById(R.id.editNumPuesto);
    }

    public void consultarPuesto(View v) {
        helper.abrir();
        Puesto puesto =
                helper.consultarPuesto(editIdPuesto.getText().toString());
        helper.cerrar();
        if(puesto == null)
            Toast.makeText(this, "Puesto con Id " + editIdPuesto.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdPuesto.setText(puesto.getId());
            editIdOferta.setText(puesto.getIdOferta());
            editIdArea.setText(puesto.getIdArea());
            editNombrePuesto.setText(puesto.getNomPuesto());
            editNumPuesto.setText(puesto.getVacPuesto());
        }

    }

    public void limpiarTexto(View v) {
        editIdPuesto.setText("");
        editIdOferta.setText("");
        editIdArea.setText("");
        editNombrePuesto.setText("");
        editNumPuesto.setText("");
    }
}