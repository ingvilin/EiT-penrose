package no.penrose.prosjekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Forskning extends Activity{
	private Intent triviaIntent;
	private TextView pengerView, levelView;

	int level, penger, priceSmallDonasjon = 10000, priceMediumDonasjon = 50000, priceStorDonasjon = 100000;
	int smallDonasjon = -1, mediumDonasjon = -1, storDonasjon = -1;
	
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
	private static final String OPT_REPORT_THREE = "rapport_tre";

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forskningssenter);
		
		penger = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_MONEY);
		level = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_LEVEL);
		smallDonasjon = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_LITTLE);
		mediumDonasjon = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_MEDIUM);
		storDonasjon = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_DONATE_LARGE);
		
		triviaIntent = new Intent(getApplicationContext(), Trivia.class);
        
        pengerView = (TextView) findViewById(R.id.penger_forskning);
        penger = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_MONEY);
        pengerView.setText("Penger: " + Integer.toString(penger));
        
        levelView = (TextView) findViewById(R.id.level_forskning);
        level = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_LEVEL);
        levelView.setText("Level: " + Integer.toString(level) + "/10");
	}
	
	public void startTrivia(View view){
		startActivity(triviaIntent);
	
	}
	
	public void startDonasjon(View view) {
		final String [] items=new String []{"UiB forskningsrapport kr " + priceSmallDonasjon, "UiO forskningsrapport kr " + priceMediumDonasjon, "NTNU forskningsrapport kr " + priceStorDonasjon};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Ønsker du å bestille en god forskningsrapport fra UiB, en litt bedre fra UiO eller en veldig god fra NTNU?");

		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					if ((penger >= priceSmallDonasjon) && (smallDonasjon == -1)) {
						penger = penger - priceSmallDonasjon;
						pengerView.setText("Penger: " + Integer.toString(penger));
						PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_LITTLE, 1);
						PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_TIME, (int) SystemClock.elapsedRealtime());
					}
					else if (penger < priceSmallDonasjon) {
						toast("Du har ikke råd til forskning.");
					}
					else {
						toast("Du venter allerede på en forskningsrapport.");
					}
				}
				else if (which == 1) {
					if ((penger >= priceMediumDonasjon) && (mediumDonasjon == -1)) {
						penger = penger - priceMediumDonasjon;
						pengerView.setText("Penger: " + Integer.toString(penger));
						PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_MEDIUM, 1);
						PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_TIME, (int) SystemClock.elapsedRealtime());
					}
					else if (penger < priceMediumDonasjon){
						toast("Du har ikke råd til forskning.");
					}
					else {
						toast("Du venter allerede på en forskningsrapport.");
					}
				}
				else if (which == 2) {
					if ((penger >= priceStorDonasjon) && (storDonasjon == -1)) {
						penger = penger - priceStorDonasjon;
						pengerView.setText("Penger: " + Integer.toString(penger));
						PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_LARGE, 1);
						PreferenceController.saveIntPreferences(getApplicationContext(), OPT_DONATE_TIME, (int) SystemClock.elapsedRealtime());
					}
					else if (penger < priceStorDonasjon) {
						toast("Du har ikke råd til forskning.");
					}
					else {
						toast("Du venter allerede på en forskningsrapport.");
					}
				}
			}
		});
		builder.show();
	}
	
	public void startJuks(View view) {
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_LEVEL, 4);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_MONEY, 10000000);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, 200);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_ZIRKON, 200);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_RENT_SILSIUM, 200);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_METALLURGISK_SILISUM, 200);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_HCl, 200);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_AMOUNT_EG_SILISUM, 200);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_REPORT_THREE, 1);
		penger = 10000000;
		level = 4;
		pengerView.setText("Penger: " + Integer.toString(penger));
		levelView.setText("Level: " + Integer.toString(level) + "/10");
		toast("Du er har kommet til level 4 og har rikelig med ressurser.");
	}
	
	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
}
