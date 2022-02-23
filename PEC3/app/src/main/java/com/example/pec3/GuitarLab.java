package com.example.pec3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pec3.database.GuitarBaseHelper;
import com.example.pec3.database.GuitarCursorWrapper;
import com.example.pec3.database.GuitarDbSChema.GuitarTable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GuitarLab {

	private static GuitarLab sGuitarLab;

	//variables miembro
	private Context mContext;
	private SQLiteDatabase mDatabase;

	//constructor
	private GuitarLab(Context context){
		mContext = context.getApplicationContext();
		mDatabase = new GuitarBaseHelper(mContext).getWritableDatabase();
	}

	//metodo estatico para obtener un objeto
	public static GuitarLab get(Context context){
		if(sGuitarLab == null){
			sGuitarLab = new GuitarLab(context);
		}
		return sGuitarLab;
	}

	//esta funcion devuleve el dataset de objetos para insertarlos en la base de datos
	public static List<Guitar> getDataset(Context ctx) {
		//creo una lista dinamica
		List<Guitar> guitars = new ArrayList<>();
		//añado los objetos a la lista
		guitars.add(new Guitar("Fender",transformImageToArray(ctx,R.drawable.fender)));
		guitars.add(new Guitar("Gibson",transformImageToArray(ctx,R.drawable.gibson)));
		guitars.add(new Guitar("Ibanez",transformImageToArray(ctx,R.drawable.ibanez)));
		guitars.add(new Guitar("Esp",transformImageToArray(ctx,R.drawable.esp)));
		guitars.add(new Guitar("Epiphone",transformImageToArray(ctx,R.drawable.epiphone)));
		guitars.add(new Guitar("Jackson",transformImageToArray(ctx,R.drawable.jackson)));
		guitars.add(new Guitar("Paul Reed Smith",transformImageToArray(ctx,R.drawable.prs)));
		guitars.add(new Guitar("Rickenbacker",transformImageToArray(ctx,R.drawable.rickenbacker)));
		guitars.add(new Guitar("Suhr",transformImageToArray(ctx,R.drawable.suhr)));
		guitars.add(new Guitar("Yamaha",transformImageToArray(ctx,R.drawable.yamaha)));

		return guitars;
	}

	//devuelve una lista de objetos Guitar
	public List<Guitar> getGuitars(){
		//creo una lista de tipo List<Guitar>
		List<Guitar> guitars = new ArrayList<>();
		//creo un cursor para consultas en la base de datos
		GuitarCursorWrapper cursor = queryGuitars(GuitarTable._ID,null);
		try{
			//mueve el cursor a la primera posicion
			cursor.moveToFirst();
			//while que recorre las filas
			while(!cursor.isAfterLast()){
				//añade un objeto tipo Guitar la lista List<Guitar>
				guitars.add(cursor.getGuitar());
				//el cursor se mueve a la siguiente fila
				cursor.moveToNext();
			}
		}finally {
			//se cierra el cursor
			cursor.close();
		}
		//devuelve la lista
		return guitars;
	}

	//devuelve una lista de las cinco objetos Guitar con mejor rating en orden descendente
	public List<Guitar> getTopGuitars(){
		//creo una lista de tipo List<Guitar>
		List<Guitar> guitars = new ArrayList<>();
		//creo un cursor para consultas en la base de datos
		GuitarCursorWrapper cursor = queryGuitars(GuitarTable.COLUMN_NAME_RATING + " DESC",Integer.toString(5));
		try{
			//mueve el cursor a la primera posicion
			cursor.moveToFirst();
			//recorre las filas
			while(!cursor.isAfterLast()){
				//añade un objeto tipo Guitar la lista List<Guitar>
				guitars.add(cursor.getGuitar());
				//el cursor se mueve a la siguiente fila
				cursor.moveToNext();
			}
		}finally {
			//se cierra el cursor
			cursor.close();
		}
		//devuelve la lista
		return guitars;
	}

	//devuelve un objeto GuitarCursorWrapper
	private GuitarCursorWrapper queryGuitars( String orderBy, String limit){
		//consulta en la base de datos utilizando orderBy y limit
		Cursor cursor = mDatabase.query(
				GuitarTable.TABLE_NAME,
				null,
				null,
				null,
				null,
				null,
				orderBy,
				limit

		);
		return new GuitarCursorWrapper(cursor);
	}

	//transforma un Drawable a un objeto byte[] para poder guardar las imagenes en la base de datos
	public static byte[] transformImageToArray(Context ctx, int id){
		//obtiene el drawable y lo convierte a bitmap
		Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), id);
		//crea un objeto ByteArrayOutputStream;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		//cmomprime el bitmap en formato jpeg con total calidad pasandolo al objeto stream
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		//devuelve la imagen convertida a un array de bytes
		return stream.toByteArray();
	}

	//convierte un array a un objeto de tipo Bitmap
	public static Bitmap transformImageToBitmap(byte[] bitmap){
		return BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);
	}

	//actualiza el rating en la base de datos
	public void updateRating(int rating, String name) {
		ContentValues values = new ContentValues();
		values.put(GuitarTable.COLUMN_NAME_RATING,rating);
		//hace un update en la basede datos
		mDatabase.update(GuitarTable.TABLE_NAME,values,GuitarTable.COLUMN_NAME_NAME + " = ?", new String[]{name});
	}
}
