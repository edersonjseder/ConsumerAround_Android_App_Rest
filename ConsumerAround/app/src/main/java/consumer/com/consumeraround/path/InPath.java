package consumer.com.consumeraround.path;

import android.util.Log;

public class InPath {
    private static final String TAG = "InPath" +
            "";
    public static final String URL_PATH_STANDARD = "http://192.168.1.102:8080/location/rest/";
    public static String URL_PATH = " ";

    public static String HOST = "http://";
    public static String PATH = "/location/rest/";
    public static String PORT = "8080";

    public static final String CITIES = "cities";
    public static final String COUNTRY = "country";

    public static final String JSON = ".json";

    public static void setIp(String ip){

        URL_PATH = ip;

        Log.i(TAG, "New URL IP: " + URL_PATH);

    }

}
