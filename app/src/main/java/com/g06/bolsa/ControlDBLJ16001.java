package com.g06.bolsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.g06.bolsa.clases_auxiliares.Usuario;
import com.g06.bolsa.clases_auxiliares.Departamento;
import com.g06.bolsa.clases_auxiliares.Municipio;

public class ControlDBLJ16001 {

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDBLJ16001(Context ctx) {
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
                db.execSQL("create table OFERTA_LABORAL (ID_OFERTA CHAR(4) not null,ID_EMPRESA CHAR(4),INICIO_OFERTA DATE not null,FIN_OFERTA DATE not null,NOMBRE_OFERTA CHAR(256) not null,primary key (ID_OFERTA),foreign key (ID_EMPRESA) references EMPRESA (ID_EMPRESA));");
                db.execSQL("create table DETALLES_OFERTA (ID_DETALLE_OFERTA CHAR(4) not null,ID_OFERTA CHAR(4),PERFIL CHAR(256) not null,SALARIO_OFERTA CHAR(4) not null,primary key (ID_DETALLE_OFERTA),foreign key (ID_OFERTA) references OFERTA_LABORAL (ID_OFERTA));");
                db.execSQL("create table ASPIRANTE (ID_ASPIRANTE CHAR(4) not null,ID_DETALLE_OFERTA CHAR(4),ID_EMPRESA CHAR(4),ESTADO smallint not null,primary key (ID_ASPIRANTE),foreign key (ID_DETALLE_OFERTA) references DETALLES_OFERTA (ID_DETALLE_OFERTA),foreign key (ID_EMPRESA) references EMPRESA (ID_EMPRESA));");
                db.execSQL("create table CONTACTO_ASPIRANTE (ID_CONTACTO CHAR(4) not null,ID_CANDIDATO CHAR(2),TELEFONO1_CONTACTO CHAR(8) not null,TELEFONO2_CONTACTO   CHAR(8) not null,CORRE_CONTACTO CHAR(8) not null,primary key (ID_CONTACTO),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");
                db.execSQL("create table INSTITUCION_EDUCATIVA (ID_INSTITUCION CHAR(4) not null,ID_DEPARTAMENTO CHAR(4),NOMBRE_INSTITUCION CHAR(32) not null,primary key (ID_INSTITUCION),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO));");
                db.execSQL("create table DATOS_ESTUDIOS (ESTUDIOS_NIVEL CHAR(32) not null,ID_CANDIDATO CHAR(2),ID_INSTITUCION CHAR(4),primary key (ESTUDIOS_NIVEL),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO),foreign key (ID_INSTITUCION) references INSTITUCION_EDUCATIVA (ID_INSTITUCION));");
                db.execSQL("create table DETALLE_APLICACION (ID_APLICACION CHAR(4) not null,ID_CANDIDATO CHAR(2),ESTADO_APLICACION smallint not null,primary key (ID_APLICACION),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");
                db.execSQL("create table DETALLE_EXPERIENCIA (ID_DETALLE_EXPERIENCIA CHAR(2) not null,ID_CANDIDATO CHAR(2),LUGAR_EXPERIENCIA CHAR(64) not null,PUESTO_EXPERIENCIA CHAR(64) not null,TIEMPO_EXPERIENCIA TIME,primary key (ID_DETALLE_EXPERIENCIA),foreign key (ID_CANDIDATO) references PERFIL_CANDIDATO (ID_CANDIDATO));");
                db.execSQL("create table EVALUACION (ID_EVALUACION CHAR(4) not null,ID_APLICACION CHAR(4),TIPO_EVALUACION CHAR(32) not null,FECHA_EVALUACION DATE not null,ESTADO_EVALUACION CHAR(32),primary key (ID_EVALUACION),foreign key (ID_APLICACION) references DETALLE_APLICACION (ID_APLICACION));");
                db.execSQL("create table MUNICIPIO (ID_MUNICIPIP CHAR(4) not null,NOMBRE_MUNICIPIO CHAR(32) not null,ID_DEPARTAMENTO CHAR(4),primary key (ID_MUNICIPIP, NOMBRE_MUNICIPIO),foreign key (ID_DEPARTAMENTO) references DEPARTAMENTO (ID_DEPARTAMENTO));");
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

        municipioCV.put("id_municipio", municipio.getId());
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

    /* Fin métodos para insertar. */

    /* Inicio métodos para actualizar. */

    /* Fin métodos para actualizar. */
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

    /* Inicio métodos para eliminar. */

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

    /* Fin métodos para consultar. */


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

        }
        return true;
    }

    public String llenarBD() {
        final String[] VIDUsuario = {"1","2","3"};
        final String[] VNombreUsuario = {"Carlos","Pedro","ues"};
        final String[] VPasswordUsuario = {"1234","1234","1234"};
        final String[] VTipoUsuario = {"administrador","candidato","empresa"};

        final String[] VIDDepartamento = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        final String[] VNombreDepartamento = {"Ahuachanpan","Cabañas","Chalatenango","Cuscatlan","La Libertad","Morazan","San Salvador","La Paz"+
                "Santa Ana","San Miguel", "Sonsonate","San Vicente","La Union","Usulutan"};

        final String[] VIDMunicipio = {"1","2","3","4"};
        final String[] VNombreMunicipio = {"Tonacatepeque","Soyapango","Apopa","Ciudad Barrios"};
        final String[] VIDDep = {"7","7","7","10"};

        abrir();
        db.execSQL("DELETE FROM usuario");
        db.execSQL("DELETE FROM departamento");
        db.execSQL("DELETE FROM municipio");


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
        for (int i=0;i<4;i++) {
            departamento.setId(VIDDepartamento[i]);
            departamento.setNombre(VNombreDepartamento[i]);

            //insertar(departamento);
        }

        Municipio municipio = new Municipio();
        for (int i=0;i<4;i++) {
            municipio.setId(VIDMunicipio[i]);
            municipio.setNombre(VNombreMunicipio[i]);
            municipio.setNombre(VNombreMunicipio[i]);
            municipio.setIdDepartamento(VIDDep[i]);

            //insertar(municipio);
        }


        cerrar();
        return "DB llenada correctamente";
    }
}
