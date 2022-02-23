package com.example.pec3.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.pec3.Guitar;

import java.util.UUID;

public class GuitarCursorWrapper extends CursorWrapper {
	//constructor
	public GuitarCursorWrapper(Cursor cursor){
		super(cursor);
	}

	//devuelve un objeto de tipo Guitar obteniendo antes las propiedades de la base de datos
	public Guitar getGuitar(){
		//obtiene los datos y los guarda en variables
		String uuidString = getString(getColumnIndex(GuitarDbSChema.GuitarTable.COLUMN_NAME_UUID));
		String name = getString(getColumnIndex(GuitarDbSChema.GuitarTable.COLUMN_NAME_NAME));
		byte[] img = getBlob(getColumnIndex(GuitarDbSChema.GuitarTable.COLUMN_NAME_IMG));
		int rating = getInt(getColumnIndex(GuitarDbSChema.GuitarTable.COLUMN_NAME_RATING));

		//crea un objeto guitar, setea las propiedades y lo devuelve
		Guitar guitar = new Guitar();
		guitar.setmUuid(UUID.fromString(uuidString));
		guitar.setmName(name);
		guitar.setmImage(img);
		guitar.setmRating(rating);
		return guitar;
	}
}
