package com.kerbabian.taxcalculator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {


    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        final Timer timer = new Timer(true);
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
       /* final Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.notification_message))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();*/


        if (networkInfo != null && networkInfo.isConnected()){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                        try{
                            URL url = new URL("https://quotes.rest/qod?language=en");
                            InputStream in = url.openStream();

                            Scanner s = new Scanner(in).useDelimiter("\\A");
                            String result = s.hasNext()
                                    ? s.next()
                                    : "";

                            final Notification notification = new Notification.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                                    .setContentTitle(getString(R.string.app_name))
                                    .setContentText(new JSONObject(result)
                                            .getJSONObject("contents")
                                            .getJSONArray("quotes")
                                            .getJSONObject(0)
                                            .getString("quote")
                                            )
                                    .setAutoCancel(true)
                                    .setContentIntent(pendingIntent)
                                    .build();


                            manager.notify(1, notification);
                        }catch(IOException ex)
                        {
                            Log.d("Notificationservices", ex.toString());
                        }
                        catch (JSONException ex)
                        {
                            Log.d("Notificationservices", ex.toString());
                        }
                        finally {
                            timer.cancel();
                            stopSelf();
                        }





                }
            },500);
        }else{
            stopSelf();
        }





        super.onCreate();


    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
