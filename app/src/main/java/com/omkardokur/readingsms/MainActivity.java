package com.omkardokur.readingsms;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar beginTime = Calendar.getInstance();
        ContentValues l_event = new ContentValues();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);
        String sms = "";

        for (int i = 0; i < 15; i++) {
            if(cur.moveToNext()) {
                beginTime.setTimeInMillis(cur.getLong(4));
                l_event.put("calendar_id", 1);
                l_event.put("title", cur.getString(2));
                l_event.put("description", cur.getString(13));
                l_event.put("eventLocation", "Mobile");
                l_event.put("dtstart", beginTime.getTimeInMillis());
                l_event.put("dtend", beginTime.getTimeInMillis());
                l_event.put("allDay", 0);
                l_event.put("rrule", "FREQ=YEARLY");
                l_event.put("eventTimezone", "India");
                Uri l_eventUri;
                if (Build.VERSION.SDK_INT >= 8) {
                    l_eventUri = Uri.parse("content://com.android.calendar/events");
                } else {
                    l_eventUri = Uri.parse("content://calendar/events");
                }
                Uri l_uri = MainActivity.this.getContentResolver()
                        .insert(l_eventUri, l_event);

            }

            //  tv.setText(sms);

        }
    }

}
