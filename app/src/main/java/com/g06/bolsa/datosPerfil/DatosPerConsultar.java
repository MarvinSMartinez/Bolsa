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
import com.g06.bolsa.clases_auxiliares.DatoEstudio;
import com.g06.bolsa.clases_auxiliares.DatoPerfil;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;

public class DatosPerConsultar extends Activity {

    ControlBDp helper;
    EditText id;
    EditText nombreCandidato;
    EditText apellidoCandidato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_consultar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlBDp(this);

        // Referencia a los widgets de la interfaz.
        id = findViewById(R.id.idperfilc);
        nombreCandidato = findViewById(R.id.anombre);
        apellidoCandidato = findViewById(R.id.aapellido);
    }
    public void consultar(View v) {
        helper.abrir();
        DatoPerfil dp = helper.consultardp(id.getText().toString());
        helper.cerrar();

        if (dp == null)
            Toast.makeText(this, "Dato no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            id.setText(String.valueOf(dp.getIdCandidato()));
            nombreCandidato.setText(String.valueOf(dp.getNombreCandidato()));
            apellidoCandidato.setText(String.valueOf(dp.getApellidoCandidato()));
        }
    }


    public void limpiarcdp(View view) {
        id.setText("");
        nombreCandidato.setText("");
        apellidoCandidato.setText("");

    }

    public void consultardp(View view) {
        helper.abrir();
        DatoPerfil de = helper.consultardp(id.getText().toString());
        helper.cerrar();
        if(de == null)
            Toast.makeText(this, "no hay registro",
                    Toast.LENGTH_LONG).show();
        else{
            id.setText(String.valueOf(de.getIdCandidato()));
            nombreCandidato.setText(String.valueOf(de.getNombreCandidato()));
            apellidoCandidato.setText(String.valueOf(de.getApellidoCandidato()));
        }
    }
}