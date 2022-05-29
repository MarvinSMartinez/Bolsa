package com.g06.bolsa.detalle_aplicacion;



import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DetalleAplicacionMenuActivity extends ListActivity {
    String[] menu={"Insertar dato detalle aplicación",
            "Eliminar dato detalle aplicación",
            "Consultar dato detalle aplicación",
            "Actualizar dato detalle aplicación"};

    String[] activities={"DetalleAplicacionInsertarActivity",
            "DetalleAplicacionEliminarActivity",
            "DetalleAplicacionConsultarActivity",
            "DetalleAplicacionActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(203, 222, 251));
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
            Class<?> clase=Class.forName("com.g06.bolsa.detalle_aplicacion." + nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}