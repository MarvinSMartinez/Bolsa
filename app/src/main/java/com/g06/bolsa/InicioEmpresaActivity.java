package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class InicioEmpresaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_empresa);
    }

    public void botonPerfil(View v){
        Toast.makeText(this, "Perfil de empresa", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_empresa_editar);
    }

    public void botonOfertas(View v){
        Toast.makeText(this, "Ofertas de empresa", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_menu_ofertas);
    }

}