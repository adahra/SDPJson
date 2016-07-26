package es.hol.adahra.sdpjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button SDPJson;
    private Button SDPRetrofit;
    private Button SDPMaps;
    private Button SDPListCustom;
    private Button SDPCameraUpload;

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
    }
}
