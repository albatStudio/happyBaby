package com.superkorsuk.happybaby.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 1001078 on 2016. 10. 4..
 */

public class ProfileImageUtil {

    public static void storeImageFile(Context context, Bitmap bitmap, String filePath) {
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
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(copyFile)));

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
