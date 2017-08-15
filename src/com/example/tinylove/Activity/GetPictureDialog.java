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
	
	public static int TAKE_PHOTO_REQUEST_CODE = 1; //����
	public static int PHOTO_REQUEST_CUT = 2; //����
	public static int PHOTO_REQUEST_GALLERY = 3; //���
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
	 * ���������
	 */
	private void takePhotos() {
	 
		imageUri = Uri.fromFile(getImageStoragePath(this));
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//ָ����Ƭ�洢·��
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		isTakePhoto=true;
		startActivityForResult(intent,TAKE_PHOTO_REQUEST_CODE);
	}
	
	/**
	 * �����ѡ��ͼƬ
	 */
	private void choicePicFromAlbum() {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		isChoosePhoto=true;
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}
	
	/**
	 * ����ͼƬ����·��
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
	 * ����ϵͳ�ü�
	 *
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		
		intent.setDataAndType(uri, "image/*");
		// cropΪtrue�������ڿ�����intent��������ʾ��view���Լ���
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 9);
		intent.putExtra("aspectY", 16);
		
		// outputX,outputY �Ǽ���ͼƬ�Ŀ��
		intent.putExtra("outputX", 1800);
		intent.putExtra("outputY", 3200);
	 
		//������true�Ļ�ֱ�ӷ���bitmap�����ܻ��ռ�ڴ�
		intent.putExtra("return-data", false);
		//��������ĸ�ʽ
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		//��������ĵ�ַ
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		//����������ʶ��
		intent.putExtra("noFaceDetection", true);
		isCut=true;
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}	
	
	public void saveBitmap(String picName,Bitmap bm) {
		Log.e("TAG", "����ͼƬ");
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
			Log.i("TAG", "�Ѿ�����");
			if(GetPictureDialog.isHomePage){
				HomepageFragment.PATH=f.getAbsolutePath();
			}
			else{
				TimeFragment.PATH=f.getAbsolutePath();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.i("TAG", "����ʧ��1");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("TAG", "����ʧ��2");
			e.printStackTrace();
		}
		
	}
	
}
