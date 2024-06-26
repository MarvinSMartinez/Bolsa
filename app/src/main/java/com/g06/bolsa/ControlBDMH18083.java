package com.g06.bolsa;

import android.content.ContentValues;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.Aspirante;
import com.g06.bolsa.clases_auxiliares.DetalleOferta;
import com.g06.bolsa.clases_auxiliares.OfertaLaboral;
import com.g06.bolsa.clases_auxiliares.Usuario;
import com.g06.bolsa.empresa.ControlEmpresa;

import java.util.Date;

public class ControlBDMH18083 {

    private static final String[]camposOferta = new String []
            {"id_oferta","id_empresa","inicio_oferta","fin_oferta", "nombre_oferta"};
    private static final String[]camposDetalleOferta = new String []
            {"id_detalle_oferta","id_oferta","perfil","salario_oferta"};
    private static final String[] camposAspirante = new String []
            {"id_aspirante","id_detalle_oferta","id_empresa","estado"};

    private final Context context;
    private ControlBDMH18083.DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private ControlEmpresa helperEmpresa;

    public ControlBDMH18083(Context ctx) {
        this.context = ctx;
        DBHelper = new ControlBDMH18083.DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
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

    //Llenar base
    public String llenarBD() {return "DB llenada correctamente";}

    //verificar integridad
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista oferta
                OfertaLaboral ofertaLaboral = (OfertaLaboral) dato;
                String[] id = {ofertaLaboral.getIdOferta()};
                abrir();
                Cursor c2 = db.query("OFERTA_LABORAL", null, "id_oferta = ?", id, null, null,
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
            case 4: {
                //verificar que al insertar oferta exista empresa
                OfertaLaboral ofertaLaboral = (OfertaLaboral) dato;
                String[] id2 = {ofertaLaboral.getIdEmpresa()};
                //abrir();
                Cursor cursor2 = db.query("empresa", null, "id_empresa = ?", id2,
                        null, null, null);
                if(cursor2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 5:{
                //verificar que al ingresar detalle exista oferta
                DetalleOferta detalleOferta = (DetalleOferta) dato;
                String[] id2 = {detalleOferta.getIdOferta()};
                //abrir();
                Cursor cursor2 = db.query("oferta_laboral", null, "id_oferta = ?", id2,
                        null, null, null);
                if(cursor2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 6:{
                //verificar que al ingresar aspirante exista detalle y empresa
                Aspirante aspirante = (Aspirante) dato;
                String[] id1 = {aspirante.getIdDetalleOferta()};
                String[] id2 = {aspirante.getIdEmpresa()};
                //abrir();
                Cursor cursor1 = db.query("detalles_oferta", null, "id_detalle_oferta= ?", id1,
                        null, null, null);
                Cursor cursor2 = db.query("empresa", null, "id_empresa= ?", id2,
                        null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 7:{
                //Verificacion que no existan detalles para eliminar oferta
                OfertaLaboral ofertaLaboral = (OfertaLaboral) dato;
                Cursor c=db.query(true, "detalles_oferta", new String[] {
                                "carnet" }, "carnet='"+ofertaLaboral.getIdOferta()+"'",null,
                        null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            default:
                return false;

        }
    }


    //Inserciones
    public String insertar(OfertaLaboral ofertaLaboral) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        if(verificarIntegridad(ofertaLaboral, 4)) {
            ContentValues ofer = new ContentValues();
            ofer.put("id_oferta", ofertaLaboral.getIdOferta());
            ofer.put("id_empresa", ofertaLaboral.getIdEmpresa());
            ofer.put("inicio_oferta", ofertaLaboral.getInicioOferta());
            ofer.put("fin_oferta", ofertaLaboral.getFinOferta());
            ofer.put("nombre_oferta", ofertaLaboral.getNombreOferta());
            contador = db.insert("OFERTA_LABORAL", null, ofer);
            if (contador == -1 || contador == 0) {
                registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                registrosInsertados = registrosInsertados + contador;
            }
        }else{
            registrosInsertados="No existe empresa";
        }

        return registrosInsertados;
    }
    public String insertar(Aspirante aspirante) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        if(verificarIntegridad(aspirante,6)) {
            ContentValues asp = new ContentValues();
            asp.put("id_aspirante", aspirante.getIdAspirante());
            asp.put("id_empresa", aspirante.getIdEmpresa());
            asp.put("id_detalle_oferta", aspirante.getIdDetalleOferta());
            asp.put("estado", aspirante.getEstado());
            contador = db.insert("ASPIRANTE", null, asp);
            if (contador == -1 || contador == 0) {
                registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                registrosInsertados = registrosInsertados + contador;
            }
        }else{
            registrosInsertados="No existe empresa o detalle de la oferta";
        }
        return registrosInsertados;
    }

    public String insertar(DetalleOferta detalleOferta) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        if(verificarIntegridad(detalleOferta,5)) {
            ContentValues detalle = new ContentValues();
            detalle.put("id_detalle_oferta", detalleOferta.getIdDetalleOferta());
            detalle.put("id_oferta", detalleOferta.getIdOferta());
            detalle.put("perfil", detalleOferta.getPerfil());
            detalle.put("salario_oferta", detalleOferta.getSalarioOferta());
            contador = db.insert("DETALLES_OFERTA", null, detalle);
            if (contador == -1 || contador == 0) {
                registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                registrosInsertados = registrosInsertados + contador;
            }
        }else {
            registrosInsertados = "No existe oferta";
        }
        return registrosInsertados;
    }

    //Consultas
    public OfertaLaboral consultarOferta(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("OFERTA_LABORAL", camposOferta, "id_oferta = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            OfertaLaboral ofertaLaboral = new OfertaLaboral();
            ofertaLaboral.setIdOferta(cursor.getString(0));
            ofertaLaboral.setIdEmpresa(cursor.getString(1));
            ofertaLaboral.setInicioOferta(cursor.getString(2));
            ofertaLaboral.setFinOferta(cursor.getString(3));
            ofertaLaboral.setNombreOferta(cursor.getString(4));
            return ofertaLaboral;
        }else{
            return null;
        }
    }

    public DetalleOferta consultarDetalle(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("DETALLES_OFERTA", camposDetalleOferta, "id_detalle_oferta = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetalleOferta detalleOferta = new DetalleOferta();
            detalleOferta.setIdDetalleOferta(cursor.getString(0));
            detalleOferta.setIdOferta(cursor.getString(1));
            detalleOferta.setPerfil(cursor.getString(2));
            detalleOferta.setSalarioOferta(cursor.getString(3));
            return detalleOferta;
        }else{
            return null;
        }
    }

    public Aspirante consultarAspirante(String idO){
        String[] id = {idO};
        Cursor cursor = db.query("ASPIRANTE", camposAspirante, "id_aspirante = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Aspirante aspirante = new Aspirante();
            aspirante.setIdAspirante(cursor.getString(0));
            aspirante.setIdDetalleOferta(cursor.getString(1));
            aspirante.setIdEmpresa(cursor.getString(2));
            aspirante.setEstado(cursor.getInt(3));
            return aspirante;
        }else{
            return null;
        }
    }

    //Actualizaciones
    public String actualizarOferta(OfertaLaboral ofertaLaboral){
        if(verificarIntegridad(ofertaLaboral, 1)){
            String[] id = {ofertaLaboral.getIdOferta()};
            ContentValues cv = new ContentValues();
            cv.put("inicio_oferta", ofertaLaboral.getInicioOferta());
            cv.put("fin_oferta", ofertaLaboral.getFinOferta());
            cv.put("nombre_oferta", ofertaLaboral.getNombreOferta());
            db.update("OFERTA_LABORAL", cv, "id_oferta = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con carnet " + ofertaLaboral.getIdOferta() + " no existe";
        }
    }

    public String actualizarDetalle(DetalleOferta detalleOferta){
        if(verificarIntegridad(detalleOferta, 3)){
            String[] id = {detalleOferta.getIdOferta()};
            ContentValues cv = new ContentValues();
            cv.put("perfil", detalleOferta.getPerfil());
            cv.put("salario_oferta", detalleOferta.getSalarioOferta());
            db.update("DETALLES_OFERTA", cv, "id_detalle_oferta = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id " + detalleOferta.getIdDetalleOferta() + " no existe";
        }
    }

    public String actualizarAspirante(Aspirante aspirante){
        if(verificarIntegridad(aspirante, 2)){
            String[] id = {aspirante.getIdAspirante()};
            ContentValues cv = new ContentValues();
            cv.put("estado", aspirante.getEstado());
            db.update("ASPIRANTE", cv, "id_aspirante = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id " + aspirante.getIdAspirante() + " no existe";
        }
    }

    //Eliminaciones
    public String eliminar(OfertaLaboral ofertaLaboral){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("OFERTA_LABORAL", "id_oferta='"+ofertaLaboral.getIdOferta()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(DetalleOferta detalleOferta){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("DETALLES_OFERTA", "id_detalle_oferta='"+detalleOferta.getIdDetalleOferta()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(Aspirante aspirante){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("ASPIRANTE", "id_aspirante='"+aspirante.getIdAspirante()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

}
