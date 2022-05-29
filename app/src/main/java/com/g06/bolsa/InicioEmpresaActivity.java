package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class InicioEmpresaActivity extends ListActivity {


    String[] menu={"Oferta Laboral","Detalle de Oferta", "Aspirantes","Puestos","Evaluaciones"};
    String[]
            activities={"OfertaMenuActivity","DetalleOfertaMenuActivity", "AspiranteMenuActivity","PuestoMenuActivity","EvaluacionMenuActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        try{
            Class<?>
                    clase=Class.forName("com.g06.bolsa."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
 