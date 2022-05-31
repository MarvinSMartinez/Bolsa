package com.g06.bolsa.datosPerfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.ControlBDp;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DatoPerfil;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;
import com.g06.bolsa.clases_auxiliares.PerfilCandidato;

public class DatosPerActualizarActivity extends Activity {

    ControlBDp helper;
    EditText idperfila;
   // EditText idDepartamentoa;
   // EditText idUsuarioa;
    EditText nombrea;
    EditText apellidoa;
    EditText duia;
    EditText nita;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_actualizar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlBDp(this);

        // Referencia a los widgets de la interfaz.
        idperfila = (EditText) findViewById(R.id.idperfila);
      //  idDepartamentoa=(EditText) findViewById(R.id.idDepartamentoa);
     //   idUsuarioa=(EditText) findViewById(R.id.idUsuarioa);
        nombrea = (EditText) findViewById(R.id.nombrea);
        apellidoa =(EditText) findViewById(R.id.apellidoa);
        duia=(EditText) findViewById(R.id.duia);
        nita=(EditText) findViewById(R.id.nita);

    }
    public void actualizardp(View view) {
        PerfilCandidato dpe = new PerfilCandidato();
        dpe.setIdperfilcandidato(idperfila.getText().toString());
       // dpe.setIddepartamento(idDepartamentoa.getText().toString());
       // dpe.setIdusuario(idUsuarioa.getText().toString());
        dpe.setNombre(nombrea.getText().toString());
        dpe.setApellido(apellidoa.getText().toString());
        dpe.setDui(duia.getText().toString());
        dpe.setNit(nita.getText().toString());
        helper.abrir();
        String estado = helper.actualizardp(dpe);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarT(View view) {
        idperfila.setText("");
      //  idDepartamentoa.setText("");
     //   idUsuarioa.setText("");
        nombrea.setText("");
        apellidoa.setText("");
        duia.setText("");
        nita.setText("");

    }


}