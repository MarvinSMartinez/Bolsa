package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class AspiranteActualizarActivity extends AppCompatActivity {

    ControlBDMH18083 helper;
    EditText editIdAspirante;
    //EditText editIdDetalleOferta;
    //EditText editIdEmpresa;
    Spinner estado;
    String estadoSeleccionado;
    int estadoSel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirante_actualizar);
        helper = new ControlBDMH18083(this);
        editIdAspirante = (EditText) findViewById(R.id.editIdAspirante);
        //editIdDetalleOferta = (EditText) findViewById(R.id.editIdDetalleOferta);
        //editIdEmpresa = (EditText) findViewById(R.id.editIdEmpresa);
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

    public void actualizarAspirante(View v) {
        Aspirante aspirante = new Aspirante();
        aspirante.setIdAspirante(editIdAspirante.getText().toString());

        if(estadoSeleccionado.equals("Aprobado")){
            estadoSel=1;
        }else{
            estadoSel=0;
        }
        aspirante.setEstado(estadoSel);
        helper.abrir();
        String estado = helper.actualizarAspirante(aspirante);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdAspirante.setText("");
        estado.setSelection(0);
    }
}