package com.g06.bolsa.departamento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class ControlDepartamento {
    private static final String[]camposDepartamento = new String []
            {"ID_DEPARTAMENTO","NOMBRE_DEPARTAMENTO",};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlDepartamento(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlDepartamento.DatabaseHelper(context);
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

    public String insertarDepartamento(Departamento departamento) {
        String registrosInsertados = "Registro insertado Nº= ";
        long contador = 0;
        ContentValues values = new ContentValues();
        values.put("ID_DEPARTAMENTO", departamento.getId());
        values.put("NOMBRE_DEPARTAMENTO", departamento.getNombre());

        contador = db.insert("DEPARTAMENTO", null, values);
        if (contador == -1 || contador == 0) {
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            registrosInsertados = registrosInsertados + contador;
        }

        return registrosInsertados;
    }

    public String actualizarDepartamento(Departamento departamento) {
        if (verificarIntegridad(departamento, 1)) {
            String[] id = {departamento.getId()};
            ContentValues values = new ContentValues();
            values.put("NOMBRE_DEPARTAMENTO", departamento.getNombre());
            db.update("DEPARTAMENTO", values, "id_oferta = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con carnet " + departamento.getId() + " no existe";


        }
    }
    public Departamento consultarDepartamento(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("DEPARTAMENTO", camposDepartamento, "ID_DEPARTAMENTO = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Departamento departamento = new Departamento();
            departamento.setId(cursor.getString(0));
            departamento.setNombre(cursor.getString(1));
            return departamento;
        }else{
            return null;
        }
    }
    public String eliminarDepartamento(Departamento departamento){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("DEPARTAMENTO", "ID_DEPARTAMENTO='"+departamento.getId()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista departamento
                Departamento departamento = (Departamento) dato;
                String[] id = {departamento.getId()};
                ;
                Cursor c2 = db.query("DEPARTAMENTO", null, "ID_DEPARTAMENTO = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro oferta
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que exista aspirante
                Aspirante aspirante = (Aspirante) dato;
                String[] id = {aspirante.getIdAspirante()};
                abrir();
                Cursor c2 = db.query("ASPIRANTE", null, "id_aspirante = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro aspirante
                    return true;
                }
                return false;
            }
            case 3: {
                //verificar que exista detalle
                DetalleOferta detalleOferta = (DetalleOferta) dato;
                String[] id = {detalleOferta.getIdDetalleOferta()};
                abrir();
                Cursor c2 = db.query("DETALLES_OFERTA", null, "id_detalle_oferta = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro detalle
                    return true;
                }
                return false;
            }
            default:
                return false;

        }
    }
}
