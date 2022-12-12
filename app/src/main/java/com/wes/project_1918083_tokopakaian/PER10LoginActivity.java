package com.wes.project_1918083_tokopakaian;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import com.wes.project_1918083_tokopakaian.databinding.ActivityPer10LoginBinding;
import android.widget.Toast;
public class PER10LoginActivity extends AppCompatActivity implements View.OnClickListener {
    PER10MyDbHelper myDbHelper;
    private ActivityPer10LoginBinding binding;
    private PER10SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per10_login);
        getSupportActionBar().hide();
        binding = ActivityPer10LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        session = new PER10SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(PER10LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        session = new PER10SessionManager(getApplicationContext());
        myDbHelper = new PER10MyDbHelper(this);
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        binding.Signinbuttonid.setOnClickListener(this);
        binding.SignUpHerebuttonid.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String username = binding.signinusernameEditText.getText().toString();
        String password = binding.signinpasswordEditText.getText().toString();
        if (v.getId() == R.id.Signinbuttonid) {
            Boolean result = myDbHelper.findPassword(username,
                    password);
            if (result == true) {
                Intent intent = new Intent(PER10LoginActivity.this, MainActivity.class);
                startActivity(intent);
                session.setLogin(true);
                finish();
            } else {
                Toast.makeText(this, "username and password didn`t match", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.SignUpHerebuttonid) {
            Intent intent = new Intent(PER10LoginActivity.this, PER10RegisterActivity.class);
            startActivity(intent);
        }
    }
}