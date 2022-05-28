package com.g06.bolsa.area_trabajo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.Area_Trabajo;

public class ControlArea_trabajo {
    private static final String[]camposArea_Trabajo = new String []
            {"ID_AREA","NOMBRE_AREA",};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlArea_trabajo(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlArea_trabajo.DatabaseHelper(context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "tarea.s3db";
        private static final int VERSION = 1;

        // constructor.
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Crear Base
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }

    //Abrir base
    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    //Cerrar base
    public void cerrar(){
        DBHelper.close();
    }

    public String insertarArea_Trabajo(Area_Trabajo area) {
        String registrosInsertados = "Registro insertado Nº= ";
        long contador = 0;
        ContentValues values = new ContentValues();
        values.put("ID_AREA", area.getId());
        values.put("NOMBRE_Area", area.getNombre());

        contador = db.insert("AREA_TRABAJO", null, values);
        if (contador == -1 || contador == 0) {
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            registrosInsertados = registrosInsertados + contador;
        }

        return registrosInsertados;
    }

    public String actualizarArea_Trabajo(Area_Trabajo area) {
        if (verificarIntegridad(area, 1)) {
            String[] id = {area.getId()};
            ContentValues values = new ContentValues();
            values.put("NOMBRE_AREA", area.getNombre());
            db.update("AREA_TRABAJO", values, "id_oferta = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con carnet " + area.getId() + " no existe";


        }
    }
    public Area_Trabajo consultarArea_Trabajo(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("AREA_TRABAJO", camposArea_Trabajo, "ID_AREA = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Area_Trabajo area = new Area_Trabajo();
            area.setId(cursor.getString(0));
            area.setNombre(cursor.getString(1));
            return area;
        }else{
            return null;
        }
    }
    public String eliminarArea_Trabajo(Area_Trabajo area){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("AREA_TRABAJO", "ID_AREA='"+area.getId()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista Area_Trabajo
                Area_Trabajo area = (Area_Trabajo) dato;
                String[] id = {area.getId()};
                ;
                Cursor c2 = db.query("AREA_TRABAJO", null, "ID_AREA = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro oferta
                    return true;
                }
                return false;
            }

            default:
                return false;

        }
    }
}
