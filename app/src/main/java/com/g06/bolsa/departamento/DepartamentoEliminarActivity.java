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

public class DepartamentoEliminarActivity extends Activity {
    ControlDepartamento DBHelper;
    EditText idDepartamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_eliminar);
        DBHelper = new ControlDepartamento(this);

        idDepartamento = findViewById(R.id.editEliminarIdDepartamento);
    }

    public void eliminarDepartamento(View v) {
        String regEliminadas;
        Departamento departamento = new Departamento();
        departamento.setId(idDepartamento.getText().toString());

        DBHelper.abrir();
        regEliminadas = DBHelper.eliminarDepartamento(departamento);
        DBHelper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}