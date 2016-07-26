package es.hol.adahra.sdpjson;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SDPActivity extends AppCompatActivity {
    private String[] daftarID;
    private String[] daftarNama;
    private String[] daftarAlamat;
    private String[] daftarUserName;
    private String[] daftarPassword;
    private String[] daftarLatitude;
    private String[] daftarLongitude;
    private String[] daftarFoto;
    private ListView listProspek;
    private JSONObject jsonObject;
    private String jsonResult = "";
    private ProgressDialog progressDialog;
    public static MainActivity mainActivity;
    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdp);

        btnTambah = (Button) findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SDPActivity.this, AddActivity.class);
                startActivity(mIntent);

                ambilData();
            }
        });

        //ambilData();

        RequestData();
    }

    public void RequestData() { //final String User,final String Pass){

        StringRequest PostData = new StringRequest(Request.Method.POST, "http://adahra.hol.es/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(SDPActivity.this, response, Toast.LENGTH_SHORT).show();
//                    JSONObject res = new JSONObject(response);
//                    boolean jsonResponse = res.getBoolean("success");

                    JSONObject jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject.getJSONArray("data");


                    daftarID = new String[menuitemArray.length()];
                    daftarNama = new String[menuitemArray.length()];
                    daftarAlamat = new String[menuitemArray.length()];
                    daftarUserName = new String[menuitemArray.length()];
                    daftarPassword = new String[menuitemArray.length()];
                    daftarLongitude = new String[menuitemArray.length()];
                    daftarLatitude = new String[menuitemArray.length()];
                    daftarFoto = new String[menuitemArray.length()];

                    for (int i = 0; i < menuitemArray.length(); i++) {
                        daftarID[i] = menuitemArray.getJSONObject(i).getString("id").toString();
                        daftarNama[i] = menuitemArray.getJSONObject(i).getString("nama").toString();
                        daftarAlamat[i] = menuitemArray.getJSONObject(i).getString("alamat").toString();
                        daftarUserName[i] = menuitemArray.getJSONObject(i).getString("username").toString();
                        daftarPassword[i] = menuitemArray.getJSONObject(i).getString("password").toString();
                        daftarLongitude[i] = menuitemArray.getJSONObject(i).getString("longitude").toString();
                        daftarLatitude[i] = menuitemArray.getJSONObject(i).getString("latitude").toString();
                        daftarFoto[i] = menuitemArray.getJSONObject(i).getString("foto").toString();
                    }

                    listProspek = (ListView)findViewById(R.id.listAnggota);
                    listProspek.setAdapter(new ArrayAdapter(SDPActivity.this, android.R.layout.simple_list_item_1, daftarNama));

                } catch (Exception e) {
                    Toast.makeText(SDPActivity.this, "error " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                progressDialog.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SDPActivity.this,"eror mas",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                //params.put("username", User);
                //params.put("password", Pass);

                return params;
            }
        };
        progressDialog = ProgressDialog.show(SDPActivity.this, "Please Wait", "Connecting", true);
        progressDialog.setCancelable(true);

        Volley.newRequestQueue(this).add(PostData);
    }


    public void ambilData() {
        Thread background = new Thread(new Runnable() {

            // program yang dijalankan ketika thread background berjalan
            public void run() {
                String SetServerString = "";

                try{
                    HttpRequest req=new HttpRequest("http://adahra.hol.es/");
                    SetServerString = req.preparePost().sendAndReadString();

                } catch (Exception e) {

                }

                Message msgObj = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("message", SetServerString);
                msgObj.setData(b);
                handler.sendMessage(msgObj);
            }

            // membuat Handler untuk menerima pesan ketika selesai
            private final Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    String aResponse = msg.getData().getString("message");
                    // String cek = "Login Sukses";
                    Toast.makeText(
                            getBaseContext(),
                            "Response Server : "+aResponse,
                            Toast.LENGTH_LONG).show();
                    try {
                        jsonObject = new JSONObject(aResponse);
                        JSONArray menuitemArray = jsonObject.getJSONArray("data");

                        daftarID = new String[menuitemArray.length()];
                        daftarNama = new String[menuitemArray.length()];
                        daftarAlamat = new String[menuitemArray.length()];
                        daftarUserName = new String[menuitemArray.length()];
                        daftarPassword = new String[menuitemArray.length()];
                        daftarLongitude = new String[menuitemArray.length()];
                        daftarLatitude = new String[menuitemArray.length()];
                        daftarFoto = new String[menuitemArray.length()];

                        for (int i = 0; i < menuitemArray.length(); i++) {
                            daftarID[i] = menuitemArray.getJSONObject(i).getString("id").toString();
                            daftarNama[i] = menuitemArray.getJSONObject(i).getString("nama").toString();
                            daftarAlamat[i] = menuitemArray.getJSONObject(i).getString("alamat").toString();
                            daftarUserName[i] = menuitemArray.getJSONObject(i).getString("username").toString();
                            daftarPassword[i] = menuitemArray.getJSONObject(i).getString("password").toString();
                            daftarLongitude[i] = menuitemArray.getJSONObject(i).getString("longitude").toString();
                            daftarLatitude[i] = menuitemArray.getJSONObject(i).getString("latitude").toString();
                            daftarFoto[i] = menuitemArray.getJSONObject(i).getString("foto").toString();
                        }

                        listProspek = (ListView)findViewById(R.id.listAnggota);
                        listProspek.setAdapter(new ArrayAdapter(SDPActivity.this, android.R.layout.simple_list_item_1, daftarNama));
                        listProspek.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //final String selection = daftar[position]; //.getItemAtPosition(arg2).toString();
                                final int posisi = position;
                                final CharSequence[] dialogitem = {"Edit", "Delete"};
                                AlertDialog.Builder builder = new AlertDialog.Builder(SDPActivity.this);
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
                                                hapusData(daftarID[posisi]);
                                                break;
                                        }
                                    }
                                });
                                builder.create().show();
                            }
                        });

                    } catch (JSONException e) {
                        Toast.makeText(getBaseContext(), "Gagal",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    progressDialog.dismiss();
                }
            };

        });
        // Start Thread
        progressDialog = ProgressDialog.show(SDPActivity.this, "Please Wait",
                "Connecting..", true);

        background.start(); // memanggil thread background agar start
    }

    public void hapusData(final String id) {
        Thread background = new Thread(new Runnable() {

            // program yang dijalankan ketika thread background berjalan
            public void run() {
                String SetServerString = "";

                try{
                    HttpRequest req=new HttpRequest("http://adahra.hol.es/delete.php?id="+id);
                    SetServerString = req.preparePost().sendAndReadString();

                } catch (Exception e) {

                }

                Message msgObj = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("message", SetServerString);
                msgObj.setData(b);
                handler.sendMessage(msgObj);
            }

            // membuat Handler untuk menerima pesan ketika selesai
            private final Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    String aResponse = msg.getData().getString("message");
                    // String cek = "Login Sukses";
                    Toast.makeText(
                            getBaseContext(),
                            "Data Terhapus " + aResponse,
                            Toast.LENGTH_LONG).show();

                    progressDialog.dismiss();
                    ambilData();
                    //finish();
                    // btnloginlogin.setEnabled(true);
                }
            };

        });
        // Start Thread
        progressDialog = ProgressDialog.show(SDPActivity.this, "Please Wait", "Connecting..", true);
        // btnloginlogin.setEnabled(false);

        background.start(); // memanggil thread background agar start
    }
}
