package kol.kolwiki;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kol.kolwiki.Thing.ThingType;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
		Log.d(TAG, "creating Card, id: " + id);

		DatabaseHelper db = new DatabaseHelper(this);

		// determine which type of thing is to be displayed and read it from the
		// db
		switch (Integer.toString(id).charAt(0)) { // switching on a char
		case '1': // monster
			thing = (Monster) db.get(id, ThingType.MONSTER);
			break;
		case '2': // item
			thing = (Item) db.get(id, ThingType.ITEM);
			break;
		default:
			Log.e(TAG, "unknown id[0]: " + Integer.toString(id).charAt(0));
		}

		db.close();

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
		Log.d(TAG, "showSide()");
		
		TextView nameText = (TextView) findViewById(R.id.card_name);
		TextView detailsText = (TextView) findViewById(R.id.card_sideA_details);
		nameText.setText(thing.getName());
		if (currentSide == Side.A) {
			detailsText.setText(thing.sideAText());
		}
		else {
			detailsText.setText(thing.sideBText());
		}

		ImageView thingImage = (ImageView) findViewById(R.id.card_image);
		new ImageDownloadTask(thing.getUrl(), thingImage)
		.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
	}

	private void flip() {
		if (currentSide == Side.A) {
			currentSide = Side.B;
		} else {
			currentSide = Side.A;
		}

		showSide();
	}

	private static class ImageDownloadTask extends
	AsyncTask<Bitmap, Void, Bitmap> {
		private String mUrl;
		private ImageView mImageView;

		public ImageDownloadTask(String url, ImageView imageView) {
			mUrl = url;
			mImageView = imageView;
		}

		public static Bitmap getBitmapFromURL(String src) {
			try {
				URL url = new URL(src);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
				return myBitmap;
			} catch (IOException e) {
				Log.e(TAG, "error loading " + src);
				Log.e(TAG, e.getMessage());
				return null;
			}
		}

		@Override
		protected Bitmap doInBackground(Bitmap... params) {
			return getBitmapFromURL(mUrl);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				mImageView.setImageBitmap(result);
			}
		}
	}
}
