package com.teenx.chillMeme;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.teenx.chillMeme.Adapters.RecyclerViewAdapter;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
	private ImageView image,logs;
	private MaterialButton btn;
	ProgressBar progress;
	public static  ArrayList<DataStructures> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		image = findViewById(R.id.img);
		btn = findViewById(R.id.btn);
		progress = findViewById(R.id.progress);
		logs = findViewById(R.id.logs);
		init();
		list = new ArrayList<>();
		btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				init();
			}

		});
		logs.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Logs.class);
				startActivity(intent);
			}

		});
    }
	public static void initAdapter(Context context,RecyclerView r){
		RecyclerViewAdapter adapter = new RecyclerViewAdapter(context,list);
		r.setAdapter(adapter);
		
	}
	public void vis(int fst,int scnd){
		image.setVisibility(fst);
		btn.setVisibility(fst);
		progress.setVisibility(scnd);
	}
	public void init(){
		vis(View.GONE,View.VISIBLE);
		//JSONObject IO;
		final OkHttpClient clientt = new OkHttpClient();
		String url ="https://meme-api.herokuapp.com/gimme";
		String author = "Author :- %s";
		Request request = new Request.Builder()
				.url(url).
				build();
		clientt.newCall(request)
				.enqueue(new Callback(){
					@Override
					public void onFailure(Call request, IOException error) {
						Log.d("LogsOfOkhttp","Not Working From Sorry");
					}

					@Override
					public void onResponse(Call request, Response response) {
						if (!response.isSuccessful()){
							return;
						}
 						try{
						   JSONObject IO = new JSONObject(response.body().string());
						   MainActivity.this.runOnUiThread(new Runnable(){
							   
								@Override
								public void run() {
									try{
									   Glide.with(MainActivity.this).load(IO.getString("url")).into(image);
									   Toast.makeText(MainActivity.this,"fetched",Toast.LENGTH_SHORT).show();
									   list.add(new DataStructures(IO.getString("url"),String.format(author,IO.getString("author"))));
									   vis(View.VISIBLE,View.GONE);
									   }
									   catch (Exception a){}
								}

						   });
						  // Glide.with(MainActivity.this).load(IO.getString("url")).into(image);
						}catch (Exception c){}
					}

			});
		}
}