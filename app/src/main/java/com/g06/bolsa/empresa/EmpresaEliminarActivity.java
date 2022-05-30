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

public class EmpresaEliminarActivity extends Activity {
    ControlEmpresa DBHelper;
    EditText IdEmpresa;
    EditText IdDepartamento;
    EditText RazonSocial;
    EditText NombreEmpresa;
    EditText DireccionEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_eliminar);
        DBHelper = new ControlEmpresa(this);
        IdEmpresa = findViewById(R.id.editIdEliminarEmpresa);
        IdDepartamento = findViewById(R.id.editIdDepartamentoEliminarEmpresa);
        RazonSocial = findViewById(R.id.editRazonSocialEliminarEmpresa);
        NombreEmpresa = findViewById(R.id.editNombreEliminarEmpresa);
        DireccionEmpresa = findViewById(R.id.editDireccionEliminarEmpresa);
    }
    public void eliminarEmpresa(View v) {
        String regEliminadas;
        Empresa empresa = new Empresa();
        empresa.setId(IdEmpresa.getText().toString());
        empresa.setIdDepartamento(IdDepartamento.getText().toString());
        empresa.setRazon_social(RazonSocial.getText().toString());
        empresa.setNombre(NombreEmpresa.getText().toString());
        empresa.setDireccion(DireccionEmpresa.getText().toString());

        DBHelper.abrir();
        regEliminadas = DBHelper.eliminarEmpresa(empresa);
        DBHelper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
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
    public void limpiarEliminarEmpresa(View v) {
        IdEmpresa.setText("");
        IdDepartamento.setText("");
        RazonSocial.setText("");
        NombreEmpresa.setText("");
        DireccionEmpresa.setText("");

    }
}
