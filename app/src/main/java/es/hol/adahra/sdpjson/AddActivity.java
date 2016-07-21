package es.hol.adahra.sdpjson;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddActivity extends AppCompatActivity {
    private EditText etNama;
    private EditText etAlamat;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnSave;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etNama = (EditText) findViewById(R.id.etNama);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData(etNama.getText().toString(), etAlamat.getText().toString(), etUserName.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    public void simpanData(final String nama, final String username, final String password, final String alamat) {
        Thread background = new Thread(new Runnable() {

            // program yang dijalankan ketika thread background berjalan
            public void run() {
                String SetServerString = "";

                try{
                    HttpRequest req=new HttpRequest("http://adahra.hol.es/tambah.php");//nama="+nama+",alamat="+alamat+",username="+userName+",password="+password);
                    HashMap<String, String> params=new HashMap<String, String>();
                    params.put("nama", nama);
                    params.put("username", username);
                    params.put("password", password);
                    params.put("alamat", alamat);
                    //edcari.getText().toString());
                    //req.preparePost().withData(params).sendAndReadJSON();
                    //req.preparePost().withData(params).sendAndReadString();
                    //SetServerString = req.preparePost().withData("name=123&age=29").sendAndReadString(); //req.preparePost().withData(params).sendAndReadString();

                    SetServerString = req.preparePost().withData(params).sendAndReadString();

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
                    Toast.makeText(getBaseContext(), "Berhasil" + aResponse, Toast.LENGTH_LONG).show();

                    progressDialog.dismiss();
                    // getData();
                    //finish();
                    // btnloginlogin.setEnabled(true);
                }
            };

        });
        // Start Thread
        progressDialog = ProgressDialog.show(AddActivity.this, "Please Wait", "Connecting..", true);
        // btnloginlogin.setEnabled(false);

        background.start(); // memanggil thread background agar start
    }
}
