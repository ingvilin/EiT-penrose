package no.penrose.prosjekt;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Trivia extends Activity{

	private int kostnadTrivia, levelTeller, questionTeller;
	private Button buttonLevel1, buttonLevel2, buttonLevel3, buttonLevel4, buttonLevel5, buttonLevel6, buttonLevel7, buttonLevel8, buttonLevel9, buttonLevel10;
	ArrayList <Button> levelButtonArray = new ArrayList<Button>();
	private Dialog settingsDialog;
	
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
		
		settingsDialog = new Dialog(this); 
	}

	public void levelEn(View view){
		questionTeller = 0;
		settingsDialog = new Dialog(this);
		settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
		settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.level_one_question_one 
		        , null)); 
		settingsDialog.show(); 

	}
	
	public void correctAnswer(View view){
		switch(questionTeller){
		case 0:
			settingsDialog.dismiss();
			settingsDialog = new Dialog(this);
			settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
			settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.level_one_question_two 
			        , null)); 
			settingsDialog.show(); 
			questionTeller++;
			break;
		case 1:
			settingsDialog.dismiss();
			settingsDialog = new Dialog(this);
			settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
			settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.level_one_question_one 
			        , null)); 
			settingsDialog.show(); 
			questionTeller++;
			break;
		case 2:
			settingsDialog.dismiss();
			settingsDialog = new Dialog(this);
			settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
			settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.level_one_question_one
			        , null)); 
			settingsDialog.show(); 
			questionTeller++;
			break;
		case 3:
			settingsDialog.dismiss();
			levelButtonArray.get(levelTeller-1).setBackgroundDrawable(getResources().getDrawable(R.drawable.levelbuttonfinished));
			levelButtonArray.get(levelTeller-1).setClickable(false);
			levelTeller++;
			levelButtonArray.get(levelTeller-1).setClickable(true);
			for (int i = 0; i < levelTeller; i++) {
				System.out.println("Er denne sant tralalalala " + levelButtonArray.get(i).isClickable() + "     " + i);
			}
			
		}
			
	}
	
	public void wrongAnswer(View view){
		Toast toast = Toast.makeText(getApplicationContext(), "Feil svar", Toast.LENGTH_SHORT);
		settingsDialog.dismiss();
		toast.show();
	}
}
