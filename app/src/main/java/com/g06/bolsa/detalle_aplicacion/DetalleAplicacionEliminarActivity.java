package com.g06.bolsa.detalle_aplicacion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DetalleAplicacion;

public class DetalleAplicacionEliminarActivity extends Activity {
    EditText editid;
    ControlBDLJ16001 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aplicacion_eliminar);

        controlhelper=new ControlBDLJ16001(this);

        editid=(EditText)findViewById(R.id.editIdAplicacion);
    }

    public void eliminar(View v){
        String regEliminadas;
        DetalleAplicacion de = new DetalleAplicacion();
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