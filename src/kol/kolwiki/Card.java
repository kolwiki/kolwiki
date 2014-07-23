package kol.kolwiki;

import kol.kolwiki.Thing.ThingType;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Card extends ActionBarActivity {
	private static final String TAG = "Card";

	private static final Monster[] monsters = {
			new Monster(10, "Knob Goblin Assistant Chef", 1, 2, 1),
			new Monster(11, "Knob Goblin Barbeque Team", 2, 2, 2),
			new Monster(12, "Sleeping Knob Goblin Guard", 1, 0, 1),
			new Monster(13, "Sub Assistant Knob Mad Scientist", 1, 1, 1) };

	private enum Side {
		A, B
	};

	private Thing thing;
	private Side currentSide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);

		currentSide = Side.A;

		int id = getIntent().getExtras().getInt("ID");

		// determine which type of thing is to be displayed and read it from the
		// db
		switch (Integer.toString(id).charAt(0)) { // switching on a char
		case '1': // monster
			thing = monsters[id - 10];
			break;
		default:
			Log.e(TAG, "unknown id[0]: " + Integer.toString(id).charAt(0));
		}

		showSide();

		RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.card_relativelayout);
		rlayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				flip();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.card, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showSide() {
		if (currentSide == Side.A) {
			TextView nameText = (TextView) findViewById(R.id.card_name);
			ImageView thingImage = (ImageView) findViewById(R.id.card_image);
			TextView detailsText = (TextView) findViewById(R.id.card_sideA_details);

			if (thing.getType() == ThingType.MONSTER) {
				Monster monster = (Monster) thing;

				nameText.setText(monster.getName());

				switch (monster.getId()) {
				case 10:
					thingImage.setImageResource(R.drawable.monster0);
					break;
				case 11:
					thingImage.setImageResource(R.drawable.monster1);
					break;
				case 12:
					thingImage.setImageResource(R.drawable.monster2);
					break;
				case 13:
					thingImage.setImageResource(R.drawable.monster3);
					break;
				}

				detailsText.setText(monster.sideAText());
			}

			else { // item

			}
		} else { // showing side B
			TextView nameText = (TextView) findViewById(R.id.card_name);
			ImageView thingImage = (ImageView) findViewById(R.id.card_image);
			TextView detailsText = (TextView) findViewById(R.id.card_sideA_details);

			if (thing.getType() == ThingType.MONSTER) {
				Monster monster = (Monster) thing;

				nameText.setText(monster.getName());

				switch (monster.getId()) {
				case 10:
					thingImage.setImageResource(R.drawable.monster0);
					break;
				case 11:
					thingImage.setImageResource(R.drawable.monster1);
					break;
				case 12:
					thingImage.setImageResource(R.drawable.monster2);
					break;
				case 13:
					thingImage.setImageResource(R.drawable.monster3);
					break;
				}

				detailsText.setText(monster.sideBText());
			}
		}
	}

	private void flip() {
		if (currentSide == Side.A) {
			currentSide = Side.B;
		} else {
			currentSide = Side.A;
		}
		
		showSide();
	}
}
