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

public class DatosPerInsertar extends Activity implements AdapterView.OnItemSelectedListener{

    ControlBDp helper;
    EditText nombreCandidato;
    EditText apellidoCandidato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_insertar);

        helper = new ControlBDp(this);
        nombreCandidato = findViewById(R.id.anombre);
        apellidoCandidato = findViewById(R.id.aapellido);

        //departamentos
        Spinner spinner=findViewById(R.id.spinnerdepto);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.departamentos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //municipios
        Spinner spinner2=findViewById(R.id.spinnermunicipio);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.municipios_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }
    public void insertar(View v) {
        String nombrec = nombreCandidato.getText().toString();
        String apellidoc = apellidoCandidato.getText().toString();

        String regInsertados;

        DatoPerfil dp = new DatoPerfil();
        dp.setNombreCandidato(nombrec);
        dp.setApellidoCandidato(apellidoc);

        helper.abrir();
        regInsertados = helper.insertar(dp);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiaridp(View view) {
        nombreCandidato.setText("");
        apellidoCandidato.setText("");

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}