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
	private static final String OPT_LEVEL = "spillerens_level";
	private static final String OPT_OVEN_LOCK_LEVEL1 = "oven_level_1_kjopt";
	private static final String OPT_OVEN_LOCK_LEVEL2 = "oven_level_2_kjopt";
	private static final String OPT_OVEN_LOCK_LEVEL3 = "oven_level_3_kjopt";
	private static final String OPT_AMOUNT_HCl = "mengde_hcl";
	private static final String OPT_AMOUNT_ZIRKON = "mengde_zirkon";
	private static final String OPT_AMOUNT_METALLURGISK_SILISUM = "mengde_mg_silisium";
	private static final String OPT_AMOUNT_EG_SILISUM = "mengde_eg_silisum";
	private static final String OPT_AMOUNT_RENT_SILSIUM = "mengde_rent_silisum";
	private static final String OPT_START_TIMER_COUNTER = "tidpunktet_utvinning_startet";
	private static final String OPT_COUNTER_OVEN = "teller_utvinningstid";
	private static final String OPT_OVEN_ON = "hvilken_ovn_som_utvinner";
	private static final String OPT_DONATE_LITTLE = "liten_donasjon";
	private static final String OPT_DONATE_MEDIUM = "medium_donasjon";
	private static final String OPT_DONATE_LARGE = "stor_donasjon";
	private static final String OPT_DONATE_TIME = "tid_til_rapport_kommer";
	private static final String OPT_REPORT_ONE = "rapport_en";
	private static final String OPT_REPORT_TWO = "rapport_to";
	private static final String OPT_REPORT_THREE = "rapport_tre";
	
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
    				" profitten ved å investere i forskning, oppgradere utstyr, rekruttere briliante NTNU-studenter og utvide" +
    				" produktsegmentet. Lykke til!");
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
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_LEVEL, 1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL1, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL2, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL3, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_HCl, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_ZIRKON, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_EG_SILISUM, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM, 0);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_COUNTER_OVEN, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_ON, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_START_TIMER_COUNTER, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_MEDIUM, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_LITTLE, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_LARGE, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_TIME, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_ONE, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_TWO, -1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_THREE, -1);
		//PreferenceController.saveIntPreferences(this.getApplicationContext(), , value)
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