package no.penrose.prosjekt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hovedsiden extends Activity implements OnSharedPreferenceChangeListener{
	private int antallPenger = 0, antallKvarts = 0;
	private TextView antallPengerView, antallKvartsView;
	private Intent steinbrudd, fabrikk, forsknigssenter;
	private String kvartsTittel ="Kvarts: ", pengerTittel="Penger: ";
	public static final String PENGER ="Penger", KVARTS ="Kvarts";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hovedsiden);

		steinbrudd = new Intent(getApplicationContext(), Steinbrudd.class);
		fabrikk = new Intent(getApplicationContext(),Fabrikk.class);
		forsknigssenter = new Intent(getApplicationContext(), Forskning.class);
		
		antallKvartsView = (TextView) findViewById(R.id.kvartsText);
	    antallPengerView = (TextView) findViewById(R.id.pengerText);
		
		SharedPreferences settingsPenger = getSharedPreferences(PENGER, 0);
		antallPenger = settingsPenger.getInt(PENGER, 0);
		antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
		
		SharedPreferences settingsKvarts = getSharedPreferences(KVARTS, 0);
		antallKvarts = settingsKvarts.getInt(KVARTS, 0);
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts));
	}
	
	
	
	public void updateKvarts(){
		SharedPreferences settingsKvarts = getSharedPreferences(KVARTS, 0);
		antallKvarts = settingsKvarts.getInt(KVARTS, 0);
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts));
	}
	public void steinbruddOnClick(View view){
		startActivity(steinbrudd);
	}
	
	public void fabrikkOnClick(View view){
		startActivity(fabrikk);
	}
	
	public void forskningssenterOnClick(View view){
		startActivity(forsknigssenter);
	}



	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		if(sharedPreferences==getSharedPreferences(KVARTS, 0))
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts));

		
	}

}
