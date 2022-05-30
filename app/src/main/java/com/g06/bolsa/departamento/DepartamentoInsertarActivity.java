package com.g06.bolsa.departamento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.ContactoAspirante;
import com.g06.bolsa.clases_auxiliares.Departamento;

public class DepartamentoInsertarActivity extends Activity {
    ControlDepartamento DBHelper;
    EditText idDepartamento;
    EditText nombreDepartamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_insertar);
        DBHelper = new ControlDepartamento(this);
        idDepartamento = findViewById(R.id.dptIdInsertar);
        nombreDepartamento = findViewById(R.id.dptnombreInsertar);
    }

    public void InsertarEmpresa(View v) {
        String insertaridDepartamento = idDepartamento.getText().toString();
        String inseratarnombreDepartamento = nombreDepartamento.getText().toString();
        String regInsertados;
        Departamento departamento = new Departamento();
            departamento.setId(insertaridDepartamento);
            departamento.setNombre(inseratarnombreDepartamento);
        DBHelper.abrir();
        regInsertados = DBHelper.insertarDepartamento(departamento);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}
