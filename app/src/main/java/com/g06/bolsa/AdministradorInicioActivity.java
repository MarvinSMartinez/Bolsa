package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.g06.bolsa.area_trabajo.Area_TrabajoMenuActivity;
import com.g06.bolsa.departamento.DepartamentoMenuActivity;


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


}