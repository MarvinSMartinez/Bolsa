package com.g06.bolsa.municipio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.Empresa;
import com.g06.bolsa.clases_auxiliares.Municipio;
import com.g06.bolsa.empresa.ControlEmpresa;

public class MunicipioInsertarActivity extends AppCompatActivity {
    ControlMunicipio DBHelper;
    EditText IdMunicipio;
    EditText IdDepartamento;
    EditText NombreMunicipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_insertar);
        DBHelper = new ControlMunicipio(this);
        IdMunicipio = findViewById(R.id.editIdInsertarMunicipio);
        IdDepartamento = findViewById(R.id.editIdDepartamentoInsertarMunicipio);
        NombreMunicipio = findViewById(R.id.editNombreInsertarMunicipio);
    }
    public void insertarMunicipio(View v) {
        String insertaridMunicipio = IdMunicipio.getText().toString();
        String insertaridDepartamento = IdDepartamento.getText().toString();
        String insertarNombreMunicipio = NombreMunicipio.getText().toString();
        String regInsertados;
        Municipio municipio = new Municipio();
            municipio.setId(insertaridMunicipio);
            municipio.setIdDepartamento(insertaridDepartamento);
            municipio.setNombre(insertarNombreMunicipio);
        DBHelper.abrir();
        regInsertados = DBHelper.insertarMunicipio(municipio);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void consultarInsertarMunicipio(View v) {
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
    public void limpiarInsertarMunicipio(View v) {
        IdMunicipio.setText("");
        IdDepartamento.setText("");
        NombreMunicipio.setText("");

    }
}