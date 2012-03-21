package no.penrose.prosjekt;

import no.penrose.prosjekt.PreferenceController;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fabrikk extends Activity implements OnClickListener {
	private ImageView image_level1;
	private ImageView image_level2;
	private ImageView image_level3;
	private TextView antallPengerView;	
	private TextView antallKvartsView;
	private TextView levelView;
	private TextView antallMetallurgiskSilisumView;
	private TextView antallEgSilisumView;
	private TextView antallRentSilisumView;
	private TextView utvinningstidView;
	
	private int counter = -1;
	private int startTimeCounter = -1;
	private int ovenOn = -1; //hvilken ovn som er på, 1 for ovn1, 2 for ovn2 og 3 for ovn3, -1 er ingen på (funker som lås)
	
	private int priceHCl = 25;
	private int priceZirkonium = 25;
	private int priceMetallurgiskSilisum = 50;
	private int priceEgSilisum = 50;
	
	private int level = -1;
	private int antallKvarts = -1;
	private int antallMetallurgiskSilisum = -1;
	private int antallEgSilisum = -1;
	private int antallRentSilisum = -1;
	private int antallPenger = -1;
	private int antallHCl = -1;
	private int antallZirkonium = -1;
	private double factor_level1 = -1; //fraksjon av pris til gevinst
	private double factor_level2 = -1; //fraksjon av pris til gevinst
	private double factor_level3 = -1; //fraksjon av pris til gevinst
	private int speed_oven_1 = -1; //produksjonstid ovn 1
	private int speed_oven_2 = -1; //produksjonstid ovn 2
	private int speed_oven_3 = -1; //produksjonstid ovn 3
	private int price_oven_1 = -1; //strømkostnad ovn 1
	private int price_oven_2 = -1; //strømkostnad ovn 2
	private int price_oven_3 = -1; //strømkostnad ovn 3
	private int environment_cost_level1 = -1; //miljøgebyr ovn 1
	private int environment_cost_level2 = -1; //miljøgebyr ovn 2
	private int environment_cost_level3 = -1; //miljøgebyr ovn 3
	private int unlocked_oven_level1 = -1; //lås for kjøp av ovn 1, 1 er opplåst og -1 er låst
	private int unlocked_oven_level2 = -1; //lås for kjøp av ovn 2, 1 er opplåst og -1 er låst
	private int unlocked_oven_level3 = -1; //lås for kjøp av ovn 3, 1 er opplåst og -1 er låst
	private int maksKvartsGrense = 200;
	
	private String pengerTittel = "Penger: ";
	private String kvartsTittel = "Kvarts: ";
	private String levelTittel = "Level: ";
	private String metallurgiskSilisumTittel = "Mg Si: ";
	private String egSilisumTittel = "Eg Si: ";
	private String rentSilisiumTittel = "Sg Si: ";

	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_LEVEL = "spillerens_level";
	private static final String OPT_MONEY = "antall_kroner";
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
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabrikk);        
        image_level1 = (ImageView) findViewById(R.id.ovn_level_1_frabrikk);
        image_level1.setOnClickListener(this);
        image_level2 = (ImageView) findViewById(R.id.ovn_level_2_frabrikk);
        image_level2.setOnClickListener(this);
        image_level3 = (ImageView) findViewById(R.id.ovn_level_3_frabrikk);
        image_level3.setOnClickListener(this);
        
        antallMetallurgiskSilisumView = (TextView) findViewById(R.id.antall_metallurgisk_sillisum_fabrikk);
        antallMetallurgiskSilisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM);
        antallMetallurgiskSilisumView.setText(metallurgiskSilisumTittel + Integer.toString(antallMetallurgiskSilisum));
        
        antallEgSilisumView = (TextView) findViewById(R.id.antall_eg_sillisum_fabrikk);
        antallEgSilisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_EG_SILISUM);
        antallEgSilisumView.setText(egSilisumTittel + Integer.toString(antallEgSilisum));
        
        antallRentSilisumView = (TextView) findViewById(R.id.antall_rent_sillisum_fabrikk);
        antallRentSilisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM);
        antallRentSilisumView.setText(rentSilisiumTittel + Integer.toString(antallRentSilisum));
        
        unlocked_oven_level1 = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL1);
        unlocked_oven_level2 = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL2);
        unlocked_oven_level3 = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL3);
        antallHCl = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_HCl);
        antallZirkonium = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_ZIRKON);
        
        antallPengerView = (TextView) findViewById(R.id.antall_penger_fabrikk);
        antallPenger = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_MONEY);
        antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
        
        antallKvartsView = (TextView) findViewById(R.id.antall_kvarts_fabrikk);
        antallKvarts = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_KVARTS);
        antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
        
        levelView = (TextView) findViewById(R.id.level_fabrikk);        
        level = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_LEVEL);
        levelView.setText(levelTittel + Integer.toString(level) + "/10");
        
        if(level < 5) {
        	image_level2.setVisibility(View.INVISIBLE);
        }
        if(level < 9) {
        	image_level3.setVisibility(View.INVISIBLE);
        }
        updateProductionFactors();
        
        utvinningstidView = (TextView) findViewById(R.id.utvinningstid_fabrikk);
        startTimeCounter = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_START_TIMER_COUNTER);
        counter = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_COUNTER_OVEN);
        ovenOn = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_OVEN_ON);
        if(ovenOn != -1) {
        	int sec = (int) SystemClock.elapsedRealtime();
        	counter = counter - (sec - startTimeCounter);
        	startTimeCounter = sec;
        	final MyCounter klokke = new MyCounter(counter, 1000);
        	klokke.start();
        }
        else {
        	utvinningstidView.setText("Ingen utvinning pågår.");
        }
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.ovn_level_1_frabrikk:
			antallKvarts = 100; //DENNE MÅ BORT ETTER HVERTTTTTTTTTTTTTTTTTTT
			if(unlocked_oven_level1 == -1) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Ønsker du å kjøpe ovenen til en pris av 200 000 kr?").setCancelable(false)
					.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if(antallPenger >= 200000) {
								antallPenger = antallPenger - 200000;
								antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
								unlocked_oven_level1 = 1;
								toast("Gratulerer du har nå kjøpt din første ovn.");
							}
							else {
								toast("Du har dessverre ikke råd til ovnen.");
							}
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
			}
			else if(counter == -1) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Ønsker du å utvinne metallurgisk grad silisum (Mg Si) til en pris av " + (price_oven_1 + environment_cost_level1)	 + "?").setCancelable(false)
					.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if((antallKvarts >= 50) && (antallPenger >= price_oven_1 + environment_cost_level1)) {
								counter = speed_oven_1;
								ovenOn = 1;
								startTimeCounter = (int) SystemClock.elapsedRealtime();
								antallPenger = antallPenger - price_oven_1 - environment_cost_level1;
								antallKvarts = antallKvarts - 50;
								antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
								antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
								final MyCounter klokke = new MyCounter(counter, 1000);
								klokke.start();
							}
							else {
								if((antallKvarts < 50) && (antallPenger < price_oven_1 + environment_cost_level1)) {
									toast("Du har ikke tilstrekkelig med verken kvarts eller penger.");
								}
								else if(antallKvarts < 50) {
									toast("Du har ikke tilstrekkelig med kvarts.");
								}
								else if(antallPenger < price_oven_1 + environment_cost_level1) {
									toast("Du har ikke tilstrekkelig med penger.");
								}
							}
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
			}
			else {
				if(ovenOn == 1) { 
					toast("Utvinning av metallurgisk grad silisium pågår.");
				}
				else if (ovenOn == 2) {
					toast("Utvinning av elektronisk grad silisum pågår.");
				}
				else if(ovenOn == 3) {
					toast("Utvinning av solcellegrad silisum pågår.");
				}
			}
			break;
		case R.id.ovn_level_2_frabrikk:
			if(unlocked_oven_level2 == -1) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Ønsker du å kjøpe ovenen til en pris av 300 000 kr?").setCancelable(false)
					.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if(antallPenger >= 300000) {
								antallPenger = antallPenger - 300000;
								antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
								unlocked_oven_level2 = 1;
								toast("Gratulerer du har nå kjøpt din andre ovn.");
							}
							else {
								toast("Du har dessverre ikke råd til ovnen.");
							}
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
			}
			else if(counter == -1) { 
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Ønsker du å utvinne elektronisk grad silisium (Eg Si) til en pris " + (price_oven_2 + environment_cost_level2) + "?").setCancelable(false)
					.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if((antallMetallurgiskSilisum >= priceMetallurgiskSilisum) && (antallPenger >= price_oven_2 + environment_cost_level2) && (antallHCl >= priceHCl)) {
								counter = speed_oven_2;
								ovenOn = 2;
								startTimeCounter = (int) SystemClock.elapsedRealtime();
								antallPenger = antallPenger - price_oven_2 - environment_cost_level2;
								antallMetallurgiskSilisum = antallMetallurgiskSilisum - priceMetallurgiskSilisum;
								antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
								antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
								final MyCounter klokke = new MyCounter(counter, 1000);
								klokke.start();		
							}
							else {
								if((antallMetallurgiskSilisum < priceMetallurgiskSilisum) && (antallPenger < price_oven_2 + environment_cost_level2) && (antallHCl < priceHCl)) {
									toast("Du har ikke tilstrekkelig med verken metallurgisk grad silisum, HCl eller penger.");
								}
								else if(antallMetallurgiskSilisum < priceMetallurgiskSilisum) {
									toast("Du har ikke tilstrekkelig med metallurgisk grad silisum.");
								}
								else if(antallPenger < price_oven_2 + environment_cost_level2) {
									toast("Du har ikke tilstrekkelig med penger.");
								}
								else if(antallHCl < priceHCl) {
									toast("Du har ikke tilstrekkelig med saltsyre.");
								}
							}
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
			}
			else {
				if(ovenOn == 1) { 
					toast("Utvinning av metallurgisk grad silisium pågår.");
				}
				else if (ovenOn == 2) {
					toast("Utvinning av elektronisk grad silisum pågår.");
				}
				else if(ovenOn == 3) {
					toast("Utvinning av solcellegrad silisum pågår.");
				}
			}
			break;
		case R.id.ovn_level_3_frabrikk:
			if(unlocked_oven_level3 == -1) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Ønsker du å kjøpe ovenen til en pris av 400 000 kr?").setCancelable(false)
					.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if(antallPenger >= 400000) {
								antallPenger = antallPenger - 400000;
								antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
								unlocked_oven_level3 = 1;
								toast("Gratulerer du har nå kjøpt din tredje ovn.");
							}
							else {
								toast("Du har dessverre ikke råd til ovnen.");
							}
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
			}
			else if(counter == 1) { 
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Ønsker du å utvinne solcellegrad silisium (Sog Si) til en pris av " + Integer.toString(price_oven_3 + environment_cost_level3) + "?").setCancelable(false)
					.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if((antallEgSilisum >= priceEgSilisum) && (antallPenger >= price_oven_3 + environment_cost_level3) && (antallZirkonium >= priceZirkonium)) {
								counter = speed_oven_3;
								ovenOn = 3;
								startTimeCounter = (int) SystemClock.elapsedRealtime();
								antallPenger = antallPenger - price_oven_3 - environment_cost_level3;
								antallEgSilisum = antallEgSilisum - priceEgSilisum;
								antallPengerView.setText(pengerTittel + Integer.toString(antallPenger));
								antallKvartsView.setText(kvartsTittel + Integer.toString(antallKvarts) + "/" + maksKvartsGrense);
								final MyCounter klokke = new MyCounter(counter, 1000);
								klokke.start();		
							}
							else {
								if((antallEgSilisum < priceEgSilisum) && (antallPenger < price_oven_3 + environment_cost_level3) && (antallZirkonium < priceZirkonium)) {
									toast("Du har ikke tilstrekkelig med verken E grad silisum eller penger.");
								}
								else if(antallEgSilisum < priceEgSilisum) {
									toast("Du har ikke tilstrekkelig med kvarts.");
								}
								else if(antallPenger < price_oven_3 + environment_cost_level3) {
									toast("Du har ikke tilstrekkelig med penger.");
								}
								else if(antallZirkonium < priceZirkonium) {
									toast("Du har ikke tilstrekkelig med Zirkonium");
								}
							}
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
			}
			else {
				if(ovenOn == 1) { 
					toast("Utvinning av metallurgisk grad silisium pågår.");
				}
				else if (ovenOn == 2) {
					toast("Utvinning av elektronisk grad grad silisum pågår.");
				}
				else if(ovenOn == 3) {
					toast("Utvinning av solcellegrad silisum pågår.");
				}
			}
			break;
		}
	}
	
	private void updateProductionFactors() {
		if(level == 1) {
			factor_level1 = 0.3;
			speed_oven_1 = 180000;
			price_oven_1 = 100000;
			environment_cost_level1 = 100000;
		}
		else if(level == 2) {
			factor_level1 = 0.4;
			speed_oven_1 = 170000;
			price_oven_1 = 90000;
			environment_cost_level1 = 50000;
		}
		else if(level == 3) {
			factor_level1 = 0.5;
			speed_oven_1 = 160000;
			price_oven_1 = 80000;
			environment_cost_level1 = 50000;
		}
		else if(level == 4) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			environment_cost_level1 = 0;
		}
		else if(level == 5) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			factor_level2 = 0.5;
			speed_oven_2 = 150000;
			price_oven_2 = 100000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 10000;
		}
		else if(level == 6) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			factor_level2 = 0.5;
			speed_oven_2 = 140000;
			price_oven_2 = 90000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 10000;
		}
		else if(level == 7) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 130000;
			price_oven_2 = 80000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 10000;
		}
		else if(level == 8) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 120000;
			price_oven_2 = 70000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 0;
		}
		else if(level == 9) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 120000;
			price_oven_2 = 70000;
			factor_level3 = 0.6;
			speed_oven_3 = 120000;
			price_oven_3 = 80000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 0;
			environment_cost_level3 = 0;
		}
		else if(level == 10) {
			factor_level1 = 0.4;
			speed_oven_1 = 150000;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 120000;
			price_oven_2 = 70000;
			factor_level3 = 0.7;
			speed_oven_3 = 100000;
			price_oven_3 = 70000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 0;
			environment_cost_level3 = 0;
		}
	}
	
	protected void onStop() {
		super.onStop();
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, antallKvarts);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_MONEY, antallPenger);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_LEVEL, level);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL1, unlocked_oven_level1);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL2, unlocked_oven_level2);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_LOCK_LEVEL3, unlocked_oven_level3);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_HCl, antallHCl);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_ZIRKON, antallZirkonium);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM, antallMetallurgiskSilisum);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_EG_SILISUM, antallEgSilisum);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM, antallRentSilisum);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_OVEN_ON, ovenOn);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_START_TIMER_COUNTER, startTimeCounter);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_COUNTER_OVEN, counter);
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
			utvinningstidView.setText("Ingen utvinning pågår");
			counter = -1;
			startTimeCounter = -1;
			if(ovenOn == 1) {
				antallMetallurgiskSilisum = antallMetallurgiskSilisum + (int)(factor_level1 * price_oven_1);
			}
			else if(ovenOn == 2) {
				antallEgSilisum = antallEgSilisum + (int)(factor_level2 * price_oven_2);
			}
			else if(ovenOn == 3) {
				antallRentSilisum = antallRentSilisum + (int)(factor_level3 * price_oven_3);
			}
			ovenOn = -1; //funker som lås
			antallMetallurgiskSilisumView.setText(metallurgiskSilisumTittel + Integer.toString(antallMetallurgiskSilisum));
			antallEgSilisumView.setText(egSilisumTittel + Integer.toString(antallEgSilisum));
			antallRentSilisumView.setText(rentSilisiumTittel + Integer.toString(antallRentSilisum));
		}

		@Override
		public void onTick(long millisUntilFinished) {
			utvinningstidView.setText((millisUntilFinished/1000)+"");
		}
	}
}