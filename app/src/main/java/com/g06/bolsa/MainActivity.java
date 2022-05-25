package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.g06.bolsa.clases_auxiliares.Usuario;



public class MainActivity extends AppCompatActivity {
    ControlBDLJ16001 DBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper = new ControlBDLJ16001(this);
    }

    public void autentificarUsuario(View view) {
        switch(getFirstTimeRun()) {
            case 0:
                Log.d("appPreferences", "Es la primera vez!");
                llenarBaseDeDatos(view);
                break;
            case 1:
                Log.d("appPreferences", "ya has iniciado la app alguna vez");
                break;
            case 2:
                Log.d("appPreferences", "es una versión nueva");
        }

        EditText editNombre = findViewById(R.id.etUsuario);
        EditText editPassword = findViewById(R.id.etPassword);

        DBHelper.abrir();
        Object result = DBHelper.consultarSiExisteUsuario(editNombre.getText().toString(),
                                         editPassword.getText().toString());
        DBHelper.cerrar();

        if (result != null) {
            DBHelper.abrir();
            Usuario usuario = (Usuario) result;
            usuario.setLoggedState(1);

            DBHelper.actualizar(usuario);
            DBHelper.cerrar();

            // Lazar el InicioActivity
        switch (((Usuario) result).getTipo()){
            case "candidato":
                try {
                    Class<?> clase = Class.forName("com.g06.bolsa.Inicio");
                    Intent inte = new Intent(this, clase);
                    this.startActivity(inte);
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }break;
            case "administrador":
                try {
                    Class<?> clase = Class.forName("com.g06.bolsa.AdministradorInicioActivity");
                    Intent inte = new Intent(this, clase);
                    this.startActivity(inte);
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }break;
            case "empresa":
                try {
                    Class<?> clase = Class.forName("com.g06.bolsa.InicioEmpresaActivity");
                    Intent inte = new Intent(this, clase);
                    this.startActivity(inte);
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }break;
        }

        }
        else {

            Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }


                }

    public void llenarBaseDeDatos(View view) {

        DBHelper.abrir();
        String toast = DBHelper.llenarBD();
        DBHelper.cerrar();

        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
    private int getFirstTimeRun() {
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }

}