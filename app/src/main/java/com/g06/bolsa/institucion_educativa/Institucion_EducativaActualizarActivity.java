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

public class Institucion_EducativaActualizarActivity extends Activity {
    ControlInstitucionEducativa DBHelper;
    EditText IdInstitucion;
    EditText IdDepartamento;
    EditText NombreInstitucion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institucion_educativa_actualizar);
        DBHelper = new ControlInstitucionEducativa(this);
        IdInstitucion = findViewById(R.id.editIdActualizarInstitucion);
        IdDepartamento = findViewById(R.id.editIdDepartamentoActualizarInstitucion);
        NombreInstitucion = findViewById(R.id.editNombreActualizarInstitucion);
    }

    public void actualizarInstitucion(View v) {
        InstitucionEducativa ie = new InstitucionEducativa();
        ie.setIdIstitucion(IdInstitucion.getText().toString());
        ie.setIdDepartamento(IdDepartamento.getText().toString());
        ie.setNombreInstitucion(NombreInstitucion.getText().toString());
        DBHelper.abrir();
        String estado = DBHelper.actualizarInstitucionEducativa(ie);
        DBHelper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void consultarActualizarInstitucion(View v) {
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

    public void limpiarActualizarInstitucion(View v) {
        IdInstitucion.setText("");
        IdDepartamento.setText("");
        NombreInstitucion.setText("");
    }
}
