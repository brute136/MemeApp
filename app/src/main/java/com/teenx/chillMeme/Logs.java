package com.teenx.chillMeme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Logs extends AppCompatActivity {
   RecyclerView recycler;
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_logs);
	   getSupportActionBar().setTitle("Logs Of Memes");
	   recycler = findViewById(R.id.Recycler);
	   LinearLayoutManager manager = new LinearLayoutManager(this);
	   manager.setOrientation(LinearLayout.VERTICAL);
	   recycler.setLayoutManager(manager);
	   MainActivity.initAdapter(Logs.this,recycler);
   }
}