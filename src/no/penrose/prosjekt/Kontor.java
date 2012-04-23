package no.penrose.prosjekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Kontor extends Activity implements OnClickListener {
	private TextView antallPengerView;	
	private TextView antallKvartsView;
	private TextView levelView;
	private TextView antallMetallurgiskSilisumView;
	private TextView antallEgSilisumView;
	private TextView antallRentSilisumView;
	private TextView antallHClView;
	private TextView antallZirkoniumView;
	
	private Intent rapport_en_intent, rapport_to_intent, rapport_tre_intent;
	
	private int buyPriceHCl = 25000;
	private int buyPriceZirkonium = 30000;
	private int sellPriceKvarts = 10000;
	private int sellPriceMg = 20000;
	private int sellPriceEg = 50000;
	private int sellPriceSg = 75000;
	private int maksKvartsGrense = 200;
	
	private int level = -1;
	private int antallKvarts = -1;
	private int antallMetallurgiskSilisum = -1;
	private int antallEgSilisum = -1;
	private int antallRentSilisum = -1;
	private int antallPenger = -1;
	private int antallHCl = -1;
	private int antallZirkonium = -1;
	private int rapportTid = -1;
	private int rapportEn;
	private int rapportTo;
	private int rapportTre;
	private int rapportEnTilgjengelig = -1;
	private int rapportToTilgjengelig = -1;
	private int rapportTreTilgjengelig = -1;
	
	private String pengerTittel = "Penger: ";
	private String kvartsTittel = "Kvarts: ";
	private String levelTittel = "Level: ";
	private String metallurgiskSilisumTittel = "Mg Si: ";
	private String egSilisumTittel = "Eg Si: ";
	private String rentSilisiumTittel = "Sg Si: ";
	private String HClTittel = "HCl: ";
	private String zirkoniumTittel = "Zirkonium: ";
	
	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_LEVEL = "spillerens_level";
	private static final String OPT_MONEY = "antall_kroner";
	private static final String OPT_AMOUNT_HCl = "mengde_hcl";
	private static final String OPT_AMOUNT_ZIRKON = "mengde_zirkon";
	private static final String OPT_AMOUNT_METALLURGISK_SILISUM = "mengde_mg_silisium";
	private static final String OPT_AMOUNT_EG_SILISUM = "mengde_eg_silisum";
	private static final String OPT_AMOUNT_RENT_SILSIUM = "mengde_rent_silisum";
	private static final String OPT_DONATE_LITTLE = "liten_donasjon";
	private static final String OPT_DONATE_MEDIUM = "medium_donasjon";
	private static final String OPT_DONATE_LARGE = "stor_donasjon";
	private static final String OPT_DONATE_TIME = "tid_til_rapport_kommer";
	private static final String OPT_REPORT_ONE = "rapport_en";
	private static final String OPT_REPORT_TWO = "rapport_to";
	private static final String OPT_REPORT_THREE = "rapport_tre";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kontor);
		
		rapport_en_intent = new Intent(this.getApplicationContext(), Rapport1.class);
		rapport_to_intent = new Intent(this.getApplicationContext(), Rapport2.class);
		rapport_tre_intent = new Intent(this.getApplicationContext(), Rapport3.class);
		
		
		antallMetallurgiskSilisumView = (TextView) findViewById(R.id.antall_metallurgisk_sillisum_kontor);
        antallMetallurgiskSilisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM);
        antallMetallurgiskSilisumView.setText(metallurgiskSilisumTittel + Integer.toString(antallMetallurgiskSilisum));
        
        antallEgSilisumView = (TextView) findViewById(R.id.antall_eg_sillisum_kontor);
        antallEgSilisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_EG_SILISUM);
        antallEgSilisumView.setText(egSilisumTittel + Integer.toString(antallEgSilisum));
        
        antallRentSilisumView = (TextView) findViewById(R.id.antall_rent_sillisum_kontor);
        antallRentSilisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM);
        antallRentSilisumView.setText(rentSilisiumTittel + Integer.toString(antallRentSilisum));
        
        antallPengerView = (TextView) findViewById(R.id.antall_penger_kontor);
        antallPenger = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_MONEY);
        antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
        
        antallKvartsView = (TextView) findViewById(R.id.antall_kvarts_kontor);
        antallKvarts = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_KVARTS);
        antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
        
        levelView = (TextView) findViewById(R.id.level_kontor);        
        level = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_LEVEL);
        levelView.setText(levelTittel + Integer.toString(level) + "/10");
        
        antallHClView = (TextView) findViewById(R.id.antall_hcl_kontor);
        antallHCl = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_HCl);
        antallHClView.setText(HClTittel + Integer.toString(antallHCl));
        
        antallZirkoniumView = (TextView) findViewById(R.id.antall_zirkonium_kontor);
        antallZirkonium = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_ZIRKON);
        antallZirkoniumView.setText(zirkoniumTittel + Integer.toString(antallZirkonium));
        
        rapportTid = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_TIME);
        rapportEn = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_LITTLE);
        rapportTo = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_MEDIUM);
        rapportTre = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_LARGE);
        
        rapportEnTilgjengelig = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_REPORT_ONE);
        rapportToTilgjengelig = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_REPORT_TWO);
        rapportTreTilgjengelig = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_REPORT_THREE);
        
        if(rapportTid != -1) {
        	if(rapportEn != -1) {
        		int tmp = 120000 - ((int) SystemClock.elapsedRealtime() - rapportTid);
        		if(tmp > 0) {
        			final MyCounter rapportTimer = new MyCounter(tmp, 1000);
        			rapportTimer.start();
        		}
        		else {
        			toast("Du har mottatt en ny forskningsrapport.");
        			rapportTid = -1;
        			rapportEn = -1;
        			rapportEnTilgjengelig = 1;
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_TIME, rapportTid);
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_LITTLE, rapportEn);
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_ONE, rapportEnTilgjengelig);
        		}
        	}
        	if(rapportEn != -1) {
        		int tmp = 180000 - ((int) SystemClock.elapsedRealtime() - rapportTid);
        		if(tmp > 0) {
        			final MyCounter rapportTimer = new MyCounter(tmp, 1000);
        			rapportTimer.start();
        		}
        		else {
        			toast("Du har mottatt en ny forskningsrapport.");
        			rapportTid = -1;
        			rapportTo = -1;
        			rapportToTilgjengelig = 1;
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_TIME, rapportTid);
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_MEDIUM, rapportTo);
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_TWO, rapportToTilgjengelig);
        		}
        	}
        	if(rapportEn != -1) {
        		int tmp = 240000 - ((int) SystemClock.elapsedRealtime() - rapportTid);
        		if(tmp > 0) {
        			final MyCounter rapportTimer = new MyCounter(tmp, 1000);
        			rapportTimer.start();
        		}
        		else {
        			toast("Du har mottatt en ny forskningsrapport.");
        			rapportTid = -1;
        			rapportTre = -1;
        			rapportTreTilgjengelig = 1;
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_TIME, rapportTid);
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_DONATE_LARGE, rapportTre);
        			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_THREE, rapportTreTilgjengelig);
        		}
        	}
        }
	}
	
	public void onClick(View v) {
		//switch (v.getId()) {
		//case R.id.kontoret_kontor:
		//	toast("hei dette er en test for on click!");
		//	break;
		//}
	}
	
	public void kontorOnClick_kjop(View v) {
		final String [] items=new String []{"Kr " + buyPriceHCl + ": Saltsyre (HCl)","Kr " + buyPriceZirkonium + ": Zirkonium (Zr)"};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Ønsker du å kjøpe noen av de følgende stoffene?");

		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					if (antallPenger >= buyPriceHCl) {
						antallPenger = antallPenger - buyPriceHCl;
						antallHCl = antallHCl + 50;
						antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
						antallHClView.setText(HClTittel + Integer.toString(antallHCl));
						toast("Du har nå kjøpt 50 kg saltsyre.");
					}
					else {
						toast("Du har ikke råd til saltsyre.");
					}
				}
				else if (which == 1) {
					if (antallPenger >= buyPriceZirkonium) {
						antallPenger = antallPenger - buyPriceZirkonium;
						antallZirkonium = antallZirkonium + 50;
						toast("Du har nå kjøpt 50 kg saltsyre.");
						antallZirkoniumView.setText(zirkoniumTittel + Integer.toString(antallZirkonium));
						antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
					}
					else {
						toast("Du har ikke råd til Zirkonium.");
					}
				}
			}
		});
		builder.show();
	}
	
	public void kontorOnClick_selg(View v) {
		final String [] items=new String []{"10 kg kvarts til kr: " + sellPriceKvarts,"10 kg metallurgisk grad silisum til kr: " + sellPriceMg, "10 kg elektrisk grad silisum til kr: " + sellPriceEg, "10 kg solcellegrad silisium til kr: " + sellPriceSg};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Ønsker du å selge noe av de følgende formene silisium?");

		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					if(antallKvarts >= 10) {
						antallKvarts = antallKvarts - 10;
						antallPenger = antallPenger + sellPriceKvarts;
						antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
						antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
					}
					else {
						toast("Du har ikke nok kvarts.");
					}
				}
				else if (which == 1) {
					if(antallMetallurgiskSilisum >= 10) {
						antallMetallurgiskSilisum = antallMetallurgiskSilisum - 10;
						antallPenger = antallPenger + sellPriceMg;
						antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
						antallMetallurgiskSilisumView.setText(metallurgiskSilisumTittel + Integer.toString(antallMetallurgiskSilisum));
					}
					else {
						toast("Du har ikke nok metallurgisk grad silisium.");
					}
				}
				else if (which == 2) {
					if(antallEgSilisum >= 10) {
						antallEgSilisum = antallEgSilisum - 10;
						antallPenger = antallPenger + sellPriceEg;
						antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
						antallEgSilisumView.setText(egSilisumTittel + Integer.toString(antallEgSilisum));
					}
					else {
						toast("Du har ikke nok elektrisk grad silisium.");
					}
				}
				else if (which == 3) {
					if(antallRentSilisum >= 10) {
						antallRentSilisum = antallRentSilisum - 10;
						antallPenger = antallPenger + sellPriceSg;
						antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
						antallRentSilisumView.setText(rentSilisiumTittel + Integer.toString(antallRentSilisum));
					}
					else {
						toast("Du har ikke nok solcellegrad silisium.");
					}
				}
			}
		});
		builder.show();
	}
	
	public void kontorOnClick_bok(View v) {		
		final String [] items=new String []{"Forskningsrapport fra UiB", "Forskningsrapport fra UiO", "Forskningsrapport fra NTNU"};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Forskningsrapporter:");

		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					if (rapportEnTilgjengelig == 1) {
						startActivity(rapport_en_intent);
					}
					else {
						toast("Du har ikke kjøpt/mottatt denne rapporten.");
					}
				}
				else if (which == 1) {
					if (rapportToTilgjengelig == 1) {
			    		startActivity(rapport_to_intent);
					}
					else {
						toast("Du har ikke kjøpt/mottatt denne rapporten.");
					}
				}
				else if (which == 2) {
					if (rapportTreTilgjengelig == 1) {
			    		startActivity(rapport_tre_intent);
					}
					else {
						toast("Du har ikke kjøpt/mottatt denne rapporten.");
					}
				}
			}
		});
		builder.show();
	}
	
	protected void onStop() {
		super.onStop();
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, antallKvarts);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_LEVEL, level);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_MONEY, antallPenger);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_HCl, antallHCl);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_ZIRKON, antallZirkonium);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM, antallMetallurgiskSilisum);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_EG_SILISUM, antallEgSilisum);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM, antallRentSilisum);
	}
	
	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
	
	public class MyCounter extends CountDownTimer {
		public MyCounter(long millisecInFuture, long countDownInterval) {
			super(millisecInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			if(rapportEn != -1) {
				rapportEn = -1;
				rapportEnTilgjengelig = 1;
				PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_LITTLE, rapportEn);
				PreferenceController.saveIntPreferences(getApplicationContext(), OPT_REPORT_ONE, rapportEnTilgjengelig);
			}
			else if (rapportTo != -1) {
				rapportTo = -1;
				rapportToTilgjengelig = 1;
				PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_MEDIUM, rapportTo);
				PreferenceController.saveIntPreferences(getApplicationContext(), OPT_REPORT_TWO, rapportToTilgjengelig);
			}
			else if (rapportTre != -1) {
				rapportTre = -1;
				rapportTreTilgjengelig = 1;
				PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_LARGE, rapportTre);
				PreferenceController.saveIntPreferences(getApplicationContext(), OPT_REPORT_THREE, rapportTreTilgjengelig);
			}
			rapportTid = -1;
			PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_TIME, rapportTid);
			toast("Du har mottatt en ny forskningsrapport.");
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	}
}