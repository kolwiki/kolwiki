package kol.kolwiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MonsterAdapter extends ArrayAdapter<Monster> {
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
 
		int id = monsters[position].getId();
		
		// TODO make better link between id and images
		switch (id) {
		case 0: 
			imageView.setImageResource(R.drawable.monster0);
			break;
		case 1: 
			imageView.setImageResource(R.drawable.monster1);
			break;
		case 2: 
			imageView.setImageResource(R.drawable.monster2);
			break;
		case 3: 
			imageView.setImageResource(R.drawable.monster3);
			break;		
		}
 
		return rowView;
	}
}