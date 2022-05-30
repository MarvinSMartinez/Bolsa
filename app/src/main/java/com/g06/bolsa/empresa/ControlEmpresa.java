package com.g06.bolsa.empresa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.Empresa;
import com.g06.bolsa.departamento.ControlDepartamento;

public class ControlEmpresa {
    private static final String[]camposEmpresa = new String []
            {"ID_EMPRESA","ID_DEPARTAMENTO","RAZON_SOCIAL_EMPRESA","NOMBRE_EMPRESA","DIRECCION_EMPRESA"};
    private final Context context;
    private ControlEmpresa.DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlEmpresa(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlEmpresa.DatabaseHelper(context);
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

    public String insertarEmpresa(Empresa empresa) {
        if(verificarIntegridad(empresa,2)) {
            String registrosInsertados = "Registro insertado Nº= ";
            long contador = 0;
            ContentValues values = new ContentValues();
            values.put("ID_EMPRESA", empresa.getId());
            values.put("ID_DEPARTAMENTO", empresa.getIdDepartamento());
            values.put("RAZON_SOCIAL_EMPRESA", empresa.getRazon_social());
            values.put("NOMBRE_EMPRESA", empresa.getNombre());
            values.put("DIRECCION_EMPRESA", empresa.getDireccion());


            contador = db.insert("EMPRESA", null, values);
            if (contador == -1 || contador == 0) {
                registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                registrosInsertados = registrosInsertados + contador;
            }

            return registrosInsertados;
        }else{
            return "Registro con id " + empresa.getIdDepartamento() + " no existe";
        }
    }

    public String actualizarEmpresa(Empresa empresa) {
        if (verificarIntegridad(empresa, 1)) {
            if(verificarIntegridad(empresa,2)){
            String[] id = {empresa.getId()};
            ContentValues values = new ContentValues();
            values.put("ID_DEPARTAMENTO", empresa.getIdDepartamento());
            values.put("RAZON_SOCIAL_EMPRESA", empresa.getRazon_social());
            values.put("NOMBRE_EMPRESA",empresa.getNombre());
            values.put("DIRECCION_EMPRESA",empresa.getDireccion());

            db.update("EMPRESA", values, "id_oferta = ?", id);
            return "Registro Actualizado Correctamente";
        }else {
            return "Registro con id " + empresa.getIdDepartamento() + " no existe";
        } }else {
            return "Registro con id " + empresa.getId() + " no existe";


        }
    }
    public Empresa consultarEmpresa(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("EMPRESA", camposEmpresa, "ID_EMPRESA = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Empresa empresa = new Empresa();
            empresa.setId(cursor.getString(0));
            empresa.setIdDepartamento(cursor.getString(1));
            empresa.setRazon_social(cursor.getString(2));
            empresa.setNombre(cursor.getString(3));
            empresa.setDireccion(cursor.getString(4));
            return empresa;
        }else{
            return null;
        }
    }
    public String eliminarEmpresa(Empresa empresa){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("EMPRESA", "ID_EMPRESA='"+empresa.getId()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista Empresa
                Empresa empresa = (Empresa) dato;
                String[] id = {empresa.getId()};
                ;
                Cursor c2 = db.query("EMPRESA", null, "ID_EMPRESA = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro oferta
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista Departamento
                Empresa empresa = (Empresa) dato;
                String[] id = {empresa.getIdDepartamento()};
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

