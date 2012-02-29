//package no.penrose.prosjekt;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.CountDownTimer;
//import android.os.IBinder;
//
//public class CountDownTimeren extends Service{
//	
//	public CountDownTimeren(){
//		
//	}
//
//	@Override
//	public IBinder onBind(Intent arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	Runnable updateKvartsDigging = new Runnable() {
//		
//		public void run() {
//			// TODO Auto-generated method stub
//			antallKvarts = maksKvartsGrense;
//		}
//	};
//	
//	public void doCountDown(int antallKvarts, int maksKvartsGrense){
//	CountDownTimer countDownTimer= new CountDownTimer(8000, 1000) {
//
//	     public void onTick(long millisUntilFinished) {
//	         antallKvartsView.setText("seconds remaining: " + millisUntilFinished / 1000);
//	     }
//
//	     public void onFinish() {
//	    	 antallKvarts = maksKvartsGrense+10;
//	         antallKvartsView.setText("Kvarts: " + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
//	         view.setClickable(true);
//	         
//	        SharedPreferences settingsKvarts = getSharedPreferences(Hovedsiden.KVARTS, 0);
//	 		SharedPreferences.Editor editor = settingsKvarts.edit();
//	 		editor.putInt(Hovedsiden.KVARTS, antallKvarts);
//	 		editor.commit();
//	 		
//	 		
//	     }
//	  }.start();
//}
//}