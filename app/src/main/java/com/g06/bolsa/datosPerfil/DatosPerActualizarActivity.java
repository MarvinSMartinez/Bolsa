package com.g06.bolsa.datosPerfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.g06.bolsa.ControlBDLJ16001;
import com.g06.bolsa.ControlBDp;
import com.g06.bolsa.R;
import com.g06.bolsa.clases_auxiliares.DatoPerfil;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;

public class DatosPerActualizarActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ControlBDp helper;
    EditText nombreCandidato;
    EditText apellidoCandidato;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_per_actualizar);

        // Instancia de la clase que controla la base de datos.
        helper = new ControlBDp(this);

        // Referencia a los widgets de la interfaz.
        nombreCandidato = findViewById(R.id.anombre);
        apellidoCandidato = findViewById(R.id.aapellido);
        //departamentos
        Spinner spinner=findViewById(R.id.spinnerdepto);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.departamentos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //municipios
        Spinner spinner2=findViewById(R.id.spinnermunicipio);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.municipios_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }
   /* public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_datos_per_actualizar,container,false);
        Spinner spinner= (Spinner)view.findViewById(R.id.spinnerdepto);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(view.getContext(),R.array.departamentos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return view;
    }*/
    public void actualizar(View v) {
        DatoPerfil dp = new DatoPerfil();
        dp.setNombreCandidato(nombreCandidato.getText().toString());
        dp.setApellidoCandidato(apellidoCandidato.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(dp);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarT(View view) {
        nombreCandidato.setText("");
        apellidoCandidato.setText("");

    }
    public void actualizardp(View view) {
        DatoPerfil de = new DatoPerfil();
        de.setNombreCandidato(nombreCandidato.getText().toString());
        de.setApellidoCandidato(apellidoCandidato.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(de);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

      //  String text=parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}