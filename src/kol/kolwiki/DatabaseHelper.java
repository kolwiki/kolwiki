package kol.kolwiki;

import java.util.ArrayList;
import java.util.List;

import kol.kolwiki.Thing.ThingType;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
	private static final String TAG = "DatabaseHelper";

	private static final String DATABASE_NAME = "monster.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getMonsterCursor() {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables("Monsters");

		Cursor c = qb.query(db, null, null, null, null, null, null);
		c.moveToFirst();
		
		return c;
	}
	
	// reload db from asset directory
	public static void forceDatabaseReload(Context context){
	    DatabaseHelper dbHelper = new DatabaseHelper(context);
	    dbHelper.setForcedUpgradeVersion(DATABASE_VERSION);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    db.setVersion(-1);
	    db.close();
	    db = dbHelper.getWritableDatabase();
	}
	
	public List<Monster> getAllMonsters() {
		List<Monster> monsters = new ArrayList<Monster>();
		
		Cursor c = getMonsterCursor();	
		
		do {			
			monsters.add(readNextMonster(c));
		} while (c.moveToNext());
		
		return monsters;
	}
	
	public List<Monster> getNMonsters(int n) {
		List<Monster> monsters = new ArrayList<Monster>();
		
		Cursor c = getMonsterCursor();
		
		for (int i = 0; i < n; i++) {			
			monsters.add(readNextMonster(c));
			c.moveToNext();
		}
		
		return monsters;
	}
	
	public Monster getNextMonster() {
		return readNextMonster(getMonsterCursor());
	}
	
	public Monster getMonster(int id) {
		Monster monster = new Monster();
		
		SQLiteDatabase db = getReadableDatabase();		
		Cursor c = db.rawQuery("SELECT * FROM Monsters WHERE _id=" + id, null);		
		c.moveToFirst();
		
		return readNextMonster(c);		
	}
	
	// returns the Monster described at the Cursor position
	private Monster readNextMonster(Cursor c) {
		Monster monster = new Monster();
		
		monster.setType(ThingType.MONSTER);		
		monster.setName(c.getString(c.getColumnIndexOrThrow("name")));
		monster.setId(c.getInt(c.getColumnIndexOrThrow("_id")));
		monster.setHp(c.getInt(c.getColumnIndexOrThrow("hp")));
		monster.setAttack(c.getInt(c.getColumnIndexOrThrow("att")));
		monster.setDefense(c.getInt(c.getColumnIndexOrThrow("def")));
		monster.setSafeMoxie(c.getInt(c.getColumnIndexOrThrow("sm")));
		monster.setInit(c.getInt(c.getColumnIndexOrThrow("init")));			
		monster.setElement(c.getString(c.getColumnIndexOrThrow("element")));
		monster.setResistance(c.getString(c.getColumnIndexOrThrow("res")));
		monster.setMeat(c.getFloat(c.getColumnIndexOrThrow("meat")));
		monster.setPhylum(c.getString(c.getColumnIndexOrThrow("phylum")));
		monster.setDescription(c.getString(c.getColumnIndexOrThrow("descr")));
		monster.setItems(c.getString(c.getColumnIndexOrThrow("items")));		
		monster.setLocation(c.getString(c.getColumnIndexOrThrow("location")));
		monster.setUrl(c.getString(c.getColumnIndexOrThrow("url")));
		
		return monster;
	}
	
	public List<Monster> monsterQuery(String query) {
		List<Monster> monsterList = new ArrayList<Monster>();		
		SQLiteDatabase db = getReadableDatabase();		
		Cursor c = db.rawQuery(query, null);		
		c.moveToFirst();
		
		do {			
			monsterList.add(readNextMonster(c));
		} while (c.moveToNext());
		
		return monsterList;
	}
}
