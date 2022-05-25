package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class AspiranteConsultarActivity extends AppCompatActivity {

    ControlBDMH18083 helper;
    EditText editIdAspirante;
    EditText editIdDetalleOferta;
    EditText editIdEmpresa;
    EditText editEstado;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirante_consultar);
        helper = new ControlBDMH18083(this);
        editIdAspirante = (EditText) findViewById(R.id.editIdAspirante);
        editIdDetalleOferta = (EditText) findViewById(R.id.editIdDetalleOferta);
        editIdEmpresa = (EditText) findViewById(R.id.editIdEmpresa);
        editEstado = (EditText) findViewById(R.id.editEstado);
    }

    public void consultarAspirante(View v) {
        helper.abrir();
        Aspirante aspirante =
                helper.consultarAspirante(editIdAspirante.getText().toString());
        helper.cerrar();
        if(aspirante == null)
            Toast.makeText(this, "Aspirante con id " + editIdAspirante.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdAspirante.setText(aspirante.getIdAspirante());
            editIdEmpresa.setText(aspirante.getIdEmpresa());
            editIdDetalleOferta.setText(aspirante.getIdDetalleOferta());
            if(aspirante.getEstado()==1){
                editEstado.setText("Aprobado");
            }else{
                editEstado.setText("Rechazado");
            }

        }

    }


    public void limpiarTexto(View v) {
        editIdAspirante.setText("");
        editIdEmpresa.setText("");
        editIdDetalleOferta.setText("");
        editEstado.setText("");
    }
}