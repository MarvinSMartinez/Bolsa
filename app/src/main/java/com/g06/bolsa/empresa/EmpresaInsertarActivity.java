package com.g06.bolsa.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.clases_auxiliares.Empresa;

public class EmpresaInsertarActivity extends Activity {
    ControlEmpresa DBHelper;
    EditText IdEmpresa;
    EditText IdDepartamento;
    EditText RazonSocial;
    EditText NombreEmpresa;
    EditText DireccionEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_insertar);
        DBHelper = new ControlEmpresa(this);
        IdEmpresa = findViewById(R.id.editIdInsertarEmpresa);
        IdDepartamento = findViewById(R.id.editIdDepartamentoInsertarEmpresa);
        RazonSocial = findViewById(R.id.editRazonSocialInsertarEmpresa);
        NombreEmpresa = findViewById(R.id.editNombreInsertarEmpresa);
        DireccionEmpresa = findViewById(R.id.editDireccionInsertarEmpresa);
    }
    public void insertarEmpresa(View v) {
        String insertaridEmpresa = IdEmpresa.getText().toString();
        String insertaridDepartamento = IdDepartamento.getText().toString();
        String insertarRazonSocial = RazonSocial.getText().toString();
        String insertarNombreEmpresa = NombreEmpresa.getText().toString();
        String insertarDireccionEmpresa = DireccionEmpresa.getText().toString();
        String regInsertados;
        Empresa empresa = new Empresa();
        empresa.setId(insertaridEmpresa);
        empresa.setIdDepartamento(insertaridDepartamento);
        empresa.setRazon_social(insertarRazonSocial);
        empresa.setNombre(insertarNombreEmpresa);
        empresa.setDireccion(insertarDireccionEmpresa);
        DBHelper.abrir();
        regInsertados = DBHelper.insertarEmpresa(empresa);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }

    public void consultarEmpresa(View v) {
        DBHelper.abrir();
        Empresa empresa = DBHelper.consultarEmpresa(IdEmpresa.getText().toString());
        DBHelper.cerrar();
        if (empresa == null)
            Toast.makeText(this, "Empresa no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            IdEmpresa.setText(String.valueOf(empresa.getId()));
            IdDepartamento.setText(String.valueOf(empresa.getIdDepartamento()));
            RazonSocial.setText(String.valueOf(empresa.getRazon_social()));
            NombreEmpresa.setText(String.valueOf(empresa.getNombre()));
            DireccionEmpresa.setText(String.valueOf(empresa.getDireccion()));

        }
    }
    public void limpiarInsertarEmpresa(View v) {
        IdEmpresa.setText("");
        IdDepartamento.setText("");
        RazonSocial.setText("");
        NombreEmpresa.setText("");
        DireccionEmpresa.setText("");

    }
}
