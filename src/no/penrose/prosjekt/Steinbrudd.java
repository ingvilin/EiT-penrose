package no.penrose.prosjekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Steinbrudd extends Activity implements OnClickListener {
	private Button tilbake;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steinbrudd);
        
        tilbake = (Button) findViewById(R.id.til_fabrikken);
        tilbake.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.til_fabrikken:
			//sende tilbake data med putExtra
			finish();
			break;
		}
	}
}