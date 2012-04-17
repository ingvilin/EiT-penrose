package no.penrose.prosjekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
	
	private int buyPriceHCl = 25000;
	private int buyPriceZirkonium = 30000;
	private int maksKvartsGrense = 200;
	
	private int level = -1;
	private int antallKvarts = -1;
	private int antallMetallurgiskSilisum = -1;
	private int antallEgSilisum = -1;
	private int antallRentSilisum = -1;
	private int antallPenger = -1;
	private int antallHCl = -1;
	private int antallZirkonium = -1;
	
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kontor);
		
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
	}
	
	public void onClick(View v) {
		//switch (v.getId()) {
		//case R.id.kontoret_kontor:
		//	toast("hei dette er en test for on click!");
		//	break;
		//}
	}
	
	public void kontorOnClick(View v) {
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
}