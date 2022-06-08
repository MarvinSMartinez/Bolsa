package com.g06.bolsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.g06.bolsa.clases_auxiliares.PerfilCandidato;

public class ControlBDp {
    private static final String[]camposPerfil = new String []
            {"ID_CANDIDATO","ID_DEPARTAMENTO","ID_USUARIO",
                    "NOMBRES_CANDIDATO", "APELLIDOS_CANIDATO",
                    "DUI_CANDIDATO","NIT_CANDIDATO"};

    private final Context context;
    private ControlBDp.DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDp(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlBDp.DatabaseHelper(context);
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
    public String llenarBD() {return "DB llenada correctamente";}

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista
                PerfilCandidato datoPerfil = (PerfilCandidato) dato;
                String[] id = {datoPerfil.getIdperfilcandidato()};
                abrir();
                Cursor c2 = db.query("PERFIL_CANDIDATO", null, "ID_CANDIDATO = ?", id, null, null,
                        null);
                if (c2.moveToFirst()) {
                    //Se encontro
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }

    /*public String insertardatop(DatoPerfil dp) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        ContentValues ofer = new ContentValues();
        ofer.put("ID_CANDIDATO", dp.getIdCandidato());
        ofer.put("ID_DEPARTAMENTO", dp.getDepartamento());
        ofer.put("ID_USUARIO",dp.getIdUsuario());
        ofer.put("NOMBRES_CANDIDATO", dp.getNombreCandidato());
        ofer.put("APELLIDOS_CANDIDATO", dp.getApellidoCandidato());
        ofer.put("DUI_CANDIDATO",dp.getDui());
        ofer.put("NIT_CANDIDATO",dp.getNit());
        contador=db.insert("PERFIL_CANDIDATO", null, ofer);
        if(contador==-1 || contador==0)
        {
            registrosInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados=registrosInsertados+contador;
        }

        return registrosInsertados;
    }*/
    public String insertar(PerfilCandidato de) {
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        //if (verificarIntegridad(de,3)) {
        ContentValues alum = new ContentValues();
        alum.put("ID_CANDIDATO", de.getIdperfilcandidato());
        alum.put("ID_DEPARTAMENTO ", de.getIddepartamento());
        alum.put("ID_USUARIO", de.getIdusuario());
        alum.put("NOMBRES_CANDIDATO", de.getNombre());
        alum.put("APELLIDOS_CANIDATO", de.getApellido());
        alum.put("DUI_CANDIDATO", de.getDui());
        alum.put("NIT_CANDIDATO", de.getNit());

        contador = db.insert("PERFIL_CANDIDATO", null, alum);
        // }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro: Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizardp(PerfilCandidato dp) {
        if(verificarIntegridad(dp, 1)){
            String[] id = {dp.getIdperfilcandidato()};
            ContentValues cv = new ContentValues();
           // cv.put("ID_DEPARTAMENTO",dp.getIddepartamento());
           // cv.put("ID_USUARIO",dp.getIdusuario());
            cv.put("NOMBRES_CANDIDATO", dp.getNombre());
            cv.put("APELLIDOS_CANIDATO", dp.getApellido());
            cv.put("DUI_CANDIDATO",dp.getDui());
            cv.put("NIT_CANDIDATO",dp.getNit());
           // cv.put("");
            db.update("PERFIL_CANDIDATO", cv, "ID_CANDIDATO = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id " + dp.getIdperfilcandidato() + " no existe";
        }
    }



    public PerfilCandidato consultardp(String id) {
        String[] ids = {id};
        Cursor cursor = db.query("PERFIL_CANDIDATO"
                , camposPerfil, "ID_CANDIDATO = ?", ids, null, null, null);

        if(cursor.moveToFirst()){
            PerfilCandidato cand = new PerfilCandidato();
            cand.setIdperfilcandidato(cursor.getString(0));
            cand.setIddepartamento(cursor.getString(1));
            cand.setIdusuario(cursor.getString(2));
            cand.setNombre(cursor.getString(3));
            cand.setApellido(cursor.getString(4));
            cand.setDui(cursor.getString(5));
            cand.setNit(cursor.getString(6));

            return cand;
        }else{
            return null;
        }
    }

    public String eliminardp(PerfilCandidato dp) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("PERFIL_CANDIDATO", "ID_CANDIDATO='"+dp.getIdperfilcandidato()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }


}
