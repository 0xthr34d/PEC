package com.example.pec3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pec3.Guitar;
import com.example.pec3.GuitarLab;
import com.example.pec3.R;
import com.example.pec3.database.GuitarDbSChema.GuitarTable;

import java.util.ArrayList;
import java.util.List;

public class GuitarBaseHelper extends SQLiteOpenHelper {
	//version de la base de datos
	private static final int VERSION = 1;
	//nombre de la base de datos
	private static final String DATABASE_NAME = "guitarBase.db";
	//contexto de la base de datos
	private Context mContext;

	//sentencia sql que crea la tabla
	private static final  String SQL_CREATE_ENTRIES = "CREATE TABLE " + GuitarTable.TABLE_NAME + " (" +
			GuitarTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			GuitarTable.COLUMN_NAME_UUID + " TEXT," +
			GuitarTable.COLUMN_NAME_NAME + " TEXT," +
			GuitarTable.COLUMN_NAME_IMG + " BLOB," +
			GuitarTable.COLUMN_NAME_RATING + " INTEGER)";

	//constructor
	public GuitarBaseHelper(Context context){
		super(context,DATABASE_NAME,null,VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//ejecuto la sentencia que crea la tabla
		db.execSQL(SQL_CREATE_ENTRIES);

		//objeto utilizado para almacenar valores que pueden ser procesados por ContentValues
		ContentValues values = new ContentValues();

		//creo una lista de objetos Guitar
		List<Guitar> guitars = GuitarLab.getDataset(mContext);

		//inserto los valores iniciales en la base de datos
		for(int i = 0; i < guitars.size(); i++) {

			//almaceno los datos en values
			values.put(GuitarTable.COLUMN_NAME_UUID, guitars.get(i).getmUuid().toString());
			values.put(GuitarTable.COLUMN_NAME_NAME, guitars.get(i).getmName());
			values.put(GuitarTable.COLUMN_NAME_IMG, guitars.get(i).getmImage());
			values.put(GuitarTable.COLUMN_NAME_RATING, guitars.get(i).getmRating());

			//inserto los datos en la base de datos
			db.insert(GuitarTable.TABLE_NAME,null, values);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);
	}
}
