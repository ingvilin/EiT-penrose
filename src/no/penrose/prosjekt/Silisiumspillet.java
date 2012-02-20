package no.penrose.prosjekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Silisiumspillet extends Activity implements OnClickListener {
	private static final String TAG = "Silisiumspillet";
	
	private static Button newGameButton;
	private static Button aboutButton;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //final Intent i = new Intent(this, Om.class);
        newGameButton = (Button) findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);
        aboutButton = (Button) findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        /*aboutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (v.getId() == R.id.about_button) {
					startActivity(i);
				}
			}
		});*/
    }

	public void onClick(View v) {
    	switch(v.getId()) {
    	case R.id.new_game_button:
    		toast("Under utvikling!");
    		startGame();
    		break;
    	case R.id.about_button:
    		Intent i = new Intent(this, Om.class);
    		startActivity(i);
    		break;
    	}	
    }
	
	private void startGame() {
		Log.d(TAG, "clicked on ");
		Intent i = new Intent(Silisiumspillet.this, Fabrikk.class);
		startActivity(i);
		// TODO Auto-generated method stub
		
	}

	protected void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
}