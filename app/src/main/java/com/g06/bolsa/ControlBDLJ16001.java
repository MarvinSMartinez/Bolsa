package com.g06.bolsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.ContactoAspirante;
import com.g06.bolsa.clases_auxiliares.DatoEstudio;
import com.g06.bolsa.clases_auxiliares.DetalleAplicacion;
import com.g06.bolsa.clases_auxiliares.DetalleExperiencia;
import com.g06.bolsa.clases_auxiliares.InstitucionEducativa;
import com.g06.bolsa.clases_auxiliares.PerfilCandidato;
import com.g06.bolsa.clases_auxiliares.Usuario;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.clases_auxiliares.Municipio;

public class ControlBDLJ16001 {

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDLJ16001(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
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
            try{
                db.execSQL("create table DEPARTAMENTO (ID_DEPARTAMENTO CHAR(4) not null,NOMBRE_DEPARTAMENTO  CHAR(32),primary key (ID_DEPARTAMENTO));");
                db.execSQL("create table PERFIL_CANDIDATO (ID_CANDIDATO CHAR(2) not null,ID_DEPARTAMENTO CHAR(4),ID_USUARIO CHAR(4),NOMBRES_CANDIDATO CHAR(32) not null,APELLIDOS_CANIDATO CHAR(32) not null,DUI_CANDIDATO CHAR(10) not null,NIT_CANDIDATO CHAR(17) not null,primary key (ID_CANDIDATO),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO),foreign key (ID_USUARIO) references USUARIO (ID_USUARIO));");
                db.execSQL("create table USUARIO (ID_USUARIO CHAR(4) not null,NOMBRE_USUARIO CHAR(32) not null,PASSWORD_USUARIO CHAR(32) not null,TIPO_USUARIO CHAR(30) not null,IS_LOGGED INTEGER,primary key (ID_USUARIO));");
                db.execSQL("create table OPCIONCRUD (ID_OPCIONCRUD CHAR(4) not null,\"CREATE\" smallint not null,READ smallint not null,\"UPDATE\" smallint not null,\"DELETE\" smallint not null,primary key (ID_OPCIONCRUD));");
                db.execSQL("create table ACCESO_USUARIO (ID_ACCESO CHAR(4) not null,ID_OPCIONCRUD CHAR(4),ID_USUARIO CHAR(4),TIPO_ACCESO CHAR(4) not null,primary key (ID_ACCESO),foreign key (ID_USUARIO) references USUARIO (ID_USUARIO),foreign key (ID_OPCIONCRUD) references OPCIONCRUD (ID_OPCIONCRUD));");
                db.execSQL("create table AREA_TRABAJO (ID_AREA CHAR(4) not null,NOMBRE_AREA CHAR(32) not null,primary key (ID_AREA));");
                db.execSQL("create table EMPRESA (ID_EMPRESA CHAR(4) not null,ID_DEPARTAMENTO CHAR(4),RAZON_SOCIAL_EMPRESA CHAR(32) not null,NOMBRE_EMPRESA CHAR(32) not null,DIRECCION_EMPRESA CHAR(256) not null,primary key (ID_EMPRESA),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO));");
                db.execSQL("create table OFERTA_LABORAL (ID_OFERTA CHAR(4) not null,ID_EMPRESA CHAR(4),INICIO_OFERTA CHAR(10) not null,FIN_OFERTA CHAR(10) not null,NOMBRE_OFERTA CHAR(256) not null,primary key (ID_OFERTA),foreign key (ID_EMPRESA) references EMPRESA (ID_EMPRESA));");
                db.execSQL("create table DETALLES_OFERTA (ID_DETALLE_OFERTA CHAR(4) not null,ID_OFERTA CHAR(4),PERFIL CHAR(256) not null,SALARIO_OFERTA CHAR(4) not null,primary key (ID_DETALLE_OFERTA),foreign key (ID_OFERTA) references OFERTA_LABORAL (ID_OFERTA));");
                db.execSQL("create table ASPIRANTE (ID_ASPIRANTE CHAR(4) not null,ID_DETALLE_OFERTA CHAR(4),ID_EMPRESA CHAR(4),ESTADO smallint not null,primary key (ID_ASPIRANTE),foreign key (ID_DETALLE_OFERTA) references DETALLES_OFERTA (ID_DETALLE_OFERTA),foreign key (ID_EMPRESA) references EMPRESA (ID_EMPRESA));");
                db.execSQL("create table CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACTO),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");
                db.execSQL("create table INSTITUCION_EDUCATIVA (ID_INSTITUCION CHAR(4) not null,ID_DEPARTAMENTO CHAR(4),NOMBRE_INSTITUCION CHAR(32) not null,primary key (ID_INSTITUCION),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO));");
                db.execSQL("create table DATOS_ESTUDIOS (ESTUDIOS_NIVEL CHAR(32) not null,ID_CANDIDATO CHAR(2),ID_INSTITUCION CHAR(4),primary key (ESTUDIOS_NIVEL),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO),foreign key (ID_INSTITUCION) references INSTITUCION_EDUCATIVA (ID_INSTITUCION));");
                db.execSQL("create table DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");
                db.execSQL("create table DETALLE_EXPERIENCIA (ID_DETALLE_EXPERIENCIA CHAR(2) not null,ID_CANDIDATO CHAR(2),LUGAR_EXPERIENCIA CHAR(64) not null,PUESTO_EXPERIENCIA CHAR(64) not null,TIEMPO_EXPERIENCIA TIME,primary key (ID_DETALLE_EXPERIENCIA),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");
                db.execSQL("create table EVALUACION (ID_EVALUACION CHAR(4) not null,ID_APLICACION CHAR(4),TIPO_EVALUACION CHAR(32) not null,FECHA_EVALUACION DATE not null,ESTADO_EVALUACION CHAR(32),primary key (ID_EVALUACION),foreign key (ID_APLICACION) references DETALLE_APLICACION (ID_APLICACION));");
                db.execSQL("create table MUNICIPIO (ID_MUNICIPIO CHAR(4) not null,NOMBRE_MUNICIPIO CHAR(32) not null,ID_DEPARTAMENTO CHAR(4),primary key (ID_MUNICIPIO, NOMBRE_MUNICIPIO),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO));");
                db.execSQL("create table PUESTO (ID_PUESTO CHAR(4) not null,ID_OFERTA CHAR(4),ID_AREA CHAR(4),NOMBRE_PUESTO CHAR(32) not null,VACANTES_PUESTO CHAR(4) not null, primary key (ID_PUESTO),foreign key (ID_OFERTA) references OFERTA_LABORAL (ID_OFERTA),foreign key (ID_AREA) references AREA_TRABAJO (ID_AREA));");
                db.execSQL("create table REFERENCIA_PERSONAL (DUI_REFERENCIA NUMERIC(9) not null,NOMBRE_REFERENCIA CHAR(32) not null,TEL_REFERENCIA CHAR(8) not null,ID_CANDIDATO CHAR(2),primary key (DUI_REFERENCIA, TEL_REFERENCIA),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");

            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    /* Inicio métodos para insertar. */
    public String insertar(Usuario usuario) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;

        ContentValues usuarios = new ContentValues();

        usuarios.put("id_usuario", usuario.getId());
        usuarios.put("nombre_usuario", usuario.getNombre());
        usuarios.put("password_usuario", usuario.getPassword());
        usuarios.put("tipo_usuario", usuario.getTipo());
        usuarios.put("is_logged", 0);

        contador= db.insert("usuario", null, usuarios);

        if (contador==-1 || contador==0) {
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = registrosInsertados + contador;
        }
        return registrosInsertados;
    }

    public String insertar(Departamento departamento) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;

        ContentValues departamentoCV = new ContentValues();

        departamentoCV.put("id_departamento", departamento.getId());
        departamentoCV.put("nombre_departamento", departamento.getNombre());


        contador= db.insert("departamento", null, departamentoCV);

        if (contador==-1 || contador==0) {
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = registrosInsertados + contador;
        }
        return registrosInsertados;
    }

    public String insertar(Municipio municipio) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;

        ContentValues municipioCV = new ContentValues();

        municipioCV.put("ID_MUNICIPIO", municipio.getId());
        municipioCV.put("nombre_municipio", municipio.getNombre());
        municipioCV.put("id_departamento", municipio.getIdDepartamento());


        contador= db.insert("municipio", null, municipioCV);

        if (contador==-1 || contador==0) {
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = registrosInsertados + contador;
        }
        return registrosInsertados;
    }

    public String insertar(DetalleExperiencia de){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if (verificarIntegridad(de,2)) {
            ContentValues alum = new ContentValues();
            alum.put("ID_DETALLE_EXPERIENCIA", de.getId());
            alum.put("ID_CANDIDATO", de.getIdCandidato());
            alum.put("LUGAR_EXPERIENCIA", de.getLugarExperiencia());
            alum.put("PUESTO_EXPERIENCIA", de.getPuestoExperiencia());
            alum.put("TIEMPO_EXPERIENCIA", de.getTiempoExperiencia());
            contador = db.insert("DETALLE_EXPERIENCIA", null, alum);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el dato de experiencia: Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(DatoEstudio de){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if (verificarIntegridad(de,4)) {
            ContentValues alum = new ContentValues();
            alum.put("ESTUDIOS_NIVEL", de.getEstudioNivel());
            alum.put("ID_CANDIDATO", de.getIdCandidato());
            alum.put("ID_INSTITUCION", de.getIdInstitucion());

            contador = db.insert("DATOS_ESTUDIOS", null, alum);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro: Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    ////CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACT

    public String insertar(ContactoAspirante de) {
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if (verificarIntegridad(de,3)) {
            ContentValues alum = new ContentValues();
            alum.put("ID_CONTACTO", de.getId());
            alum.put("ID_CANDIDATO", de.getIdCandidato());
            alum.put("TELEFONO1_CONTACTO", de.getTelefono1());
            alum.put("TELEFONO2_CONTACTO", de.getTelefono2());
            alum.put("CORRE_CONTACTO", de.getCorreo());

            contador = db.insert("CONTACTO_ASPIRANTE", null, alum);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro: Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    //PERFIL_CANDIDATO (ID_CANDIDATO CHAR(2) not null,ID_DEPARTAMENTO CHAR(4),ID_USUARIO CHAR(4),NOMBRES_CANDIDATO CHAR(32) not null,APELLIDOS_CANIDATO CHAR(32) not null,DUI_CANDIDATO CHAR(10) not null,NIT_CANDIDATO CHAR(17) not null
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
    public String insertar(InstitucionEducativa de) {
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        //if (verificarIntegridad(de,3)) {
        ContentValues alum = new ContentValues();
        alum.put("ID_INSTITUCION", de.getIdIstitucion());
        alum.put("ID_DEPARTAMENTO ", de.getIdDepartamento());
        alum.put("NOMBRE_INSTITUCION", de.getNombreInstitucion());


        contador = db.insert("INSTITUCION_EDUCATIVA", null, alum);
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
    //INSTITUCION_EDUCATIVA (ID_INSTITUCION CHAR(4) not null,ID_DEPARTAMENTO CHAR(4),NOMBRE_INSTITUCION CHAR(32) not null,

    //DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));
    public String insertar(DetalleAplicacion municipio) {
        String registrosInsertados="Registro insertado Nº= ";
        long contador = 0;
        if (verificarIntegridad(municipio,6)) {
            ContentValues municipioCV = new ContentValues();

            municipioCV.put("ID_APLICACION", municipio.getId());
            municipioCV.put("ID_CANDIDATO", municipio.getIdCandidato());
            municipioCV.put("ESTADO_APLICACION", municipio.getEstado());


            contador = db.insert("DETALLE_APLICACION", null, municipioCV);
        }
        if (contador==-1 || contador==0) {
            registrosInsertados = "Error al insertar el registro. Verificar inserción";
        }
        else {
            registrosInsertados = registrosInsertados + contador;
        }
        return registrosInsertados;
    }
    /* Fin métodos para insertar. */

    /* Inicio métodos para actualizar. */

    public String actualizar(Usuario usuario){
        if (verificarIntegridad(usuario, 1)) {
            String[] id = {usuario.getId()};
            ContentValues cv = new ContentValues();
            cv.put("is_logged", usuario.isLogged());
            db.update("usuario", cv, "id_usuario = ?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro no existe";
        }
    }

    public String actualizar(DatoEstudio de) {
        if (verificarIntegridad(de, 3)) {
            String[] id = {de.getEstudioNivel()};

            ContentValues cv = new ContentValues();
            cv.put("ID_CANDIDATO", de.getIdCandidato());//ID_CANDIDATO", de.getIdCandidato());
            cv.put("ID_INSTITUCION", de.getIdInstitucion());

            db.update("DATOS_ESTUDIOS", cv, "ESTUDIOS_NIVEL=?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro no existe";
        }
    }

    public String actualizar(DetalleExperiencia de){
        if (verificarIntegridad(de, 2)) {
            String[] id = {de.getId()};
            ContentValues cv = new ContentValues();
            cv.put("ID_CANDIDATO", de.getIdCandidato());//ID_CANDIDATO", de.getIdCandidato());
            cv.put("LUGAR_EXPERIENCIA", de.getLugarExperiencia());
            cv.put("PUESTO_EXPERIENCIA", de.getPuestoExperiencia());
            cv.put("TIEMPO_EXPERIENCIA", de.getTiempoExperiencia());

            db.update("DETALLE_EXPERIENCIA", cv, "ID_DETALLE_EXPERIENCIA= ?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro no existe";
        }
    }

    public String actualizar(ContactoAspirante de){
        if (verificarIntegridad(de, 3)) {
            String[] id = {de.getId()};
            ContentValues cv = new ContentValues();
            cv.put("ID_CONTACTO", de.getIdCandidato());//ID_CANDIDATO", de.getIdCandidato());
            cv.put("ID_CANDIDATO", de.getIdCandidato());
            cv.put("TELEFONO1_CONTACTO", de.getTelefono1());
            cv.put("TELEFONO2_CONTACTO", de.getTelefono2());
            cv.put("CORRE_CONTACTO", de.getTelefono2());
            //CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACTO),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO))

            db.update("CONTACTO_ASPIRANTE", cv, "ID_CONTACTO= ?", id);
            return "Contacto actualizado correctamente";
        }else{
            return "Contacto no existe";
        }
    }

    //DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));
    public String actualizar(DetalleAplicacion de){
        //verificamos que exista el id del candidato en la tabla candidato.
        if (verificarIntegridad(de, 6)) {
            //Si existe, actualizamos.
            String[] id = {de.getId()};
            ContentValues cv = new ContentValues();
            //cv.put("ID_APLICACION", de.getIdCandidato());/si se intenta cambiar dara error
            cv.put("ID_CANDIDATO", de.getIdCandidato());
            cv.put("ESTADO_APLICACION", de.getEstado());

            //CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACTO),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO))

            db.update("DETALLE_APLICACION", cv, "ID_APLICACION = ?", id);
            return "Detalle de aplicación actualizado correctamente";
        }else{
            return "Detalle de aplicación no existe";
        }
    }
    /* Fin métodos para actualizar. */


    /* Inicio métodos para eliminar. */
    public String eliminar(DetalleExperiencia de){
        String regAfectados="filas afectadas= ";
        int contador = 0;
        String where = "ID_DETALLE_EXPERIENCIA='" + de.getId() + "'";
        contador += db.delete("DETALLE_EXPERIENCIA", where, null);
        regAfectados += contador;
        return regAfectados;
    }
    /////DATOS_ESTUDIOS (ESTUDIOS_NIVEL CHAR(32) not null,ID_CANDIDATO CHAR(2),ID_INSTITUCION CHAR(4),primary key (EST
    public String eliminar(DatoEstudio de){
        String regAfectados="filas afectadas= ";
        int contador = 0;
        String where = "ESTUDIOS_NIVEL='" + de.getEstudioNivel() + "'";
        contador += db.delete("DATOS_ESTUDIOS", where, null);
        regAfectados += contador;
        return regAfectados;
    }
    //CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACTO),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO))
    public String eliminar(ContactoAspirante de){
        String regAfectados="filas afectadas= ";
        int contador = 0;
        String where = "ID_CONTACTO='" + de.getId() + "'";
        contador += db.delete("CONTACTO_ASPIRANTE", where, null);
        regAfectados += contador;
        return regAfectados;
    }

    //DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));
    public String eliminar(DetalleAplicacion de){

        String regAfectados="filas afectadas= ";
        int contador = 0;
        if (verificarIntegridad(de, 5)) {
            return "Existen registros asociados.";
        }
        String where = "ID_APLICACION='" + de.getId() + "'";
        contador += db.delete("DETALLE_APLICACION", where, null);
        regAfectados += contador;
        return regAfectados;
    }
    /* Fin métodos para eliminar. */

    /* Inicio métodos para consultar. */

    // Solo se usa para iniciar sesion.
    public Usuario consultarSiExisteUsuario(String nombre, String password) {
        String[] campos = {nombre, password};
        Cursor cursor = db.query("usuario",
                                 new String[]{"id_usuario", "nombre_usuario", "password_usuario",
                                              "tipo_usuario", "is_logged"},
                                 "nombre_usuario = ? and password_usuario = ?",
                                 campos, null, null, null);
        if(cursor.moveToFirst()) {
            Usuario u = new Usuario();
            u.setId(cursor.getString(0));
            u.setNombre(cursor.getString(1));
            u.setPassword(cursor.getString(2));
            u.setTipo(cursor.getString(3));
            u.setLoggedState(cursor.getInt(4));

            return u;
        }else{
            return null;
        }
    }

    // Solo se usa para cerrar sesion.
    public Usuario obtenerUsuarioLogeado(){
        String[] campos = {"1"};

        Cursor cursor = db.query("usuario",
                new String[]{"id_usuario", "nombre_usuario", "password_usuario",
                             "tipo_usuario", "is_logged"},
                "is_logged = ?",
                campos, null, null, null);

        if (cursor.moveToFirst()) {
            Usuario u = new Usuario();
            u.setId(cursor.getString(0));
            u.setNombre(cursor.getString(1));
            u.setPassword(cursor.getString(2));
            u.setTipo(cursor.getString(3));
            u.setLoggedState(cursor.getInt(4));

            return u;
        }else{
            return null;
        }
    }
//DETALLE_EXPERIENCIA (ID_DETALLE_EXPERIENCIA CHAR(2) not null,ID_DETALLE_EXPERIENCIA CHAR(2),LUGAR_EXPERIENCIA CHAR(64) not null,PUESTO_EXPERIENCIA CHAR(64) not null,TIEMPO_EXPERIENCIA TIME
    public DetalleExperiencia consultarDetalleExperiencia(String id) {
        String[] ids = {id};
        Cursor cursor = db.query("DETALLE_EXPERIENCIA",
                new String[] {"ID_DETALLE_EXPERIENCIA","ID_CANDIDATO","LUGAR_EXPERIENCIA", "PUESTO_EXPERIENCIA","TIEMPO_EXPERIENCIA"},
                "ID_DETALLE_EXPERIENCIA = ?",
                ids, null, null, null);

        if(cursor.moveToFirst()){
                DetalleExperiencia alumno = new DetalleExperiencia();
            alumno.setId(cursor.getString(0));
            alumno.setIdCandidato(cursor.getString(1));
            alumno.setLugarExperiencia(cursor.getString(2));
            alumno.setPuestoExperiencia(cursor.getString(3));
            alumno.setTiempoExperiencia(cursor.getString(4));
            return alumno;
        }else{
            return null;
        }
    }

    //DATOS_ESTUDIOS (ESTUDIOS_NIVEL CHAR(32) not null,ID_CANDIDATO CHAR(2),ID_INSTITUCION CHAR(4),primary key (ESTUDIOS_
    public DatoEstudio consultarDatoEstudio(String id) {
        String[] ids = {id};
        Cursor cursor = db.query("DATOS_ESTUDIOS",
                new String[] {"ESTUDIOS_NIVEL","ID_CANDIDATO","ID_INSTITUCION"},
                "ESTUDIOS_NIVEL = ?",
                ids, null, null, null);

        if(cursor.moveToFirst()){
            DatoEstudio alumno = new DatoEstudio();

            alumno.setEstudioNivel(cursor.getString(0));
            alumno.setIdCandidato(cursor.getString(1));
            alumno.setIdInstitucion(cursor.getString(2));

            return alumno;
        }else{
            return null;
        }
    }

    //CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACTO),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO))
    public ContactoAspirante consultarContactoAspirante(String id) {
        String[] ids = {id};
        Cursor cursor = db.query("CONTACTO_ASPIRANTE",
                new String[] {"ID_CONTACTO","ID_CANDIDATO","TELEFONO1_CONTACTO","TELEFONO2_CONTACTO","CORRE_CONTACTO"},
                "ID_CONTACTO = ?",
                ids, null, null, null);

        if(cursor.moveToFirst()){
            ContactoAspirante alumno = new ContactoAspirante();

            alumno.setId(cursor.getString(0));
            alumno.setIdCandidato(cursor.getString(1));
            alumno.setTelefono1(cursor.getString(2));
            alumno.setTelefono2(cursor.getString(3));
            alumno.setCorreo(cursor.getString(4));
            return alumno;
        }else{
            return null;
        }
    }

    //DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));
    public DetalleAplicacion consultarDetalleAplicacion(String id) {
        String[] ids = {id};
        Cursor cursor = db.query("DETALLE_APLICACION",
                new String[] {"ID_APLICACION","ID_CANDIDATO","ESTADO_APLICACION"},
                "ID_APLICACION = ?",
                ids, null, null, null);

        if(cursor.moveToFirst()){
            DetalleAplicacion alumno = new DetalleAplicacion();

            alumno.setId(cursor.getString(0));
            alumno.setIdCandidato(cursor.getString(1));
            alumno.setEstado(cursor.getString(2));

            return alumno;
        }else{
            return null;
        }
    }
    /* Fin métodos para consultar. */

//PERFIL_CANDIDATO (ID_CANDIDATO CHAR(2) not null,ID_DEPARTAMENTO CHAR(4),ID_USUARIO CHAR(4),NOMBRES_CANDIDATO CHAR(32) not null,APELLIDOS_CANIDATO CHAR(32) not null,DUI_CANDIDATO CHAR(10) not null,NIT_CANDIDATO CHAR(17) not null
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1:
            {
                //verificar que exista Usuario. Se usa al crear las que dependen o actualizar usuario.
                Usuario usuario = (Usuario) dato;
                String[] id = {usuario.getId()};
                abrir();
                Cursor c1 = db.query("usuario", null,
                        "id_usuario = ?", id, null,
                        null, null);
                if(c1.moveToFirst()){
                    //Se encontro el usuario.
                    return true;
                }
                return false;
            }
            case 2:
            {
                //verificar que al modificar o crear DETALLE_EXPERIENCIA exista el candidato.
                DetalleExperiencia de= (DetalleExperiencia) dato;
                String[] ids = {de.getIdCandidato()};
                abrir();
                Cursor c = db.query("PERFIL_CANDIDATO", null, "ID_CANDIDATO = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 3:
            {
                //verificar que al modificar o crear CONTACTO_ASPIRANTE exista el candidato.
                //CONTACTO_ASPIRANTE (ID_CONTACTO
                ContactoAspirante de= (ContactoAspirante) dato;
                String[] ids = {de.getIdCandidato()};
                abrir();
                Cursor c = db.query("PERFIL_CANDIDATO", null, "ID_CANDIDATO = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 4:
            {
                //verificar que al modificar o crear DATO_ESTUDIO exista el candidato y la institucion.
                //CONTACTO_ASPIRANTE (ID_CONTACTO
                //INSTITUCION_EDUCATIVA (ID_INSTITUCION CHAR(4) not null,ID_DEPARTAMENTO CHAR(4),NOMBRE_INSTITUCION CHAR(32) not null,primary key (ID_INSTITUCION),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO));
                DatoEstudio de= (DatoEstudio) dato;
                String[] ids = {de.getIdCandidato()};
                String[] id2 = {de.getIdInstitucion()};
                abrir();
                Cursor c = db.query("PERFIL_CANDIDATO", null, "ID_CANDIDATO = ?", ids, null, null, null);
                Cursor c2 = db.query("INSTITUCION_EDUCATIVA", null, "ID_INSTITUCION = ?", id2,
                        null, null, null);
                if(c.moveToFirst() && c2.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 5:
            {
                //verificar que al eliminar DETALLE_APLICACION no existan registros en evaluacion que dependan de este.
                //EVALUACION (ID_EVALUACION CHAR(4) not null,ID_APLICACION CHAR(4),TIPO_EVALUACION CHAR(32) not null,FECHA_EVALUACION DATE not null,ESTADO_EVALUACION CHAR(32)
                //DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));
                DetalleAplicacion de= (DetalleAplicacion) dato;
                String[] ids = {de.getId()};
                abrir();
                Cursor c = db.query("EVALUACION", null, "ID_APLICACION = ?", ids, null, null, null);
                if(c.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 6:
            {
                //verificar que al modificar o crear DETALLE_APLICACION exista el candidato.
                //DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));
                DetalleAplicacion de= (DetalleAplicacion) dato;
                String[] ids = {de.getIdCandidato()};
                abrir();
                Cursor c = db.query("PERFIL_CANDIDATO", null, "ID_CANDIDATO = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

        }
        return true;
    }

    public String llenarBD() {
        final String[] VIDUsuario = {"1","2","3"};
        final String[] VNombreUsuario = {"carlos","pedro","ues"};
        final String[] VPasswordUsuario = {"1234","1234","1234"};
        final String[] VTipoUsuario = {"administrador","candidato","empresa"};

        final String[] VIDDepartamento = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        final String[] VNombreDepartamento = {"Ahuachanpan","Cabañas","Chalatenango","Cuscatlan","La Libertad","Morazan","San Salvador","La Paz",
                "Santa Ana","San Miguel", "Sonsonate","San Vicente","La Union","Usulutan"};

        final String[] VIDMunicipio = {"1","2","3","4"};
        final String[] VNombreMunicipio = {"Tonacatepeque","Soyapango","Apopa","Ciudad Barrios"};
        final String[] VIDDep = {"7","7","7","10"};

        //PERFIL_CANDIDATO (ID_CANDIDATO CHAR(2) not null,ID_DEPARTAMENTO CHAR(4),ID_USUARIO CHAR(4),NOMBRES_CANDIDATO CHAR(32) not null,APELLIDOS_CANIDATO CHAR(32) not null,DUI_CANDIDATO CHAR(10) not null,NIT_CANDIDATO CHAR(17) not null
        final String[] VID_CANDIDATO = {"1","2","3"};
        final String[] VID_DEPARTAMENTO = {"1","2","3"};
        final String[] VID_USUARIO = {"1","2","3"};
        final String[] VNOMBRES_CANDIDATO  = {"Carlos","Pedro","Universidad"};
        final String[] VAPELLIDOS_CANIDATO = {"Perez","Perez","Perez"};
        final String[] VIDUI_CANDIDATO = {"7543534","745354","754543"};
        final String[] VNIT_CANDIDATO = {"7","7","7"};

        //INSTITUCION_EDUCATIVA (ID_INSTITUCION CHAR(4) not null,ID_DEPARTAMENTO CHAR(4),NOMBRE_INSTITUCION CHAR(32) not null,
        final String[] ViID_INSTITUCION = {"1","2","3"};
        final String[] ViID_DEPARTAMENTO = {"1","1","1"};
        final String[] ViNOMBRE_INSTITUCION  = {"Escuela uno","Escuela dos","Escuela tres"};

        abrir();
        db.execSQL("DELETE FROM usuario");
        db.execSQL("DELETE FROM departamento");
        db.execSQL("DELETE FROM municipio");
        db.execSQL("DELETE FROM PERFIL_CANDIDATO");
        db.execSQL("DELETE FROM INSTITUCION_EDUCATIVA");

        InstitucionEducativa departament = new InstitucionEducativa();
        for (int i=0;i<3;i++) {
            departament.setIdIstitucion(ViID_INSTITUCION[i]);
            departament.setIdDepartamento(ViID_DEPARTAMENTO[i]);
            departament.setNombreInstitucion(ViNOMBRE_INSTITUCION[i]);

            insertar(departament);
        }

        PerfilCandidato usuariow = new PerfilCandidato();

        for (int i=0;i<3;i++) {
            usuariow.setIdperfilcandidato(VID_CANDIDATO[i]);
            usuariow.setIddepartamento(VID_DEPARTAMENTO[i]);
            usuariow.setIdusuario(VID_USUARIO[i]);
            usuariow.setNombre(VNOMBRES_CANDIDATO[i]);
            usuariow.setApellido(VAPELLIDOS_CANIDATO[i]);
            usuariow.setDui(VIDUI_CANDIDATO[i]);
            usuariow.setNit(VNIT_CANDIDATO[i]);

            insertar(usuariow);
        }


        Usuario usuario = new Usuario();
        for (int i=0;i<3;i++) {
            usuario.setId(VIDUsuario[i]);
            usuario.setNombre(VNombreUsuario[i]);
            usuario.setPassword(VPasswordUsuario[i]);
            usuario.setTipo(VTipoUsuario[i]);
            usuario.setLoggedState(0);

            insertar(usuario);
        }

        Departamento departamento = new Departamento();
        for (int i=0;i<14;i++) {
            departamento.setId(VIDDepartamento[i]);
            departamento.setNombre(VNombreDepartamento[i]);

            insertar(departamento);
        }

        Municipio municipio = new Municipio();
        for (int i=0;i<4;i++) {
            municipio.setId(VIDMunicipio[i]);
            municipio.setNombre(VNombreMunicipio[i]);
            municipio.setNombre(VNombreMunicipio[i]);
            municipio.setIdDepartamento(VIDDep[i]);

            insertar(municipio);
        }


        cerrar();
        return "DB llenada correctamente";
    }
}
