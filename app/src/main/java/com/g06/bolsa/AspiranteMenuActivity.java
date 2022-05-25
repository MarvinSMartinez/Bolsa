package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AspiranteMenuActivity extends ListActivity {

    String[] menu={"Insertar Aspirante","Eliminar Aspirante","Consultar Aspirante",
            "Actualizar Aspirante"};
    String[]
            activities={"AspiranteInsertarActivity","AspiranteEliminarActivity","AspiranteConsultarActivity",
            "AspiranteActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(255, 192, 203));
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));

        try{
            Class<?> clase=Class.forName("com.g06.bolsa."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}