package kol.kolwiki;

import java.util.ArrayList;
import java.util.Collection;
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

	private static final String DATABASE_NAME = "test.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getCursor(ThingType type) {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		switch (type) {
		case MONSTER:
			qb.setTables("Monsters");
			break;
		case ITEM:
			qb.setTables("Items");
		}

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
	
	public List<Thing> getAll(ThingType type) {
		List<Thing> things = new ArrayList<Thing>();
		
		Cursor c = getCursor(type);
		do {
			things.add(readNext(c, type));			
		} while (c.moveToNext());
		
		return things;
	}
	
	public List<Thing> getN(int n, ThingType type) {
		List<Thing> things = new ArrayList<Thing>();
		
		Cursor c = getCursor(type);
		for (int i = 0; i < n; i++) {
			things.add(readNext(c, type));
			c.moveToNext();
		}
		
		return things;
	}
	
	/*
	public Monster getNextMonster() {
		return readNextMonster(getMonsterCursor());
	}
	*/
	
	public Thing get(int id, ThingType type) {
		// TODO use parameterized input or something
		String tableName;
		switch(type) {
		case MONSTER:
			tableName = "Monsters";
			break;
		case ITEM:
			tableName = "Items";
			break;
		default:
			tableName = null;
		}
		SQLiteDatabase db = getReadableDatabase();		
		Cursor c = db.rawQuery("SELECT * FROM " + tableName + " WHERE _id=" + id, null);		
		c.moveToFirst();
		
		return readNext(c, type);		
	
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
	
	private Thing readNext(Cursor c, ThingType type) {
		switch(type) {
		case MONSTER:
			return readNextMonster(c);
		case ITEM:
			return readNextItem(c);
		default:
			return null;
		}
	}

	/*
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
	*/
		
	// returns the Item described at the Cursor position
	private Item readNextItem(Cursor c) {
		Item item = new Item();
		
		item.setType(ThingType.ITEM);		
		item.setName(c.getString(c.getColumnIndexOrThrow("name")));
		item.setId(c.getInt(c.getColumnIndexOrThrow("_id")));
		item.setDescription(c.getString(c.getColumnIndexOrThrow("descr")));
		item.setItemType(c.getString(c.getColumnIndexOrThrow("type")));
		item.setSellPrice(c.getInt(c.getColumnIndexOrThrow("sellPrice")));
		item.setTradable(Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow("tradable"))));
		item.setDiscardable(Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow("discardable"))));
		item.setQuestItem(Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow("questItem"))));
		item.setLocation(c.getString(c.getColumnIndexOrThrow("location")));
		item.setRequirement(c.getString(c.getColumnIndexOrThrow("requirement")));
		item.setPower(c.getInt(c.getColumnIndexOrThrow("power")));
		item.setSize(c.getInt(c.getColumnIndexOrThrow("size")));
		item.setAdventures(c.getString(c.getColumnIndexOrThrow("adventures")));
		item.setStats(c.getString(c.getColumnIndexOrThrow("stats")));
		item.setEnchantment(c.getString(c.getColumnIndexOrThrow("enchantment")));
		item.setDuration(c.getString(c.getColumnIndexOrThrow("duration")));
		item.setQuality(c.getString(c.getColumnIndexOrThrow("quality")));	
		item.setUrl(c.getString(c.getColumnIndexOrThrow("url")));
		
		return item;
	}
	
	/*
	public List<Item> itemQuery(String query) {
		List<Item> itemList = new ArrayList<Item>();		
		SQLiteDatabase db = getReadableDatabase();		
		Cursor c = db.rawQuery(query, null);		
		c.moveToFirst();
		
		do {			
			itemList.add(readNextItem(c));
		} while (c.moveToNext());
		
		return itemList;
	}
	*/
}