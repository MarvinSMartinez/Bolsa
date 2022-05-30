package com.g06.bolsa.municipio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.Empresa;
import com.g06.bolsa.clases_auxiliares.Municipio;
import com.g06.bolsa.empresa.ControlEmpresa;

public class MunicipioConsultarActivity extends Activity {
    ControlMunicipio DBHelper;
    EditText IdMunicipio;
    EditText IdDepartamento;
    EditText NombreMunicipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_consultar);
        DBHelper = new ControlMunicipio(this);
        IdMunicipio = findViewById(R.id.editIdConsultarMunicipio);
        IdDepartamento = findViewById(R.id.editIdDepartamentoConsultarMunicipio);
        NombreMunicipio = findViewById(R.id.editNombreConsultarMunicipio);
    }
    public void consultarMunicipio(View v) {
        DBHelper.abrir();
        Municipio municipio = DBHelper.consultarMunicipio(IdMunicipio.getText().toString());
        DBHelper.cerrar();
        if (municipio == null)
            Toast.makeText(this, "Municipio no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            IdMunicipio.setText(String.valueOf(municipio.getId()));
            IdDepartamento.setText(String.valueOf(municipio.getIdDepartamento()));
            NombreMunicipio.setText(String.valueOf(municipio.getNombre()));
             }
    }
}