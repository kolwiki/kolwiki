package kol.kolwiki;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ThingViewer extends ListActivity {
		private static final String TAG = "ThingViewer";
		
		private static final Monster[] monsters = {new Monster(10, "Knob Goblin Assistant Chef", 1, 2, 1), new Monster(11, "Knob Goblin Barbeque Team", 2, 2, 2), 
											new Monster(12, "Sleeping Knob Goblin Guard", 1, 0, 1), new Monster(13, "Sub Assistant Knob Mad Scientist", 1, 1, 1)};
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	 
			//DatabaseHandler db = new DatabaseHandler(this);
			
			//List<Monster> monsterList = db.getAllMonsters();			
			//Monster[] monsters = new Monster[monsterList.size()];
			//monsterList.toArray(monsters);
			
			setListAdapter(new MonsterAdapter(this, monsters));
						
		}
	 
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			Thing selectedThing = (Thing) l.getItemAtPosition(position);
			
		    Intent intent = new Intent(this, Card.class);
		    intent.putExtra("ID", selectedThing.getId()); // pass id to card 
		    
		    startActivity(intent);
		}
	 
	}
