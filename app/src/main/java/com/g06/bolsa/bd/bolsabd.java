package com.g06.bolsa.bd;

import android.content.Context;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bolsabd {
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public bolsabd(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "bolsa.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
try {
    //Creaciones de tablas inciciales
    db.execSQL("CREATE TABLE IF NOT EXISTS Perfil_Candidato (id_candidato TEXT NOT NULL,nombre_candidato TEXT NOT NULL,apellidos_candidato TEXT NOT NULL,dui_candidato	TEXT NOT NULL, nit_candidato TEXT NOT NULL, PRIMARY KEY(id_candidato));");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Detalle_Aplicacion\" (\n" +
            "\t\"id_aplicacion\"\tINTEGER NOT NULL,\n" +
            "\t\"estado_aplicacion\"\tBLOB NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_aplicacion\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Evaluacion\" (\n" +
            "\t\"id_evaluacion\"\tTEXT NOT NULL,\n" +
            "\t\"tipo_evaluacion\"\tTEXT NOT NULL,\n" +
            "\t\"fecha_evaluacion\"\tTEXT NOT NULL,\n" +
            "\t\"estado_evaluacion\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_evaluacion\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Datos_Estudios\" (\n" +
            "\t\"id_datos_estudios\"\tINTEGER NOT NULL,\n" +
            "\t\"estudios_nivel\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_datos_estudios\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Institucion_Educativa\" (\n" +
            "\t\"id_institucion\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_institucion\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_institucion\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Departamento\" (\n" +
            "\t\"id_departamento\"\tNUMERIC NOT NULL,\n" +
            "\t\"nombre_departamento\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_departamento\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Municipio\" (\n" +
            "\t\"id_municipio\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_municipio\"\tINTEGER NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_municipio\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Usuario\" (\n" +
            "\t\"id_usuario\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_usuario\"\tTEXT NOT NULL,\n" +
            "\t\"password_usuario\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_usuario\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Acceso_Usuario\" (\n" +
            "\t\"id_acceso\"\tTEXT NOT NULL,\n" +
            "\t\"tipo_acceso\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_acceso\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"OpcionCrud\" (\n" +
            "\t\"id_opcioncrud\"\tTEXT NOT NULL,\n" +
            "\t\"create\"\tBLOB NOT NULL,\n" +
            "\t\"read\"\tBLOB NOT NULL,\n" +
            "\t\"update\"\tBLOB NOT NULL,\n" +
            "\t\"delete\"\tBLOB NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_opcioncrud\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Contacto_Aspirante\" (\n" +
            "\t\"id_contacto\"\tTEXT NOT NULL,\n" +
            "\t\"telefono1_contacto\"\tNUMERIC NOT NULL,\n" +
            "\t\"telefono2_contacto\"\tNUMERIC NOT NULL,\n" +
            "\t\"correo_contacto\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_contacto\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Referencia\" (\n" +
            "\t\"dui_referencia\"\tNUMERIC NOT NULL,\n" +
            "\t\"nombre_referencia\"\tTEXT NOT NULL,\n" +
            "\t\"tel_referencia\"\tNUMERIC NOT NULL,\n" +
            "\tPRIMARY KEY(\"dui_referencia\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Detalle_Experiencia\" (\n" +
            "\t\"id_detalle_experiencia\"\tTEXT NOT NULL,\n" +
            "\t\"lugar_experiencia\"\tTEXT NOT NULL,\n" +
            "\t\"puesto_experiencia\"\tTEXT NOT NULL,\n" +
            "\t\"tiempo_experiencia\"\tNUMERIC NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_detalle_experiencia\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Empresa\" (\n" +
            "\t\"id_empresa\"\tTEXT NOT NULL,\n" +
            "\t\"razon_social_empresa\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_empresa\"\tTEXT NOT NULL,\n" +
            "\t\"direccion_empresa\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_empresa\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Aspirante\" (\n" +
            "\t\"id_aspirante\"\tTEXT NOT NULL,\n" +
            "\t\"estado\"\tBLOB NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_aspirante\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Detalle_Oferta\" (\n" +
            "\t\"id_detalle_oferta\"\tTEXT NOT NULL,\n" +
            "\t\"perfil\"\tTEXT NOT NULL,\n" +
            "\t\"salario_oferta\"\tINTEGER NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_detalle_oferta\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Oferta_Laboral\" (\n" +
            "\t\"id_oferta\"\tTEXT NOT NULL,\n" +
            "\t\"inicio_oferta\"\tTEXT NOT NULL,\n" +
            "\t\"fin_oferta\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_oferta\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_oferta\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Puesto\" (\n" +
            "\t\"id_puesto\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_puesto\"\tTEXT NOT NULL,\n" +
            "\t\"vacante_puesto\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_puesto\")\n" +
            ");");
    db.execSQL("CREATE TABLE IF NOT EXISTS \"Area_trabajo\" (\n" +
            "\t\"id_area\"\tTEXT NOT NULL,\n" +
            "\t\"nombre_area\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_area\")\n" +
            ");");
}catch (SQLException e){
    e.printStackTrace();
}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }
    }
}