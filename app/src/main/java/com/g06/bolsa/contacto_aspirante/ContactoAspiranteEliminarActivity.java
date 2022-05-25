package com.g06.bolsa.contacto_aspirante;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.ContactoAspirante;

public class ContactoAspiranteEliminarActivity extends Activity {
    ControlBDLJ16001 DBHelper;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_aspirante_eliminar);
        DBHelper = new ControlBDLJ16001(this);

        id = findViewById(R.id.editContactoId);
    }

    public void eliminar(View v) {
        String regEliminadas;
        ContactoAspirante de = new ContactoAspirante();
        de.setId(id.getText().toString());

        DBHelper.abrir();
        regEliminadas = DBHelper.eliminar(de);
        DBHelper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        id.setText("");
    }
}