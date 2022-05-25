package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class OfertaLaboralConsultarActivity extends Activity {

    ControlBDMH18083 helper;
    EditText editIdOferta;
    EditText editIdEmpresa;
    EditText editInicioOferta;
    EditText editFinOferta;
    EditText editNombreOferta ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_laboral_consultar);
        helper = new ControlBDMH18083(this);
        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        editIdEmpresa = (EditText) findViewById(R.id.editIdEmpresa);
        editInicioOferta = (EditText) findViewById(R.id.editInicioOferta);
        editFinOferta = (EditText) findViewById(R.id.editFinOferta);
        editNombreOferta = (EditText) findViewById(R.id.editNombreOferta);
    }

    public void consultarOferta(View v) {
        helper.abrir();
        OfertaLaboral ofertaLaboral =
                helper.consultarOferta(editIdOferta.getText().toString());
        helper.cerrar();
        if(ofertaLaboral == null)
            Toast.makeText(this, "Oferta con id " + editIdOferta.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdOferta.setText(ofertaLaboral.getIdOferta());
            editIdEmpresa.setText(ofertaLaboral.getIdEmpresa());
            editInicioOferta.setText(ofertaLaboral.getInicioOferta());
            editFinOferta.setText(ofertaLaboral.getFinOferta());
            editNombreOferta.setText(ofertaLaboral.getNombreOferta());
        }

    }


    public void limpiarTexto(View v) {
        editIdOferta.setText("");
        editIdEmpresa.setText("");
        editInicioOferta.setText("");
        editFinOferta.setText("");
        editNombreOferta.setText("");
    }

}