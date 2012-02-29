package no.penrose.prosjekt;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Steinbrudd extends Activity implements OnClickListener{
	private int maksKvartsGrense = 200;
	private int antallKvarts = 0;
	private TextView antallKvartsView;
	private TextView utgravingstid;
	private String kvartsTittel ="Kvarts: ";
	private ImageView kvarts_bilde;
	public static final String KVARTS ="Kvarts";

	private static final String OPT_KVARTS = "antall_kvarts";
	
	final MyCounter kvartsTimer = new MyCounter(10000, 1000); 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.steinbrudd);

		kvarts_bilde = (ImageView) findViewById(R.id.steinbrudd_image1);
        kvarts_bilde.setOnClickListener(this);
        
        utgravingstid = (TextView) findViewById(R.id.tid_gaatt_steinbrudd);
        
        //skrive over tid_gaatt_steinbrudd om dette er første gang og ikke fra tidligere 

		antallKvartsView = (TextView) findViewById(R.id.editText1);
		antallKvarts = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_KVARTS);
		antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
	}

		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.steinbrudd_image1:
				antallKvarts = 75;
				PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, antallKvarts);
				kvartsTimer.start();
				break;
	//		case R.id.til_fabrikken:
	//			//sende tilbake data med putExtra
	//			finish();
	//			break;
			}
		}

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
	
/*	private void saveStringPreferences(String key, String value) {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}*/
	
	public class MyCounter extends CountDownTimer {
		public MyCounter(long millisecInFuture, long countDownInterval) {
			super(millisecInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			utgravingstid.setText("Utgravingen er ferdig!");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			utgravingstid.setText((millisUntilFinished/1000)+"");
		}
	}
}














