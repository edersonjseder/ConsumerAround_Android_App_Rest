package consumer.com.consumeraround.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by root on 16/04/17.
 */

public class Utils {
    private static final String TAG = "Utils";

    public static void writeToSDFile(String ip_key, Context context) throws IOException {

        Log.i(TAG, "writeToSDFile() inside method - ip_key: " + ip_key);

        //This will get the SD Card directory and create a folder named MyFiles in it.
        File sdCard = android.os.Environment.getExternalStorageDirectory();

        //Now create the file in the above directory and write the contents into it
        File file = new File(sdCard, "config.txt");
        FileOutputStream fOut = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        osw.write(ip_key);
        osw.flush();
        osw.close();

    }

    public static String getSavedIp() throws IOException {

        File file = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(), "config.txt");

        String line = "";
        StringBuilder text = new StringBuilder();

        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            while( (line = bReader.readLine()) != null  ){
                text.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return text.toString();

    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


}
