package no.penrose.prosjekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Factory extends Activity implements OnClickListener {
	private LinearLayout root;
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factory);
        root = (LinearLayout) findViewById(R.id.factory_root);
        root.setOnClickListener(this);
        image1 = (ImageView) findViewById(R.id.factory_image1);
        image1.setOnClickListener(this);
        image2 = (ImageView) findViewById(R.id.factory_image2);
        image2.setOnClickListener(this);
        image3 = (ImageView) findViewById(R.id.factory_image3);
        image3.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.factory_image1:
			toast("Min fabrikk??");
			break;
		case R.id.factory_image2:
			toast("Oppgrader produksjonen??");
			break;
		case R.id.factory_image3:
			toast("Invistere i forskning??");
			break;
		}
	}

	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
}