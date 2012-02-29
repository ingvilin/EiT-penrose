package no.penrose.prosjekt;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class Trivia extends Activity{

	private int kostnadTrivia;
	private int levelTeller;
	private Button buttonLevel1, buttonLevel2, buttonLevel3, buttonLevel4, buttonLevel5, buttonLevel6, buttonLevel7, buttonLevel8, buttonLevel9, buttonLevel10;
	ArrayList <Button> levelButtonArray = new ArrayList<Button>();
	public Trivia (){
		levelTeller = 1;
	}
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trivia);
		
		buttonLevel1 = (Button) findViewById(R.id.buttonlevel1);
		buttonLevel2 = (Button) findViewById(R.id.buttonlevel2);
		buttonLevel3 = (Button) findViewById(R.id.buttonlevel3);
		buttonLevel4 = (Button) findViewById(R.id.buttonlevel4);
		buttonLevel5 = (Button) findViewById(R.id.buttonlevel5);
		buttonLevel6 = (Button) findViewById(R.id.buttonlevel6);
		buttonLevel7 = (Button) findViewById(R.id.buttonlevel7);
		buttonLevel8 = (Button) findViewById(R.id.buttonlevel8);
		buttonLevel9 = (Button) findViewById(R.id.buttonlevel9);
		buttonLevel10 = (Button) findViewById(R.id.buttonlevel10);
		
		levelButtonArray.add(buttonLevel1);
		levelButtonArray.add(buttonLevel2);
		levelButtonArray.add(buttonLevel3);
		levelButtonArray.add(buttonLevel4);
		levelButtonArray.add(buttonLevel5);
		levelButtonArray.add(buttonLevel6);
		levelButtonArray.add(buttonLevel7);
		levelButtonArray.add(buttonLevel8);
		levelButtonArray.add(buttonLevel9);
		levelButtonArray.add(buttonLevel10);
		
		for (int i = 0; i < levelTeller; i++) {
			levelButtonArray.get(i).setClickable(true);
		}
		for (int i = 0; i < levelTeller-1; i++) {
			levelButtonArray.get(i).setBackgroundColor(Color.GREEN);
			}
	}

	public void levelEn(View view){

		AlertDialog.Builder startAlertBox = new AlertDialog.Builder(this);
		startAlertBox.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		startAlertBox.setMessage("Denne avktiviteten koster " + kostnadTrivia + " kr.");
		startAlertBox.setNeutralButton("Start", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			//TODO inkrementere penger.
				level1Questions();
				System.out.println("kjem du her -------------------------------");
			}
		});
		startAlertBox.show();

	}
	
	public void level1Questions(){
		AlertDialog.Builder question1Alert = new AlertDialog.Builder(this);
		question1Alert.setMessage("Hvilken kake skal vi spise neste kakedag?");
		question1Alert.setPositiveButton("s",  new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				levelButtonArray.get(0).setBackgroundColor(Color.GREEN);
			}
			});
		question1Alert.setNeutralButton("b",  null);
		question1Alert.setNeutralButton("bl",  null);
		question1Alert.setNeutralButton("ba",  null);
		
		question1Alert.show();
	}
}
