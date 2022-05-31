package com.g06.bolsa.datosPerfil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.ControlBDp;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;
import com.g06.bolsa.clases_auxiliares.DatoPerfil;
import com.g06.bolsa.clases_auxiliares.PerfilCandidato;

public class DatosPerInsertar extends Activity {

    ControlBDp helper;
    EditText idperfili;
    EditText idDepartamentoi;
    EditText idUsuarioi;
    EditText nombrei;
    EditText apellidoi;
    EditText duii;
    EditText niti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_insertar);

        helper = new ControlBDp(this);
        idperfili=(EditText) findViewById(R.id.idperfili);
        idDepartamentoi=(EditText) findViewById(R.id.idDepartamentoi);
        idUsuarioi=(EditText) findViewById(R.id.idUsuarioi);
        nombrei =(EditText) findViewById(R.id.nombrei);
        apellidoi =(EditText) findViewById(R.id.apellidoi);
        duii=(EditText) findViewById(R.id.duii);
        niti=(EditText) findViewById(R.id.niti);

    }
    public void insertardp(View view) {
        String sid = idperfili.getText().toString();
        String idDepto=idDepartamentoi.getText().toString();
        String idUs=idUsuarioi.getText().toString();
        String nombrec = nombrei.getText().toString();
        String apellidoc = apellidoi.getText().toString();
        String d=duii.getText().toString();
        String n=niti.getText().toString();

        String regInsertados;

        PerfilCandidato de = new PerfilCandidato();
        de.setIdperfilcandidato(sid);
        de.setIddepartamento(idDepto);
        de.setIdusuario(idUs);
        de.setNombre(nombrec);
        de.setApellido(apellidoc);
        de.setDui(d);
        de.setNit(n);

        helper.abrir();
        regInsertados = helper.insertar(de);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiaridp(View view) {
        idperfili.setText("");
        idDepartamentoi.setText("");
        idUsuarioi.setText("");
        nombrei.setText("");
        apellidoi.setText("");
        duii.setText("");
        niti.setText("");
    }
}