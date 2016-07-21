package es.hol.adahra.sdpjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button SDPJson;
    private Button SDPRetrofit;
    private Button SDPMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
