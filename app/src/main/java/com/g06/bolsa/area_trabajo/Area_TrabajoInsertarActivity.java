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

public class Area_TrabajoInsertarActivity extends Activity {
    ControlArea_trabajo DBHelper;
    EditText idAreaTrabajo;
    EditText nombreAreaTrabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_trabajo_insertar);
        DBHelper = new ControlArea_trabajo(this);
        idAreaTrabajo = findViewById(R.id.dptIdInsertar);
        nombreAreaTrabajo = findViewById(R.id.dptnombreInsertar);
    }
    public void insertarArea_AreaTrabajo(View v) {
        String insertarIdArea = idAreaTrabajo.getText().toString();
        String insertarNombreArea = nombreAreaTrabajo.getText().toString();
        String regInsertados;
        Area_Trabajo area = new Area_Trabajo();
        area.setId(insertarIdArea);
        area.setNombre(insertarNombreArea);
        DBHelper.abrir();
        regInsertados = DBHelper.insertarArea_Trabajo(area);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}