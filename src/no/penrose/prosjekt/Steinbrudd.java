package no.penrose.prosjekt;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Steinbrudd extends Activity {
	private int maksKvartsGrense = 200;
	private int antallKvarts = 0;
	private TextView antallKvartsView;
	private String kvartsTittel ="Kvarts: ";
	private ProgressBar progressBar;
	private Handler handler;
	public static final String KVARTS ="Kvarts";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.steinbrudd);

		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		this.handler = new Handler();
		//		Runnable updateKvartsDigging = new Runnable() {
		//			
		//			public void run() {
		//				// TODO Auto-generated method stub
		//				antallKvarts = maksKvartsGrense;
		//			}
		//		};

		antallKvartsView = (TextView) findViewById(R.id.editText1);
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);

		SharedPreferences settingsKvarts = getSharedPreferences(KVARTS, 0);
		antallKvarts = settingsKvarts.getInt(KVARTS, 0);
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);

	}

	//	public void onClick(View v) {
	//		switch(v.getId()) {
	//		case R.id.til_fabrikken:
	//			//sende tilbake data med putExtra
	//			finish();
	//			break;
	//		}
	//	}

	/** In the steinbrudd.xml file onClick is set to trigger this method. It will start the digging process and count down till it ends and then update the kvarts amount*/
		
	public void diggKvarts(final View view){
		view.setClickable(false);

	}
	public void updateKvartsScore(){
		antallKvartsView.setText("Kvarts: " + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
	}

	public int getKvartsAmount() {
		return antallKvarts;
	}

	public void setKvartsAmount(int kvartsAmount) {
		this.antallKvarts = kvartsAmount;
	}
}