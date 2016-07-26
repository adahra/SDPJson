package es.hol.adahra.sdpjson;

import android.app.LauncherActivity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button SDPJson;
    private Button SDPRetrofit;
    private Button SDPMaps;
    private Button SDPListCustom;
    private Button SDPCameraUpload;
    private Button btnNotifikasi;
    private Button btnLokasi;
//    private TextView mTextProcess;

    private static final int ID_NOTIFICATION = 1;
    public static final String ID_BROADCAST_OTHER_PROCESS = "broadcastotherprocess";
    public static final String ID_RESULT_FROM_OTHER_PROCESS = "resultprocess";

    private static BroadcastReceiver getProcessStatus = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                final String message = extras.getString(ID_RESULT_FROM_OTHER_PROCESS);
//                mTextProcess.setText(message);
//                ((MainActivity)mTextProcess.getContext()).generateNotification(message);
            }
        }
    };

    MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
        @Override
        public void gotLocation(Location location){
            //Got the location!
            Toast.makeText(getApplicationContext(), "Lokasi : "+String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivGambarMain = (ImageView) findViewById(R.id.ivGambarMain);

        try {
            // Glide.with(this).load("http://adahra.hol.es/images/image01.jpg").into(ivGambarMain);
            // Glide.with(this).load("http://goo.gl/gEgYUd").into(ivGambarMain);
            Glide.with(this).load("http://adahra.hol.es/images/image02.jpg").into(ivGambarMain);
        } catch (Exception e) {

        }

        initKomponen();
        registerReceiver(getProcessStatus, new IntentFilter(ID_BROADCAST_OTHER_PROCESS));
    }

    private void initKomponen() {
        SDPJson = (Button) findViewById(R.id.btnSDP);
        SDPJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sdpJsonIntent = new Intent(MainActivity.this, SDPActivity.class);
                startActivity(sdpJsonIntent);
            }
        });

        SDPRetrofit = (Button) findViewById(R.id.btnSDPRetrofit);
        SDPRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sdpRetrofitIntent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(sdpRetrofitIntent);
            }
        });

        SDPMaps = (Button) findViewById(R.id.btnSDPMaps);
        SDPMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sdpMapsIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(sdpMapsIntent);
            }
        });

        SDPListCustom = (Button) findViewById(R.id.btnSDPListCustom);
        SDPListCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sdpListCustom = new Intent(MainActivity.this, CustomListActivity.class);
                startActivity(sdpListCustom);
            }
        });

        SDPCameraUpload = (Button) findViewById(R.id.btnSDPCameraUpload);
        SDPCameraUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sdpCameraUpload = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(sdpCameraUpload);
            }
        });

        btnNotifikasi = (Button) findViewById(R.id.btnNotifikasi);
        btnNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceSample.class);
                getApplicationContext().startService(intent);

                notifikasi();
            }
        });

        btnLokasi = (Button) findViewById(R.id.btnLokasi);
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLocation myLocation = new MyLocation();
                myLocation.getLocation(MainActivity.this, locationResult);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(getProcessStatus);
    }

    private void notifikasi() {
        Context context = getApplicationContext();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICATION);//Untuk menutup notifikasi yang ada (dengan id yang disebutkan), ditutup dulu.

        long when = System.currentTimeMillis();

        String contentTitle = "Notification";
        // boolean is_notif_sound = true;

        Bitmap icon_big = BitmapFactory.decodeResource(context.getResources(), R.drawable.cast_ic_notification_0);

//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
//                {

                    /*Notification notification = new Notification(R.drawable.ic_launcher, message, when); // deprecated in API level 11
                    Intent toLaunch = new Intent(context,LauncherActivity.class);
                    toLaunch.putExtra("NOTIFICATION",true);

                    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, toLaunch, PendingIntent.FLAG_UPDATE_CURRENT);
                    notification.setLatestEventInfo(context, contentTitle, message, contentIntent); // deprecated in API level 11
                    //Setting Notification Flags
                    notification.flags |= Notification.FLAG_AUTO_CANCEL;
                    if (is_notif_sound) {
                        notification.sound = Uri.parse("android.resource://com.yandi.mp3player/" + R.raw.pop);
                    }
                    //vibrate permission harus diregister dimanifest
                    //notification.vibrate = new long[]{200, 200, 200, 200, 200};
                    notificationManager.notify(1, notification);*/
//                } else{

        Intent notificationIntent = new Intent(context, LauncherActivity.class );
        notificationIntent.putExtra("NOTIFICATION",true);

        PendingIntent intent = PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine("Ada Notifikasi");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(contentTitle)
                .setSmallIcon(R.drawable.cast_ic_notification_0)
                .setLargeIcon(icon_big)
                .setSubText("Sub Text here")
                .setContentInfo("")
                .setWhen(when)
                .setAutoCancel(true)
                .setContentIntent(intent)
                .setStyle(inboxStyle)
                //vibrate permission harus diregister dimanifest
                //.setVibrate(new long[]{200,200,200,200,200})
                .setTicker("Ada Notifikasi");

//                    if(is_notif_sound)
//                        mBuilder.setSound(Uri.parse("android.resource://com.yandi.mp3player/" + R.raw.pop));

        notificationManager.notify(1, mBuilder.build());
//            }
    }
}
