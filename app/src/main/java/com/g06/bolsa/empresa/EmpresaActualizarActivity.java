package com.g06.bolsa.empresa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;

import com.g06.bolsa.clases_auxiliares.Empresa;


public class EmpresaActualizarActivity extends Activity {
    ControlEmpresa DBHelper;
    EditText IdEmpresa;
    EditText IdDepartamento;
    EditText RazonSocial;
    EditText NombreEmpresa;
    EditText DireccionEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_actualizar);
        DBHelper = new ControlEmpresa(this);
        IdEmpresa = findViewById(R.id.editIdActualizarEmpresa);
        IdDepartamento = findViewById(R.id.editIdDepartamentoActualizarEmpresa);
        RazonSocial = findViewById(R.id.editRazonSocialActualizarEmpresa);
        NombreEmpresa = findViewById(R.id.editNombreActualizarEmpresa);
        DireccionEmpresa = findViewById(R.id.editDireccionActualizarEmpresa);

    }
    public void actualizarEmpresa(View v) {
        Empresa empresa = new Empresa();

        empresa.setId(IdEmpresa.getText().toString());
        empresa.setIdDepartamento(IdDepartamento.getText().toString());
        empresa.setRazon_social(RazonSocial.getText().toString());
        empresa.setNombre(NombreEmpresa.getText().toString());
        empresa.setDireccion(DireccionEmpresa.getText().toString());

        DBHelper.abrir();
        String estado = DBHelper.actualizarEmpresa(empresa);
        DBHelper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarActualizarEmpresa(View v) {
        IdEmpresa.setText("");
        IdDepartamento.setText("");
        RazonSocial.setText("");
        NombreEmpresa.setText("");
        DireccionEmpresa.setText("");

    }
}
