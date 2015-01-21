package com.adlinaputri.resep_makanan;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
	private static final String  	DB_NAME = "db_resep_makanan";
	private static final int  		DB_VER  = 1;

	private static final String  TB_DATA  	= "tb_data";
	public static final String  COL_ID  	= "_id";
	public static final String  COL_NAMA 	= "nama";
	public static final String  COL_RESEP	= "resep";

	private static DatabaseHelper dbInstance;
	private static SQLiteDatabase db;
	
	private String[] allColumns = { 
			dbInstance.COL_NAMA,
			dbInstance.COL_RESEP
		  };
	
	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VER);
	}

	public static DatabaseHelper getInstance(Context context) {
		if (dbInstance == null) {
			dbInstance = new DatabaseHelper(context);
			db = dbInstance.getWritableDatabase();
		}
		return dbInstance;
	}

	@Override
	public synchronized void close() {
		super.close();
		if (dbInstance != null) {
			dbInstance.close();
		}
	}

	public List<Resep> getAllKamus() {
		List<Resep> lisKamus = new ArrayList<Resep>();

		Cursor cursor = db.query(TB_DATA, new String[] { COL_ID, COL_ID,
		COL_RESEP, COL_NAMA }, null, null, null, null, COL_NAMA);
		if (cursor.getCount() >= 1) {
			cursor.moveToFirst();

			do {
				Resep resep = new Resep();
				resep.setResep(cursor.getString(cursor.getColumnIndexOrThrow(COL_RESEP)));
				resep.setNama(cursor.getString(cursor.getColumnIndexOrThrow(COL_NAMA)));
				lisKamus.add(resep);

			} while (cursor.moveToNext());
		}
		return lisKamus;
	}
	
	public Resep create(String nama, String resep) {
		ContentValues value = new ContentValues();
		value.put(dbInstance.COL_NAMA, nama);
		value.put(dbInstance.COL_RESEP, resep);
		
		long insertId = db.insert(dbInstance.TB_DATA, null, value);
		Cursor cursor = db.query(dbInstance.TB_DATA, allColumns, dbInstance.COL_ID + " = " + insertId, null,
		null, null, null);
		cursor.moveToFirst();
		Resep newUser = cursorToString(cursor);
		cursor.close();
		return newUser;
	}
	
	private Resep cursorToString(Cursor cursor) {
	    Resep resep = new Resep();
	    resep.setNama(cursor.getString(0));
	    resep.setResep(cursor.getString(1));
	    return resep;
	}
}
