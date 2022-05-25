package com.g06.bolsa.detalle_experiencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.R;
import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;

public class DetalleExperienciaInsertarActivity extends AppCompatActivity {
    ControlBDLJ16001 DBHelper;
    TextView candidatoId;
    TextView id;
    TextView lugar;
    TextView puesto;
    TextView tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_experiencia_insertar);
        DBHelper = new ControlBDLJ16001(this);
        id = findViewById(R.id.editIdDetalleExperiencia);
        candidatoId = findViewById(R.id.edit_detalle_experiencia_idcandidato);
        lugar = findViewById(R.id.editLugar);
        puesto = findViewById(R.id.editPuesto);
        tiempo = findViewById(R.id.editTiempo);
    }

    public void insertar(View v) {
        String sid = id.getText().toString();
        String scandidatoId = candidatoId.getText().toString();
        String slugar = lugar.getText().toString();
        String spuesto = puesto.getText().toString();
        String stiempo = tiempo.getText().toString();

        String regInsertados;

        DetalleExperiencia de = new DetalleExperiencia();
        de.setId(sid);
        de.setIdCandidato(scandidatoId);
        de.setLugarExperiencia(slugar);
        de.setPuestoExperiencia(spuesto);
        de.setTiempoExperiencia(stiempo);

        DBHelper.abrir();
        regInsertados = DBHelper.insertar(de);
        DBHelper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        id.setText("");
        candidatoId.setText("");
        lugar.setText("");
        puesto.setText("");
        tiempo.setText("");
    }
}