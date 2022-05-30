package com.g06.bolsa.institucion_educativa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.InstitucionEducativa;
import com.g06.bolsa.clases_auxiliares.Municipio;
import com.g06.bolsa.municipio.ControlMunicipio;

public class Institucion_EducativaInsertarActivity extends Activity {
    ControlInstitucionEducativa DBHelper;
    EditText IdInstitucion;
    EditText IdDepartamento;
    EditText NombreInstitucion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institucion_educativa_insertar);
        DBHelper = new ControlInstitucionEducativa(this);
        IdInstitucion = findViewById(R.id.editIdInsertarInstitucion);
        IdDepartamento = findViewById(R.id.editIdDepartamentoInsertarInstitucion);
        NombreInstitucion = findViewById(R.id.editNombreInsertarInstitucion);
    }
    public void insertarInstitucion(View v) {
        String insertaridInstitucion = IdInstitucion.getText().toString();
        String insertaridDepartamento = IdDepartamento.getText().toString();
        String insertarNombreInstitucicon = NombreInstitucion.getText().toString();
        String regInsertados;
        InstitucionEducativa ie = new InstitucionEducativa();
        ie.setIdIstitucion(insertaridInstitucion);
        ie.setIdDepartamento(insertaridDepartamento);
        ie.setNombreInstitucion(insertarNombreInstitucicon);
        DBHelper.abrir();
        regInsertados = DBHelper.insertarInstitucionEducativa(ie);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void consultarInsertarInstitucion(View v) {
        DBHelper.abrir();
        InstitucionEducativa ie = DBHelper.consultarInstitucionEducativa(IdInstitucion.getText().toString());
        DBHelper.cerrar();
        if (ie == null)
            Toast.makeText(this, "Institucion no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            IdInstitucion.setText(String.valueOf(ie.getIdIstitucion()));
            IdDepartamento.setText(String.valueOf(ie.getIdDepartamento()));
            NombreInstitucion.setText(String.valueOf(ie.getNombreInstitucion()));
        }
    }

    public void limpiarInsertarInstitucion(View v) {
        IdInstitucion.setText("");
        IdDepartamento.setText("");
        NombreInstitucion.setText("");
    }
}