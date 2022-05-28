package com.g06.bolsa.departamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.departamento.ControlDepartamento;
import com.g06.bolsa.clases_auxiliares.Departamento;

public class DepartamentoActualizarActivity extends AppCompatActivity {
    ControlDepartamento DBHelper;
    EditText idDepartamento;
    EditText nombreDepartamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_actualizar);
        DBHelper = new ControlDepartamento(this);

        idDepartamento = findViewById(R.id.editIdDepartamento);
        nombreDepartamento = findViewById(R.id.editNombreDepartamento);

    }

    public void actualizarDepartamento(View v) {
        Departamento departamento = new Departamento();

        departamento.setId(idDepartamento.getText().toString());
        departamento.setNombre(nombreDepartamento.getText().toString());

        DBHelper.abrir();
        String estado = DBHelper.ActualizarDepartamento(departamento);
        DBHelper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        idDepartamento.setText("");
        nombreDepartamento.setText("");

    }
}