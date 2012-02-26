package no.penrose.prosjekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hovedsiden extends Activity{
	private int antallPenger;
	private TextView antallPengerView;
	private Intent steinbrudd, fabrikk, forsknigssenter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hovedsiden);

		antallPengerView = (TextView) findViewById(R.id.pengerText);
	    
		steinbrudd = new Intent(getApplicationContext(), Steinbrudd.class);
		fabrikk = new Intent(getApplicationContext(),Fabrikk.class);
		forsknigssenter = new Intent(getApplicationContext(), Forskning.class);
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

}
