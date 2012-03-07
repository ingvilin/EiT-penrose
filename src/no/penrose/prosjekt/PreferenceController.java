package no.penrose.prosjekt;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;

public class PreferenceController extends PreferenceActivity {
	private static final String PREF_NAME = "SPILL_ATTRIBUTT";
	private static final int MODE = Context.MODE_WORLD_READABLE;

	// Navn til de ulike variabelene som lagres
	//private static final String OPT_PENGER = "penger";
	//private static final String OPT_KVARTS = "kvarts";
	//private static final String OPT_ST_UTGRAVING = "st_utgraving"; //st = starttidspunkt

	public static void saveIntPreferences(Context context, String key, int value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);//this.getSharedPreferences(PREF_NAME, MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	private static void saveStringPreferences(Context context, String key, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static int loadIntPreferences(Context context, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		int savedInt = sharedPreferences.getInt(key, 0);
		return savedInt;
	}

}