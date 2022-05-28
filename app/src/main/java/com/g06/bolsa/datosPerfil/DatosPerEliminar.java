package com.g06.bolsa.datosPerfil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.ControlBDp;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DatoPerfil;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;

public class DatosPerEliminar extends Activity {

    ControlBDp helper;
    TextView id;
    EditText nombreCandidato;
    EditText apellidoCandidato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_eliminar);
    }
    public void eliminardp(View view) {
        String regEliminadas;
        DatoPerfil de = new DatoPerfil();
        de.setIdCandidato(id.getText().toString());

        helper.abrir();
        regEliminadas = helper.eliminardp(de);
        helper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiaredp(View view) {
        nombreCandidato.setText("");
        apellidoCandidato.setText("");
    }

}