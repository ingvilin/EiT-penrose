package no.penrose.prosjekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Silisiumspillet extends Activity implements OnClickListener {
	private static final String TAG = "Silisiumspillet";
	
	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_DIGG_TIME = "gravetid_kvarts";
	private static final String OPT_DIGG_COUNTER = "utfort_gravetid_kvarts";
	private static final String OPT_START_DIGG_TIME = "tiden_da_aktiviteten_ble_avsluttet";
	private static final String OPT_NEXT_DIGG_TIME = "neste_utgravetid_kvarts";
	private static final String OPT_MONEY = "antall_kroner";
	
	private static Button newGameButton;
	private static Button aboutButton;
	private static Button continueButton;
	
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
        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        /*aboutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (v.getId() == R.id.about_button) {
					startActivity(i);
				}
			}
		});*/

    }

//    public void onClick(View view){
//    	switch(view.getId()){
//    	case R.id.new_game_button:
//    		startActivity(newGameIntent);
//    		break;
//    	case R.id.about_button:
//    		startActivity(aboutIntent);
//    		break;
//    	}
//    }

	public void onClick(View v) {
    	switch(v.getId()) {
    	case R.id.continue_button:
    		startGame();
    		break;
    	case R.id.new_game_button:
    		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
    		alertbox.setMessage("Du starter som eier av en silisiumfabrikk i trøndelag med 1 000 000 kr. Ditt mål er å øke" +
    				" profitten ved å invistere i forskning, oppgradere utstyr, rekrutere briliante NTNU-studenter og utvide" +
    				" produktsegmentet.");
    		alertbox.setNeutralButton("Start", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					init();
					startGame();
				}
			});
    		alertbox.show();
    		break;
    	case R.id.about_button:
    		Intent i = new Intent(this, Om.class);
    		startActivity(i);
    		break;
    	}	
    }
	
	private void init() {
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DIGG_COUNTER, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_NEXT_DIGG_TIME, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DIGG_TIME, 10000);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_START_DIGG_TIME, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_MONEY, 1000000);
	}
	
	private void startGame() {
		Log.d(TAG, "clicked on ");
		Intent i = new Intent(Silisiumspillet.this, Hovedsiden.class);
		startActivity(i);		
	}

	protected void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}

}