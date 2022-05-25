package com.g06.bolsa.contacto_aspirante;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import com.g06.bolsa.R;
import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.clases_auxiliares.ContactoAspirante;


public class ContactoAspiranteActualizarActivity extends Activity {
    ControlBDLJ16001 DBHelper;
    EditText id;
    EditText candidatoId;
    EditText  telefono1;
    EditText  telefono2;
    EditText  correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_aspirante_actualizar);
        DBHelper = new ControlBDLJ16001(this);

        id = findViewById(R.id.editContactoId);
        candidatoId = findViewById(R.id.editCandidatoId);
        telefono1 = findViewById(R.id.editTelefono1);
        telefono2 = findViewById(R.id.editTelefono2);
        correo = findViewById(R.id.editCorreo);
    }

    public void actualizar(View v) {
        ContactoAspirante de = new ContactoAspirante();

        de.setId(id.getText().toString());
        de.setIdCandidato(candidatoId.getText().toString());
        de.setTelefono1(telefono1.getText().toString());
        de.setTelefono2(telefono2.getText().toString());
        de.setCorreo(correo.getText().toString());

        DBHelper.abrir();
        String estado = DBHelper.actualizar(de);
        DBHelper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        telefono1.setText("");
        telefono2.setText("");
        correo.setText("");
    }
}