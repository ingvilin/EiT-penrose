package no.penrose.prosjekt;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.os.SystemClock;

public class Steinbrudd extends Activity implements OnClickListener{
	private int maksKvartsGrense = 200;
	private int antallKvarts = 0;
	private int antallPenger = -1;
	private int level = -1;
	private int diggTime = 10000; //tid det tar å grave - økes hver gang man graver, -1 betyr at denne tiden er brukt under pågående utgraving er aktiv
	private int nextDiggTime = -1; //tiden neste utgraving tar, -1 betyr at diggTime er aktiv
	private int diggCounter = 0; //tiden til gravingen er ferdig
	private int startDiggTime = -1; //tiden når du gravingen startet, -1 om ingen utgraving pågård
	private TextView antallKvartsView;
	private TextView antallPengerView;
	private TextView antallLevelView;
	private TextView utgravingstid;
	private String kvartsTittel = "Kvarts: ";
	private String pengerTittel = "Penger: ";
	private ImageView kvarts_bilde;
	public static final String KVARTS ="Kvarts";
	
	private int prisUtgraving = 10000;

	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_DIGG_TIME = "gravetid_kvarts";
	private static final String OPT_DIGG_COUNTER = "utfort_gravetid_kvarts";
	private static final String OPT_START_DIGG_TIME = "tiden_da_aktiviteten_ble_avsluttet";
	private static final String OPT_NEXT_DIGG_TIME = "neste_utgravetid_kvarts";
	private static final String OPT_MONEY = "antall_kroner";
	private static final String OPT_LEVEL = "spillerens_level";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.steinbrudd);
		
		kvarts_bilde = (ImageView) findViewById(R.id.steinbrudd_image2);
        kvarts_bilde.setOnClickListener(this);
        
        utgravingstid = (TextView) findViewById(R.id.tid_gaatt_steinbrudd);
        
        antallKvartsView = (TextView) findViewById(R.id.antall_kvarts_steinbrudd);
		antallKvarts = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_KVARTS);
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
		
		antallPengerView = (TextView) findViewById(R.id.antall_penger_steinbrudd);
		antallPenger = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_MONEY);
		antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
		
		antallLevelView = (TextView) findViewById(R.id.level_steinbrudd);
		level = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_LEVEL);
		antallLevelView.setText("Level: " + Integer.toString(level));

		diggTime = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DIGG_TIME);		
		diggCounter = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DIGG_COUNTER);
		startDiggTime = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_START_DIGG_TIME);
		nextDiggTime = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_NEXT_DIGG_TIME);
		if(diggCounter > 0) {
			int sec = (int) SystemClock.elapsedRealtime();
			if(startDiggTime != -1) {
				if(diggCounter - ((sec - startDiggTime)) <= diggTime) {
					diggCounter = 0;
					startDiggTime = -1;
					diggTime = nextDiggTime;
					nextDiggTime = -1;
					utgravingstid.setText("Utgravingen er ferdig.");
				}
				else {
					diggCounter = diggCounter - (sec - startDiggTime);
					startDiggTime = sec;
					final MyCounter kvartsTimer = new MyCounter(diggCounter, 1000);
					kvartsTimer.start();
				}
			}
			else {
				toast("diggCounter = " + diggCounter);
				final MyCounter kvartsTimer = new MyCounter(diggCounter, 1000);
				kvartsTimer.start();
			}
		}
		else if(diggCounter == 0){
			diggCounter = 0;
			utgravingstid.setText("Ingen utgravning pågår.");
		}
	}

		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.steinbrudd_image2:
				if((diggCounter == 0) && (antallKvarts < maksKvartsGrense) && (antallPenger >= prisUtgraving)) {
					antallPenger = antallPenger - prisUtgraving;
					antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
					startDiggTime = (int) SystemClock.elapsedRealtime();
				//	PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_START_DIGG_TIME, startDiggTime);
					diggCounter = diggTime;
					final MyCounter kvartsTimer = new MyCounter(diggCounter, 1000);
					kvartsTimer.start();
					nextDiggTime = diggTime + 10000;
					diggTime = -1;
				}
				else if(diggCounter > 0) {
					toast("Utgravingen av kvarts er enda ikke ferdig.");
				}
				else if(antallKvarts >= maksKvartsGrense) {
					toast("Du har ikke plass på lageret til mer kvarts.");
				}
				break;
			}
		}
		
	protected void onStop() {
		super.onStop();
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DIGG_COUNTER, diggCounter);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_NEXT_DIGG_TIME, nextDiggTime);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DIGG_TIME, diggTime);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, antallKvarts);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_START_DIGG_TIME, startDiggTime);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_MONEY, antallPenger);
	}

	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
	
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
	
	public class MyCounter extends CountDownTimer {
		public MyCounter(long millisecInFuture, long countDownInterval) {
			super(millisecInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			utgravingstid.setText("Utgravingen er ferdig.");
			startDiggTime = -1;
			diggCounter = 0;
			diggTime = nextDiggTime;
			nextDiggTime = -1;	
			antallKvarts = antallKvarts + 25;
			antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			utgravingstid.setText((millisUntilFinished/1000)+"");
		}
	}
}














