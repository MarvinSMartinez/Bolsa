package com.g06.bolsa.empresa;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.clases_auxiliares.Empresa;

public class EmpresaConsultarActivity extends Activity {
    ControlEmpresa DBHelper;
    EditText IdEmpresa;
    EditText IdDepartamento;
    EditText RazonSocial;
    EditText NombreEmpresa;
    EditText DireccionEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_consultar);
        DBHelper = new ControlEmpresa(this);
        IdEmpresa = findViewById(R.id.editIdConsultarEmpresa);
        IdDepartamento = findViewById(R.id.editIdDepartamentoConsultarEmpresa);
        RazonSocial = findViewById(R.id.editRazonSocialConsultarEmpresa);
        NombreEmpresa = findViewById(R.id.editNombreConsultarEmpresa);
        DireccionEmpresa = findViewById(R.id.editDireccionConsultarEmpresa);
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
    }