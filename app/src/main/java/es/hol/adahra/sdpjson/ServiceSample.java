package es.hol.adahra.sdpjson;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by sdp04 on 7/26/16.
 */

public class ServiceSample extends Service {
    public static final String ID_BROADCAST_OTHER_PROCESS = "broadcastotherprocess";
    public static final String ID_RESULT_FROM_OTHER_PROCESS = "resultprocess";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        processing(0);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private  void processing(final int index){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if(index <= 5){
                    int increment = index + 1;
                    String message = "PROCESS : "+String.valueOf((20*index))+"%";
                    Intent intent = new Intent(ID_BROADCAST_OTHER_PROCESS);
                    intent.putExtra(ID_RESULT_FROM_OTHER_PROCESS, message);
                    getApplicationContext().sendBroadcast(intent);
                    processing(increment);
                } else {
                    stopSelf();
                }
            }
        }, 1500);
    }
}
