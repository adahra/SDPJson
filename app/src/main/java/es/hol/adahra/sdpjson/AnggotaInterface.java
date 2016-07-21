package es.hol.adahra.sdpjson;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by blackshadow on 19/07/16.
 */
public interface AnggotaInterface {
    @GET("/getdata.php")
    void getStream(Callback<List<AnggotaPojo>> callback);

    @POST("/tambah.php")
    void tambahAnggota(@Body AnggotaPojo body, Callback<AnggotaPojo> callBack);
}
