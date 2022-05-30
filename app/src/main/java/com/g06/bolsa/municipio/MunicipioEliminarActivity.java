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

public class MunicipioEliminarActivity extends Activity {
    ControlMunicipio DBHelper;
    EditText IdMunicipio;
    EditText IdDepartamento;
    EditText NombreMunicipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_eliminar);
        DBHelper = new ControlMunicipio(this);
        IdMunicipio = findViewById(R.id.editIdEliminarMunicipio);
        IdDepartamento = findViewById(R.id.editIdDepartamentoEliminarMunicipio);
        NombreMunicipio = findViewById(R.id.editNombreEliminarMunicipio);
    }
    public void eliminarMunicipio(View v) {
        String regEliminadas;
        Municipio municipio = new Municipio();
        municipio.setId(IdMunicipio.getText().toString());
        municipio.setIdDepartamento(IdDepartamento.getText().toString());
        municipio.setNombre(NombreMunicipio.getText().toString());
        DBHelper.abrir();
        regEliminadas = DBHelper.eliminarMunicipio(municipio);
        DBHelper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void consultarEliminarMunicipio(View v) {
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
    public void limpiarEliminarMunicipio(View v) {
        IdMunicipio.setText("");
        IdDepartamento.setText("");
        NombreMunicipio.setText("");

    }
}