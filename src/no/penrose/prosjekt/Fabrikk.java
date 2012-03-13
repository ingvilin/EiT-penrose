package no.penrose.prosjekt;

import no.penrose.prosjekt.PreferenceController;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fabrikk extends Activity implements OnClickListener {
	private ImageView image_level1;
	private ImageView image_level2;
	private ImageView image_level3;
	private TextView antallPengerView;	
	private TextView antallKvartsView;
	private TextView levelView;
	private TextView antallMetallurgiskSillisumView;
	private TextView antallRentSillisumView;
	
	private int level = -1;
	private int antallKvarts = -1;
	private int antallMetallurgiskSillisum = -1;
	private int antallRentSillisum = -1;
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
	private String metallurgiskSillisumTittel = "Metallurgisks sillisum: ";
	private String rentSillisiumTittel = "Rent sillisium";

	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_LEVEL = "spillerens_level";
	private static final String OPT_MONEY = "antall_kroner";
	private static final String OPT_OVEN_LOCK_LEVEL1 = "oven_level_1_kjopt";
	private static final String OPT_OVEN_LOCK_LEVEL2 = "oven_level_2_kjopt";
	private static final String OPT_OVEN_LOCK_LEVEL3 = "oven_level_3_kjopt";
	private static final String OPT_AMOUNT_HCl = "mengde_hcl";
	private static final String OPT_AMOUNT_ZIRKON = "mengde_zirkon";
	private static final String OPT_AMOUNT_METALLURGISK_SILLISUM = "mengde_eg_silisium";
	private static final String OPT_AMOUNT_RENT_SILLSIUM = "mengde_rent_sillisum";
	
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
        
        antallMetallurgiskSillisumView = (TextView) findViewById(R.id.antall_metallurgisk_sillisum_fabrikk);
        antallMetallurgiskSillisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILLISUM);
        antallMetallurgiskSillisumView.setText(metallurgiskSillisumTittel + Integer.toString(antallMetallurgiskSillisum));
        
        antallRentSillisumView = (TextView) findViewById(R.id.antall_rent_sillisum_fabrikk);
        antallRentSillisum = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILLSIUM);
        antallRentSillisumView.setText(rentSillisiumTittel + Integer.toString(antallRentSillisum));
        
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
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.ovn_level_1_frabrikk:
			//legge in else if på om du har nok kvarts
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
			else if((antallKvarts >= 50) && (antallPenger >= price_oven_1 + environment_cost_level1)) {
				//sette på klokke
				//trekke penger
				//trekke kvarts
				//(gevinst med en gang eller lagres om andre former for sillisum??)
				//(det må addes metallurgisk silisum) - det du får
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
			//level = 6;
			//levelView.setText(levelTittel + Integer.toString(level) + "/10");
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
			//legge inn else if på du har nok av metalurgisk silisum til å fordle det og HCL
			else if((antallMetallurgiskSillisum >= 50) && (antallPenger >= price_oven_2 + environment_cost_level2)) {
				//sette på klokke
				//trekke penger
				//trekke metallurgisk silisum
				//(gevinst med en gang eller lagres om andre former for sillisum??)
				//(adde rent sillisum) - det du får
			}
			else {
				if((antallMetallurgiskSillisum < 50) && (antallPenger < price_oven_2 + environment_cost_level2)) {
					toast("Du har ikke tilstrekkelig med verken kvarts eller penger.");
				}
				else if(antallMetallurgiskSillisum < 50) {
					toast("Du har ikke tilstrekkelig med kvarts.");
				}
				else if(antallPenger < price_oven_2 + environment_cost_level2) {
					toast("Du har ikke tilstrekkelig med penger.");
				}
			}
			//level = 1;
			//levelView.setText(levelTittel + Integer.toString(level) + "/10");	
			break;
		case R.id.ovn_level_3_frabrikk:
			//legge inn else if på om du har nok av EG silsium og zirkonium
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
			else {
				toast("Du har allerede kjøpt ovenen.");
			}
			break;
		}
	}
	
	private void updateProductionFactors() {
		if(level == 1) {
			factor_level1 = 0.3;
			speed_oven_1 = 180;
			price_oven_1 = 100000;
			environment_cost_level1 = 100000;
		}
		else if(level == 2) {
			factor_level1 = 0.4;
			speed_oven_1 = 170;
			price_oven_1 = 90000;
			environment_cost_level1 = 50000;
		}
		else if(level == 3) {
			factor_level1 = 0.5;
			speed_oven_1 = 160;
			price_oven_1 = 80000;
			environment_cost_level1 = 50000;
		}
		else if(level == 4) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			environment_cost_level1 = 0;
		}
		else if(level == 5) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			factor_level2 = 0.5;
			speed_oven_2 = 150;
			price_oven_2 = 100000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 10000;
		}
		else if(level == 6) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			factor_level2 = 0.5;
			speed_oven_2 = 140;
			price_oven_2 = 90000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 10000;
		}
		else if(level == 7) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 130;
			price_oven_2 = 80000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 10000;
		}
		else if(level == 8) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 120;
			price_oven_2 = 70000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 0;
		}
		else if(level == 9) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 120;
			price_oven_2 = 70000;
			factor_level3 = 0.6;
			speed_oven_3 = 120;
			price_oven_3 = 80000;
			environment_cost_level1 = 0;
			environment_cost_level2 = 0;
			environment_cost_level3 = 0;
		}
		else if(level == 10) {
			factor_level1 = 0.4;
			speed_oven_1 = 150;
			price_oven_1 = 70000;
			factor_level2 = 0.6;
			speed_oven_2 = 120;
			price_oven_2 = 70000;
			factor_level3 = 0.7;
			speed_oven_3 = 100;
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
	}

	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
}