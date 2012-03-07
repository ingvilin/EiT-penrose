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
import android.widget.Toast;

public class Fabrikk extends Activity implements OnClickListener {
	private LinearLayout root;
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private Button button_steinbrudd;
	private Button button_avslutt;
	private Button button_startProduksjonElektro;
	private Button button_startProduksjonLegering;
	private Button button_startProduksjonSolcelle;
	
	int testAntallKvarts = 0;
	private static final String OPT_KVARTS = "antall_kvarts";
	
	Spill mSpill = new Spill();
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabrikk);        
        root = (LinearLayout) findViewById(R.id.fabrikk_root);
        root.setOnClickListener(this);
        image1 = (ImageView) findViewById(R.id.fabrikk_image1);
        image1.setOnClickListener(this);
        image2 = (ImageView) findViewById(R.id.fabrikk_image2);
        image2.setOnClickListener(this);
        image3 = (ImageView) findViewById(R.id.fabrikk_image3);
        image3.setOnClickListener(this);
        button_startProduksjonElektro = (Button) findViewById(R.id.start_produksjon_elektro);
        button_startProduksjonElektro.setOnClickListener(this);
        button_startProduksjonLegering = (Button) findViewById(R.id.start_produksjon_legering);
        button_startProduksjonLegering.setOnClickListener(this);
        button_startProduksjonSolcelle = (Button) findViewById(R.id.start_produksjon_solcelle);
        button_startProduksjonSolcelle.setOnClickListener(this);
        button_steinbrudd = (Button) findViewById(R.id.til_steinbruddet);
        button_steinbrudd.setOnClickListener(this);
        button_avslutt = (Button) findViewById(R.id.avslutt);
        button_avslutt.setOnClickListener(this);
    }
    
    public void askHowMuchToProduce(final int type) {
    	final CharSequence[] items = {"5", "10", "50", "100", "1 000", "10 000", "100 000", "1 000 000"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Velg hvor mye du ønsker å produsere");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if(type == 1) {
					if(item == 0)
						mSpill.startProduction(5, 1);
					else if(item == 1)
						mSpill.startProduction(10, 1);
					else if(item == 2)
						mSpill.startProduction(50, 1);
					else if(item == 3)
						mSpill.startProduction(100, 1);
					else if(item == 4)
						mSpill.startProduction(1000, 1);
					else if(item == 5)
						mSpill.startProduction(10000, 1);
					else if(item == 6)
						mSpill.startProduction(100000, 1);
					else if(item == 7)
						mSpill.startProduction(1000000, 1);
				}
				else if(type == 2) {
					if(item == 0)
						mSpill.startProduction(5, 2);
					else if(item == 1)
						mSpill.startProduction(10, 2);
					else if(item == 2)
						mSpill.startProduction(50, 2);
					else if(item == 3)
						mSpill.startProduction(100, 2);
					else if(item == 4)
						mSpill.startProduction(1000, 2);
					else if(item == 5)
						mSpill.startProduction(10000, 2);
					else if(item == 6)
						mSpill.startProduction(100000, 2);
					else if(item == 7)
						mSpill.startProduction(1000000, 2);
				}
				else if(type == 3) {
					if(item == 0)
						mSpill.startProduction(5, 3);
					else if(item == 1)
						mSpill.startProduction(10, 3);
					else if(item == 2)
						mSpill.startProduction(50, 3);
					else if(item == 3)
						mSpill.startProduction(100, 3);
					else if(item == 4)
						mSpill.startProduction(1000, 3);
					else if(item == 5)
						mSpill.startProduction(10000, 3);
					else if(item == 6)
						mSpill.startProduction(100000, 3);
					else if(item == 7)
						mSpill.startProduction(1000000, 3);
				}
				toast("Du har nå igjen: " + mSpill.getRocks() + " kg silisiumstein");
			}
		});
		AlertDialog alert = builder.show();
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.fabrikk_image1:
			toast("Min fabrikk.");
			testAntallKvarts = 500;
			//saveIntPreferences(OPT_KVARTS, 200);
			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, 200);
			break;
		case R.id.fabrikk_image2:
			toast("Oppgrader produksjonen??");
			break;
		case R.id.fabrikk_image3:
			toast("Invistere i forskning??");
			break;
		case R.id.start_produksjon_elektro:
			askHowMuchToProduce(1);
			break;
		case R.id.start_produksjon_legering:
			askHowMuchToProduce(2);
			break;
		case R.id.start_produksjon_solcelle:
			askHowMuchToProduce(3);
			break;
		case R.id.til_steinbruddet:
			Intent i = new Intent(this, Steinbrudd.class);
			//send data med putExtra
			startActivity(i);
			break;
		case R.id.avslutt:
			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
			alertbox.setMessage("Er du sikker på at du ønsker å avslutte og selge alt?");
			alertbox.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			alertbox.setNegativeButton("Nei", new DialogInterface.OnClickListener() {	
				public void onClick(DialogInterface dialog, int which) {
					//Ikke gjøre noe som helst
				}
			});
			alertbox.show();
			break;
		}
	}

	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
}