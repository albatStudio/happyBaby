package com.superkorsuk.happybaby.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.superkorsuk.happybaby.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetPhotoActivity extends AppCompatActivity {

    private static final int GET_PHOTO_FROM_GALLERY = 0;
    private static final int GET_PHOTO_FROM_CAMERA = 1;
    private static final int CROP_IMAGE = 2;

    private ImageView imageViewPhoto;
    private Uri imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_photo);

        imageViewPhoto = (ImageView) findViewById(R.id.imageView_Photo);

        File storedImgeFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/happyBaby/sample.jpg");
        Log.d("Image", storedImgeFile.toString());
        if (storedImgeFile.canRead()) {
            Log.d("Image", "있음");
            Bitmap storedBitmap = BitmapFactory.decodeFile(String.valueOf(storedImgeFile));

            imageViewPhoto.setImageBitmap(storedBitmap);
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_photo :
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, GET_PHOTO_FROM_GALLERY);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case GET_PHOTO_FROM_GALLERY :
                imagePath = data.getData();

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(imagePath, "image/*");
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_IMAGE);
                break;
            case CROP_IMAGE:
                Bundle extras = data.getExtras();
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/happyBaby/" + "sample.jpg";
//                        + System.currentTimeMillis() + ".jpg";

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    imageViewPhoto.setImageBitmap(photo);

                    storeImageFile(photo, filePath);
                }

                break;
            default:
                break;
        }
    }

    protected void storeImageFile(Bitmap bitmap, String filePath) {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/happyBaby";
        File directoryHappyBaby = new File(dirPath);

        if (!directoryHappyBaby.exists()) {
            boolean result = directoryHappyBaby.mkdir();

            Log.d("Image", dirPath );
            Log.d("Image", "디렉토리 생성? " + result );
        }

        File copyFile = new File(filePath);
        BufferedOutputStream bufferedOutputStream = null;
        Log.d("Image", copyFile.toString());
        try {
            copyFile.createNewFile();
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);

            // send broadcast to see new file
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(copyFile)));

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Log.d("Image", "저장 성공");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Image", "저장 에러");
            Log.d("Image", e.getLocalizedMessage());
        }
    }

}