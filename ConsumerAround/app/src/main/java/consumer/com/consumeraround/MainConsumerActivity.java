package consumer.com.consumeraround;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import consumer.com.consumeraround.activities.CityListActivity;
import consumer.com.consumeraround.activities.CountryListActivity;
import consumer.com.consumeraround.databinding.ActivityMainConsumerBinding;
import consumer.com.consumeraround.path.InPath;
import consumer.com.consumeraround.util.AlertDialogManager;
import consumer.com.consumeraround.util.ConnectionDetector;
import consumer.com.consumeraround.util.Utils;

public class MainConsumerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainConsumerActivity";

    AlertDialogManager dialogManager;
    private ConnectionDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainConsumerBinding awareBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main_consumer);


        dialogManager = new AlertDialogManager();
        detector = new ConnectionDetector(getApplicationContext());

        // Check for internet connection
        if (!detector.isConnectingToInternet()) {
            // Internet Connection is not present
            dialogManager.showAlertDialog(this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        if (detector.isConnectingToInternet() && savedInstanceState == null) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setMessage("Please add your IP and Port");
            alert.setTitle("Enter IP and Port");

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.edittext_ip, null);

            final EditText editIp = (EditText) alertLayout.findViewById(R.id.text_ip);

            alert.setView(alertLayout);

            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // gets the IP from user
                    String mEditTextValue = editIp.getText().toString();
                    Log.i(TAG, "setPositiveButton() inside method " + mEditTextValue);

                    try {

                        Log.i(TAG, "SDFile Storage read-only: " + Utils.isExternalStorageReadOnly());
                        Log.i(TAG, "SDFile Storage available: " + Utils.isExternalStorageAvailable());

                        Utils.writeToSDFile(mEditTextValue, getApplicationContext());

                        String ipPort = Utils.getSavedIp();

                        Log.i(TAG, "String from file: " + ipPort);

                        ipPort = ipPort.trim();


                        String url = InPath.HOST + ipPort + InPath.PATH;

                        Log.i(TAG, "setPositiveButton() inside method - Url: " + url);

                        InPath.setIp(url);

                        Toast.makeText(getApplicationContext(), "IP saved...", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {

                        e.printStackTrace();

                        // Any error set the default URL Path which is 192.168.1.102
                        InPath.setIp(InPath.URL_PATH_STANDARD);

                        Toast.makeText(getApplicationContext(), "File not found...", Toast.LENGTH_SHORT).show();

                        Log.e(TAG, "setPositiveButton() inside catch - value: " + InPath.URL_PATH_STANDARD);

                    }
                }
            });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                    dialog.cancel();
                }
            });
            alert.show();
        }

        awareBinding.countryAccess.setOnClickListener(this);
        awareBinding.cityAccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.country_access:
                intent = new Intent(getApplicationContext(), CountryListActivity.class);
                break;
            case R.id.city_access:
                intent = new Intent(getApplicationContext(), CityListActivity.class);
                break;
        }

        startActivity(intent);
    }
}
