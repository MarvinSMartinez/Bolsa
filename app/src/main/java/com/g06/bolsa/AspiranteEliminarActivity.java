package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class AspiranteEliminarActivity extends AppCompatActivity {

    EditText editIdAspirante;
    ControlBDMH18083 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirante_eliminar);
        controlhelper=new ControlBDMH18083 (this);
        editIdAspirante=(EditText)findViewById(R.id.editIdAspirante);
    }

    public void eliminarAspirante(View v){
        String regEliminadas;
        Aspirante aspirante=new Aspirante();
        aspirante.setIdAspirante(editIdAspirante.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(aspirante);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}