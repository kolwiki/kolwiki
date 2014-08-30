package kol.kolwiki;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MonsterAdapter extends ArrayAdapter<Monster> {
	private final static String TAG = "MonsterAdapter";
	
	private final Context context;
	private final Monster[] monsters;
 
	public MonsterAdapter(Context context, Monster[] monsters) {
		super(context, R.layout.activity_viewer, monsters);
		this.context = context;
		this.monsters = monsters;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
 
		View rowView = inflater.inflate(R.layout.activity_viewer, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.monster_name);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.monster_image);
		textView.setText(monsters[position].getName());
		Log.d(TAG, "Creating view for monster named " + monsters[position].getName());
 
		ViewHolder holder = new ViewHolder();
		holder.thumbnail = imageView;
		holder.position = position;
		holder.url = monsters[position].getUrl();
		
	    new ThumbnailTask(position, holder)
        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
		
		return rowView;
	}
	
	private static class ThumbnailTask extends AsyncTask<Bitmap, Void, Bitmap> {
	    private int mPosition;
	    private ViewHolder mHolder;
	    
	    public ThumbnailTask(int position, ViewHolder holder) {
	        mPosition = position;
	        mHolder = holder;
	    }

		public static Bitmap getBitmapFromURL(String src) {
		    try {
		        URL url = new URL(src);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
			return getBitmapFromURL(mHolder.url);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
		    if (result != null && mHolder.position == mPosition) {	        	
	            mHolder.thumbnail.setImageBitmap(result);
	        }	        	
		}
	}

	private static class ViewHolder {
	    public ImageView thumbnail;
	    public String url;
	    public int position;
	}
}