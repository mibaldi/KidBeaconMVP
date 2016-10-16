package com.mibaldi.kidbeaconmvp.util;

import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mikelbalducieldiaz on 25/9/16.
 */

public class Utils {
    public static Uri getUriFromFile(File file){return Uri.fromFile(file);}
    public static File createTempFile(File cacheDir){
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = null;

        try {
            file = File.createTempFile(date, ".jpg",cacheDir);
            file.setWritable(true, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }
}
