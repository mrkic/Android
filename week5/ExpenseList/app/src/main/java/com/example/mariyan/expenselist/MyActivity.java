package com.example.mariyan.expenselist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    private Button button;
    private List<Bills> list;
    private EditText label;
    private EditText price;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        button = (Button) findViewById(R.id.button);
        label = (EditText) findViewById(R.id.labelA);
        price = (EditText) findViewById(R.id.priceA);
        list = new ArrayList<Bills>();
        final ListView listView = (ListView) findViewById(R.id.list_view);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float priceString = Float.valueOf(price.getText().toString());
                String labelString = label.getText().toString();

                Bills bills = new Bills(labelString, priceString);
                list.add(bills);

                myAdapter.notifyDataSetChanged();
            }
        });
//
        myAdapter = new MyAdapter(list, MyActivity.this);
        myAdapter.notifyDataSetChanged();
        listView.setAdapter(myAdapter);


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
