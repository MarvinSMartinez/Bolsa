package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.clases_auxiliares.Usuario;

public class MainActivity extends AppCompatActivity {
    ControlDBLJ16001 DBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper = new ControlDBLJ16001(this);
    }

    public void autentificarUsuario(View view) {
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
            try {
                Class<?> clase = Class.forName("com.g06.bolsa.Inicio");
                Intent inte = new Intent(this, clase);
                this.startActivity(inte);
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    public void llenarBaseDeDatos(View view) {

        DBHelper.abrir();
        String toast = DBHelper.llenarBD();
        DBHelper.cerrar();
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}