package com.example.mariyan.startform;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.mariyan.startform.R.id.nickname_text_id;


public class LoginActivity extends Activity {

    private Button startButton;
    private EditText nicknameEditText;
    private Button backButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences sharedPreferences = getSharedPreferences(Constants.NICKNAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        nicknameEditText = (EditText) findViewById(nickname_text_id);

        String setText = sharedPreferences.getString(Constants.NICKNAME, "");
        if (setText != null) {
            nicknameEditText.setText(setText);
        }

        startButton = (Button) findViewById(R.id.start_button_id);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TEXTNick", nicknameEditText.getText().toString().length() + "");
                if (nicknameEditText.getText().toString().length() != 0) {
                    String message = "The screen is going to rotate to landscape";
                    AlertDialog.Builder builder = Constants.alertMessage("Start", message, null, view.getContext());
                    builder.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(LoginActivity.this, Puzzle.class);
                            intent.putExtra(Constants.LOGIN_EXTRA, nicknameEditText.getText().toString());
                            intent.putExtra(Constants.START_TIMER, System.currentTimeMillis());
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.show();

                } else {
                    String title = "Error!";
                    String message = "Nickname field is empty!";
                    AlertDialog.Builder builder = Constants.alertMessage(title, message, "Ok", view.getContext());

                    builder.show();
                }

            }
        });

        backButton = (Button) findViewById(R.id.back_button_id);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
            }
        });

        saveButton = (Button) findViewById(R.id.save_button_id);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(Constants.NICKNAME, nicknameEditText.getText().toString());
                editor.commit();
            }
        });




    }



}
