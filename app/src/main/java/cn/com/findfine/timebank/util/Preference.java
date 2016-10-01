package cn.com.findfine.timebank.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import cn.com.findfine.timebank.TBApplication;

public class Preference {
	
	private static final String EVENT_COVER_ID = "event_cover_id";

	public static void saveEventCoverId(String eventId) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TBApplication.getContext());
		Editor editor = sp.edit();
		editor.putString(EVENT_COVER_ID, eventId);
		editor.commit();
	}
	
	public static String getEventCoverId() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(TBApplication.getContext());
		return sp.getString(EVENT_COVER_ID, null);
	}

}
