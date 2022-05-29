package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;
import com.g06.bolsa.clases_auxiliares.Puesto;

import java.util.ArrayList;
import java.util.Locale;

public class PuestoInsertarActivity extends Activity {

    ControlBDCD17008 helper;
    EditText editIdPuesto;
    EditText editIdOferta;
    EditText editIdArea;
    EditText editNomPuesto;
    EditText editVacPuesto;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puesto_insertar);
        helper = new ControlBDCD17008(this);
        editIdPuesto = (EditText) findViewById(R.id.editIdPuesto);
        editNomPuesto = (EditText) findViewById(R.id.editNombrePuesto);
        editVacPuesto = (EditText) findViewById(R.id.editVacPuesto);
        editIdArea = (EditText) findViewById(R.id.editIdAreaP);
        editIdOferta = (EditText) findViewById(R.id.editIdOfertaP);
    }

    public void insertarPuesto(View v) {
        String idPuesto=editIdPuesto.getText().toString();
        String nomPuesto=editNomPuesto.getText().toString();
        String vacPuesto = editVacPuesto.getText().toString();
        String idOferta = editIdOferta.getText().toString();
        String idArea = editIdArea.getText().toString();

        String regInsertados;
        Puesto puesto = new Puesto();
        puesto.setId(idPuesto);
        puesto.setNomPuesto(nomPuesto);
        puesto.setVacPuesto(vacPuesto);
        puesto.setIdOferta(idOferta);
        puesto.setIdArea(idArea);
        helper.abrir();
        regInsertados=helper.insertarPuesto(puesto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdPuesto.setText("");
        editNomPuesto.setText("");
        editVacPuesto.setText("");
        editIdOferta.setText("");
        editIdArea.setText("");
    }
}