package es.hol.adahra.sdpjson;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetrofitActivity extends AppCompatActivity {
    private String[] daftarID;
    private String[] daftarNama;
    private String[] daftarAlamat;
    private String[] daftarUserName;
    private String[] daftarPassword;
    private String[] daftarLatitude;
    private String[] daftarLongitude;
    private String[] daftarFoto;
    private ListView lvAnggota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://adahra.hol.es")
                .build();
        final AnggotaInterface quoteService = restAdapter.create(AnggotaInterface.class);
        quoteService.getStream(new Callback<List<AnggotaPojo>>() {
            @Override
            public void success(List<AnggotaPojo> anggotaPojos, Response response) {
                int i = 0;

                daftarID = new String[anggotaPojos.size()];
                daftarNama = new String[anggotaPojos.size()];
                daftarAlamat = new String[anggotaPojos.size()];
                daftarUserName = new String[anggotaPojos.size()];
                daftarPassword = new String[anggotaPojos.size()];
                daftarLongitude = new String[anggotaPojos.size()];
                daftarLatitude = new String[anggotaPojos.size()];
                daftarFoto = new String[anggotaPojos.size()];

                for (AnggotaPojo anggotaPojo : anggotaPojos) {
                    daftarNama[i] = anggotaPojo.getNama();
                    i++;
                }

                lvAnggota = (ListView) findViewById(R.id.lvAnggota);
                lvAnggota.setAdapter(new ArrayAdapter(RetrofitActivity.this, android.R.layout.simple_list_item_1, daftarNama));
                lvAnggota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //final String selection = daftar[position]; //.getItemAtPosition(arg2).toString();
                        final int posisi = position;
                        final CharSequence[] dialogitem = {"Edit", "Delete"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(RetrofitActivity.this);
                        builder.setTitle("Pilih Pilihan");
                        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch(item){
                                    case 0 :
                                                /*Intent i = new Intent(getApplicationContext(), EditActivity.class); //KulineryogyaActivity.class);
                                                i.putExtra("id", daftarid[posisi]);
                                                i.putExtra("nama", daftarnama[posisi]);
                                                i.putExtra("alamat", daftaralamat[posisi]);
                                                i.putExtra("telp", daftartelp[posisi]);
                                                startActivity(i);
                                                */
                                        break;
                                    case 1 :
                                        // hapusData(daftarID[posisi]);
                                        break;
                                }
                            }
                        });
                        builder.create().show();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
