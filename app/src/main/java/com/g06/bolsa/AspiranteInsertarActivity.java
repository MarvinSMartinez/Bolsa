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

import java.util.ArrayList;
import java.util.Locale;

public class AspiranteInsertarActivity extends Activity {

    ControlBDMH18083 helper;
    EditText editIdAspirante;
    EditText editIdDetalleOferta;
    EditText editIdEmpresa;
    Spinner estado;
    String estadoSeleccionado;
    int estadoSel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirante_insertar);
        helper = new ControlBDMH18083(this);
        editIdAspirante = (EditText) findViewById(R.id.editIdAspirante);
        editIdDetalleOferta = (EditText) findViewById(R.id.editIdDetalleOferta);
        editIdEmpresa = (EditText) findViewById(R.id.editIdEmpresa);
        //editEstado = (EditText) findViewById(R.id.spinner_estado);

        estado= (Spinner) findViewById(R.id.spinner_estado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estado,
                android.R.layout.simple_spinner_item);

        estado.setAdapter(adapter);

        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                estadoSeleccionado=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void insertarOferta(View v) {
        String idAspirante=editIdAspirante.getText().toString();
        String idDetalleOferta=editIdDetalleOferta.getText().toString();
        String idEmpresa = editIdEmpresa.getText().toString();
        if(estadoSeleccionado.equals("Aprobado")){
            estadoSel=1;
        }else{
            estadoSel=0;
        }

        String regInsertados;
        Aspirante aspirante = new Aspirante();
        aspirante.setIdAspirante(idAspirante);
        aspirante.setIdDetalleOferta(idDetalleOferta);
        aspirante.setIdEmpresa(idEmpresa);
        aspirante.setEstado(estadoSel);;
        helper.abrir();
        regInsertados=helper.insertar(aspirante);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, estadoSeleccionado,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdAspirante.setText("");
        editIdEmpresa.setText("");
        editIdDetalleOferta.setText("");
        estado.setSelection(0);
    }
}