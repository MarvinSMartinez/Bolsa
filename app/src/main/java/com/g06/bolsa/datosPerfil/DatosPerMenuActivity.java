package com.g06.bolsa.datosPerfil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.g06.bolsa.R;

public class DatosPerMenuActivity extends ListActivity {
    String[] menu={
            "Insertar dato al perfil",
            "Eliminar dato del perfil",
            "Consultar dato del perfil",
            "Actualizar dato del perfil"};

    String[] activities={
            "DatosPerInsertar",
            "DatosPerElimninar",
            "DatosPerConsultar",
            "DatosPerActualizarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_datos_per_menu);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu));

        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(203, 222, 251));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(203, 222, 251));
        try{
            Class<?> clase=Class.forName("com.g06.bolsa.datosPerfil." + nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}