package com.g06.bolsa.detalle_aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.clases_auxiliares.DetalleAplicacion;

public class DetalleAplicacionInsertarActivity extends AppCompatActivity {
    ControlBDLJ16001 DBHelper;
    TextView candidatoId;
    TextView id;
    TextView estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aplicacion_insertar);
        DBHelper = new ControlBDLJ16001(this);
        id = findViewById(R.id.editIdAplicacion);
        candidatoId = findViewById(R.id.editCandidatoId);
        estado = findViewById(R.id.editEstado);
    }

    public void insertar(View v) {
        String sid = id.getText().toString();
        String scandidatoId = candidatoId.getText().toString();
        String sestado = estado.getText().toString();

        String regInsertados;

        DetalleAplicacion de = new DetalleAplicacion();

        de.setId(sid);
        de.setIdCandidato(scandidatoId);
        de.setEstado(sestado);

        DBHelper.abrir();
        regInsertados = DBHelper.insertar(de);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        estado.setText("");
    }
}