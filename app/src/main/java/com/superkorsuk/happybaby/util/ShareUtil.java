package com.superkorsuk.happybaby.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjhok on 2016. 9. 26..
 */

public class ShareUtil {
    // TODO : more test with device..
    public static void sendShare(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        Log.d("SHARE", "can use for sharing app list : " + resInfo.toString());
        if (resInfo.isEmpty()) {
            return;
        }

        List<Intent> shareIntentList = new ArrayList<Intent>();

        for (ResolveInfo info : resInfo) {
            Intent shareIntent = (Intent) intent.clone();

            if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
                //facebook
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
//                shareIntent.setType("image/jpg");
//                shareIntent.putExtra(Intent.EXTRA_STREAM,  Uri.parse("file:///"+mImagePath));
            } else {
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "제목");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "구글 http://www.google.com #");
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/happyBaby/" + "sample.jpg"));
//                shareIntent.putExtra(Intent.EXTRA_STREAM,  Uri.parse("file:///"+mImagePath));
            }
            shareIntent.setPackage(info.activityInfo.packageName);
            shareIntentList.add(shareIntent);
        }

        Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
        context.startActivity(chooserIntent);
    }

    public static void sendMail(Context context, String sendMailAdress){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setType("plaine/text");
        intent.putExtra(Intent.EXTRA_EMAIL, sendMailAdress);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(intent, "Email 앱을 선택해 주세요."));
    }
}
