package com.example.tinylove.Adapter;

import java.util.LinkedList;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyBackgroundRecyclerAdapter.ViewHolder;
import com.example.tinylove.Interface.ItemClickListener;
import com.example.tinylove.View.CustomImageView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyWishRecyclerAdapter extends RecyclerView.Adapter<MyWishRecyclerAdapter.ViewHolder>{

	LinkedList<String> wishContent;
	LinkedList<Integer> isFinish;
	
	ItemClickListener itemClickListener;
	


	//选背景图片list数据初始化
	public MyWishRecyclerAdapter(LinkedList<String> wishContent,LinkedList<Integer> isFinish){
		this.wishContent=wishContent;
		this.isFinish=isFinish;
	}
	
	public void setOnClickListener(ItemClickListener itemClickListener){
		this.itemClickListener=itemClickListener;
	}
		
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return wishContent.size();
	}

	@Override
	public void onBindViewHolder(MyWishRecyclerAdapter.ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		viewHolder.textWishContent.setText(wishContent.get(position));
		if(isFinish.get(position)==1){
			viewHolder.imageWishIsFinish.setVisibility(View.VISIBLE);
		}
		else{
			viewHolder.imageWishIsFinish.setVisibility(View.GONE);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		// TODO Auto-generated method stub
		View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wish_recycler_item, viewGroup,false);
		return new ViewHolder(view, itemClickListener);
	}
	
	
	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		private TextView textWishContent;
		private ImageView imageWishIsFinish;
		
		ItemClickListener listener;
		
		public ViewHolder(View itemView,ItemClickListener itemClickListener) {
			super(itemView);
			// TODO Auto-generated constructor stub
			textWishContent=(TextView)itemView.findViewById(R.id.wish_item_3_text);
			imageWishIsFinish=(ImageView)itemView.findViewById(R.id.wish_item_4_finish);
			
			itemView.setOnClickListener(this);
			
			this.listener=itemClickListener;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			listener.onItemClickListener(getPosition());
		}
		
	}
	

}
