package kol.kolwiki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemViewer extends Activity {
	
	public enum Side { A, B }
	
	private Side currentSide;
	
	private String sideAText = "This is side A";
	private String sideBText = "This is side B";

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.item_viewer);
	    
	    Intent intent = getIntent();
	    String itemName = intent.getStringExtra(MainActivity.EXTRA_ITEM_NAME);
	    sideAText = itemName + "\n\n" + sideAText;
	    sideBText = itemName + "\n\n" + sideBText;	    
	    
	    currentSide = Side.A;
	    
	    refreshSide();
	    
	    LinearLayout layout = (LinearLayout) findViewById(R.id.item_viewer_layout);
	    layout.setOnClickListener(new OnClickListener() {
	        public void onClick(View view)
	        {
	        	flip();
	        }
	    });
	}
	
	private void flip() {
		if (currentSide == Side.A){
			currentSide = Side.B;
		}
		else {
			currentSide = Side.A;
		}
		
		refreshSide();
	}
	
	private void refreshSide() {
		TextView itemText = (TextView) findViewById(R.id.item_text);
		
		if (currentSide == Side.A) {
			itemText.setText(sideAText);		
		}
		else {
			itemText.setText(sideBText);
		}
	}

}
