package no.penrose.prosjekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Forskning extends Activity{
	private ImageView trivia;
	private Intent triviaIntent;


	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forskningssenter);
		
		triviaIntent = new Intent(getApplicationContext(), Trivia.class);
		
		trivia = (ImageView) findViewById(R.id.trivia);
	}
	
	public void startTrivia(View view){
		startActivity(triviaIntent);
	
	}
}
