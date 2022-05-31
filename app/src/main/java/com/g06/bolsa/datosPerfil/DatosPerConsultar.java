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
import com.g06.bolsa.clases_auxiliares.PerfilCandidato;

public class DatosPerConsultar extends Activity {

    ControlBDp helper;
    EditText id;
    EditText idDepartamento;
    EditText idUsuario;
    EditText nombreCandidato;
    EditText apellidoCandidato;
    EditText dui;
    EditText nit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_consultar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlBDp(this);

        // Referencia a los widgets de la interfaz.
        id = findViewById(R.id.idperfilc);
        idDepartamento=findViewById(R.id.idDepartamentoc);
        idUsuario=findViewById(R.id.idUsuarioc);
        nombreCandidato = findViewById(R.id.nombrec);
        apellidoCandidato = findViewById(R.id.apellidoc);
        dui=findViewById(R.id.duic);
        nit=findViewById(R.id.nitc);
    }


    public void consultardp(View view) {
        helper.abrir();
        PerfilCandidato de = helper.consultardp(id.getText().toString());
        helper.cerrar();
        if(de == null)
            Toast.makeText(this, "no hay registro",
                    Toast.LENGTH_LONG).show();
        else{
            id.setText(String.valueOf(de.getIdperfilcandidato()));
            idDepartamento.setText(String.valueOf(de.getIddepartamento()));
            idUsuario.setText(String.valueOf(de.getIdusuario()));
            nombreCandidato.setText(String.valueOf(de.getNombre()));
            apellidoCandidato.setText(String.valueOf(de.getApellido()));
            dui.setText(String.valueOf(de.getDui()));
            nit.setText(String.valueOf(de.getNit()));
        }
    }
    public void limpiarcdp(View view) {
        id.setText("");
        idDepartamento.setText("");
        idUsuario.setText("");
        nombreCandidato.setText("");
        apellidoCandidato.setText("");
        dui.setText("");
        nit.setText("");
    }
}