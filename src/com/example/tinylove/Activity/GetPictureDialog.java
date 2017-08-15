package com.example.tinylove.Activity;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyTimePicture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class GetPictureDialog extends Activity {
	
	public static int TAKE_PHOTO_REQUEST_CODE = 1; //拍照
	public static int PHOTO_REQUEST_CUT = 2; //裁切
	public static int PHOTO_REQUEST_GALLERY = 3; //相册
	public Uri imageUri;

	
	private TextView takePhoto;
	private TextView localPhoto;
	
	public static View imageView;
	
	public static boolean isHomePage;
	
	public static String fileName="";
	
	public static boolean isSelect;
	private boolean isTakePhoto;
	private boolean isChoosePhoto;
	private boolean isCut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		isSelect=false;
		isTakePhoto=false;
		isChoosePhoto=false;
		isCut=false;
		
		initView();
		
		initListener();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.dialog_change_head);

		takePhoto=(TextView)findViewById(R.id.take_photo);
		localPhoto=(TextView)findViewById(R.id.local_photo);
		
	}
	
	private void initListener() {
		// TODO Auto-generated method stub
		
		takePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				takePhotos();
			}
		});
		
		localPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				choicePicFromAlbum();
			}
		});
	}
	
	/**
	 * 打开相机拍照
	 */
	private void takePhotos() {
	 
		imageUri = Uri.fromFile(getImageStoragePath(this));
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//指定照片存储路径
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		isTakePhoto=true;
		startActivityForResult(intent,TAKE_PHOTO_REQUEST_CODE);
	}
	
	/**
	 * 打开相册选择图片
	 */
	private void choicePicFromAlbum() {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		isChoosePhoto=true;
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}
	
	/**
	 * 设置图片保存路径
	 * @return
	 */
	private File getImageStoragePath(Context context){
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"temp.jpg");
			return file;
		}
		return null;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == TAKE_PHOTO_REQUEST_CODE){
			if (isTakePhoto&&imageUri != null){
				//startPhotoZoom(imageUri);
				Bitmap bitmap = decodeUriBitmap(imageUri);
				//bitmap=Bitmap.createScaledBitmap(bitmap, 1800, 3200, true);
				imageView.setBackground(new BitmapDrawable(bitmap));
				saveBitmap(fileName, bitmap);
				isSelect=true;
				finish();
			}
		}
		else if (requestCode == PHOTO_REQUEST_CUT){
			if (isCut&&imageUri != null){
				Bitmap bitmap = decodeUriBitmap(imageUri);
				//bitmap=Bitmap.createScaledBitmap(bitmap, 1800, 3200, true);
				imageView.setBackground(new BitmapDrawable(bitmap));
				saveBitmap(fileName, bitmap);
				isSelect=true;
			}
			finish();
		}
		else if (requestCode == PHOTO_REQUEST_GALLERY){
			if (isChoosePhoto&&data != null){
				imageUri = data.getData();
				Bitmap bitmap = decodeUriBitmap(imageUri);
				imageView.setBackground(new BitmapDrawable(bitmap));
				saveBitmap(fileName, bitmap);
				isSelect=true;
			}
			finish();
		}
	}
	
	private Bitmap decodeUriBitmap(Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}


	/**
	 * 调用系统裁剪
	 *
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 9);
		intent.putExtra("aspectY", 16);
		
		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 1800);
		intent.putExtra("outputY", 3200);
	 
		//设置了true的话直接返回bitmap，可能会很占内存
		intent.putExtra("return-data", false);
		//设置输出的格式
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		//设置输出的地址
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		//不启用人脸识别
		intent.putExtra("noFaceDetection", true);
		isCut=true;
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}	
	
	public void saveBitmap(String picName,Bitmap bm) {
		Log.e("TAG", "保存图片");
		File dir=new File("/sdcard/tinylove/");
		if(!dir.exists()){
			dir.mkdir();
		}
		File f = new File("/sdcard/tinylove/", picName);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
			Log.i("TAG", "已经保存");
			if(GetPictureDialog.isHomePage){
				HomepageFragment.PATH=f.getAbsolutePath();
			}
			else{
				TimeFragment.PATH=f.getAbsolutePath();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.i("TAG", "保存失败1");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("TAG", "保存失败2");
			e.printStackTrace();
		}
		
	}
	
}
