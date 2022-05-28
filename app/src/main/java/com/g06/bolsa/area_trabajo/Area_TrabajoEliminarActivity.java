package com.g06.bolsa.area_trabajo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.Area_Trabajo;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.departamento.ControlDepartamento;


public class Area_TrabajoEliminarActivity extends Activity {
    ControlArea_trabajo DBHelper;
    EditText idAreaTrabajo;
    EditText nombreAreaTrabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_trabajo_eliminar);
        DBHelper = new ControlArea_trabajo(this);
        idAreaTrabajo = findViewById(R.id.editEliminarIdDepartamento);
        nombreAreaTrabajo= findViewById(R.id.editEliminarNombreDepartamento);
    }
    public void eliminarArea_Trabajo(View v) {
        String regEliminadas;
        Area_Trabajo area = new Area_Trabajo();
        area.setId(idAreaTrabajo.getText().toString());

        DBHelper.abrir();
        regEliminadas = DBHelper.eliminarArea_Trabajo(area);
        DBHelper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void consultarArea_Trabajo(View v) {
        DBHelper.abrir();
        Area_Trabajo area = DBHelper.consultarArea_Trabajo(idAreaTrabajo.getText().toString());
        DBHelper.cerrar();
        if (area == null)
            Toast.makeText(this, "Area de Trabajo no registrada",
                    Toast.LENGTH_LONG).show();
        else {
            idAreaTrabajo.setText(String.valueOf(area.getId()));
            nombreAreaTrabajo.setText(String.valueOf(area.getNombre()));

        }
    }
}