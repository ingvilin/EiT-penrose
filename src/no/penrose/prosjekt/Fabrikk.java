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
	private ImageView image_level1;
	private ImageView image_level2;
	private ImageView image_level3;
	
	private int level = -1;
	private int antallKvarts = -1;

	private static final String OPT_KVARTS = "antall_kvarts";
	private static final String OPT_LEVEL = "spillerens_level";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabrikken);        
        image_level1 = (ImageView) findViewById(R.id.ovn_level_1_frabrikk);
        image_level1.setOnClickListener(this);
        image_level2 = (ImageView) findViewById(R.id.ovn_level_2_frabrikk);
        image_level2.setOnClickListener(this);
        image_level3 = (ImageView) findViewById(R.id.ovn_level_3_frabrikk);
        image_level3.setOnClickListener(this);
        
        level = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_LEVEL);
        if(level < 2) {
        	image_level1.setVisibility(View.INVISIBLE);
        }
        if(level < 5) {
        	image_level2.setVisibility(View.INVISIBLE);
        }
        if(level < 8) {
        	image_level3.setVisibility(View.INVISIBLE);
        }
        
        antallKvarts = PreferenceController.loadIntPreferences(this.getApplicationContext(), OPT_KVARTS);
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.ovn_level_1_frabrikk:
			level = 6;
			//saveIntPreferences(OPT_KVARTS, 200);
			PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, 200);
			break;
		case R.id.ovn_level_2_frabrikk:
			toast("Oppgrader produksjonen??");
			break;
		case R.id.ovn_level_3_frabrikk:
			toast("Invistere i forskning??");
			break;
		}
	}
	
	protected void onStop() {
		super.onStop();
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_KVARTS, antallKvarts);
		PreferenceController.saveIntPreferences(this.getApplicationContext(), OPT_LEVEL, level);
	}

	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
}