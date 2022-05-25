package com.g06.bolsa.contacto_aspirante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.ContactoAspirante;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;

public class ContactoAspiranteConsultarActivity extends Activity {
    ControlBDLJ16001 DBHelper;
    EditText id;
    EditText candidatoId;
    EditText  telefono1;
    EditText  telefono2;
    EditText  correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_aspirante_consultar);
        DBHelper = new ControlBDLJ16001(this);

        id = findViewById(R.id.editContactoId);
        candidatoId = findViewById(R.id.editCandidatoId);
        telefono1 = findViewById(R.id.editTelefono1);
        telefono2 = findViewById(R.id.editTelefono2);
        correo = findViewById(R.id.editCorreo);
    }

    public void consultar(View v) {
        DBHelper.abrir();
        ContactoAspirante de = DBHelper.consultarContactoAspirante(id.getText().toString());
        DBHelper.cerrar();

        if (de == null)
            Toast.makeText(this, "Contacto no registrado",
                    Toast.LENGTH_LONG).show();
        else {
            id.setText(String.valueOf(de.getId()));
            candidatoId.setText(String.valueOf(de.getIdCandidato()));
            telefono1.setText(String.valueOf(de.getTelefono1()));
            telefono2.setText(String.valueOf(de.getTelefono2()));
            correo.setText(String.valueOf(de.getCorreo()));
        }
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        telefono1.setText("");
        telefono2.setText("");
        correo.setText("");
    }
}