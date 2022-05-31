package com.g06.bolsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.Evaluacion;
import com.g06.bolsa.clases_auxiliares.Puesto;

public class ControlBDCD17008 {

    private static final String[]camposPuesto = new String[]
            {"id_puesto","id_oferta","id_area","nombre_puesto","vacantes_puesto"};
    private static final String[]camposEvaluacion = new String[]
            {"id_evaluacion","id_aplicacion","tipo_evaluacion","fecha_evaluacion","estado_evaluacion"};

    private final Context context;
    private ControlBDCD17008.DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDCD17008(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlBDCD17008.DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "tarea2.s3db";
        private static final int VERSION = 1;

        // constructor.
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

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

    //Llenar base
    public String llenarBD() {return "DB llenada correctamente";}

    //verificar integridad
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista puesto
                Puesto puesto = (Puesto) dato;
                String[] id = {puesto.getId()};
                abrir();
                Cursor c2 = db.query("PUESTO", null, "ID_PUESTO = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro puesto
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista evaluacion
                Evaluacion eva = (Evaluacion) dato;
                String [] id={eva.getId()};
                abrir();
                Cursor c2 = db.query("EVALUACION", null, "id_evaluacion = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro evaluacion
                    return true;
                }
                return false;
            }
            default:
                return false;

        }
    }

////////Create
    public String insertarPuesto(Puesto puesto) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        ContentValues pues = new ContentValues();
        pues.put("id_puesto", puesto.getId());
        pues.put("id_oferta", puesto.getIdOferta());
        pues.put("id_area", puesto.getIdArea());
        pues.put("nombre_puesto", puesto.getNomPuesto());
        pues.put("vacantes_puesto", puesto.getVacPuesto());
        contador=db.insert("PUESTO", null, pues);
        if(contador==-1 || contador==0)
        {
            registrosInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados=registrosInsertados+contador;
        }

        return registrosInsertados;
    }

    public String insertarEvaluacion(Evaluacion evaluacion) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        ContentValues eva = new ContentValues();
        eva.put("id_evaluacion", evaluacion.getId());
        eva.put("id_aplicacion", evaluacion.getIdAplicacion());
        eva.put("tipo_evaluacion", evaluacion.getTipo());
        eva.put("fecha_evaluacion", evaluacion.getFecha());

        contador=db.insert("EVALUACION", null, eva);
        if(contador==-1 || contador==0)
        {
            registrosInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados=registrosInsertados+contador;
        }

        return registrosInsertados;
    }


///////////Read
    public Puesto consultarPuesto(String id0){
        String[] id = {id0};
        Cursor cursor = db.query("PUESTO", camposPuesto, "ID_OFERTA = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Puesto puesto = new Puesto();

            puesto.setId(cursor.getString(0));
            puesto.setIdOferta(cursor.getString(1));
            puesto.setIdArea(cursor.getString(2));
            puesto.setNomPuesto(cursor.getString(3));
            puesto.setVacPuesto(cursor.getString(4));

            return puesto;
        }else{
            return null;
        }
    }

    public Evaluacion consultarEvaluacion(String id0){
        String[] id = {id0};
        Cursor cursor = db.query("EVALUACION", camposEvaluacion, "ID_EVALUACION = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Evaluacion eva = new Evaluacion();
            eva.setId(cursor.getString(0));
            eva.setIdAplicacion(cursor.getString(1));
            eva.setTipo(cursor.getString(2));
            eva.setFecha(cursor.getString(3));
            eva.setEstado(cursor.getString(4));
            return eva;
        }else{
            return null;
        }
    }


//////////Update
    public String actualizarPuesto(Puesto puesto) {
        if (verificarIntegridad(puesto, 1)) {
            String[] id = {puesto.getId()};

            ContentValues pues = new ContentValues();
            //pues.put("ID_PUESTO", puesto.getId());
            pues.put("ID_OFERTA", puesto.getIdOferta());
            pues.put("ID_AREA", puesto.getIdArea());
            pues.put("NOMBRE_PUESTO", puesto.getNomPuesto());
            pues.put("VACANTES_PUESTO", puesto.getVacPuesto());

            db.update("PUESTO", pues, "ID_PUESTO=?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro no existe";
        }
    }

    public String actualizarEvaluacion(Evaluacion evaluacion) {
        if (verificarIntegridad(evaluacion, 2)) {
            String[] id = {evaluacion.getId()};

            ContentValues eva = new ContentValues();
            //pues.put("ID_PUESTO", puesto.getId());
            //eva.put("ID_EVALUACION",evaluacion.getId());
            eva.put("ID_APLICACION",evaluacion.getIdAplicacion());
            eva.put("TIPO_EVALUACION",evaluacion.getTipo());
            eva.put("FECHA_EVALUACION",evaluacion.getFecha());
            eva.put("ESTADO_EVALUACION",evaluacion.getEstado());

            db.update("EVALUACION", eva, "ID_EVALUACION=?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro no existe";
        }
    }

//////////Delete
    public String eliminarPuesto(Puesto puesto){
        String regAfectados="filas afectadas= ";
        int contador = 0;
        String where = "ID_PUESTO='" + puesto.getId() + "'";
        contador += db.delete("PUESTO", where, null);
        regAfectados += contador;
        return regAfectados;
    }

    public String eliminarEvaluacion(Evaluacion eva){
        String regAfectados="filas afectadas= ";
        int contador = 0;
        String where = "ID_EVALUACION='" + eva.getId() + "'";
        contador += db.delete("EVALUACION", where, null);
        regAfectados += contador;
        return regAfectados;
    }


}