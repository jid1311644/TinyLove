package com.example.tinylove.Adapter;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyRecyclerAdapter.ViewHolder;
import com.example.tinylove.Interface.ItemClickListener;
import com.example.tinylove.View.CustomImageView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MyBackgroundRecyclerAdapter extends RecyclerView.Adapter<MyBackgroundRecyclerAdapter.ViewHolder>{

	//设置纪念日背景图片
	final int[] backgroundSmall={R.drawable.anni_bg_preview_1,R.drawable.anni_bg_preview_2,R.drawable.anni_bg_preview_3,R.drawable.anni_bg_preview_4,R.drawable.anni_bg_preview_5,
			R.drawable.anni_bg_preview_6,R.drawable.anni_bg_preview_7,R.drawable.anni_bg_preview_8,R.drawable.anni_bg_preview_9,R.drawable.anni_bg_preview_10,
			R.drawable.anni_bg_preview_11,R.drawable.anni_bg_preview_12,R.drawable.anni_bg_preview_13,R.drawable.anni_bg_preview_14,R.drawable.anni_bg_preview_15,
			R.drawable.anni_bg_preview_16,R.drawable.anni_bg_preview_17,R.drawable.anni_bg_preview_18,R.drawable.anni_bg_preview_19,R.drawable.anni_bg_preview_20};
	
	//选中背景图片的ID
	private int selectID=-1;
	
	ItemClickListener itemClickListener;
	
	public int getSelectID() {
		return selectID;
	}

	public void setSelectID(int selectID) {
		this.selectID = selectID;
	}

	//选背景图片list数据初始化
	public MyBackgroundRecyclerAdapter(int id){
		this.selectID=id;
	}
	
	public void setOnClickListener(ItemClickListener itemClickListener){
		this.itemClickListener=itemClickListener;
	}
		
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return backgroundSmall.length;
	}

	@Override
	public void onBindViewHolder(MyBackgroundRecyclerAdapter.ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		viewHolder.llBackPicture.setImageResource(backgroundSmall[position]);
//		viewHolder.imageSelect.setVisibility(View.GONE);
//		viewHolder.imageSelect.setChecked(false);
//		if(position==selectID){
//			viewHolder.imageSelect.setVisibility(View.VISIBLE);
//			viewHolder.imageSelect.setChecked(false);
//		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		// TODO Auto-generated method stub
		View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_anni_recycler_item, viewGroup,false);
		return new ViewHolder(view, itemClickListener);
	}
	
	
	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		//纪念日背景list_item涉及到的控件
		CustomImageView llBackPicture;
		//RadioButton imageSelect;
		
		ItemClickListener listener;
		
		public ViewHolder(View itemView,ItemClickListener itemClickListener) {
			super(itemView);
			// TODO Auto-generated constructor stub
			//纪念日背景list_item涉及到的控件
			llBackPicture=(CustomImageView)itemView.findViewById(R.id.add_anni_recycler_item_picture);
			llBackPicture.setType(CustomImageView.TYPE_ROUND);
			llBackPicture.setRoundRadius(10);
			
			//imageSelect=(RadioButton)itemView.findViewById(R.id.add_anni_recycler_item_select);
			
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
