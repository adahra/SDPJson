package es.hol.adahra.sdpjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetrofitActivity extends AppCompatActivity {
    private TextView tvRetroNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        tvRetroNama = (TextView) findViewById(R.id.tvRetroNama);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://adahra.hol.es")
                .build();
        final AnggotaInterface quoteService = restAdapter.create(AnggotaInterface.class);
        quoteService.getStream(new Callback<List<AnggotaPojo>>() {
            @Override
            public void success(List<AnggotaPojo> anggotaPojos, Response response) {
                for (AnggotaPojo anggotaPojo : anggotaPojos) {
                    // Toast.makeText(getApplicationContext(), "Nama : " + anggotaPojo.getNama(), Toast.LENGTH_LONG).show();
                    tvRetroNama.setText("Nama: " + anggotaPojo.getNama());
                    Log.d("SDP", "Nama : " + anggotaPojo.getNama());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("SDP", error.getMessage());
                }
            }
        });
    }
}
