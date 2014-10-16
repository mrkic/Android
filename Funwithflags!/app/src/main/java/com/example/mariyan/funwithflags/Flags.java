package com.example.mariyan.funwithflags;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class Flags extends Activity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulgarian_flag);

        view = findViewById(R.id.flag);

        view.setOnClickListener(new View.OnClickListener() {

            int randomIndex = 0;
            Random random = new Random();
            @Override
            public void onClick(View view) {

                randomIndex = random.nextInt(11);
                switch (randomIndex){
                    case 0: {
                        int[] colors = getResources().getIntArray(R.array.russia);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Russia");

                        break;
                    }
                    case 1: {
                        int[] colors = getResources().getIntArray(R.array.austria);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Austria");

                        break;
                    }
                    case 2: {
                        int[] colors = getResources().getIntArray(R.array.belarus);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Belarus");

                        break;
                    }
                    case 3: {
                        int[] colors = getResources().getIntArray(R.array.germany);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Germany");

                        break;
                    }
                    case 4: {
                        int[] colors = getResources().getIntArray(R.array.spain);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Spain");

                        break;
                    }
                    case 5: {
                        int[] colors = getResources().getIntArray(R.array.luxembourg);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Luxembourg");

                        break;
                    }
                    case 6: {
                        int[] colors = getResources().getIntArray(R.array.holland);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Holland");

                        break;
                    }
                    case 7: {
                        int[] colors = getResources().getIntArray(R.array.armenia);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Armenia");

                        break;
                    }
                    case 8: {
                        int[] colors = getResources().getIntArray(R.array.estonia);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Estonia");

                        break;
                    }
                    case 10: {
                        int[] colors = getResources().getIntArray(R.array.serbia);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Serbia");

                        break;
                    }
                    default: {
                        int[] colors = getResources().getIntArray(R.array.bulgaria);
                        findViewById(R.id.first).setBackgroundColor(colors[0]);
                        findViewById(R.id.second).setBackgroundColor(colors[1]);
                        findViewById(R.id.third).setBackgroundColor(colors[2]);
                        TextView textView = (TextView) findViewById(R.id.country_name);
                        textView.setText("Bulgaria");

                        break;

                    }
                }



            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.flags, menu);
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
