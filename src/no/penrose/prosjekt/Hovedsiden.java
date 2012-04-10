package no.penrose.prosjekt;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hovedsiden extends Activity implements OnSharedPreferenceChangeListener{
	private int antallPenger = 0, antallKvarts = 0, level = -1, antallHcl = -1, antallZirkon = -1, antallMg = -1, antallEg = -1, antallSg = -1;
	private TextView antallPengerView, antallKvartsView, levelView, antallHclView, antallZirkonView, antallMgView, antallEgView, antallSgView;
	private Intent steinbrudd, fabrikk, forsknigssenter, kontor;
	private String kvartsTittel ="Kvarts: ", pengerTittel="Penger: ";
	public static final String PENGER ="Penger", KVARTS ="Kvarts";
	
	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_DIGG_TIME = "gravetid_kvarts";
	private static final String OPT_DIGG_COUNTER = "utfort_gravetid_kvarts";
	private static final String OPT_START_DIGG_TIME = "tiden_da_aktiviteten_ble_avsluttet";
	private static final String OPT_NEXT_DIGG_TIME = "neste_utgravetid_kvarts";
	private static final String OPT_MONEY = "antall_kroner";
	private static final String OPT_LEVEL = "spillerens_level";
	private static final String OPT_AMOUNT_HCl = "mengde_hcl";
	private static final String OPT_AMOUNT_ZIRKON = "mengde_zirkon";
	private static final String OPT_AMOUNT_METALLURGISK_SILISUM = "mengde_mg_silisium";
	private static final String OPT_AMOUNT_EG_SILISUM = "mengde_eg_silisum";
	private static final String OPT_AMOUNT_RENT_SILSIUM = "mengde_rent_silisum";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hovedsiden);

		steinbrudd = new Intent(getApplicationContext(), Steinbrudd.class);
		fabrikk = new Intent(getApplicationContext(),Fabrikk.class);
		forsknigssenter = new Intent(getApplicationContext(), Forskning.class);
		kontor = new Intent(getApplicationContext(), Kontor.class);
		
		antallKvartsView = (TextView) findViewById(R.id.antall_kvarts_hovedsiden);
		antallKvarts = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_KVARTS);
		antallKvartsView.setText("Kvarts: " + Integer.toString(antallKvarts));
		
	    antallPengerView = (TextView) findViewById(R.id.antall_penger_hovedsiden);
	    antallPenger = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_MONEY);
	    antallPengerView.setText("Penger: " + Integer.toString(antallPenger));
	    
	    antallHclView = (TextView) findViewById(R.id.antall_hcl_hovedsiden);
	    antallHcl = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_AMOUNT_HCl);
	    antallHclView.setText("HCL: " + Integer.toString(antallHcl));
	    
	    antallMgView = (TextView) findViewById(R.id.antall_metallurgisk_sillisum_hovedsiden);
	    antallMg = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM);
	    antallMgView.setText("Mg Si: " + Integer.toString(antallMg));
	    
	    antallEgView = (TextView) findViewById(R.id.antall_eg_sillisum_hovedsiden);
	    antallEg = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_AMOUNT_EG_SILISUM);
	    antallEgView.setText("Eg Si: " + Integer.toString(antallEg));
	    
	    antallSgView = (TextView) findViewById(R.id.antall_rent_sillisum_hovedsiden);
	    antallSg = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM);
	    antallSgView.setText("Sg Si: " + Integer.toString(antallSg));
	    
	    antallZirkonView = (TextView) findViewById(R.id.antall_zirkonium_hovedsiden);
	    antallZirkon = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_AMOUNT_ZIRKON);
	    antallZirkonView.setText("Zirkon: " + Integer.toString(antallZirkon));
	    
	    levelView = (TextView) findViewById(R.id.level_hovedsiden);
	    level = PreferenceController.loadIntPreferences(getApplicationContext(), OPT_LEVEL);
	    levelView.setText("Level: " + Integer.toString(level));
		
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
	
	public void kontorOnClick(View view) {
		startActivity(kontor);
	}



	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		if(sharedPreferences==getSharedPreferences(KVARTS, 0))
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts));

		
	}

}
