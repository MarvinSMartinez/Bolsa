package com.g06.bolsa.departamento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.ContactoAspirante;
import com.g06.bolsa.clases_auxiliares.Departamento;

public class DepartamentoConsultarActivity extends Activity {
    ControlDepartamento DBHelper;
    EditText id;
    EditText nombreDepartamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_consultar);
        DBHelper = new ControlDepartamento(this);
        id = findViewById(R.id.idDepartamento);
        nombreDepartamento = findViewById(R.id.nombreDepartamento);
    }

    public void consultarDepartamento(View v) {
        DBHelper.abrir();
        Departamento departamento = DBHelper.consultarDepartamento(id.getText().toString());
        DBHelper.cerrar();
        if (departamento == null)
            Toast.makeText(this, "Departamento no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            id.setText(String.valueOf(departamento.getId()));
            nombreDepartamento.setText(String.valueOf(departamento.getNombre()));

        }
    }
}