package es.hol.adahra.sdpjson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CustomListActivity extends AppCompatActivity {
    private ListView lvCustom;
    private Context cContext;
    public static int[] psiDaftarGambar = {R.drawable.ic_cast_on_light, R.drawable.ic_cast_on_light, R.drawable.ic_cast_on_light};
    public static String[] psiDaftarURL = {"http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd"};
    private String[] daftarURL;
    public static String[] pssDaftarNama = {"Adnanto","Ahmad","Ramadhon"};
    private String[] daftarNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://adahra.hol.es")
                .build();

        final AnggotaInterface anggotaInterface = restAdapter.create(AnggotaInterface.class);
        anggotaInterface.getStream(new Callback<List<AnggotaPojo>>() {
            @Override
            public void success(List<AnggotaPojo> anggotaPojos, Response response) {
                int i = 0;

                daftarNama = new String[anggotaPojos.size()];
                daftarURL = new String[anggotaPojos.size()];

                for (AnggotaPojo anggotaPojo : anggotaPojos) {
                    daftarNama[i] = anggotaPojo.getNama();
                    daftarURL[i] = "http://adahra.hol.es/images/image02.jpg";
                    i++;
                }

                lvCustom = (ListView) findViewById(R.id.lvCustom);
                lvCustom.setAdapter(new CustomAdapter(CustomListActivity.this, daftarNama, daftarURL));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
