package com.g06.bolsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;
import com.g06.bolsa.clases_auxiliares.DatoPerfil;
import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;

public class ControlBDp {
    private static final String[]camposPerfil = new String []
            {"id_candidato","nombres_candidato","apellidos_candidato","departamento", "municipio"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDp(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "tarea.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(@Nullable Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }


    public void abrir() {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    public String actualizar(DatoPerfil dp) {
        if(verificarIntegridad(dp, 3)){
            String[] id = {dp.getIdCandidato()};
            ContentValues cv = new ContentValues();
            cv.put("nombre", dp.getNombreCandidato());
            cv.put("apellido", dp.getApellidoCandidato());
            db.update("PERFIL_CANDIDATO", cv, "ID_CANDIDATO = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id " + dp.getIdCandidato() + " no existe";
        }
    }
    public String llenarBD() {return "DB llenada correctamente";}

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista
                DatoPerfil datoPerfil = (DatoPerfil) dato;
                String[] id = {datoPerfil.getIdCandidato()};
                abrir();
                Cursor c2 = db.query("PERFIL_CANDIDATO", null, "ID_CANDIDATO = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro
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
            default:
                return false;

        }
    }


    public DatoPerfil consultardp(String id) {
        String[] ids = {id};
        Cursor cursor = db.query("PERFIL_CANDIDATO",
                new String[] {"ID_CANDIDATO","nombres_candidato","apellidos_candidato","departamento","municipio"},
                "id_candidato = ?",
                ids, null, null, null);

        if(cursor.moveToFirst()){
            DatoPerfil cand = new DatoPerfil();
            cand.setIdCandidato(cursor.getString(0));
            cand.setIdCandidato(cursor.getString(1));
            cand.setIdCandidato(cursor.getString(2));

            return cand;
        }else{
            return null;
        }
    }

    public String insertar(DatoPerfil dp) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        ContentValues ofer = new ContentValues();
        ofer.put("ID_CANDIDATO", dp.getIdCandidato());
        ofer.put("NOMBRES_CANDIDATO", dp.getNombreCandidato());
        ofer.put("APELLIDOS_CANDIDATO", dp.getApellidoCandidato());
        ofer.put("DEPARTAMENTO", dp.getDepartamento());
        ofer.put("MUNICIPIO", dp.getMunicipio());
        contador=db.insert("PERFIL_CANDIDATO", null, ofer);
        if(contador==-1 || contador==0)
        {
            registrosInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados=registrosInsertados+contador;
        }

        return registrosInsertados;
    }

    public String eliminardp(DatoPerfil dp) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("PERFIL_CANDIDATO", "ID_CANDIDATO='"+dp.getIdCandidato()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }


}
