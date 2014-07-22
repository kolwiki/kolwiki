package kol.kolwiki;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ThingViewer extends ListActivity {
		private static final String TAG = "ThingViewer";
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	 
			DatabaseHandler db = new DatabaseHandler(this);
			
			db.getMonster(0);
			
			List<Monster> monsterList = db.getAllMonsters();			
			Log.d(TAG, "getAllMonsters is size " + monsterList.size());
			Monster[] monsters = new Monster[monsterList.size()];
			monsterList.toArray(monsters);
					
			setListAdapter(new MonsterAdapter(this, monsters));	 
		}
	 
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
	 	 
		}
	 
	}
