package com.g06.bolsa.area_trabajo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.Area_Trabajo;

public class Area_TrabajoActualizarActivity extends Activity {
    ControlArea_trabajo DBHelper;
    EditText idAreaTrabajo;
    EditText nombreAreaTrabajo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_trabajo_actualizar);

        idAreaTrabajo = findViewById(R.id.editIdDepartamento);
        nombreAreaTrabajo = findViewById(R.id.editNombreDepartamento);
    }
    public void actualizarArea_Trabajo(View v) {
        Area_Trabajo area = new Area_Trabajo();

        area.setId(idAreaTrabajo.getText().toString());
        area.setNombre(nombreAreaTrabajo.getText().toString());

        DBHelper.abrir();
        String estado = DBHelper.actualizarArea_Trabajo(area);
        DBHelper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        idAreaTrabajo.setText("");
        nombreAreaTrabajo.setText("");

    }
}
