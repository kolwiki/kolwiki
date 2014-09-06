package kol.kolwiki;

import java.util.ArrayList;
import java.util.List;

import kol.kolwiki.Thing.ThingType;
import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ThingViewer extends ListActivity {
		private static final String TAG = "ThingViewer";		
		
		private Thing.ThingType type = Thing.ThingType.MONSTER;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			Log.d(TAG, "creating ThingViewer");
			
			getWindow().getDecorView().setBackgroundColor(Color.WHITE);
	 
			DatabaseHelper.forceDatabaseReload(this);
			DatabaseHelper db = new DatabaseHelper(this);
			List<Thing> thingList = null;
			switch (type) {
			case MONSTER:
				thingList = new ArrayList<Thing>(db.getAll(ThingType.MONSTER));
				break;
			case ITEM:
				thingList = new ArrayList<Thing>(db.getAll(ThingType.ITEM));
				break;
			}
			
			
			List<Thing> monsterList = new ArrayList<Thing>(db.getAll(ThingType.MONSTER));
			Filter filter = new Filter(monsterList);
			filter.addRestriction("Location", "Tower Ruins", Filter.RestrictionType.EQUAL);
			filter.addRestriction("Safe moxie", "27", Filter.RestrictionType.GREATEREQUALTHAN);
			Monster[] monsters = filter.getList().toArray(new Monster[0]);
			
						
			setListAdapter(new MonsterAdapter(this, monsters));
		}
	 
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			Thing selectedThing = (Thing) l.getItemAtPosition(position);
			
			Log.d(TAG, "id: " + selectedThing.getId());
		    Intent intent = new Intent(this, Card.class);		    
		    intent.putExtra("ID", selectedThing.getId()); // pass id to card 
		    
		    startActivity(intent);
		}
	 
	}
