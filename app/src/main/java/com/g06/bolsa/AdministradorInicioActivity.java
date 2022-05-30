package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.g06.bolsa.area_trabajo.Area_TrabajoMenuActivity;
import com.g06.bolsa.departamento.DepartamentoMenuActivity;
import com.g06.bolsa.empresa.EmpresaMenuActivity;
import com.g06.bolsa.institucion_educativa.Institucion_EducativaMenuActivity;
import com.g06.bolsa.municipio.MunicipioMenuActivity;


public class AdministradorInicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_inicio);
    }
    public void DepartamentoMenu(View view) {
        Intent intent = new Intent(this, DepartamentoMenuActivity.class);
            startActivity(intent);
    }
    public void AreaTrabajoMenu(View view) {
        Intent intent = new Intent(this, Area_TrabajoMenuActivity.class);
        startActivity(intent);
    }
    public void EmpresaMenu(View view) {
        Intent intent = new Intent(this, EmpresaMenuActivity.class);
        startActivity(intent);
    }
    public void MunicipioMenu(View view) {
        Intent intent = new Intent(this, MunicipioMenuActivity.class);
        startActivity(intent);
    }
    public void InstitucionMenu(View view) {
        Intent intent = new Intent(this, Institucion_EducativaMenuActivity.class);
        startActivity(intent);
    }

}