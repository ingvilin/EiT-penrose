package no.penrose.prosjekt;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Steinbrudd extends Activity {
	private Button tilbake;
	private int kvartLimitAmount = 200;
	private int kvartsAmount = 0;
	private Runnable updateKvartsDigging;
	private TextView kvartsAmountView;



	public Steinbrudd(){
		this.updateKvartsDigging = new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				kvartsAmount = kvartLimitAmount;
				//		        kvartsAmountView.setText("Kvarts: " + Integer.toString(kvartsAmount) + "/" + kvartLimitAmount + "testing");
				kvartsAmountView.setText("testing");
			}
		};
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.steinbrudd);

		tilbake = (Button) findViewById(R.id.til_fabrikken);
//		tilbake.setOnClickListener(this);
	     
	    kvartsAmountView = (TextView) findViewById(R.id.editText1);
	    kvartsAmountView.setText("Kvarts: " + Integer.toString(kvartsAmount) + "/" + kvartLimitAmount);
	
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
//		kvartsAmountView.setText("testing");
//	}
		view.setClickable(false);
		CountDownTimer countDownTimer= new CountDownTimer(30000, 1000) {

		     public void onTick(long millisUntilFinished) {
		         kvartsAmountView.setText("seconds remaining: " + millisUntilFinished / 1000);
		     }

		     public void onFinish() {
		    	 kvartsAmount = kvartLimitAmount;
		         kvartsAmountView.setText("Kvarts: " + Integer.toString(kvartsAmount) + "/" + kvartLimitAmount);
		         view.setClickable(true);
		     }
		  }.start();
		  
	}
	
	public void updateKvartsScore(){
		kvartsAmountView.setText("Kvarts: " + Integer.toString(kvartsAmount) + "/" + kvartLimitAmount);
	}
	
	public int getKvartsAmount() {
		return kvartsAmount;
	}
	
	public void setKvartsAmount(int kvartsAmount) {
		this.kvartsAmount = kvartsAmount;
	}
}