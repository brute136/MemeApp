package com.teenx.chillMeme.Adapters;

import android.content.ClipData;
import android.content.Context;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.teenx.chillMeme.Adapters.RecyclerViewAdapter.mHolder;
import com.teenx.chillMeme.DataStructures;
import com.teenx.chillMeme.Logs;
import com.teenx.chillMeme.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mHolder>{
	Context context;
	ArrayList<DataStructures> list;
	public RecyclerViewAdapter(Context context,ArrayList<DataStructures> list){
		this.context = context;
		this.list = list;
	}
	@Override
	public mHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_layout,parent,false);
		mHolder holder = new mHolder(view);
	    return holder;
	}

	@Override
	public void onBindViewHolder(mHolder holder, int position) {
		holder.bind(list.get(position).getUrl(),list.get(position).getAuthorName());
	}

	@Override
	public int getItemCount() {
	    return list.size();
	}
	class mHolder extends RecyclerView.ViewHolder{
		ImageView image;
		TextView url,author;
		public mHolder(View v){
			super(v);
			image = v.findViewById(R.id.img);
			this.url = v.findViewById(R.id.url);
			this.author = v.findViewById(R.id.author);
		}
		public void bind(String url,String authorName){
			Glide.with(context).load(url).into(image);
			this.url.setText(url);
			this.author.setText(authorName);
			this.url.setOnClickListener(new View.OnClickListener(){
				
				@Override
				public void onClick(View v) {
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
					android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", url);
					clipboard.setPrimaryClip(clip);
					Toast.makeText(context,"Copied",Toast.LENGTH_SHORT).show();
				}

			});
		}
	}

}