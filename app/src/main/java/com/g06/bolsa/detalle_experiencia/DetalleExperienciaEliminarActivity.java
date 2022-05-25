package com.g06.bolsa.detalle_experiencia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlDBLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;


public class DetalleExperienciaEliminarActivity extends Activity {
    EditText editid;
    ControlDBLJ16001 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_experiencia_eliminar);
        controlhelper=new ControlDBLJ16001(this);

        editid=(EditText)findViewById(R.id.edit_eliminar_id_detalle_experiencia);
    }

    public void eliminar(View v){
        String regEliminadas;
        DetalleExperiencia de = new DetalleExperiencia();
        de.setId(editid.getText().toString());

        controlhelper.abrir();
        regEliminadas = controlhelper.eliminar(de);
        controlhelper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View v) {
        editid.setText("");
    }
}