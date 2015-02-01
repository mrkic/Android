package com.example.mariyan.startform;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RankingList extends Activity {

    private Button button;
    private TextView nickname;
    private TextView scores;
    private ImageView imageView;
    private List<User> users;
    private RankingAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);


        nickname = (TextView) findViewById(R.id.nickname_field_id);
        scores = (TextView) findViewById(R.id.scores);
        imageView = (ImageView) findViewById(R.id.icon);
        users = new ArrayList<User>();
        adapter = new RankingAdapter(RankingList.this, users);

        ImageView logo = new ImageView(RankingList.this);
        String uri = "@drawable/ic_launcher";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        logo.setImageDrawable(res);

//        users.add(new User("mrkic", 0.34, logo));

        MyDataBase dataBase = new MyDataBase(this);

        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        String query = "SELECT " + MyDataBase.RankingConstants.COLUMN_NICKNAME + ", "
                + MyDataBase.RankingConstants.COLUMN_TIME + ", "
//                + MyDataBase.RankingConstants.COLUMN_AVATAR
                + " FROM "
                + MyDataBase.RankingConstants.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.query(MyDataBase.RankingConstants.TABLE_NAME, new String[] {
                MyDataBase.RankingConstants.COLUMN_NICKNAME,
                MyDataBase.RankingConstants.COLUMN_TIME},
                null, /// where column
                null, /// where value
                null, /// group by
                null, /// having
                MyDataBase.RankingConstants.COLUMN_TIME); ///order by
        while (cursor.moveToNext()) {
            String nickname = cursor.getString(cursor.getColumnIndex(MyDataBase.RankingConstants.COLUMN_NICKNAME));
            double time = cursor.getDouble(cursor.getColumnIndex(MyDataBase.RankingConstants.COLUMN_TIME));
            users.add(new User(nickname, time, logo));
        }

        sqLiteDatabase.close();
        dataBase.close();

        listView = (ListView) findViewById(R.id.ranking_list);
        listView.setAdapter(adapter);

        button = (Button) findViewById(R.id.ranking_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RankingList.this, MyActivity.class);
//                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ranking_list, menu);
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
