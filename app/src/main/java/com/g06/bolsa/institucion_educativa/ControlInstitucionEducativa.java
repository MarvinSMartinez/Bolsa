package com.g06.bolsa.institucion_educativa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.InstitucionEducativa;
import com.g06.bolsa.clases_auxiliares.Municipio;
import com.g06.bolsa.municipio.ControlMunicipio;

public class ControlInstitucionEducativa {
    private static final String[]camposInstitucion = new String []
            {"ID_INSTITUCION","ID_DEPARTAMENTO","NOMBRE_INSTITUCION",};
    private final Context context;
    private ControlInstitucionEducativa.DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlInstitucionEducativa(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlInstitucionEducativa.DatabaseHelper(context);
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

    public String insertarInstitucionEducativa(InstitucionEducativa ie) {
        if(verificarIntegridad(ie,2)){
            String registrosInsertados = "Registro insertado Nº= ";
            long contador = 0;

            ContentValues values = new ContentValues();
            values.put("ID_INSTITUCION", ie.getIdIstitucion());
            values.put("ID_DEPARTAMENTO", ie.getIdDepartamento());
            values.put("NOMBRE_INSTITUCION", ie.getNombreInstitucion());

            contador = db.insert("INSTITUCION_EDUCATIVA", null, values);
            if (contador == -1 || contador == 0) {
                registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                registrosInsertados = registrosInsertados + contador;
            }

            return registrosInsertados;
        }else{
            return "Registro con id institucion " + ie.getNombreInstitucion() + " no existe";
        }}

    public String actualizarInstitucionEducativa(InstitucionEducativa ie) {
        if (verificarIntegridad(ie, 1)) {
            if(verificarIntegridad(ie,2)){
                String[] id = {ie.getIdIstitucion()};
                ContentValues values = new ContentValues();
                values.put("ID_DEPARTAMENTO", ie.getIdDepartamento());
                values.put("NOMBRE_INSTITUCION", ie.getNombreInstitucion());
                db.update("INSTITUCION_EDUCATIVA", values, "ID_INSTITUCION = ?", id);
                return "Registro Actualizado Correctamente";
            } else {
                return "Registro con departamento " + ie.getIdDepartamento() + " no existe";
            }}else{
            return "Registro con id " + ie.getIdIstitucion() + " no existe";
        }}
    public InstitucionEducativa consultarInstitucionEducativa(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("INSTITUCION_EDUCATIVA", camposInstitucion, "ID_INSTITUCION = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            InstitucionEducativa ie = new InstitucionEducativa();
            ie.setIdIstitucion(cursor.getString(0));
            ie.setIdDepartamento(cursor.getString(1));
            ie.setNombreInstitucion(cursor.getString(2));
            return ie;
        }else{
            return null;
        }
    }
    public String eliminarInstitucionEducativa(InstitucionEducativa ie){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("INSTITUCION_EDUCATIVA", "ID_INSTITUCION='"+ie.getIdIstitucion()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista Institucion Educativa
                InstitucionEducativa ie = (InstitucionEducativa) dato;
                String[] id = {ie.getIdIstitucion()};
                abrir();
                Cursor c2 = db.query("INSTITUCION_EDUCATIVA", null, "ID_INSTITUCION = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro oferta
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista Departamento
                InstitucionEducativa ie = (InstitucionEducativa) dato;
                String[] id = {ie.getIdDepartamento()};
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

