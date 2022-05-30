package com.g06.bolsa.municipio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.g06.bolsa.clases_auxiliares.Municipio;

public class ControlMunicipio {
    private static final String[]camposMunicipio = new String []
            {"ID_MUNICIPIO","ID_DEPARTAMENTO","NOMBRE_MUNICIPIO",};
    private final Context context;
    private ControlMunicipio.DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlMunicipio(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlMunicipio.DatabaseHelper(context);
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

    public String insertarMunicipio(Municipio municipio) {
        if(verificarIntegridad(municipio,2)){
        String registrosInsertados = "Registro insertado Nº= ";
        long contador = 0;

        ContentValues values = new ContentValues();
        values.put("ID_MUNICIPIO", municipio.getId());
        values.put("ID_DEPARTAMENTO", municipio.getIdDepartamento());
        values.put("NOMBRE_MUNICIPIO", municipio.getNombre());

        contador = db.insert("MUNICIPIO", null, values);
        if (contador == -1 || contador == 0) {
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            registrosInsertados = registrosInsertados + contador;
        }

        return registrosInsertados;
    }else{
            return "Registro con departamento " + municipio.getIdDepartamento() + " no existe";
        }}

    public String actualizarMunicipio(Municipio municipio) {
        if (verificarIntegridad(municipio, 1)) {
         if(verificarIntegridad(municipio,2)){
            String[] id = {municipio.getId()};
            ContentValues values = new ContentValues();
            values.put("ID_DEPARTAMENTO", municipio.getIdDepartamento());
            values.put("NOMBRE_MUNICIPIO", municipio.getNombre());
            db.update("DEPARTAMENTO", values, "ID_MUNICIPIO = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con departamento " + municipio.getIdDepartamento() + " no existe";
        }}else{
            return "Registro con id " + municipio.getId() + " no existe";
        }}
    public Municipio consultarMunicipio(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("MUNICIPIO", camposMunicipio, "ID_MUNICIPIO = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Municipio municipio = new Municipio();
            municipio.setId(cursor.getString(0));
            municipio.setIdDepartamento(cursor.getString(1));
            municipio.setNombre(cursor.getString(2));
            return municipio;
        }else{
            return null;
        }
    }
    public String eliminarMunicipio(Municipio municipio){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("MUNICIPIO", "ID_MUNICIPIO='"+municipio.getId()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista Municipio
                Municipio municipio = (Municipio) dato;
                String[] id = {municipio.getId()};
                ;
                Cursor c2 = db.query("MUNICIPIO", null, "ID_MUNICIPIO = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro oferta
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista Departamento
                Municipio municipio = (Municipio) dato;
                String[] id = {municipio.getIdDepartamento()};
                abrir();
                Cursor c2 = db.query("DEPARTAMENTO", null, "ID_DEPARTAMENTO = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro DEPARTAMENTO
                    return true;
                }
                return false;
            }
            default:
                return false;

        }
    }
}
