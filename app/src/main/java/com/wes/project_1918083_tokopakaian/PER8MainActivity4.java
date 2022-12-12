package com.wes.project_1918083_tokopakaian;

import static com.wes.project_1918083_tokopakaian.PER8DBmain.TABLENAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.content.SharedPreferences;
import com.google.android.material.navigation.NavigationView;
import com.wes.project_1918083_tokopakaian.databinding.ActivityPer8Main4Binding;
import java.util.ArrayList;
public class PER8MainActivity4 extends AppCompatActivity {
    PER8DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    PER8MyAdapter myAdapter;

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private ActivityPer8Main4Binding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPer8Main4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("AndroidHiveLogin", 0);
        editor = preferences.edit();
        findId();
        dBmain = new PER8DBmain(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(PER8MainActivity4.this, PER8AddContact.class);
                startActivity(a);
            }
        });
        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_pp){
                    Intent a = new Intent(PER8MainActivity4.this, MainActivity.class);
                    startActivity(a);
                }else if (id == R.id.nav_pw){
                    Intent a = new Intent(PER8MainActivity4.this, MainActivity2.class);
                    startActivity(a);
                }else if (id == R.id.nav_by){
                    Intent a = new Intent(PER8MainActivity4.this, MainActivity3.class);
                    startActivity(a);
                }else if (id == R.id.nav_per6){
                    Intent a = new Intent(PER8MainActivity4.this, PER6MainActivity4.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_per8){
                    Intent a = new Intent(PER8MainActivity4.this, PER8MainActivity4.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_per9){
                    Intent a = new Intent(PER8MainActivity4.this, PER9MainActivity4.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_per10){
                    Intent a = new Intent(PER8MainActivity4.this, PER10LoginActivity.class);
                    startActivity(a);
                    editor.clear();
                    editor.commit();
                }

                return true;
            }
        });
    }
    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLENAME,null);
        ArrayList<PER8Model> models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[]avatar = cursor.getBlob(3);
            String nomer = cursor.getString(2);
            models.add(new PER8Model(id,avatar,name,nomer));
        }
        cursor.close();
        myAdapter = new PER8MyAdapter(this, R.layout.activity_per8_singledata,models,sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }
    private void findId() {
        recyclerView = findViewById(R.id.rv);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}