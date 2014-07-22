package kol.kolwiki;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final String TAG = "DatabaseHandler";	
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "kol.db";
	private static final String TABLE_MONSTERS = "monsters";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_HP = "hp";
	private static final String KEY_ATTACK = "attack";
	private static final String KEY_DEFENSE = "defense";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MONSTERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_HP + " INTEGER" + KEY_ATTACK + " INTEGER" + KEY_DEFENSE
				+ " INTEGER" + ")";
		 db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONSTERS);
		onCreate(db);
	}

	public void addMonster(Monster monster) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, monster.getName());
		values.put(KEY_HP, monster.getHp());
		values.put(KEY_ATTACK, monster.getAttack());
		values.put(KEY_DEFENSE, monster.getDefense());
				
		db.insert(TABLE_MONSTERS, null, values);
		db.close();
	}

	public Monster getMonster(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		//String queryString = "";
		Cursor cursor = db.query(TABLE_MONSTERS, null, "id = " + id, null, null, null, null);
		
		/*
		Cursor cursor = db.query(TABLE_MONSTERS, new String[] { KEY_ID,
	            KEY_NAME, KEY_HP, KEY_ATTACK, KEY_DEFENSE }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    */
		if (cursor != null) {
			cursor.moveToFirst();
		}
				
		Monster monster = new Monster(Integer.parseInt(cursor.getString(0)),
	            cursor.getString(1), Integer.parseInt(cursor.getString(2)), 
	            Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));

		Log.d(TAG, "got monster: " + monster.getName());
		
		
		return monster;
	}

	public List<Monster> getAllMonsters() {
		List<Monster> monsterList = new ArrayList<Monster>();
		
	    String selectQuery = "SELECT  * FROM " + TABLE_MONSTERS;
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    if (cursor.moveToFirst()) {
	        do {
	            Monster monster = new Monster();
	            monster.setId(Integer.parseInt(cursor.getString(0)));
	            monster.setName(cursor.getString(1));
	            monster.setHp(Integer.parseInt(cursor.getString(2)));
	            monster.setAttack(Integer.parseInt(cursor.getString(3)));
	            monster.setDefense(Integer.parseInt(cursor.getString(4)));
	            
	            monsterList.add(monster);
	        } while (cursor.moveToNext());
	    }
	    
	    Log.d(TAG, "getAllMonsters list is size " + monsterList.size());
	 
	    return monsterList;
	}

	public int getMonsterCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MONSTERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        return cursor.getCount();	
	}

	public int updateMonster(Monster monster) {
		SQLiteDatabase db = this.getWritableDatabase();
	    
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, monster.getName());
	    values.put(KEY_HP, monster.getHp());
	    values.put(KEY_ATTACK, monster.getAttack());
	    values.put(KEY_DEFENSE, monster.getDefense());
	    	 
	    return db.update(TABLE_MONSTERS, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(monster.getId()) });
	}

	public void deleteMonster(Monster monster) {
		 SQLiteDatabase db = this.getWritableDatabase();
		    db.delete(TABLE_MONSTERS, KEY_ID + " = ?",
		            new String[] { String.valueOf(monster.getId()) });
		    db.close();
	}
}
