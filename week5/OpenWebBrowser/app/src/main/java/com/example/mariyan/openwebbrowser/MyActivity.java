package com.example.mariyan.openwebbrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends Activity  {

    private Button dialButton;
    private Button alarmButton;
    private Button browserButton;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        text = (EditText) findViewById(R.id.text);

        dialButton = (Button) findViewById(R.id.dial);
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "tel:" + text.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse(number));
                startActivity(intent);
            }
        });

        alarmButton = (Button) findViewById(R.id.alarm);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setAlarm = text.getText().toString();
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(setAlarm.substring(0, setAlarm.indexOf(":"))));
                intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(setAlarm.substring(setAlarm.indexOf(":")+1, setAlarm.length()-1)));
                startActivity(intent);

            }
        });

        browserButton = (Button) findViewById(R.id.browser);
        browserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://" + text.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
