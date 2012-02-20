package no.penrose.prosjekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        button_steinbrudd = (Button) findViewById(R.id.til_steinbruddet);
        button_steinbrudd.setOnClickListener(this);
        button_avslutt = (Button) findViewById(R.id.avslutt);
        button_avslutt.setOnClickListener(this);
        
        Spill mSpill = new Spill();
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.fabrikk_image1:
			toast("Min fabrikk.");
			break;
		case R.id.fabrikk_image2:
			toast("Oppgrader produksjonen??");
			break;
		case R.id.fabrikk_image3:
			toast("Invistere i forskning??");
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