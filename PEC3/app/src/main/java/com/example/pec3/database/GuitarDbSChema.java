package com.example.pec3.database;

import android.provider.BaseColumns;

public final class GuitarDbSChema {
	//se crea un constructor privado para evitar que alguien instancie la clase accidentalmente
	private GuitarDbSChema(){}

	//clse interna que define el contenido de la tabla
	public static final class GuitarTable implements BaseColumns {
		public static final String TABLE_NAME = "guitars";
		public static final String COLUMN_NAME_UUID = "uuid";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_IMG = "img";
		public static final String COLUMN_NAME_RATING = "rating";
		}
}
